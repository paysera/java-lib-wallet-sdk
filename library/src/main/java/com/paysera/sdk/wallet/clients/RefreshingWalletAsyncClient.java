package com.paysera.sdk.wallet.clients;

import bolts.Continuation;
import bolts.Task;
import bolts.TaskCompletionSource;
import com.paysera.sdk.wallet.AccessTokenRefresher;
import com.paysera.sdk.wallet.ClientServerTimeSynchronizationConfiguration;
import com.paysera.sdk.wallet.OkHttpRequestWalletApiCall;
import com.paysera.sdk.wallet.RetrofitWalletApiCall;
import com.paysera.sdk.wallet.WalletApiCall;
import com.paysera.sdk.wallet.entities.Credentials;
import com.paysera.sdk.wallet.enums.GrantType;
import com.paysera.sdk.wallet.exceptions.WalletApiException;
import com.paysera.sdk.wallet.helpers.OkHTTPQueryStringConverter;
import com.paysera.sdk.wallet.providers.TimestampProvider;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Retrofit;

//TODO pasitestuot call'us ar eina per HTTP 2 (kai įjungs)
public class RefreshingWalletAsyncClient extends WalletAsyncClient {
    private final AccessTokenRefresher accessTokenRefresher;
    private Queue<WalletApiCall> callQueue = new LinkedList<>();
    private GrantType grantType;

    public RefreshingWalletAsyncClient(
        TimestampProvider timestampProvider,
        ClientServerTimeSynchronizationConfiguration clientServerTimeSynchronizationConfiguration,
        PublicWalletApiClient publicWalletApiClient,
        WalletApiClient walletApiClient,
        AccessTokenRefresher accessTokenRefresher,
        Retrofit retrofit,
        OkHTTPQueryStringConverter okHTTPQueryStringConverter,
        GrantType grantType
    ) {
        super(
            timestampProvider,
            clientServerTimeSynchronizationConfiguration,
            publicWalletApiClient,
            walletApiClient,
            retrofit,
            okHTTPQueryStringConverter
        );
        this.accessTokenRefresher = accessTokenRefresher;
        this.grantType = grantType;
    }

    @Override
    protected <T> Task<T> execute(Call<T> call) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();

        RetrofitWalletApiCall retrofitWalletApiCall
            = new RetrofitWalletApiCall(taskCompletionSource, call);
        this.performWalletApiCall(retrofitWalletApiCall);

        return retrofitWalletApiCall.getTaskCompletionSource().getTask();
    }

    @Override
    protected <T> Task<T> execute(Request request) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        OkHttpRequestWalletApiCall walletApiCall
            = new OkHttpRequestWalletApiCall(taskCompletionSource, request);

        this.performWalletApiCall(walletApiCall);

        return walletApiCall.getTaskCompletionSource().getTask();
    }

    private void performWalletApiCall(final WalletApiCall walletApiCall) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        if (this.accessTokenRefresher != null) {
            synchronized (this.accessTokenRefresher) {
                if (this.accessTokenRefresher.isAccessTokenRefreshing()) {
                    this.callQueue.add(walletApiCall);
                    return;
                } else {
                    if (this.accessTokenRefresher.willAccessTokenExpireSoon()) {
                        this.callQueue.add(walletApiCall);
                        refreshAccessToken();
                        return;
                    }
                }
            }
        }


        if (walletApiCall instanceof RetrofitWalletApiCall) {
            RetrofitWalletApiCall retrofitWalletApiCall = ((RetrofitWalletApiCall) walletApiCall);
            this.performCallWithTaskCompletionSource(
                ((Call)retrofitWalletApiCall.getCall()).clone(),
                taskCompletionSource
            );
        } else {
            this.performCallWithTaskCompletionSource(
                ((OkHttpRequestWalletApiCall)walletApiCall).getRequest(),
                taskCompletionSource
            );
        }

        taskCompletionSource
            .getTask()
            .continueWith(new Continuation() {
                @Override public Object then(Task task) throws Exception {
                    if (!task.isFaulted()) {
                        walletApiCall.getTaskCompletionSource().setResult(task.getResult());
                        return null;
                    }

                    WalletApiException walletApiException = (WalletApiException) task.getError();

                    if (walletApiException.isTokenExpired() && accessTokenRefresher != null) {
                        synchronized (accessTokenRefresher) {
                            if (accessTokenRefresher.hasAccessTokenBeenRecentlyRefreshed()) {
                                performWalletApiCall(walletApiCall);
                            } else {
                                callQueue.add(walletApiCall);
                                refreshAccessToken();
                            }
                        }
                    } else {
                        walletApiCall.getTaskCompletionSource().setError(task.getError());
                    }

                    return null;
                }
            });
    }

    public Task<Credentials> refreshAccessToken() {
        return this.refreshAccessToken(grantType, null, null);
    }
    
    public Task<Credentials> refreshAccessToken(GrantType grantType, List<String> scopes, String code) {
        return this.accessTokenRefresher.refreshAccessToken(grantType, scopes, code)
            .continueWithTask(new Continuation<Credentials, Task<Credentials>>() {
                @Override public Task<Credentials> then(Task<Credentials> task) throws Exception {
                    if (task.isFaulted()) {
                        cancelCallQueue((WalletApiException) task.getError());
                    } else {
                        resumeCallQueue();
                    }
                    return task;
                }
            });
    }

    private void resumeCallQueue() {
        synchronized (this.accessTokenRefresher) {
            while (!this.callQueue.isEmpty()) {
                this.performWalletApiCall(this.callQueue.poll());
            }
        }
    }

    private void cancelCallQueue(WalletApiException walletApiException) {
        synchronized (this.accessTokenRefresher) {
            while (!this.callQueue.isEmpty()) {
                this.callQueue.poll().getTaskCompletionSource().setError(walletApiException);
            }
        }
    }

    @Override
    public synchronized void cancelCalls() {
        this.cancelCallQueue(new WalletApiException("Call has been canceled"));
        this.accessTokenRefresher.resetAttemptsCount();
        super.cancelCalls();
    }
}

package com.paysera.lib.wallet.clients;

import bolts.Task;
import bolts.TaskCompletionSource;
import com.paysera.lib.wallet.ClientServerTimeSynchronizationConfiguration;
import com.paysera.lib.wallet.WalletApiResponse;
import com.paysera.lib.wallet.entities.RecaptchaHeaders;
import com.paysera.lib.wallet.entities.ServerInformation;
import com.paysera.lib.wallet.entities.WalletApiErrorProperty;
import com.paysera.lib.wallet.enums.RecaptchaHeader;
import com.paysera.lib.wallet.exceptions.WalletApiException;
import com.paysera.lib.wallet.helpers.OkHTTPQueryStringConverter;
import com.paysera.lib.wallet.interfaces.TimestampSynchronizedCallback;
import com.paysera.lib.wallet.providers.TimestampProvider;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.*;

public abstract class BaseAsyncClient {
    private Queue<Call> retrofitCalls = new LinkedList<>();
    private Queue<okhttp3.Call> okhttpCalls = new LinkedList<>();
    private Retrofit retrofit;
    private PublicWalletApiClient publicWalletApiClient;
    private TimestampProvider timestampProvider;
    private ClientServerTimeSynchronizationConfiguration clientServerTimeSynchronizationConfiguration;
    private OkHTTPQueryStringConverter okHTTPQueryStringConverter;

    public BaseAsyncClient(
        TimestampProvider timestampProvider,
        ClientServerTimeSynchronizationConfiguration clientServerTimeSynchronizationConfiguration,
        PublicWalletApiClient publicWalletApiClient,
        Retrofit retrofit,
        OkHTTPQueryStringConverter okHTTPQueryStringConverter
    ) {
        this.timestampProvider = timestampProvider;
        this.clientServerTimeSynchronizationConfiguration = clientServerTimeSynchronizationConfiguration;
        this.publicWalletApiClient = publicWalletApiClient;
        this.retrofit = retrofit;
        this.okHTTPQueryStringConverter = okHTTPQueryStringConverter;
    }

    public Task<WalletApiResponse> get(String path, Map<String, String> parameters) {
        if (!parameters.isEmpty()) {
            path += "?" + okHTTPQueryStringConverter.convertToEncodedQueryString(parameters);
        }
        Request request = new Request.Builder()
            .get()
            .url(retrofit.baseUrl() + path)
            .build();

        return execute(request);
    }

    public Task<WalletApiResponse> post(String path, RequestBody requestBody) {
        Request request = new Request.Builder()
            .post(requestBody)
            .url(retrofit.baseUrl() + path)
            .build();

        return execute(request);
    }

    public Task<WalletApiResponse> put(String path, RequestBody requestBody) {
        Request request = new Request.Builder()
            .put(requestBody)
            .url(retrofit.baseUrl() + path)
            .build();

        return execute(request);
    }

    public Task<WalletApiResponse> delete(String path, RequestBody requestBody) {
        Request request = new Request.Builder()
            .delete(requestBody)
            .url(retrofit.baseUrl() + path)
            .build();

        return execute(request);
    }

    public Task<WalletApiResponse> delete(String path) {
        return delete(path, null);
    }

    protected synchronized <T> void performCallWithTaskCompletionSource(
        final Call<T> call,
        final TaskCompletionSource<T> mainTaskCompletionSource
    ) {
        retrofitCalls.add(call);
        call.enqueue(new Callback<T>() {
            public void onResponse(final Call<T> mainCall, Response<T> response) {
                synchronized (BaseAsyncClient.this) {
                    retrofitCalls.remove(call);
                }
                if (response.isSuccessful()) {
                    mainTaskCompletionSource.setResult(response.body());
                } else {
                    final WalletApiException exception;
                    String responseBody = null;
                    try {
                        responseBody = response.errorBody().string();

                        JSONObject data = new JSONObject(responseBody);
                        exception = new WalletApiException(
                            data.optString("error_description"),
                            data.getString("error"),
                            response.code()
                        );
                        if (data.has("error_data")){
                            exception.setErrorData(data.getJSONObject("error_data"));
                        }
                        if (data.has("error_properties")) {
                            exception.setErrorProperties(getErrorProperties(data.getJSONObject("error_properties")));
                        }
                        if (
                            exception.isInvalidTimestampError()
                                && clientServerTimeSynchronizationConfiguration.isEnabled()
                        ) {
                            syncTimestamp().continueWith(task -> {
                                if (!task.isFaulted()) {
                                    performCallWithTaskCompletionSource(
                                        mainCall.clone(),
                                        mainTaskCompletionSource
                                    );
                                } else {
                                    mainTaskCompletionSource.setError(exception);
                                }
                                return null;
                            });
                        } else if (exception.isRateLimitExceededError()) {
                            exception.setRecaptchaHeaders(
                                new RecaptchaHeaders(
                                    response.headers().get(RecaptchaHeader.SITE_KEY.toString()),
                                    response.headers().get(RecaptchaHeader.UNLOCK_URL.toString())
                                )
                            );
                            mainTaskCompletionSource.setError(exception);
                        } else {
                            mainTaskCompletionSource.setError(exception);
                        }

                    } catch (JSONException | IOException previousException) {
                        mainTaskCompletionSource.setError(
                            new WalletApiException(
                                "An error occurred: " + responseBody,
                                response.code()
                            )
                        );
                    }
                }
            }

            public void onFailure(Call<T> call, Throwable throwable) {
                mainTaskCompletionSource.setError(
                    new WalletApiException(
                        "An exception occurred",
                        throwable
                    )
                );
            }
        });
    }

    private List<WalletApiErrorProperty> getErrorProperties(JSONObject jsonObject) {
        List<WalletApiErrorProperty> errorProperties = new ArrayList<>();
        Iterator<String> iterator = jsonObject.keys();
        while (iterator.hasNext()) {
            String code = iterator.next();
            String description = jsonObject.getJSONArray(code).get(0).toString();
            WalletApiErrorProperty walletApiErrorProperty = new WalletApiErrorProperty(code, description);
            errorProperties.add(walletApiErrorProperty);
        }
        return errorProperties;
    }

    protected <T> Task<T> execute(Call<T> call) {
        final TaskCompletionSource<T> taskCompletionSource = new TaskCompletionSource<>();

        this.performCallWithTaskCompletionSource(
            call,
            taskCompletionSource
        );

        return taskCompletionSource.getTask();
    }

    protected <T> Task<T> execute(Request request) {
        final TaskCompletionSource<T> taskCompletionSource = new TaskCompletionSource<>();

        this.performCallWithTaskCompletionSource(
            request,
            taskCompletionSource
        );

        return taskCompletionSource.getTask();
    }

    protected synchronized void performCallWithTaskCompletionSource(
        final Request request,
        final TaskCompletionSource mainTaskCompletionSource
    ) {
        final okhttp3.Call call = this.retrofit.callFactory().newCall(request);
        okhttpCalls.add(call);
        call.enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                mainTaskCompletionSource.setError(new WalletApiException("An exception occurred", e));
            }

            @Override
            public void onResponse(final okhttp3.Call mainCall, final okhttp3.Response response) throws IOException {
                synchronized (BaseAsyncClient.this) {
                    okhttpCalls.remove(call);
                }

                String responseBody = null;
                try {
                    responseBody = response.body().string();
                } catch (IOException exception) {
                    // intentionally
                }

                if (response.isSuccessful()) {
                    WalletApiResponse walletApiResponse = new WalletApiResponse(
                        response.code(),
                        responseBody,
                        response.headers()
                    );
                    mainTaskCompletionSource.setResult(walletApiResponse);
                } else {
                    try {
                        List<WalletApiErrorProperty> errorProperties = null;
                        WalletApiException walletApiException = null;

                        try {
                            if (responseBody != null) {
                                JSONObject data = new JSONObject(responseBody);
                                String errorDescription = data.optString("error_description");
                                String errorCode = data.getString("error");
                                if (data.has("error_properties")) {
                                    errorProperties = getErrorProperties(data.getJSONObject("error_properties"));
                                }

                                walletApiException = new WalletApiException(
                                    errorDescription,
                                    errorCode,
                                    response.code()
                                );
                                if (data.has("error_data")){
                                    walletApiException.setErrorData(data.getJSONObject("error_data"));
                                }
                            }
                        } catch (JSONException exception) {
                            walletApiException = new WalletApiException(
                                response.message(),
                                response.code()
                            );
                        }

                        if (errorProperties != null) {
                            walletApiException.setErrorProperties(errorProperties);
                        }
                        if (walletApiException.isInvalidTimestampError() && clientServerTimeSynchronizationConfiguration.isEnabled()) {
                            WalletApiException finalWalletApiException = walletApiException;
                            syncTimestamp().continueWith(task -> {
                                if (!task.isFaulted()) {
                                    performCallWithTaskCompletionSource(
                                        request,
                                        mainTaskCompletionSource
                                    );
                                } else {
                                    mainTaskCompletionSource.setError(finalWalletApiException);
                                }
                                return null;
                            });
                        } else if (walletApiException.isRateLimitExceededError()) {
                            walletApiException.setRecaptchaHeaders(
                                new RecaptchaHeaders(
                                    response.header(RecaptchaHeader.SITE_KEY.toString()),
                                    response.header(RecaptchaHeader.UNLOCK_URL.toString())
                                )
                            );
                            mainTaskCompletionSource.setError(walletApiException);
                        } else {
                            mainTaskCompletionSource.setError(walletApiException);
                        }
                    } catch (Exception exception) {
                        mainTaskCompletionSource.setError(
                            new WalletApiException(
                                "An error occurred: " + responseBody,
                                response.code()
                            )
                        );
                    }
                }
            }
        });
    }

    private Task<Void> syncTimestamp() {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        publicWalletApiClient.getServerInformation().enqueue(new Callback<ServerInformation>() {
            @Override
            public void onResponse(
                Call<ServerInformation> call,
                Response<ServerInformation> response
            ) {
                Date serverTime = response.body().getTime();
                timestampProvider.setTime(
                    System.currentTimeMillis(),
                    serverTime.getTime()
                );
                TimestampSynchronizedCallback timestampSynchronizedCallback
                    = clientServerTimeSynchronizationConfiguration.getTimestampSynchronizedCallback();
                if (timestampSynchronizedCallback != null) {
                    clientServerTimeSynchronizationConfiguration
                        .getTimestampSynchronizedCallback()
                        .onTimestampUpdated(
                            serverTime,
                            new Date(System.currentTimeMillis())
                        );
                }
                taskCompletionSource.setResult(null);
            }

            @Override
            public void onFailure(
                Call<ServerInformation> call,
                Throwable throwable
            ) {
                taskCompletionSource.setError((Exception) throwable);
            }
        });

        return taskCompletionSource.getTask();
    }

    protected synchronized void cancelCalls() {
        while (!retrofitCalls.isEmpty()) {
            retrofitCalls.poll().cancel();
        }

        while (!okhttpCalls.isEmpty()) {
            okhttpCalls.poll().cancel();
        }
    }
}
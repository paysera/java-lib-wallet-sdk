package com.paysera.sdk.wallet;

import bolts.Continuation;
import bolts.Task;
import bolts.TaskCompletionSource;
import com.paysera.sdk.wallet.clients.OAuthAsyncClient;
import com.paysera.sdk.wallet.entities.Credentials;
import com.paysera.sdk.wallet.enums.GrantType;
import com.paysera.sdk.wallet.exceptions.WalletApiException;
import com.paysera.sdk.wallet.interfaces.AccessTokenRefresherDelegate;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AccessTokenRefresher {
    private OAuthAsyncClient oAuthAsyncClient;
    private AccessTokenRefresherDelegate accessTokenRefresherDelegate;
    private Credentials activeCredentials;
    private Credentials inactiveCredentials;
    private Task<Credentials> accessTokenRefreshTask;
    private Date accessTokenRefreshedAt;
    private int refreshAttempt = 0;
    private boolean tokenRefreshSuspended = false;

    public AccessTokenRefresher(
        OAuthAsyncClient oAuthAsyncClient,
        AccessTokenRefresherDelegate accessTokenRefresherDelegate,
        Credentials activeCredentials,
        Credentials inactiveCredentials
    ) {
        this.oAuthAsyncClient = oAuthAsyncClient;
        this.accessTokenRefresherDelegate = accessTokenRefresherDelegate;
        this.activeCredentials = activeCredentials;
        this.inactiveCredentials = inactiveCredentials;
    }

    public synchronized boolean isAccessTokenRefreshing() {
        return accessTokenRefreshTask != null || tokenRefreshSuspended;
    }

    public synchronized Task<Credentials> refreshAccessToken() {
        return this.refreshAccessToken(GrantType.REFRESH_TOKEN, null, null);
    }

    public synchronized Task<Credentials> refreshAccessToken(GrantType grantType, final List<String> scopes, final String code) {
        if (accessTokenRefreshTask != null) {
            return accessTokenRefreshTask;
        }

        final TaskCompletionSource<Credentials> taskCompletionSource = new TaskCompletionSource<Credentials>();
        final Task<Credentials> accessTokenRefreshTask = taskCompletionSource.getTask();

        this.accessTokenRefreshTask = accessTokenRefreshTask;
        tokenRefreshSuspended = true;

        switch (grantType) {
            case REFRESH_TOKEN: {
                if (activeCredentials != null && activeCredentials.getRefreshToken() != null) {
                    this.oAuthAsyncClient
                            .refreshToken(this.activeCredentials.getRefreshToken(), grantType, scopes, code)
                            .continueWith(new Continuation<Credentials, Void>() {
                                @Override
                                public Void then(Task<Credentials> task) throws Exception {
                                    synchronized (AccessTokenRefresher.this) {
                                        AccessTokenRefresher.this.accessTokenRefreshTask = null;
                                        if (!task.isFaulted()) {
                                            handleSuccessfulTokenRefresh(taskCompletionSource, task);
                                        } else {
                                            handleRefreshTokenError(taskCompletionSource, task);
                                        }
                                    }
                                    return null;
                                }
                            });
                } else {
                    taskCompletionSource.setError(new WalletApiException("Unknown"));
                }
                 break;
            }
            case REFRESH_TOKEN_WITH_ACTIVATION: {
                if (inactiveCredentials != null && inactiveCredentials.getAccessToken() != null) {
                    this.oAuthAsyncClient
                            .activate(inactiveCredentials.getAccessToken())
                            .continueWith(new Continuation<Credentials, Void>() {
                                @Override
                                public Void then(Task<Credentials> task) throws Exception {
                                    synchronized (AccessTokenRefresher.this) {
                                        AccessTokenRefresher.this.accessTokenRefreshTask = null;
                                        if (!task.isFaulted()) {
                                            handleSuccessfulTokenRefresh(taskCompletionSource, task);
                                        } else {
                                            handleRefreshTokenError(taskCompletionSource, task);
                                        }
                                    }
                                    return null;
                                }
                            });
                } else if (activeCredentials != null && activeCredentials.getRefreshToken() != null) {
                    this.oAuthAsyncClient
                            .refreshToken(this.activeCredentials.getRefreshToken(), grantType, scopes, code)
                            .continueWithTask(new Continuation<Credentials, Task<Credentials>>() {
                                @Override
                                public Task<Credentials> then(Task<Credentials> task) throws Exception {
                                    synchronized (AccessTokenRefresher.this) {
                                        if (!task.isFaulted()) {
                                            Credentials renewedCredentials = task.getResult();
                                            updateInactiveCredentials(renewedCredentials);
                                            return AccessTokenRefresher.this.oAuthAsyncClient.activate(inactiveCredentials.getAccessToken());
                                        } else {
                                            handleRefreshTokenError(taskCompletionSource, task);
                                        }
                                    }
                                    return null;
                                }
                            }).continueWith(new Continuation<Credentials, Void>() {
                                @Override
                                public Void then(Task<Credentials> task) throws Exception {
                                    synchronized (AccessTokenRefresher.this) {
                                        AccessTokenRefresher.this.accessTokenRefreshTask = null;
                                        if (!task.isFaulted()) {
                                            handleSuccessfulTokenRefresh(taskCompletionSource, task);
                                        } else {
                                            handleRefreshTokenError(taskCompletionSource, task);
                                        }
                                    }
                                    return null;
                                }
                            });
                } else {
                    taskCompletionSource.setError(new WalletApiException("Unknown"));
                }
                break;
            }
        }

        return accessTokenRefreshTask;
    }

    public boolean hasAccessTokenBeenRecentlyRefreshed() {
        return accessTokenRefreshedAt != null
            && accessTokenRefreshedAt.after(new Date(System.currentTimeMillis() - 20000));
    }

    public boolean willAccessTokenExpireSoon() {
        return activeCredentials.getValidUntil().before(new Date(System.currentTimeMillis() - 30000));
    }

    public void resetAttemptsCount() {
        refreshAttempt = 0;
    }

    private void handleSuccessfulTokenRefresh(TaskCompletionSource<Credentials> taskCompletionSource, Task<Credentials> task) {
        Credentials renewedCredentials = task.getResult();
        accessTokenRefreshedAt = new Date();
        updateActiveCredentials(renewedCredentials);
        tokenRefreshSuspended = false;
        taskCompletionSource.setResult(task.getResult());
    }

    private void handleRefreshTokenError(TaskCompletionSource<Credentials> taskCompletionSource, Task<Credentials> task) {
        WalletApiException walletApiException = (WalletApiException) task.getError();
        if (walletApiException.isRefreshTokenExpiredError()) {
            accessTokenRefresherDelegate.onRefreshTokenInvalid();
            tokenRefreshSuspended = false;
        } else if (walletApiException.getStatusCode() != null && walletApiException.getStatusCode() >= 400 && walletApiException.getStatusCode() < 500) {
            updateInactiveCredentials(null);
            scheduleRefreshUnblock();
        } else if (walletApiException.getStatusCode() != null && walletApiException.getStatusCode() >= 500 && walletApiException.getStatusCode() < 600) {
            scheduleRefreshUnblock();
        }
        taskCompletionSource.setError(task.getError());
    }

    private void updateActiveCredentials(Credentials newCredentials) {
        activeCredentials.update(newCredentials);
        accessTokenRefresherDelegate.activeCredentialsDidUpdate(newCredentials);
        updateInactiveCredentials(null);
    }

    private void updateInactiveCredentials(Credentials credentials) {
        inactiveCredentials = credentials;
        accessTokenRefresherDelegate.inactiveCredentialsDidUpdate(credentials);
    }

    private void scheduleRefreshUnblock() {
        if (refreshAttempt + 1 <= 4) {
            refreshAttempt += 1;
        }

        Random random = new Random();

        double baseDelay = Math.pow(refreshAttempt, 3);
        double randomDelay = baseDelay / 2 * random.nextDouble();
        double finalDelay = baseDelay + randomDelay;
        long milliseconds = Math.round(finalDelay * 1000);

        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.schedule(
            new Runnable() {
                @Override
                public void run() {
                    tokenRefreshSuspended = false;
                }
            },
            milliseconds,
            TimeUnit.MILLISECONDS
        );
    }
}
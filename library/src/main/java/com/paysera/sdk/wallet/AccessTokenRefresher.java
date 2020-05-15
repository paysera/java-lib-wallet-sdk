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

public class AccessTokenRefresher {
    private OAuthAsyncClient oAuthAsyncClient;
    private AccessTokenRefresherDelegate accessTokenRefresherDelegate;
    private Credentials activeCredentials;
    private Credentials inactiveCredentials;
    private GrantType grantType;
    private Task<Credentials> accessTokenRefreshTask;
    private Date accessTokenRefreshedAt;

    public AccessTokenRefresher(
        OAuthAsyncClient oAuthAsyncClient,
        AccessTokenRefresherDelegate accessTokenRefresherDelegate,
        Credentials activeCredentials,
        Credentials inactiveCredentials,
        GrantType grantType
    ) {
        this.oAuthAsyncClient = oAuthAsyncClient;
        this.accessTokenRefresherDelegate = accessTokenRefresherDelegate;
        this.activeCredentials = activeCredentials;
        this.inactiveCredentials = inactiveCredentials;
        this.grantType = grantType;
    }

    public synchronized boolean isAccessTokenRefreshing() {
        return accessTokenRefreshTask != null;
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

        switch (grantType) {
            case REFRESH_TOKEN: {
                if (activeCredentials.getRefreshToken() != null) {
                    this.oAuthAsyncClient
                            .refreshToken(this.activeCredentials.getRefreshToken(), grantType, scopes, code)
                            .continueWith(new Continuation<Credentials, Void>() {
                                @Override
                                public Void then(Task<Credentials> task) throws Exception {
                                    synchronized (AccessTokenRefresher.this) {
                                        AccessTokenRefresher.this.accessTokenRefreshTask = null;
                                        if (!task.isFaulted()) {
                                            Credentials renewedCredentials = task.getResult();
                                            accessTokenRefreshedAt = new Date();
                                            updateActiveCredentials(renewedCredentials);

                                            taskCompletionSource.setResult(task.getResult());
                                        } else {
                                            WalletApiException walletApiException = (WalletApiException) task.getError();
                                            if (walletApiException.getStatusCode() != null && walletApiException.isInvalidGrantError()) {
                                                accessTokenRefresherDelegate.onRefreshTokenInvalid();
                                            }

                                            taskCompletionSource.setError(task.getError());
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
                if (inactiveCredentials.getAccessToken() != null) {
                    this.oAuthAsyncClient
                            .activate(inactiveCredentials.getAccessToken())
                            .continueWith(new Continuation<Credentials, Void>() {
                                @Override
                                public Void then(Task<Credentials> task) throws Exception {
                                    synchronized (AccessTokenRefresher.this) {
                                        AccessTokenRefresher.this.accessTokenRefreshTask = null;
                                        if (!task.isFaulted()) {
                                            Credentials renewedCredentials = task.getResult();
                                            accessTokenRefreshedAt = new Date();
                                            updateActiveCredentials(renewedCredentials);

                                            taskCompletionSource.setResult(task.getResult());
                                        } else {
                                            WalletApiException walletApiException = (WalletApiException) task.getError();
                                            if (walletApiException.getStatusCode() != null && walletApiException.isInvalidGrantError()) {
                                                accessTokenRefresherDelegate.onRefreshTokenInvalid();
                                            }

                                            taskCompletionSource.setError(task.getError());
                                        }
                                    }
                                    return null;
                                }
                            });
                } else if (activeCredentials.getRefreshToken() != null) {
                    this.oAuthAsyncClient
                            .refreshToken(this.activeCredentials.getRefreshToken(), grantType, scopes, code)
                            .continueWith(new Continuation<Credentials, Void>() {
                                @Override
                                public Void then(Task<Credentials> task) throws Exception {
                                    synchronized (AccessTokenRefresher.this) {
                                        if (!task.isFaulted()) {
                                            Credentials renewedCredentials = task.getResult();
                                            accessTokenRefreshedAt = new Date();
                                            updateInactiveCredentials(renewedCredentials);
                                        } else {
                                            WalletApiException walletApiException = (WalletApiException) task.getError();
                                            if (walletApiException.getStatusCode() != null && walletApiException.isInvalidGrantError()) {
                                                accessTokenRefresherDelegate.onRefreshTokenInvalid();
                                            }

                                            taskCompletionSource.setError(task.getError());
                                        }
                                    }
                                    return null;
                                }
                            }).onSuccess(new Continuation<Void, Void>() {
                                @Override
                                public Void then(Task<Void> task) throws Exception {
                                    AccessTokenRefresher.this.oAuthAsyncClient
                                            .activate(inactiveCredentials.getAccessToken())
                                            .continueWith(new Continuation<Credentials, Void>() {
                                                @Override
                                                public Void then(Task<Credentials> task) throws Exception {
                                                    synchronized (AccessTokenRefresher.this) {
                                                        AccessTokenRefresher.this.accessTokenRefreshTask = null;
                                                        if (!task.isFaulted()) {
                                                            Credentials renewedCredentials = task.getResult();
                                                            accessTokenRefreshedAt = new Date();
                                                            updateActiveCredentials(renewedCredentials);

                                                            taskCompletionSource.setResult(task.getResult());
                                                        } else {
                                                            WalletApiException walletApiException = (WalletApiException) task.getError();
                                                            if (walletApiException.getStatusCode() != null && walletApiException.isInvalidGrantError()) {
                                                                accessTokenRefresherDelegate.onRefreshTokenInvalid();
                                                            }

                                                            taskCompletionSource.setError(task.getError());
                                                        }
                                                    }
                                                    return null;
                                                }
                                            });
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

    private void updateActiveCredentials(Credentials newCredentials) {
        activeCredentials.update(newCredentials);
        accessTokenRefresherDelegate.activeCredentialsDidUpdate(newCredentials);
        updateInactiveCredentials(null);
    }

    private void updateInactiveCredentials(Credentials credentials) {
        inactiveCredentials = credentials;
        accessTokenRefresherDelegate.inactiveCredentialsDidUpdate(credentials);
    }
}
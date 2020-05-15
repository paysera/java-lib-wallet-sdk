package com.paysera.sdk.wallet.interfaces;

import com.paysera.sdk.wallet.entities.Credentials;

public interface AccessTokenRefresherDelegate {

    void activeCredentialsDidUpdate(Credentials credentials);
    void inactiveCredentialsDidUpdate(Credentials credentials);
    void onRefreshTokenInvalid();
}

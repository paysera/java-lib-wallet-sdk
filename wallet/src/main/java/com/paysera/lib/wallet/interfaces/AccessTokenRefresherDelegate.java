package com.paysera.lib.wallet.interfaces;

import com.paysera.lib.wallet.entities.Credentials;

public interface AccessTokenRefresherDelegate {

    void activeCredentialsDidUpdate(Credentials credentials);
    void inactiveCredentialsDidUpdate(Credentials credentials);
    void onRefreshTokenInvalid();
}
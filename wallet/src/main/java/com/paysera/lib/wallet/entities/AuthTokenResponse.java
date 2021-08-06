package com.paysera.lib.wallet.entities;

public class AuthTokenResponse {
    private String type;
    private AuthToken authToken;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public AuthToken getAuthToken() {
        return authToken;
    }

    public void setAuthToken(AuthToken authToken) {
        this.authToken = authToken;
    }
}
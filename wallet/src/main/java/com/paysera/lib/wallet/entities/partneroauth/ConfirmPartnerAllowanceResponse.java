package com.paysera.lib.wallet.entities.partneroauth;

public class ConfirmPartnerAllowanceResponse {
    private String relatedUserId;
    private String authToken;
    private Long authTokenExpiresIn;
    private String refreshToken;
    private Long refreshTokenExpiresIn;

    public String getRelatedUserId() {
        return relatedUserId;
    }

    public String getAuthToken() {
        return authToken;
    }

    public Long getAuthTokenExpiresIn() {
        return authTokenExpiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public Long getRefreshTokenExpiresIn() {
        return refreshTokenExpiresIn;
    }
}
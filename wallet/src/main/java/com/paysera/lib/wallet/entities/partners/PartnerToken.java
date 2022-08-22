package com.paysera.lib.wallet.entities.partners;

public class PartnerToken {
    private  Integer userId;
    private String authToken;
    private Long authTokenExpiresIn;
    private String refreshToken;
    private Long refreshTokenExpiresIn;

    public Integer getUserId() {
        return userId;
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
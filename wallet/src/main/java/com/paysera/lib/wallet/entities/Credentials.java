package com.paysera.lib.wallet.entities;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.paysera.lib.wallet.adapters.CredentialsValidUntilAdapter;

import java.util.Date;

public class Credentials {
    private String accessToken;
    private String tokenType;
    @JsonAdapter(CredentialsValidUntilAdapter.class)
    @SerializedName("expires_in")
    private Date validUntil;
    private String macKey;
    private String macAlgorithm;
    private String refreshToken;
    private Date deletionRequestAt;

    public Date getDeletionRequestAt() {
        return deletionRequestAt;
    }

    public void setDeletionRequestAt(Date deletionRequestAt) {
        this.deletionRequestAt = deletionRequestAt;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }

    public String getMacKey() {
        return macKey;
    }

    public void setMacKey(String macKey) {
        this.macKey = macKey;
    }

    public String getMacAlgorithm() {
        return macAlgorithm;
    }

    public void setMacAlgorithm(String macAlgorithm) {
        this.macAlgorithm = macAlgorithm;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void update(Credentials credentials) {
        this.accessToken = credentials.accessToken;
        this.tokenType = credentials.tokenType;
        this.validUntil = credentials.validUntil;
        this.macKey = credentials.macKey;
        this.macAlgorithm = credentials.macAlgorithm;
        this.refreshToken = credentials.refreshToken;
    }
}
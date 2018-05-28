package com.paysera.sdk.wallet.entities.confirmations;

public class Confirmation {
    private String challengeId;
    private String identifier;
    private String status;
    private Integer userId;
    private ConfirmationProperties properties;

    public String getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(String challengeId) {
        this.challengeId = challengeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public ConfirmationProperties getProperties() {
        return properties;
    }

    public void setProperties(ConfirmationProperties properties) {
        this.properties = properties;
    }
}

package com.paysera.lib.wallet.entities.confirmations;

public class Confirmation {
    private String challengeId;
    private String identifier;
    private String status;
    private Integer userId;
    private ConfirmationProperties properties;
    private String countryCode;
    private String city;
    private String browser;
    private String os;
    private String model;
    private String ip;
    private String appClientInfoId;

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

    public String getCountryCode() { return countryCode; }

    public void setCountryCode(String countryCode) { this.countryCode = countryCode; }

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }

    public String getBrowser() { return browser; }

    public void setBrowser(String browser) { this.browser = browser; }

    public String getOs() { return os; }

    public void setOs(String os) { this.os = os; }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAppClientInfoId() {
        return appClientInfoId;
    }

    public void setAppClientInfoId(String appClientInfoId) {
        this.appClientInfoId = appClientInfoId;
    }
}

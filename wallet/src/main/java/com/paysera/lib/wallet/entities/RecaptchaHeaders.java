package com.paysera.lib.wallet.entities;

public class RecaptchaHeaders {
    private String siteKey;
    private String unlockUrl;

    public RecaptchaHeaders(String siteKey, String unlockUrl) {
        this.siteKey = siteKey;
        this.unlockUrl = unlockUrl;
    }

    public String getSiteKey() {
        return siteKey;
    }

    public void setSiteKey(String siteKey) {
        this.siteKey = siteKey;
    }

    public String getUnlockUrl() {
        return unlockUrl;
    }

    public void setUnlockUrl(String unlockUrl) {
        this.unlockUrl = unlockUrl;
    }

    @Override
    public String toString() {
        return String.format("siteKey=%s, unlockUrl=%s", siteKey, unlockUrl);
    }
}
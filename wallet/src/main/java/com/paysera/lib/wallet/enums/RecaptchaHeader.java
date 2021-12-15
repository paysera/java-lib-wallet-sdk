package com.paysera.lib.wallet.enums;

public enum RecaptchaHeader {
    SITE_KEY("g-recaptcha-key"),
    UNLOCK_URL("recaptcha-unlock-url");

    private final String value;

    RecaptchaHeader(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}

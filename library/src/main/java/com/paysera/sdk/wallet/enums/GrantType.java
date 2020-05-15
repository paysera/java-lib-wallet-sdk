package com.paysera.sdk.wallet.enums;

public enum GrantType {
    REFRESH_TOKEN("refresh_token"),
    REFRESH_TOKEN_WITH_ACTIVATION("refresh_token_with_activation");

    private final String value;

    GrantType(final String text) {
        this.value = text;
    }

    @Override
    public String toString() {
        return value;
    }
}

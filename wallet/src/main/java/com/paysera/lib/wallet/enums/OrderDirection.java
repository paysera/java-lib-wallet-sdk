package com.paysera.lib.wallet.enums;

public enum OrderDirection {
    DESC("DESC"),
    ASC("ASC");

    private final String text;

    OrderDirection(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}

package com.paysera.lib.wallet.entities;

public class WalletApiErrorProperty {
    private String code;
    private String description;

    public WalletApiErrorProperty(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("code=%s, desc=%s", code, description);
    }
}
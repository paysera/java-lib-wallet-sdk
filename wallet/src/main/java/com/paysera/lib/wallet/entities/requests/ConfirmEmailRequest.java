package com.paysera.lib.wallet.entities.requests;

public class ConfirmEmailRequest {
    private String code;

    public ConfirmEmailRequest(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
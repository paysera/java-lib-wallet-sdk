package com.paysera.lib.wallet.entities.transfer;

/**
 * Created by naglis on 27/03/2017.
 */
public class FailureStatus {

    private String message;
    private String code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
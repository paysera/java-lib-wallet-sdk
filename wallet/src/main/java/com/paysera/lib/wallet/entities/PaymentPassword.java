package com.paysera.lib.wallet.entities;

public class PaymentPassword {
    public static final String TYPE_PROVIDED = "provided";
    public static final String TYPE_GENERATED = "generated";
    public static final String STATUS_PENDING = "pending";
    public static final String STATUS_UNLOCKED = "unlocked";

    private String type;
    private String value;
    private String status;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
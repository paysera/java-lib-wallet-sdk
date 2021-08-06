package com.paysera.lib.wallet.entities.notification;

public class TransferNotification {

    public static final String TYPE_DONE = "done";

    private String type;
    private String email;
    private String locale;

    public TransferNotification(String type, String email, String locale) {
        this.type = type;
        this.email = email;
        this.locale = locale;
    }

    public TransferNotification() { }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }
}
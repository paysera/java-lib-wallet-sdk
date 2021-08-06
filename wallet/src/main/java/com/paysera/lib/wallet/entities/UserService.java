package com.paysera.lib.wallet.entities;

public class UserService {

    private final String STATUS_ENABLED = "enabled";
    private final String STATUS_DISABLED = "disabled";

    private String service;
    private String status;

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
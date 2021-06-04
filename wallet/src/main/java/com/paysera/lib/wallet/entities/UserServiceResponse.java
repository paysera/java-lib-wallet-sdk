package com.paysera.lib.wallet.entities;

import java.util.List;

public class UserServiceResponse {
    private Integer userId;
    private List<UserService> services;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<UserService> getServices() {
        return services;
    }

    public void setServices(List<UserService> services) {
        this.services = services;
    }
}
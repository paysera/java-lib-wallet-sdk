package com.paysera.lib.wallet.entities.requests;

import java.util.ArrayList;
import java.util.List;

public class UserPhoneConfirmationParameters {
    private String phoneLink;
    private List<String> scopes = new ArrayList<>();

    public String getPhoneLink() {
        return phoneLink;
    }

    public void setPhoneLink(String phoneLink) {
        this.phoneLink = phoneLink;
    }

    public List<String> getScopes() {
        return scopes;
    }

    public void setScopes(List<String> scopes) {
        this.scopes = scopes;
    }
}
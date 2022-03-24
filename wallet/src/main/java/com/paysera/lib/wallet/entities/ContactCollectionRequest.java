package com.paysera.lib.wallet.entities;

public class ContactCollectionRequest {
    public String email;
    public String countryCode;

    public ContactCollectionRequest(String email, String countryCode) {
        this.email = email;
        this.countryCode = countryCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}

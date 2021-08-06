package com.paysera.lib.wallet.entities.client;

import com.paysera.lib.wallet.entities.Credentials;

public class ApplicationClient extends Client {
    private Credentials credentials;
    private ApplicationClientInfo info;

    @Override
    public String getType() {
        return "app_client";
    }

    public ApplicationClientInfo getInfo() {
        return info;
    }

    public void setInfo(ApplicationClientInfo info) {
        this.info = info;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }
}


package com.paysera.lib.wallet.entities.requests;

import com.google.gson.annotations.SerializedName;

public class ChangeWalletDescriptionRequest {
    @SerializedName("description")
    private String walletDescription;

    public ChangeWalletDescriptionRequest(String walletDescription) {
        this.walletDescription = walletDescription;
    }

    public String getWalletDescription() {
        return walletDescription;
    }

    public void setWalletDescription(String walletDescription) {
        this.walletDescription = walletDescription;
    }
}
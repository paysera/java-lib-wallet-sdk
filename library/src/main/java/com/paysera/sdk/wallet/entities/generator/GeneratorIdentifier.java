package com.paysera.sdk.wallet.entities.generator;

import com.google.gson.annotations.SerializedName;

public class GeneratorIdentifier {
    private Long identifier;
    @SerializedName("wallet_id")
    private Integer walletId;

    public Long getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Long identifier) {
        this.identifier = identifier;
    }

    public Integer getWalletId() {
        return walletId;
    }

    public void setWalletId(Integer walletId) {
        this.walletId = walletId;
    }
}
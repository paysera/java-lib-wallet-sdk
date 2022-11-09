package com.paysera.lib.wallet.entities.partneroauth;

public class PartnerAllowanceRequest {
    private String partner;
    private int walletId;

    public PartnerAllowanceRequest(String partner, int walletId) {
        this.partner = partner;
        this.walletId = walletId;
    }

    public String getPartner() {
        return partner;
    }

    public int getWalletId() {
        return walletId;
    }
}
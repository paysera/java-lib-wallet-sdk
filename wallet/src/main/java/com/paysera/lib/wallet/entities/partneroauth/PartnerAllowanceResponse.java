package com.paysera.lib.wallet.entities.partneroauth;

import com.paysera.lib.wallet.entities.allowances.Allowance;

import java.util.List;

public class PartnerAllowanceResponse {
    private String key;
    private Integer walletId;
    private Allowance allowance;
    private List<String> scopes;

    public String getKey() {
        return key;
    }

    public Integer getWalletId() {
        return walletId;
    }

    public Allowance getAllowance() {
        return allowance;
    }

    public List<String> getScopes() {
        return scopes;
    }
}
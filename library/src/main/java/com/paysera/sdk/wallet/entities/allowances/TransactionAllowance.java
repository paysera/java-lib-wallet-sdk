package com.paysera.sdk.wallet.entities.allowances;

import com.google.gson.annotations.SerializedName;

public class TransactionAllowance {
    private Integer id;
    @SerializedName("data")
    private Allowance allowance;
    @SerializedName("optional")
    private Boolean isOptional;

    public Integer getId() {
        return id;
    }

    public Allowance getAllowance() {
        return allowance;
    }

    public Boolean getOptional() {
        return isOptional;
    }
}

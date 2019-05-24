package com.paysera.sdk.wallet.entities;

import com.google.gson.annotations.SerializedName;

public class AccountFlags {
    @SerializedName("savings")
    private Boolean savings;
    @SerializedName("public")
    private Boolean publicFlag;

    public Boolean getSavings() {
        return savings;
    }

    public void setSavings(Boolean savings) {
        this.savings = savings;
    }

    public Boolean getPublicFlag() {
        return publicFlag;
    }

    public void setPublicFlag(Boolean publicFlag) {
        this.publicFlag = publicFlag;
    }

    @Override
    public String toString() {
        return "AccountFlags{" +
            "savings=" + savings +
            ", publicFlag=" + publicFlag +
            '}';
    }
}

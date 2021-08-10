package com.paysera.lib.wallet.entities.generator;

import com.google.gson.annotations.SerializedName;

public class PSGenerateCodeRequestContext {
    @SerializedName("os")
    public String os;
    @SerializedName("os_version")
    public String osVersion;
    @SerializedName("amount")
    public String amount;
    @SerializedName("currency")
    public String currency;
    @SerializedName("beneficiary_iban")
    public String beneficiaryIban;

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getBeneficiaryIban() {
        return beneficiaryIban;
    }

    public void setBeneficiaryIban(String beneficiaryIban) {
        this.beneficiaryIban = beneficiaryIban;
    }
}
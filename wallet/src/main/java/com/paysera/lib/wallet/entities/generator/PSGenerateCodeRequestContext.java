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


}
package com.paysera.lib.wallet.entities.generator;

import com.google.gson.annotations.SerializedName;

public class PSGenerateCodeRequestParameters {
    @SerializedName("action")
    public String action;
    @SerializedName("context")
    public PSGenerateCodeRequestContext context;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public PSGenerateCodeRequestContext getContext() {
        return context;
    }

    public void setContext(PSGenerateCodeRequestContext context) {
        this.context = context;
    }
}
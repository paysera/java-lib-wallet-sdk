package com.paysera.lib.wallet.entities.requests;

import com.google.gson.annotations.SerializedName;
import com.paysera.lib.wallet.entities.generator.PSGenerateCodeRequestContext;

import java.util.List;

public class GenerateCodeRequest {

    private List<String> scopes;
    @SerializedName("action")
    public String action;
    @SerializedName("context")
    public PSGenerateCodeRequestContext context;

    public GenerateCodeRequest(List<String> scopes) {
        this.scopes = scopes;
    }

    public GenerateCodeRequest(List<String> scopes, String action, PSGenerateCodeRequestContext context) {
        this.scopes = scopes;
        this.action = action;
        this.context = context;
    }

    public List<String> getScopes() {
        return scopes;
    }

    public void setScopes(List<String> scopes) {
        this.scopes = scopes;
    }

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
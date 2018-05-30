package com.paysera.sdk.wallet.entities.requests;

import java.util.List;

public class GenerateCodeRequest {

    private List<String> scopes;

    public GenerateCodeRequest(List<String> scopes) {
        this.scopes = scopes;
    }

    public List<String> getScopes() {
        return scopes;
    }

    public void setScopes(List<String> scopes) {
        this.scopes = scopes;
    }
}
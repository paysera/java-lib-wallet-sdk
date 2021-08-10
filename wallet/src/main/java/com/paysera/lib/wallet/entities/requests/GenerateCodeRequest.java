package com.paysera.lib.wallet.entities.requests;

import java.util.List;

public class GenerateCodeRequest {

    private List<String> scopes;
    private PSGenerateCodeRequestParameters parameters;

    public GenerateCodeRequest(List<String> scopes) {
        this.scopes = scopes;
    }

    public GenerateCodeRequest(List<String> scopes, PSGenerateCodeRequestParameters parameters) {
        this.scopes = scopes;
        this.parameters = parameters;
    }

    public List<String> getScopes() {
        return scopes;
    }

    public void setScopes(List<String> scopes) {
        this.scopes = scopes;
    }

    public PSGenerateCodeRequestParameters getParameters() {
        return parameters;
    }

    public void setParameters(PSGenerateCodeRequestParameters parameters) {
        this.parameters = parameters;
    }
}
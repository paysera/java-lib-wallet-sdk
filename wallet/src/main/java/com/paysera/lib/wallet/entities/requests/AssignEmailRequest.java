package com.paysera.lib.wallet.entities.requests;

public class AssignEmailRequest {

    private String email;
    private UserEmailConfirmationParameters parameters;

    public AssignEmailRequest(String email, UserEmailConfirmationParameters parameters) {
        this.email = email;
        this.parameters = parameters;
    }

    public AssignEmailRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserEmailConfirmationParameters getParameters() {
        return parameters;
    }

    public void setParameters(UserEmailConfirmationParameters parameters) {
        this.parameters = parameters;
    }
}
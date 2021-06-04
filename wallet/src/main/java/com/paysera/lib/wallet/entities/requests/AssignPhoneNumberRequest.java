package com.paysera.lib.wallet.entities.requests;

public class AssignPhoneNumberRequest {
    private String phone;
    private UserPhoneConfirmationParameters parameters;

    public AssignPhoneNumberRequest(String phone, UserPhoneConfirmationParameters parameters) {
        this.phone = phone;
        this.parameters = parameters;
    }

    public AssignPhoneNumberRequest(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserPhoneConfirmationParameters getParameters() {
        return parameters;
    }

    public void setParameters(UserPhoneConfirmationParameters parameters) {
        this.parameters = parameters;
    }
}
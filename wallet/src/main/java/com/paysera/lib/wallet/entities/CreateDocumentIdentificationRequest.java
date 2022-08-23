package com.paysera.lib.wallet.entities;

public class CreateDocumentIdentificationRequest {

    public String type;
    public String countryOfIssue;

    public CreateDocumentIdentificationRequest(String type, String countryOfIssue) {
        this.type = type;
        this.countryOfIssue = countryOfIssue;
    }

    public String getType() {
        return type;
    }

    public String getCountryOfIssue() {
        return countryOfIssue;
    }
}
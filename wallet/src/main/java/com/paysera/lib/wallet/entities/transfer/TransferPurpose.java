package com.paysera.lib.wallet.entities.transfer;

public class TransferPurpose {
    private String details;
    private String reference;
    private String purposeCode;
    private TransferPurposeDetailsOptions detailsOptions;

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getPurposeCode() {
        return purposeCode;
    }

    public void setPurposeCode(String purposeCode) {
        this.purposeCode = purposeCode;
    }

    public TransferPurposeDetailsOptions getDetailsOptions() {
        return detailsOptions;
    }

    public void setDetailsOptions(TransferPurposeDetailsOptions detailsOptions) {
        this.detailsOptions = detailsOptions;
    }
}
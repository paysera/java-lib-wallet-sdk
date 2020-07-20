package com.paysera.sdk.wallet.entities;

public class CreateDocumentIdentificationRequest {

    public Long id;
    public Long identificationRequestId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdentificationRequestId() {
        return identificationRequestId;
    }

    public void setIdentificationRequestId(Long identificationRequestId) {
        this.identificationRequestId = identificationRequestId;
    }
}
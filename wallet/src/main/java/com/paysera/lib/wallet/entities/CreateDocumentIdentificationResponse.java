package com.paysera.lib.wallet.entities;

public class CreateDocumentIdentificationResponse {

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
package com.paysera.sdk.wallet.entities.identitydocuments;

import java.util.List;

public class PSSubmitIdentificationRequestResponse {

    private List<PSSubmitIdentificationRequestIdentityDocument> identityDocuments;

    public List<PSSubmitIdentificationRequestIdentityDocument> getIdentityDocuments() {
        return identityDocuments;
    }

    public void setIdentityDocuments(List<PSSubmitIdentificationRequestIdentityDocument> identityDocuments) {
        this.identityDocuments = identityDocuments;
    }
}

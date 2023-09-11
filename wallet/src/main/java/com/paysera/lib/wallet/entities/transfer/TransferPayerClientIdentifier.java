package com.paysera.lib.wallet.entities.transfer;

import com.paysera.lib.wallet.enums.ClientIdentifierType;

public class TransferPayerClientIdentifier {
    private ClientIdentifierType type;
    private String value;

    public ClientIdentifierType getType() {
        return type;
    }

    public void setType(ClientIdentifierType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

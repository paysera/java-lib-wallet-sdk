package com.paysera.lib.wallet.entities.transfer;

import com.paysera.lib.wallet.enums.ClientIdentifierType;

public class TransferPayerClientIdentifier {
    private String type;
    private String value;

    public ClientIdentifierType getType() {
        return ClientIdentifierType.valueOf(type.toUpperCase());
    }

    public void setType(ClientIdentifierType type) {
        this.type = type.toString();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

package com.paysera.lib.wallet.enums;

public enum ClientIdentifierType {
    PERSONAL_NUMBER("personal_number"),
    CUSTOMER_CODE("customer_code"),
    TAX_PAYER_CODE("tax_payer_code"),
    PASSPORT_NUMBER("passport_number"),
    COMPANY_CODE("company_code");

    private final String value;

    ClientIdentifierType(final String text) {
        this.value = text;
    }

    @Override
    public String toString() {
        return value;
    }
}

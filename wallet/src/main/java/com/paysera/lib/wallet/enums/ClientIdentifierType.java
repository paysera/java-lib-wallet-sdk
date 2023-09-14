package com.paysera.lib.wallet.enums;

public enum ClientIdentifierType {
//    personal_number,
//    customer_code,
//    tax_payer_code,
//    passport_number,
//    company_code
    PASSPORT_NUMBER("passport_number"),
    TAX_PAYER_CODE("tax_payer_code");

    private final String value;

    ClientIdentifierType(final String text) {
        this.value = text;
    }

    @Override
    public String toString() {
        return value;
    }
}

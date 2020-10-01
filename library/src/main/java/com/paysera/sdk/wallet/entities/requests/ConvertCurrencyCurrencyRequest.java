package com.paysera.sdk.wallet.entities.requests;

public class ConvertCurrencyCurrencyRequest {
    private String fromAmountDecimal;
    private String fromCurrency;
    private String toAmountDecimal;
    private String toCurrency;
    private String accountNumber;
    private String maxFromAmountDecimal;
    private String minToAmountDecimal;

    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getFromAmountDecimal() {
        return fromAmountDecimal;
    }

    public void setFromAmountDecimal(String fromAmountDecimal) {
        this.fromAmountDecimal = fromAmountDecimal;
    }

    public String getToAmountDecimal() {
        return toAmountDecimal;
    }

    public void setToAmountDecimal(String toAmountDecimal) {
        this.toAmountDecimal = toAmountDecimal;
    }

    public String getMaxFromAmountDecimal() {
        return maxFromAmountDecimal;
    }

    public void setMaxFromAmountDecimal(String maxFromAmountDecimal) {
        this.maxFromAmountDecimal = maxFromAmountDecimal;
    }

    public String getMinToAmountDecimal() {
        return minToAmountDecimal;
    }

    public void setMinToAmountDecimal(String minToAmountDecimal) {
        this.minToAmountDecimal = minToAmountDecimal;
    }
}
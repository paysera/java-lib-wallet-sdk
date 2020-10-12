package com.paysera.sdk.wallet.entities;

public class CurrencyConversionCalculation {
    private String fromAmountDecimal;
    private String fromAmount;
    private String fromCurrency;
    private String toAmountDecimal;
    private String toAmount;
    private String toCurrency;
    private String accountNumber;

    public String getFromAmountDecimal() {
        return fromAmountDecimal;
    }

    public void setFromAmountDecimal(String fromAmountDecimal) {
        this.fromAmountDecimal = fromAmountDecimal;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public String getToAmountDecimal() {
        return toAmountDecimal;
    }

    public void setToAmountDecimal(String toAmountDecimal) {
        this.toAmountDecimal = toAmountDecimal;
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

    public String getFromAmount() {
        return fromAmount;
    }

    public void setFromAmount(String fromAmount) {
        this.fromAmount = fromAmount;
    }

    public String getToAmount() {
        return toAmount;
    }

    public void setToAmount(String toAmount) {
        this.toAmount = toAmount;
    }
}
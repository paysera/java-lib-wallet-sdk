package com.paysera.lib.wallet.entities.transfer;

public class TransferPayer {
    private String accountNumber;
    private Integer userId;

    private TransferPayerClientIdentifier clientIdentifier;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public TransferPayerClientIdentifier getClientIdentifier() {
        return clientIdentifier;
    }

    public void setClientIdentifier(TransferPayerClientIdentifier clientIdentifier) {
        this.clientIdentifier = clientIdentifier;
    }
}
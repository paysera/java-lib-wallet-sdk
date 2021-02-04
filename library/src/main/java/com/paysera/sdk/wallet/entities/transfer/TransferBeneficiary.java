package com.paysera.sdk.wallet.entities.transfer;

public class TransferBeneficiary {
    private String type;
    private String name;
    private TransferBeneficiaryBankAccount bankAccount;
    private TransferBeneficiaryWebmoneyAccount webmoneyAccount;
    private TransferBeneficiaryPayzaAccount payzaAccount;
    private TransferBeneficiaryTaxAccount taxAccount;
    private TransferBeneficiaryPayseraAccount payseraAccount;
    private TransferIdentifiers identifiers;
    private TransferAddress address;
    private TransferBeneficiaryAdditionalInformation additionalInformation;

    public Boolean isBankAccount() {
        return bankAccount != null;
    }

    public Boolean isWebmoneyAccount() {
        return webmoneyAccount != null;
    }

    public Boolean isPayzaAccount() {
        return payzaAccount != null;
    }

    public Boolean isTaxAccount() {
        return taxAccount != null;
    }

    public Boolean isPayseraAccount() {
        return payseraAccount != null;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TransferBeneficiaryBankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(TransferBeneficiaryBankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public TransferBeneficiaryWebmoneyAccount getWebmoneyAccount() {
        return webmoneyAccount;
    }

    public void setWebmoneyAccount(TransferBeneficiaryWebmoneyAccount webmoneyAccount) {
        this.webmoneyAccount = webmoneyAccount;
    }

    public TransferBeneficiaryPayzaAccount getPayzaAccount() {
        return payzaAccount;
    }

    public void setPayzaAccount(TransferBeneficiaryPayzaAccount payzaAccount) {
        this.payzaAccount = payzaAccount;
    }

    public TransferBeneficiaryTaxAccount getTaxAccount() {
        return taxAccount;
    }

    public void setTaxAccount(TransferBeneficiaryTaxAccount taxAccount) {
        this.taxAccount = taxAccount;
    }

    public TransferBeneficiaryPayseraAccount getPayseraAccount() {
        return payseraAccount;
    }

    public void setPayseraAccount(TransferBeneficiaryPayseraAccount payseraAccount) {
        this.payseraAccount = payseraAccount;
    }

    public TransferIdentifiers getIdentifiers() {
        return identifiers;
    }

    public void setIdentifiers(TransferIdentifiers identifiers) {
        this.identifiers = identifiers;
    }

    public TransferAddress getAddress() {
        return address;
    }

    public void setAddress(TransferAddress address) {
        this.address = address;
    }

    public TransferBeneficiaryAdditionalInformation getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(TransferBeneficiaryAdditionalInformation additionalInformation) {
        this.additionalInformation = additionalInformation;
    }
}

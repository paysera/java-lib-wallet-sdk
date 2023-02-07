package com.paysera.lib.wallet.entities.easypay;

import com.google.gson.annotations.JsonAdapter;
import com.paysera.lib.wallet.adapters.MoneyAdapter;
import org.joda.money.Money;

public class EasyPayCreateTransfer {
    @JsonAdapter(MoneyAdapter.class)
    private Money amount;
    private Integer beneficiaryUserId;
    private Integer payerWalletId;

    public Money getAmount() {
        return amount;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }

    public Integer getBeneficiaryUserId() {
        return beneficiaryUserId;
    }

    public void setBeneficiaryUserId(Integer beneficiaryUserId) {
        this.beneficiaryUserId = beneficiaryUserId;
    }

    public Integer getPayerWalletId() {
        return payerWalletId;
    }

    public void setPayerWalletId(Integer payerWalletId) {
        this.payerWalletId = payerWalletId;
    }
}
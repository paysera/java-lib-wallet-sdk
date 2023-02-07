package com.paysera.lib.wallet.entities.easypay;

import com.google.gson.annotations.JsonAdapter;
import com.paysera.lib.wallet.adapters.MoneyAdapter;
import org.joda.money.Money;

public class EasyPayFees {
    @JsonAdapter(MoneyAdapter.class)
    private Money transferAmount;
    @JsonAdapter(MoneyAdapter.class)
    private Money feeAmount;

    public Money getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(Money transferAmount) {
        this.transferAmount = transferAmount;
    }

    public Money getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(Money feeAmount) {
        this.feeAmount = feeAmount;
    }
}
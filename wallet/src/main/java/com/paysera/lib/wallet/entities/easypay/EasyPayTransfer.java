package com.paysera.lib.wallet.entities.easypay;

import com.google.gson.annotations.JsonAdapter;
import com.paysera.lib.wallet.adapters.DateUnixTimestampSecondsAdapter;
import com.paysera.lib.wallet.adapters.MoneyAdapter;
import org.joda.money.Money;

import java.util.Date;

public class EasyPayTransfer {
    private Integer id;
    private Integer payerWalletId;
    private String status;
    private Integer beneficiaryUserId;
    @JsonAdapter(MoneyAdapter.class)
    private Money amount;
    private String invoice;
    private String revId;
    @JsonAdapter(DateUnixTimestampSecondsAdapter.class)
    private Date validUntil;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPayerWalletId() {
        return payerWalletId;
    }

    public void setPayerWalletId(Integer payerWalletId) {
        this.payerWalletId = payerWalletId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getBeneficiaryUserId() {
        return beneficiaryUserId;
    }

    public void setBeneficiaryUserId(Integer beneficiaryUserId) {
        this.beneficiaryUserId = beneficiaryUserId;
    }

    public Money getAmount() {
        return amount;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getRevId() {
        return revId;
    }

    public void setRevId(String revId) {
        this.revId = revId;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }
}
package com.paysera.sdk.wallet.entities;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.paysera.sdk.wallet.adapters.DateUnixTimestampSecondsAdapter;
import com.paysera.sdk.wallet.helpers.MoneyHelper;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Vytautas Gimbutas <v.gimbutas@evp.lt>
 */
public class Payment {

    @SerializedName("beneficiary")
    private WalletIdentifier beneficiaryIdentifier;
    @JsonAdapter(DateUnixTimestampSecondsAdapter.class)
    private Date freezeUntil;
    @JsonAdapter(DateUnixTimestampSecondsAdapter.class)
    private Date createdAt;
    private Integer price;
    private String currency;
    private Integer cashback;
    private Integer id;
    private String transactionKey;
    private String status;
    private String description;
    private boolean cancelable;
    @SerializedName("password")
    private PaymentPassword paymentPassword;

    public Money getCashbackMoney() {
        if (cashback != null) {
            return MoneyHelper.createFromCents(this.currency, this.cashback);
        } else {
            return null;
        }
    }

    public Payment setCashback(Integer cashback) {
        this.cashback = cashback;
        return this;
    }

    public Payment setCancelable(boolean cancelable) {
        this.cancelable = cancelable;
        return this;
    }

    public boolean isCancelable() {
        return cancelable;
    }

    public Payment setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getDescription() {
        return description;
    }

    private void setPrice(Integer price) {
        this.price = price;
    }

    private Integer getPrice() {
        return this.price;
    }

    public Payment setPriceMoney(Money price) {
        this.price = price.getAmount().multiply(new BigDecimal(100)).intValue();
        this.currency = price.getCurrencyUnit().getCurrencyCode();
        return this;
    }

    public Money getPriceMoney() {
        return MoneyHelper.createFromCents(this.currency, this.price);
    }

    public Integer getId() {
        return id;
    }

    public Payment setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getTransactionKey() {
        return transactionKey;
    }

    public Payment setTransactionKey(String transactionKey) {
        this.transactionKey = transactionKey;
        return this;
    }

    public WalletIdentifier getBeneficiaryIdentifier() {
        return beneficiaryIdentifier;
    }

    public Payment setBeneficiaryIdentifier(WalletIdentifier beneficiaryIdentifier) {
        this.beneficiaryIdentifier = beneficiaryIdentifier;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Payment setStatus(String status) {
        this.status = status;
        return this;
    }


    public Date getFreezeUntil() {
        return freezeUntil;
    }

    public Payment setFreezeUntil(Date freezeUntil) {
        this.freezeUntil = freezeUntil;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Payment setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public PaymentPassword getPaymentPassword() {
        return paymentPassword;
    }

    public void setPaymentPassword(PaymentPassword paymentPassword) {
        this.paymentPassword = paymentPassword;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}

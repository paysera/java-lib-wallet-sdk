package com.paysera.sdk.wallet.entities;

import com.google.gson.annotations.JsonAdapter;
import com.paysera.sdk.wallet.adapters.DateUnixTimestampSecondsAdapter;
import com.paysera.sdk.wallet.adapters.MoneyDecimalAdapter;
import org.joda.money.Money;
import java.util.Date;

public class PendingPayment {

    private Long id;
    @JsonAdapter(MoneyDecimalAdapter.class)
    private Money amount;
    private String details;
    private String type;
    private String direction;
    private boolean cancelable;
    private Integer transferId;
    private OtherParty otherParty;
    private String transactionKey;
    @JsonAdapter(DateUnixTimestampSecondsAdapter.class)
    private Date date;
    @JsonAdapter(DateUnixTimestampSecondsAdapter.class)
    private Date validUntil;
    private String password;

    public Integer getTransferId() {
        return transferId;
    }

    public void setTransferId(Integer transferId) {
        this.transferId = transferId;
    }

    public String getTransactionKey() {
        return transactionKey;
    }

    public void setTransactionKey(String transactionKey) {
        this.transactionKey = transactionKey;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Money getAmount() {
        return amount;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public boolean isCancelable() {
        return cancelable;
    }

    public void setCancelable(boolean cancelable) {
        this.cancelable = cancelable;
    }

    public OtherParty getOtherParty() {
        return otherParty;
    }

    public void setOtherParty(OtherParty otherParty) {
        this.otherParty = otherParty;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

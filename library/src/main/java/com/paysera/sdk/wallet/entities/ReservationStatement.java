package com.paysera.sdk.wallet.entities;

import com.google.gson.annotations.JsonAdapter;
import com.paysera.sdk.wallet.adapters.DateUnixTimestampSecondsAdapter;
import com.paysera.sdk.wallet.adapters.MoneyDecimalAdapter;
import org.joda.money.Money;
import java.util.Date;

public class ReservationStatement {

    @JsonAdapter(MoneyDecimalAdapter.class)
    private Money amount;
    @JsonAdapter(DateUnixTimestampSecondsAdapter.class)
    private Date date;
    private String details;
    private String type;
    private Integer transferId;
    private OtherParty otherParty;
    private String referenceNumber;
    private String direction;

    public Money getAmount() {
        return amount;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public Integer getTransferId() {
        return transferId;
    }

    public void setTransferId(Integer transferId) {
        this.transferId = transferId;
    }

    public OtherParty getOtherParty() {
        return otherParty;
    }

    public void setOtherParty(OtherParty otherParty) {
        this.otherParty = otherParty;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}

package com.paysera.sdk.wallet.entities.transfer;

import com.google.gson.annotations.JsonAdapter;
import java.util.Date;
import java.util.List;

import com.paysera.sdk.wallet.adapters.DateUnixTimestampSecondsAdapter;
import com.paysera.sdk.wallet.adapters.MoneyAdapter;
import com.paysera.sdk.wallet.entities.notification.TransferNotification;
import com.paysera.sdk.wallet.serializers.TransferNotificationAdapter;
import org.joda.money.Money;

public class Transfer {
    public static final String URGENCY_STANDARD = "standard";
    public static final String URGENCY_URGENT = "urgent";

    private String id;
    private String status;
    @JsonAdapter(MoneyAdapter.class)
    private Money amount;
    private TransferBeneficiary beneficiary;
    private TransferPayer payer;
    private TransferPurpose purpose;
    private Boolean cancelable;
    private TransferInitiator initiator;
    @JsonAdapter(DateUnixTimestampSecondsAdapter.class)
    private Date createdAt;
    @JsonAdapter(DateUnixTimestampSecondsAdapter.class)
    private Date performAt;
    @JsonAdapter(DateUnixTimestampSecondsAdapter.class)
    private Date performedAt;
    private Boolean autoCurrencyConvert;
    private Boolean autoChargeRelatedCard;
    private String urgency;
    @JsonAdapter(MoneyAdapter.class)
    private Money outCommission;
    private FailureStatus failureStatus;
    private Password password;
    @JsonAdapter(TransferNotificationAdapter.class)
    private List<TransferNotification> notifications;
    private Boolean allowedToCancel;
    private String chargeType;

    public List<TransferNotification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<TransferNotification> notifications) {
        this.notifications = notifications;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Money getAmount() {
        return amount;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }

    public TransferBeneficiary getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(TransferBeneficiary beneficiary) {
        this.beneficiary = beneficiary;
    }

    public TransferPayer getPayer() {
        return payer;
    }

    public void setPayer(TransferPayer payer) {
        this.payer = payer;
    }

    public Boolean getCancelable() {
        return cancelable;
    }

    public void setCancelable(Boolean cancelable) {
        this.cancelable = cancelable;
    }

    public String getUrgency() {
        return urgency;
    }

    public void setUrgency(String urgency) {
        this.urgency = urgency;
    }

    public TransferPurpose getPurpose() {
        return purpose;
    }

    public void setPurpose(TransferPurpose purpose) {
        this.purpose = purpose;
    }

    public TransferInitiator getInitiator() {
        return initiator;
    }

    public void setInitiator(TransferInitiator initiator) {
        this.initiator = initiator;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getPerformAt() {
        return performAt;
    }

    public void setPerformAt(Date performAt) {
        this.performAt = performAt;
    }

    public Date getPerformedAt() {
        return performedAt;
    }

    public void setPerformedAt(Date performedAt) {
        this.performedAt = performedAt;
    }

    public Boolean getAutoCurrencyConvert() {
        return autoCurrencyConvert;
    }

    public void setAutoCurrencyConvert(Boolean autoCurrencyConvert) {
        this.autoCurrencyConvert = autoCurrencyConvert;
    }

    public Boolean getAutoChargeRelatedCard() {
        return autoChargeRelatedCard;
    }

    public void setAutoChargeRelatedCard(Boolean autoChargeRelatedCard) {
        this.autoChargeRelatedCard = autoChargeRelatedCard;
    }

    public Money getOutCommission() {
        return outCommission;
    }

    public void setOutCommission(Money outCommission) {
        this.outCommission = outCommission;
    }

    public FailureStatus getFailureStatus() {
        return failureStatus;
    }

    public void setFailureStatus(FailureStatus failureStatus) {
        this.failureStatus = failureStatus;
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public Boolean getAllowedToCancel() {
        return allowedToCancel;
    }

    public void setAllowedToCancel(Boolean allowedToCancel) {
        this.allowedToCancel = allowedToCancel;
    }

    public String getChargeType() {
        return chargeType;
    }

    public void setChargeType(String chargeType) {
        this.chargeType = chargeType;
    }
}

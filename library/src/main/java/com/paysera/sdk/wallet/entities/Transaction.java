package com.paysera.sdk.wallet.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vytautas Gimbutas <v.gimbutas@evp.lt>
 */
public class Transaction {

    private String transactionKey;
    private Boolean validForPaymentCardDebit;
    private List<Payment> payments = new ArrayList<>();
    private Project project;
    private boolean autoConfirm;
    private Integer locationId;
    private Integer projectId;
    private String status;

    public String getStatus() {

        return status;
    }

    public Transaction setStatus(String status) {
        this.status = status;
        return this;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public Transaction setLocationId(Integer locationId) {
        this.locationId = locationId;
        return this;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public Transaction setProjectId(Integer projectId) {
        this.projectId = projectId;
        return this;
    }

    public Transaction setAutoConfirm(boolean autoConfirm) {
        this.autoConfirm = autoConfirm;
        return this;
    }

    public boolean isAutoConfirm() {
        return autoConfirm;
    }

    public Transaction setPayments(List<Payment> payments) {
        this.payments.clear();
        this.payments.addAll(payments);
        return this;
    }

    public String getTransactionKey() {
        return transactionKey;
    }

    public Transaction setTransactionKey(String transactionKey) {
        this.transactionKey = transactionKey;
        return this;
    }

    public List<Payment> getPayments() {
        return this.payments;
    }

    public Transaction addPayment(Payment payment) {
        this.payments.add(payment);
        return this;
    }

    public Project getProject() {
        return project;
    }

    public Transaction setProject(Project project) {
        this.project = project;
        return this;
    }

    public Boolean getValidForPaymentCardDebit() {
        return validForPaymentCardDebit;
    }

    public void setValidForPaymentCardDebit(Boolean validForPaymentCardDebit) {
        this.validForPaymentCardDebit = validForPaymentCardDebit;
    }
}

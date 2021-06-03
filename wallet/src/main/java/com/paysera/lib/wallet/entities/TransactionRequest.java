package com.paysera.lib.wallet.entities;

import com.google.gson.annotations.JsonAdapter;
import com.paysera.lib.wallet.adapters.DateUnixTimestampSecondsAdapter;

import java.util.Date;

public class TransactionRequest {
    private Integer id;
    private Integer userId;
    private String email;
    private String phone;
    private Integer initiatorId;
    @JsonAdapter(DateUnixTimestampSecondsAdapter.class)
    private Date createdAt;
    private transient String transactionKey;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getInitiatorId() {
        return initiatorId;
    }

    public void setInitiatorId(Integer initiatorId) {
        this.initiatorId = initiatorId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getTransactionKey() {
        return transactionKey;
    }

    public void setTransactionKey(String transactionKey) {
        this.transactionKey = transactionKey;
    }
}
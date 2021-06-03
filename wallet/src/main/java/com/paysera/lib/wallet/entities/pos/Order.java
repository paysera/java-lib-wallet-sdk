package com.paysera.lib.wallet.entities.pos;

import com.paysera.lib.wallet.entities.Transaction;

public class Order {

    public static final String STATUS_PENDING = "pending";
    public static final String STATUS_RESERVED = "reserved";
    public static final String STATUS_CONFIRMED = "confirmed";
    public static final String STATUS_CLOSED = "closed";

    private Integer id;
    private String transactionKey;
    private String status;
    private String userId;
    private Transaction transaction;

    public Integer getId() {
        return id;
    }

    public String getTransactionKey() {
        return transactionKey;
    }

    public String getStatus() {
        return status;
    }

    public String getUserId() {
        return userId;
    }

    public Transaction getTransaction() {
        return transaction;
    }
}
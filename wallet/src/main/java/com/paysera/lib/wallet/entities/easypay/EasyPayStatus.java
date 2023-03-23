package com.paysera.lib.wallet.entities.easypay;

import com.google.gson.annotations.SerializedName;

public enum EasyPayStatus {
    @SerializedName("new")
    NEW,
    @SerializedName("created")
    CREATED,
    @SerializedName("failed")
    FAILED,
    @SerializedName("cancelled")
    CANCELLED,
    @SerializedName("done")
    DONE,
    @SerializedName("expired")
    EXPIRED,
    @SerializedName("processing_cancellation")
    PROCESSING_CANCELLATION,
    @SerializedName("cancellation_denied")
    CANCELLATION_DENIED;
}
package com.paysera.lib.wallet.entities.easypay;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class EasyPayMarkUpdatedTransfersAsSeen {
    @SerializedName("epay_transaction_ids")
    private List<Long> epayTransactionIds;

    public List<Long> getEpayTransactionIds() {
        return epayTransactionIds;
    }

    public void setEpayTransactionIds(List<Long> epayTransactionIds) {
        this.epayTransactionIds = epayTransactionIds;
    }
}
package com.paysera.lib.wallet.entities.easypay;

import java.util.List;

public class EasyPayMarkUpdatedTransfersAsSeen {
    private List<Long> epayTransactionIds;

    public List<Long> getEpayTransactionIds() {
        return epayTransactionIds;
    }

    public void setEpayTransactionIds(List<Long> epayTransactionIds) {
        this.epayTransactionIds = epayTransactionIds;
    }
}
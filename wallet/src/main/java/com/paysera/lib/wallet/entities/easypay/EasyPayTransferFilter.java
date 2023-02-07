package com.paysera.lib.wallet.entities.easypay;

import com.paysera.lib.wallet.filters.BaseFilter;

public class EasyPayTransferFilter extends BaseFilter {
    private String status;
    private Integer beneficiaryUserId;
    private Integer payerWalletId;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getBeneficiaryUserId() {
        return beneficiaryUserId;
    }

    public void setBeneficiaryUserId(Integer beneficiaryUserId) {
        this.beneficiaryUserId = beneficiaryUserId;
    }

    public Integer getPayerWalletId() {
        return payerWalletId;
    }

    public void setPayerWalletId(Integer payerWalletId) {
        this.payerWalletId = payerWalletId;
    }
}
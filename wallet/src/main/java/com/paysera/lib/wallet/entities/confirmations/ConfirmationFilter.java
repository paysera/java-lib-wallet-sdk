package com.paysera.lib.wallet.entities.confirmations;

import com.paysera.lib.wallet.filters.BaseFilter;

public class ConfirmationFilter extends BaseFilter {

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

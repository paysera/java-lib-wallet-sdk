package com.paysera.lib.wallet.filters;

import java.util.List;

public class TransfersFilter extends BaseFilter {

    private List<String> statuses;
    private String creditAccountNumber;

    public List<String> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<String> statuses) {
        this.statuses = statuses;
    }

    public String getCreditAccountNumber() {
        return creditAccountNumber;
    }

    public void setCreditAccountNumber(String creditAccountNumber) {
        this.creditAccountNumber = creditAccountNumber;
    }
}
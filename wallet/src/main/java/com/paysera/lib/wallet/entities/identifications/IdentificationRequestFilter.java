package com.paysera.lib.wallet.entities.identifications;

import com.paysera.lib.wallet.filters.BaseFilter;

import java.util.List;

public class IdentificationRequestFilter extends BaseFilter {
    private List<String> statuses;

    public List<String> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<String> statuses) {
        this.statuses = statuses;
    }
}
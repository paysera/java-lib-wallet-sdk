package com.paysera.sdk.wallet.entities.confirmations;

import java.util.List;

public class Confirmation {
    private List<ConfirmationItem> items;

    public List<ConfirmationItem> getItems() {
        return items;
    }

    public void setItems(List<ConfirmationItem> items) {
        this.items = items;
    }
}
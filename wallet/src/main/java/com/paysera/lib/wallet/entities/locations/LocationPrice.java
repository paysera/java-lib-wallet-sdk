package com.paysera.lib.wallet.entities.locations;

import org.joda.money.Money;

public class LocationPrice {
    private String type;
    private String title;
    private Money price;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Money getPrice() {
        return price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }
}

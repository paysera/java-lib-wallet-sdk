package com.paysera.lib.wallet.entities;

import com.paysera.lib.wallet.helpers.MoneyHelper;
import org.joda.money.Money;

public class PaymentItem {
    private String title;
    private Long price;
    private String currency;
    private Integer quantity;
    private Long totalPrice;

    public String getTitle() {
        return title;
    }

    public Long getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public Money getTotalPriceMoney() {
        return MoneyHelper.createFromCents(this.currency, this.totalPrice);
    }

    public Money getPriceMoney() {
        return MoneyHelper.createFromCents(this.currency, this.price);
    }
}
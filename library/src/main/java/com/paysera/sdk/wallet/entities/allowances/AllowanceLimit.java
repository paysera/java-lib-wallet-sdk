package com.paysera.sdk.wallet.entities.allowances;

import com.google.gson.annotations.SerializedName;
import com.paysera.sdk.wallet.helpers.MoneyHelper;
import org.joda.money.Money;

public class AllowanceLimit {

    private Integer maxPrice;
    @SerializedName("time")
    private Long seconds;

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public Long getSeconds() {
        return seconds;
    }

    public Money getMaxPrice(String currency) {
        if (maxPrice != null) {
            return MoneyHelper.createFromCents(currency, this.maxPrice);
        } else {
            return null;
        }
    }
}

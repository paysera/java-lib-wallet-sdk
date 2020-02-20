package com.paysera.sdk.wallet.entities.allowances;

import com.paysera.sdk.wallet.helpers.MoneyHelper;
import org.joda.money.Money;

import java.util.List;

public class Allowance {

    private String description;
    private String currency;
    private Integer maxPrice;
    private AllowanceValid valid;
    private List<AllowanceLimit> limits;

    public String getDescription() {
        return description;
    }

    public String getCurrency() {
        return currency;
    }

    public AllowanceValid getValid() {
        return valid;
    }

    public List<AllowanceLimit> getLimits() {
        return limits;
    }

    public Money getMaxPrice() {
        if (maxPrice != null) {
            return MoneyHelper.createFromCents(this.currency, this.maxPrice);
        } else {
            return null;
        }
    }
}

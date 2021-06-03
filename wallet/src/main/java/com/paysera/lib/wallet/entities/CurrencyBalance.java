package com.paysera.lib.wallet.entities;

import com.paysera.lib.wallet.helpers.MoneyHelper;
import org.joda.money.Money;

public class CurrencyBalance {
    private Money money;
    private Money reserved;

    /* this workaround is required, because API does not include converted currency in the response */
    private Integer amountConverted;
    private Integer reservedAmountConverted;
    private String convertedCurrency;

    public CurrencyBalance(Money money, Money reserved) {
        this.money = money;
        this.reserved = reserved;
    }

    public Money getMoney() {
        return money;
    }

    public Money getReserved() {
        return reserved;
    }

    public Money getMoneyConverted() {
        if (this.convertedCurrency == null) {
            throw new RuntimeException("Conversion was not made");
        }

        return MoneyHelper.createFromCents(this.convertedCurrency, this.amountConverted);
    }

    public Money getReservedConverted() {
        if (this.convertedCurrency == null) {
            throw new RuntimeException("Conversion was not made");
        }

        return MoneyHelper.createFromCents(this.convertedCurrency, this.reservedAmountConverted);
    }

    public void setAmountConverted(Integer amountConverted) {
        this.amountConverted = amountConverted;
    }

    public void setReservedAmountConverted(Integer reservedAmountConverted) {
        this.reservedAmountConverted = reservedAmountConverted;
    }

    public void setConvertedCurrency(String convertedCurrency) {
        this.convertedCurrency = convertedCurrency;
    }
}
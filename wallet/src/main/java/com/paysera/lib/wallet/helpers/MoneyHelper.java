package com.paysera.lib.wallet.helpers;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.math.BigDecimal;

public class MoneyHelper {
    public static Money createFromCents(String currency, long cents) {
        BigDecimal amount = BigDecimal.valueOf(cents);
        return Money.of(
            CurrencyUnit.of(currency),
            amount.divide(BigDecimal.valueOf(100))
        );
    }

    public static Long toCents(Money money) {
        return money.getAmount().multiply(new BigDecimal(100)).longValue();
    }
}

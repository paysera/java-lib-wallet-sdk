package com.paysera.lib.wallet.adapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class MoneyAdapter extends TypeAdapter<Money> {

    private final RoundingMode roundingMode;

    public MoneyAdapter(RoundingMode roundingMode) {
        this.roundingMode = roundingMode;
    }

    public MoneyAdapter() {
        this.roundingMode = RoundingMode.HALF_UP;
    }

    @Override
    public void write(JsonWriter out, Money money) throws IOException {
        out.beginObject();
        out.name("amount").value(money.getAmount().toPlainString());
        out.name("currency").value(money.getCurrencyUnit().getCode());
        out.endObject();
    }

    @Override
    public Money read(JsonReader in) throws IOException {
        String amount = null;
        String currency = null;
        in.beginObject();
        while (in.hasNext()) {
            String nextName = in.nextName();
            if (nextName.equals("amount")) {
                amount = in.nextString();
            } else if (nextName.equals("currency")) {
                currency = in.nextString();
            }
        }
        in.endObject();
        return Money.of(CurrencyUnit.of(currency), new BigDecimal(amount), roundingMode);
    }
}
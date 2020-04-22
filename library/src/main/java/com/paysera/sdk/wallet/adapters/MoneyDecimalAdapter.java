package com.paysera.sdk.wallet.adapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class MoneyDecimalAdapter extends TypeAdapter<Money> {
    private final RoundingMode roundingMode;

    public MoneyDecimalAdapter(RoundingMode roundingMode) {
        this.roundingMode = roundingMode;
    }

    public MoneyDecimalAdapter() {
        this.roundingMode = RoundingMode.HALF_UP;
    }

    @Override
    public void write(JsonWriter out, Money money) throws IOException {
        out.beginObject();
        out.name("amount_decimal").value(money.getAmount().toPlainString());
        out.name("currency").value(money.getCurrencyUnit().getCode());
        out.endObject();
    }

    @Override
    public Money read(JsonReader in) throws IOException {
        String amount = null;
        String currency = null;

        in.skipValue();

        while (in.hasNext()) {
            if (amount != null && currency != null) {
                break;
            }

            String nextName = in.nextName();
            if (nextName.equals("amount_decimal")) {
                amount = in.nextString();
            } else if (nextName.equals("currency")) {
                currency = in.nextString();
            } else {
                in.skipValue();
            }
        }
        return Money.of(CurrencyUnit.of(currency), new BigDecimal(amount), roundingMode);
    }
}
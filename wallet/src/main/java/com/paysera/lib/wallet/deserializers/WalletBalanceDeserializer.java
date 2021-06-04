package com.paysera.lib.wallet.deserializers;

import com.google.gson.*;
import com.paysera.lib.wallet.entities.CurrencyBalance;
import com.paysera.lib.wallet.entities.WalletBalance;
import com.paysera.lib.wallet.helpers.MoneyHelper;
import org.joda.money.Money;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;

public class WalletBalanceDeserializer implements JsonDeserializer<WalletBalance> {
    private MoneyHelper moneyHelper;

    public WalletBalanceDeserializer(MoneyHelper moneyHelper) {
        this.moneyHelper = moneyHelper;
    }

    @Override
    public WalletBalance deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        WalletBalance walletBalance = new WalletBalance();

        Set<Map.Entry<String, JsonElement>> entries = ((JsonObject) jsonElement).entrySet();

        for (Map.Entry<String, JsonElement> entry : entries) {
            String currency = entry.getKey();
            JsonObject balance = entry.getValue().getAsJsonObject();

            Money money = moneyHelper.createFromCents(currency, balance.get("at_disposal").getAsInt());
            Money reserved = moneyHelper.createFromCents(currency, balance.get("reserved").getAsInt());

            CurrencyBalance currencyBalance = new CurrencyBalance(money, reserved);

            if (balance.has("converted")) {
                JsonObject convertedNode = balance.get("converted").getAsJsonObject();
                currencyBalance.setAmountConverted(convertedNode.get("at_disposal").getAsInt());
                currencyBalance.setReservedAmountConverted(convertedNode.get("reserved").getAsInt());
            }
            walletBalance.addCurrencyBalance(currencyBalance);
        }
        return walletBalance;
    }
}
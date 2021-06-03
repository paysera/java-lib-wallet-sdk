package com.paysera.lib.wallet.factories;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.paysera.lib.wallet.adapters.ClientAdapter;
import com.paysera.lib.wallet.adapters.MetadataAwareResponseDeserializer;
import com.paysera.lib.wallet.deserializers.WalletBalanceDeserializer;
import com.paysera.lib.wallet.entities.MetadataAwareResponse;
import com.paysera.lib.wallet.entities.WalletBalance;
import com.paysera.lib.wallet.entities.client.Client;
import com.paysera.lib.wallet.helpers.MoneyHelper;

public class GsonFactory {

    private GsonFactory() {}

    public static Gson createGson() {
        MoneyHelper moneyHelper = new MoneyHelper();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        gsonBuilder.registerTypeAdapter(WalletBalance.class, new WalletBalanceDeserializer(moneyHelper));
        gsonBuilder.registerTypeAdapter(Client.class, new ClientAdapter());
        gsonBuilder.registerTypeAdapter(MetadataAwareResponse.class, new MetadataAwareResponseDeserializer());
        return gsonBuilder.create();
    }
}
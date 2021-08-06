package com.paysera.lib.wallet.adapters;

import com.google.gson.*;
import com.paysera.lib.wallet.entities.client.ApplicationClient;
import com.paysera.lib.wallet.entities.client.Client;

import java.lang.reflect.Type;

public class ClientAdapter implements JsonSerializer<Client>, JsonDeserializer<Client> {


    @Override
    public JsonElement serialize(Client client, Type type, JsonSerializationContext context) {
        JsonElement serializedClient = context.serialize(client);
        serializedClient.getAsJsonObject().addProperty("type", client.getType());
        return serializedClient;
    }

    @Override
    public Client deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        if (jsonObject.get("type").getAsString().equals("app_client")) {
            return context.deserialize(json, ApplicationClient.class);
        } else {
            throw new IllegalArgumentException("Invalid type instance passed");
        }
    }
}
package com.paysera.lib.wallet.serializers;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.paysera.lib.wallet.entities.notification.TransferNotification;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TransferNotificationAdapter extends TypeAdapter<List<TransferNotification>> {

    @Override
    public void write(JsonWriter out, List<TransferNotification> value) throws IOException {
        out.beginObject();
        for (TransferNotification item : value) {
            out.name(item.getType());
            out.beginObject();
            out.name("email").value(item.getEmail());
            out.name("locale").value(item.getLocale());
            out.endObject();
        }
        out.endObject();
    }

    @Override
    public List<TransferNotification> read(JsonReader in) throws IOException {
        List<TransferNotification> transferNotifications = new ArrayList<>();
        in.beginObject();
        while (in.hasNext()) {
            TransferNotification transferNotification = new TransferNotification();
            transferNotification.setType(in.nextName());
            in.beginObject();
            if (in.nextName().equals("email")) {
                transferNotification.setEmail(in.nextString());
            }
            if (in.nextName().equals("locale")) {
                transferNotification.setLocale(in.nextString());
            }
            in.endObject();
            transferNotifications.add(transferNotification);
        }
        in.endObject();
        return transferNotifications;
    }
}
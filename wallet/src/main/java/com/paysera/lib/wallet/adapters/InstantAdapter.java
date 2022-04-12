package com.paysera.lib.wallet.adapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.Instant;

public class InstantAdapter extends TypeAdapter<Instant> {
    @Override
    public void write(JsonWriter out, Instant value) throws IOException {
        if (value != null) {
            out.value(value.getEpochSecond());
        }
    }

    @Override
    public Instant read(JsonReader in) throws IOException {
        long epochSecond = in.nextLong();
        return Instant.ofEpochSecond(epochSecond);
    }
}
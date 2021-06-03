package com.paysera.lib.wallet.adapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.Date;

public class DateUnixTimestampSecondsAdapter extends TypeAdapter<Date> {

    @Override
    public void write(JsonWriter out, Date date) throws IOException {
        out.value(date.getTime() / 1000L);
    }

    @Override
    public Date read(JsonReader reader) throws IOException {
        return new Date(reader.nextLong() * 1000);
    }
}
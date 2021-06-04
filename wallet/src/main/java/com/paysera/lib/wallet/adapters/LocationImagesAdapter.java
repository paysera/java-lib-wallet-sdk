package com.paysera.lib.wallet.adapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.paysera.lib.wallet.entities.locations.LocationImages;

import java.io.IOException;

public class LocationImagesAdapter extends TypeAdapter<LocationImages> {

    @Override
    public void write(JsonWriter out, LocationImages value) throws IOException {
    }

    @Override
    public LocationImages read(JsonReader in) throws IOException {
        String openUri = null;
        String closedUri = null;
        in.beginObject();
        while (in.hasNext()) {
            String nextName = in.nextName();
            if (nextName.equals("pin_open")) {
                openUri = in.nextString();
            }
            if (nextName.equals("pin_closed")) {
                closedUri = in.nextString();
            }
        }
        in.endObject();

        LocationImages locationImages = new LocationImages();
        locationImages.setPinOpenUri(openUri);
        locationImages.setPinClosedUri(closedUri);
        return locationImages;
    }
}
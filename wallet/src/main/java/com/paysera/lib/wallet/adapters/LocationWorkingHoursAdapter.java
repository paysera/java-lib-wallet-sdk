package com.paysera.lib.wallet.adapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.paysera.lib.wallet.entities.locations.LocationWorkingHour;
import com.paysera.lib.wallet.entities.locations.LocationWorkingHoursDay;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LocationWorkingHoursAdapter extends TypeAdapter<List<LocationWorkingHour>> {

    @Override
    public void write(JsonWriter out, List<LocationWorkingHour> value) throws IOException {
    }

    @Override
    public List<LocationWorkingHour> read(JsonReader in) throws IOException {
        List<LocationWorkingHour> workingHours = new ArrayList<>();
        in.beginObject();
        while (in.hasNext()) {
            String day = in.nextName();
            LocationWorkingHour locationWorkingHour = new LocationWorkingHour();
            locationWorkingHour.setDay(LocationWorkingHoursDay.valueOf(day));
            in.beginObject();
            String openingTime = null;
            String closingTime = null;
            while (in.hasNext()) {
                String nextName = in.nextName();
                if (nextName.equals("opening_time")) {
                    openingTime = in.nextString();
                }
                if (nextName.equals("closing_time")) {
                    closingTime = in.nextString();
                }
            }
            in.endObject();
            locationWorkingHour.setOpeningTime(openingTime);
            locationWorkingHour.setClosingTime(closingTime);
            workingHours.add(locationWorkingHour);
        }
        in.endObject();
        return workingHours;
    }
}

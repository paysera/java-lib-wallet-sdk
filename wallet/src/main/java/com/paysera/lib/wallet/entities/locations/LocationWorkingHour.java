package com.paysera.lib.wallet.entities.locations;

public class LocationWorkingHour {
    private LocationWorkingHoursDay day;
    private String openingTime;
    private String closingTime;

    public LocationWorkingHoursDay getDay() {
        return day;
    }

    public void setDay(LocationWorkingHoursDay day) {
        this.day = day;
    }

    public String getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(String openingTime) {
        this.openingTime = openingTime;
    }

    public String getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(String closingTime) {
        this.closingTime = closingTime;
    }
}

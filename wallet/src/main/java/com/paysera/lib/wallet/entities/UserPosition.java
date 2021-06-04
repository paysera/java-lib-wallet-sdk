package com.paysera.lib.wallet.entities;

public class UserPosition {
    public static final String TYPE_HIDDEN = "hidden";
    public static final String TYPE_MERCHANT = "merchant";

    private float lat;
    private float lng;
    private String type;

    public UserPosition(float lat, float lng, String type) {
        this.lat = lat;
        this.lng = lng;
        this.type = type;
    }

    public UserPosition() {

    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
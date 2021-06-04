package com.paysera.lib.wallet.entities.locations;
import com.google.gson.annotations.SerializedName;

public class LocationImages {
    @SerializedName("pin_open")
    private String pinOpenUri;
    @SerializedName("pin_closed")
    private String pinClosedUri;

    public String getPinOpenUri() {
        return pinOpenUri;
    }

    public void setPinOpenUri(String pinOpenUri) {
        this.pinOpenUri = pinOpenUri;
    }

    public String getPinClosedUri() {
        return pinClosedUri;
    }

    public void setPinClosedUri(String pinClosedUri) {
        this.pinClosedUri = pinClosedUri;
    }
}

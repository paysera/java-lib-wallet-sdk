package com.paysera.lib.wallet.entities.locations;

public class LocationServices {
    private LocationService cashIn;
    private LocationService cashOut;
    private LocationService identification;
    private LocationService pay;

    public LocationService getCashIn() {
        return cashIn;
    }

    public void setCashIn(LocationService cashIn) {
        this.cashIn = cashIn;
    }

    public LocationService getCashOut() {
        return cashOut;
    }

    public void setCashOut(LocationService cashOut) {
        this.cashOut = cashOut;
    }

    public LocationService getIdentification() {
        return identification;
    }

    public void setIdentification(LocationService identification) {
        this.identification = identification;
    }

    public LocationService getPay() {
        return pay;
    }

    public void setPay(LocationService pay) {
        this.pay = pay;
    }
}

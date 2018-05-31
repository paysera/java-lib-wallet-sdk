package com.paysera.sdk.wallet.entities.requests;

public class ReserveTransactionRequest {

    private String reservationCode;

    public ReserveTransactionRequest(String reservationCode) {
        this.reservationCode = reservationCode;
    }

    public String getReservationCode() {
        return reservationCode;
    }

    public void setReservationCode(String reservationCode) {
        this.reservationCode = reservationCode;
    }
}

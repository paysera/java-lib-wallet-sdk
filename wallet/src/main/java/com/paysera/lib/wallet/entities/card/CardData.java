package com.paysera.lib.wallet.entities.card;

import java.util.Date;

/**
 * @author Vytautas Gimbutas <v.gimbutas@evp.lt>
 */
public class CardData {
    protected String number;
    protected String holder;
    protected String type;
    protected String country;
    protected Date expiresAt;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
    }
}

package com.paysera.lib.wallet.entities.card;

/**
 * @author Vytautas Gimbutas <v.gimbutas@evp.lt>
 */
public class CardAccount {
    protected String number;
    protected Integer order;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}

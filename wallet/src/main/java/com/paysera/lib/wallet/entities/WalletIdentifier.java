package com.paysera.lib.wallet.entities;

/**
 * @author Vytautas Gimbutas <v.gimbutas@evp.lt>
 */
public class WalletIdentifier {
    private Integer id;
    private String email;
    private String phone;

    // Only for response. Used with extended fields.
    private Wallet wallet;

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isEmpty() {
        return this.id == null
            && this.email == null
            && this.phone == null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
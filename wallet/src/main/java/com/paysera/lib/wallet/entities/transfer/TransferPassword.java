package com.paysera.lib.wallet.entities.transfer;

public class TransferPassword {

    private String password;

    public TransferPassword() { }

    public TransferPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
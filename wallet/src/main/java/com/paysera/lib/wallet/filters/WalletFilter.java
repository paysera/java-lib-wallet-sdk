package com.paysera.lib.wallet.filters;

import com.paysera.lib.wallet.entities.AccountInformation;
import com.paysera.lib.wallet.entities.Wallet;
import com.paysera.lib.wallet.entities.WalletIdentifier;

public class WalletFilter {
    private String email;
    private String phone;
    private String accountNumber;
    private Integer userId;

    public static WalletFilter createFromWalletIdentifier(WalletIdentifier walletIdentifier) {
        WalletFilter filter = new WalletFilter();
        filter.setEmail(walletIdentifier.getEmail());
        filter.setPhone(walletIdentifier.getPhone());
        Wallet wallet = walletIdentifier.getWallet();
        if (wallet != null) {
            AccountInformation accountInformation = wallet.getAccountInformation();
            if (accountInformation != null) {
                filter.setAccountNumber(accountInformation.getAccountNumber());
            }
        }
        return filter;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
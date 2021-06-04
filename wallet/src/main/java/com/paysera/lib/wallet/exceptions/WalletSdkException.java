package com.paysera.lib.wallet.exceptions;

public class WalletSdkException extends Exception {
    public WalletSdkException() {
        super();
    }

    public WalletSdkException(String detailMessage) {
        super(detailMessage);
    }

    public WalletSdkException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public WalletSdkException(Throwable throwable) {
        super(throwable);
    }
}
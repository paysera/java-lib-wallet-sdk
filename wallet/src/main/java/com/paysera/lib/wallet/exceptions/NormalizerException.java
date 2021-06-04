package com.paysera.lib.wallet.exceptions;

public class NormalizerException extends WalletSdkException {
    public NormalizerException() {
        super();
    }

    public NormalizerException(String detailMessage) {
        super(detailMessage);
    }

    public NormalizerException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public NormalizerException(Throwable throwable) {
        super(throwable);
    }
}
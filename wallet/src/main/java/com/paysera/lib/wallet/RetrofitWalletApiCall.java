package com.paysera.lib.wallet;

import bolts.TaskCompletionSource;

public class RetrofitWalletApiCall extends WalletApiCall {
    private final Object call;

    public RetrofitWalletApiCall(TaskCompletionSource taskCompletionSource, Object call) {
        setTaskCompletionSource(taskCompletionSource);
        this.call = call;
    }

    public Object getCall() {
        return call;
    }
}
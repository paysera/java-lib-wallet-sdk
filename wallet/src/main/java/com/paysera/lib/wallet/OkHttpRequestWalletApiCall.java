package com.paysera.lib.wallet;

import bolts.TaskCompletionSource;
import okhttp3.Request;

public class OkHttpRequestWalletApiCall extends WalletApiCall {
    private Request request;

    public OkHttpRequestWalletApiCall(TaskCompletionSource taskCompletionSource, Request request) {
        setTaskCompletionSource(taskCompletionSource);
        this.request = request;
    }

    public Request getRequest() {
        return request;
    }
}
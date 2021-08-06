package com.paysera.lib.wallet.exceptions;

import okhttp3.Request;

public class RequestFailedException extends WebClientException {

    private Request request;

    public RequestFailedException() {
        super();
    }

    public RequestFailedException(Request request, Throwable throwable) {
        super(throwable);
        this.request = request;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }
}
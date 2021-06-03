package com.paysera.lib.wallet;

import okhttp3.Headers;

public class WalletApiResponse {
    private Integer code;
    private String body;
    private Headers headers;

    public WalletApiResponse(Integer code, String body, Headers headers) {
        this.code = code;
        this.body = body;
        this.headers = headers;
    }

    public Integer getCode() {
        return code;
    }

    public String getBody() {
        return body;
    }

    public Headers getHeaders() {
        return headers;
    }
}
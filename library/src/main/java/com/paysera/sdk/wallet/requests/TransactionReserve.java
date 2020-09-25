package com.paysera.sdk.wallet.requests;

import com.paysera.sdk.wallet.base.BaseRequest;
import com.paysera.sdk.wallet.clients.RefreshingWalletAsyncClient;
import org.json.JSONObject;

import java.util.Locale;

public class TransactionReserve extends BaseRequest {

    private final String key;

    public TransactionReserve(RefreshingWalletAsyncClient refreshingWalletAsyncClient, String key, JSONObject json) {
        super(refreshingWalletAsyncClient);
        this.key = key;
        authorizedRequest.jsonBody(json);
    }

    @Override
    protected BaseRequest.HttpMethod getMethod() {
        return BaseRequest.HttpMethod.PUT;
    }

    @Override
    protected String getUrl() {
        return String.format(Locale.US, "transaction/%s/reserve", key);
    }
}
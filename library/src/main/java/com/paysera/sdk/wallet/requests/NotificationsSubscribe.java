package com.paysera.sdk.wallet.requests;

import com.paysera.sdk.wallet.base.BaseRequest;
import com.paysera.sdk.wallet.clients.RefreshingWalletAsyncClient;
import org.json.JSONObject;

public class NotificationsSubscribe extends BaseRequest {

    public NotificationsSubscribe(RefreshingWalletAsyncClient refreshingWalletAsyncClient, JSONObject json) {
        super(refreshingWalletAsyncClient);
        authorizedRequest.jsonBody(json);
    }

    @Override
    protected BaseRequest.HttpMethod getMethod() {
        return BaseRequest.HttpMethod.POST;
    }

    @Override
    protected String getUrl() {
        return "subscriber";
    }
}

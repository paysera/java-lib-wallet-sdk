package com.paysera.sdk.wallet.requests;

import com.paysera.sdk.wallet.base.BaseRequest;
import com.paysera.sdk.wallet.clients.RefreshingWalletAsyncClient;
import org.json.JSONObject;

import java.util.Locale;

public class NotificationsModify extends BaseRequest {
    protected final Integer id;

    public NotificationsModify(RefreshingWalletAsyncClient refreshingWalletAsyncClient, Integer id, JSONObject json) {
        super(refreshingWalletAsyncClient);
        this.id = id;
        authorizedRequest.jsonBody(json);
    }

    @Override
    protected BaseRequest.HttpMethod getMethod() {
        return BaseRequest.HttpMethod.PUT;
    }

    @Override
    protected String getUrl() {
        return String.format(Locale.US, "subscriber/%d", id);
    }
}

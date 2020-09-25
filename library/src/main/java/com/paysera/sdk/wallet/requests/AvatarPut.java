package com.paysera.sdk.wallet.requests;

import com.paysera.sdk.wallet.base.BaseRequest;
import com.paysera.sdk.wallet.clients.RefreshingWalletAsyncClient;

public class AvatarPut extends BaseRequest {

    public AvatarPut(RefreshingWalletAsyncClient refreshingWalletAsyncClient, byte[] content) {
        super(refreshingWalletAsyncClient);
        authorizedRequest.binaryBody(content);
    }

    @Override
    protected BaseRequest.HttpMethod getMethod() {
        return BaseRequest.HttpMethod.PUT;
    }

    @Override
    protected String getUrl() {
        return "user/me/avatar";
    }
}

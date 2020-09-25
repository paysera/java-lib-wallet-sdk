package com.paysera.sdk.wallet.requests;

import com.paysera.sdk.wallet.base.BaseRequest;
import com.paysera.sdk.wallet.clients.RefreshingWalletAsyncClient;

import java.util.Locale;

public class CodeGenerationValid extends BaseRequest {

    final String id;

    public CodeGenerationValid(RefreshingWalletAsyncClient refreshingWalletAsyncClient, long id) {
        super(refreshingWalletAsyncClient);
        this.id = String.valueOf(id);
    }

    @Override
    protected BaseRequest.HttpMethod getMethod() {
        return BaseRequest.HttpMethod.GET;
    }

    @Override
    protected String getUrl() {
        return String.format(Locale.US, "generator/%s", id);
    }
}

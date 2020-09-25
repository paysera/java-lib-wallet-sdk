package com.paysera.sdk.wallet.base;

import com.paysera.sdk.wallet.clients.RefreshingWalletAsyncClient;
import com.paysera.sdk.wallet.handlers.AlwaysAsyncJsonHttpResponseHandler;

public abstract class BaseRequest {
    protected AuthorizedRequest authorizedRequest;

    protected enum HttpMethod {GET, POST, PUT, DELETE}

    public BaseRequest(RefreshingWalletAsyncClient refreshingWalletAsyncClient) {
        authorizedRequest = new AuthorizedRequest(refreshingWalletAsyncClient);
    }

    public void call(final AlwaysAsyncJsonHttpResponseHandler handler) {
        try {
            authorizedRequest.userCall(getUrl(), getMethod(), handler);
        } catch (Exception e) {
                /*Timber.d(e);
                Timber.e("Api: BaseRequest call");*/
        }
    }

    /**
     * Set when making calls using client tokens. I.e Logging in, refreshing token, reseting passwords, etc.
     *
     * @return
     */

    public AuthorizedRequest getRequest() {
        return this.authorizedRequest;
    }

    protected abstract HttpMethod getMethod();

    protected abstract String getUrl();

}
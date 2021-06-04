package com.paysera.lib.wallet;

public class Router {
    protected static String ENDPOINT_WALLET_API = "https://wallet-api.paysera.com/rest/v1/";
    protected static String ENDPOINT_OAUTH_API = "https://wallet-api.paysera.com/oauth/v1/";

    protected String walletApiEndpoint;
    protected String oAuthApiEndpoint;

    public Router() {
        this(ENDPOINT_WALLET_API, ENDPOINT_OAUTH_API);
    }

    public Router(String walletApiEndpoint, String oAuthApiEndpoint) {
        this.walletApiEndpoint = walletApiEndpoint;
        this.oAuthApiEndpoint = oAuthApiEndpoint;
    }

    public String getWalletApiEndpoint() {
        return walletApiEndpoint;
    }

    public String getoAuthApiEndpoint() {
        return oAuthApiEndpoint;
    }
}
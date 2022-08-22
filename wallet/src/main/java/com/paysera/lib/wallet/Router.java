package com.paysera.lib.wallet;

public class Router {
    protected static String ENDPOINT_WALLET_API = "https://wallet-api.paysera.com/rest/v1/";
    protected static String ENDPOINT_OAUTH_API = "https://wallet-api.paysera.com/oauth/v1/";
    protected static String ENDPOINT_PARTNER_OAUTH_API = "https://wallet.paysera.com/partner-oauth/v1/";
    protected static String ENDPOINT_PARTNER_TOKEN_API = "https://wallet.paysera.com/oauth/v1/";

    protected String walletApiEndpoint;
    protected String oAuthApiEndpoint;
    protected String partnerOAuthApiEndpoint;
    protected String partnerTokenApiEndpoint;

    public Router() {
        this(
            ENDPOINT_WALLET_API,
            ENDPOINT_OAUTH_API,
            ENDPOINT_PARTNER_OAUTH_API,
            ENDPOINT_PARTNER_TOKEN_API
        );
    }

    public Router(
        String walletApiEndpoint,
        String oAuthApiEndpoint,
        String partnerOAuthApiEndpoint,
        String partnerTokenApiEndpoint
    ) {
        this.walletApiEndpoint = walletApiEndpoint;
        this.oAuthApiEndpoint = oAuthApiEndpoint;
        this.partnerOAuthApiEndpoint = partnerOAuthApiEndpoint;
        this.partnerTokenApiEndpoint = partnerTokenApiEndpoint;
    }

    public String getWalletApiEndpoint() {
        return walletApiEndpoint;
    }

    public String getOAuthApiEndpoint() {
        return oAuthApiEndpoint;
    }

    public String getPartnerOAuthApiEndpoint() {
        return partnerOAuthApiEndpoint;
    }

    public String getPartnerTokenApiEndpoint() {
        return partnerTokenApiEndpoint;
    }
}
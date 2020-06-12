package com.paysera.sdk.wallet.clients;

import bolts.Task;
import com.paysera.sdk.wallet.ClientServerTimeSynchronizationConfiguration;
import com.paysera.sdk.wallet.entities.Credentials;
import com.paysera.sdk.wallet.enums.GrantType;
import com.paysera.sdk.wallet.helpers.StringHelper;
import com.paysera.sdk.wallet.helpers.OkHTTPQueryStringConverter;
import com.paysera.sdk.wallet.providers.TimestampProvider;
import java.util.List;
import retrofit2.Retrofit;

public class OAuthAsyncClient extends BaseAsyncClient {
    protected OAuthClient oAuthClient;

    public OAuthAsyncClient(
        OAuthClient oAuthClient,
        TimestampProvider timestampProvider,
        ClientServerTimeSynchronizationConfiguration clientServerTimeSynchronizationConfiguration,
        PublicWalletApiClient publicWalletApiClient,
        Retrofit retrofit,
        OkHTTPQueryStringConverter okHTTPQueryStringConverter
    ) {
        super(
            timestampProvider,
            clientServerTimeSynchronizationConfiguration,
            publicWalletApiClient,
            retrofit,
            okHTTPQueryStringConverter
        );
        this.oAuthClient = oAuthClient;
    }

    public Task<Credentials> refreshToken(String refreshToken, GrantType grantType, List<String> scope, String code) {

        return this.execute(this.oAuthClient.refreshToken(grantType.toString(), refreshToken,
            StringHelper.listToString(scope, " "), code));
    }

    public Task<Credentials> refreshToken(String refreshToken) {
        return this.execute(
            this.oAuthClient.refreshToken("refresh_token", refreshToken, null, null));
    }

    public Task<Credentials> activate(String accessToken) {
        return this.execute(this.oAuthClient.activate(accessToken));
    }

    public Task<Credentials> exchangeCredentialsForAccessToken(String username, String password,
        List<String> scope) {
        return this.execute(
            this.oAuthClient.exchangeCredentialsForAccessToken("password", username, password,
                StringHelper.listToString(scope, " ")));
    }

    public Task<Void> revokeAccessToken(String accessToken) {
        return this.execute(this.oAuthClient.revokeAccessToken(accessToken));
    }
}

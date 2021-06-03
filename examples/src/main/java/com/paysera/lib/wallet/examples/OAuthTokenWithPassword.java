package com.paysera.lib.wallet.examples;

import com.paysera.lib.wallet.*;
import com.paysera.lib.wallet.clients.OAuthAsyncClient;
import com.paysera.lib.wallet.clients.OAuthClient;
import com.paysera.lib.wallet.clients.PublicWalletApiClient;
import com.paysera.lib.wallet.entities.Credentials;
import com.paysera.lib.wallet.factories.HttpClientFactory;
import com.paysera.lib.wallet.factories.RetrofitFactory;
import com.paysera.lib.wallet.helpers.OkHTTPQueryStringConverter;
import com.paysera.lib.wallet.oauth.ScopeBuilder;
import com.paysera.lib.wallet.providers.TimestampProvider;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

public class OAuthTokenWithPassword {
    //TODO RequestSigner ir viską susijusią info į atskirą lib'ą perkelti

    //TODO Prieš realeasinant versiją sutvarkyti EXAMPLES
    //// todo: test with kotlin
    //// todo normalizerius - panaikinant call'ą panaikinti ir normalizer'į
    //TODO java-lib-mac-auth dar vienas todo: kaip sakėm, kad request-signer iškelti į atskirą lib'ą, tai taip pat galima iškelti retrofit į atskirą lib'ą
    //TODO java-lib-retrofit-ext
    //todo: wallet sdk proguard rules

    public static void main(String[] args) throws IOException {
        String userAgent = "Java wallet sdk library";

        final Credentials credentials = new Credentials();

        String username = "enter_me";
        String password = "enter_me";

        credentials.setMacKey("enter_me");
        credentials.setAccessToken("enter_me");
        credentials.setValidUntil(new Date(System.currentTimeMillis() + 3000000));

        TimestampProvider timestampProvider = new TimestampProvider();
        ClientServerTimeSynchronizationConfiguration
            clientServerTimeSynchronizationConfiguration = new ClientServerTimeSynchronizationConfiguration();
        clientServerTimeSynchronizationConfiguration.setEnabled(true);
        clientServerTimeSynchronizationConfiguration.setTimestampSynchronizedCallback(
            (serverTime, currentTime) -> {
            });
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHTTPQueryStringConverter okHTTPQueryStringConverter = new OkHTTPQueryStringConverter();
        RequestSigner requestSigner = new RequestSigner(
            new NonceGenerator(),
            new MacDigestGenerator(),
            okHTTPQueryStringConverter
        );
        RetrofitFactory retrofitFactory = new RetrofitFactory(new Router());

        HttpClientFactory httpClientFactory =
            new HttpClientFactory(
                requestSigner,
                null,
                "en-uS",
                timestampProvider,
                Arrays.asList("wallet-api.paysera.com", "wallet.paysera.com")
            );

        OkHttpClient okHttpClient = httpClientFactory.createHttpClient(credentials, userAgent);
        okHttpClient = okHttpClient.newBuilder().addInterceptor(httpLoggingInterceptor).build();

        PublicWalletApiClient publicWalletApiClient = retrofitFactory.createPublicWalletApiClient(
            okHttpClient
        );
        OAuthClient oAuthClient = retrofitFactory.createOAuthClient(
            okHttpClient
        );
        OAuthAsyncClient oAuthAsyncClient = new OAuthAsyncClient(
            oAuthClient,
            timestampProvider,
            clientServerTimeSynchronizationConfiguration,
            publicWalletApiClient,
            retrofitFactory.createRetrofit("https://wallet-api.paysera.com/oauth/v1/", okHttpClient),
            okHTTPQueryStringConverter
        );

        oAuthAsyncClient.exchangeCredentialsForAccessToken(
            username,
            password,
            ScopeBuilder.builder().all().buildAsList()
        ).continueWith(task -> null);
    }
}
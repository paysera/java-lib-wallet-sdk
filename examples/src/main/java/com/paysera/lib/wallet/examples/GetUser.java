package com.paysera.lib.wallet.examples;

import bolts.Continuation;
import bolts.Task;
import com.paysera.lib.wallet.*;
import com.paysera.lib.wallet.clients.PublicWalletApiClient;
import com.paysera.lib.wallet.clients.WalletApiClient;
import com.paysera.lib.wallet.clients.WalletAsyncClient;
import com.paysera.lib.wallet.entities.Credentials;
import com.paysera.lib.wallet.entities.User;
import com.paysera.lib.wallet.factories.HttpClientFactory;
import com.paysera.lib.wallet.factories.RetrofitFactory;
import com.paysera.lib.wallet.helpers.OkHTTPQueryStringConverter;
import com.paysera.lib.wallet.interfaces.TimestampSynchronizedCallback;
import com.paysera.lib.wallet.providers.TimestampProvider;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import java.io.IOException;
import java.util.Date;

public class GetUser {
    public static void main(String[] args) throws IOException {
        String userAgent = "Java wallet sdk library";

        final Credentials credentials = new Credentials();

        credentials.setAccessToken("enter_me");
        credentials.setMacKey("enter_me");
        credentials.setValidUntil(new Date(System.currentTimeMillis() + 3000000));

        TimestampProvider timestampProvider = new TimestampProvider();
        ClientServerTimeSynchronizationConfiguration
            clientServerTimeSynchronizationConfiguration = new ClientServerTimeSynchronizationConfiguration();
        clientServerTimeSynchronizationConfiguration.setEnabled(true);
        clientServerTimeSynchronizationConfiguration.setTimestampSynchronizedCallback(
            new TimestampSynchronizedCallback() {
                public void onTimestampUpdated(Date serverTime, Date currentTime) {

                }
            });
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHTTPQueryStringConverter okHTTPQueryStringConverter = new OkHTTPQueryStringConverter();
        RequestSigner requestSigner = new RequestSigner(new NonceGenerator(), new MacDigestGenerator(), okHTTPQueryStringConverter);
        RetrofitFactory retrofitFactory = new RetrofitFactory(new Router());

        HttpClientFactory httpClientFactory =
            new HttpClientFactory(
                requestSigner,
                null,
                "en-us",
                timestampProvider
            );

        OkHttpClient okHttpClient = httpClientFactory.createHttpClient(credentials, userAgent);
        okHttpClient = okHttpClient.newBuilder().addInterceptor(httpLoggingInterceptor).build();

        PublicWalletApiClient publicWalletApiClient = retrofitFactory.createPublicWalletApiClient(
            okHttpClient
        );
        WalletApiClient walletApiClient = retrofitFactory.createWalletApiClient(
            okHttpClient
        );

        WalletAsyncClient walletClient = new WalletAsyncClient(
            timestampProvider,
            clientServerTimeSynchronizationConfiguration,
            publicWalletApiClient,
            walletApiClient,
            retrofitFactory.createRetrofit("https://wallet-api.paysera.com/rest/v1/", okHttpClient),
            okHTTPQueryStringConverter
        );

        walletClient.getUser().continueWith(new Continuation<User, Object>() {
            public Object then(Task<User> task) throws Exception {
                return null;
            }
        });
    }
}

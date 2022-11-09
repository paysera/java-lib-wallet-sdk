package com.paysera.lib.wallet.factories;

import com.paysera.lib.wallet.Router;
import com.paysera.lib.wallet.clients.*;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.Executors;

public class RetrofitFactory {
    private Router router;

    public RetrofitFactory(Router router) {
        this.router = router;
    }

    public OAuthClient createOAuthClient(OkHttpClient httpClient) {
        return this
            .createRetrofit(
                this.router.getOAuthApiEndpoint(),
                httpClient
            )
            .create(OAuthClient.class);
    }

    public PartnerOauthApiClient createPartnerOAuthApiClient(OkHttpClient httpClient) {
        return this
            .createRetrofit(
                this.router.getPartnerOAuthApiEndpoint(),
                httpClient
            )
            .create(PartnerOauthApiClient.class);
    }

    public PartnerTokenApiClient createPartnerTokenApiClient(OkHttpClient httpClient) {
        return this
            .createRetrofit(
                this.router.getPartnerTokenApiEndpoint(),
                httpClient
            )
            .create(PartnerTokenApiClient.class);
    }

    public Retrofit createWalletApiRetrofit(OkHttpClient httpClient) {
        return this
            .createRetrofit(
                this.router.getWalletApiEndpoint(),
                httpClient
            );
    }

    public WalletApiClient createWalletApiClient(OkHttpClient httpClient) {
        return this
            .createRetrofit(
                this.router.getWalletApiEndpoint(),
                httpClient
            )
            .create(WalletApiClient.class);
    }

    public PublicWalletApiClient createPublicWalletApiClient(OkHttpClient httpClient) {
        return this
            .createRetrofit(
                this.router.getWalletApiEndpoint(),
                httpClient
            )
            .create(PublicWalletApiClient.class);
    }

    public Retrofit createRetrofit(String baseUrl, OkHttpClient httpClient) {
        return new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonFactory.createGson()))
            .client(httpClient)
            .callbackExecutor(Executors.newCachedThreadPool())
            .build();
    }
}
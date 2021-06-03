package com.paysera.lib.wallet.clients;

import com.paysera.lib.wallet.entities.ServerConfiguration;
import com.paysera.lib.wallet.entities.ServerInformation;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PublicWalletApiClient {
    @GET("server")
    Call<ServerInformation> getServerInformation();

    @GET("configuration")
    Call<ServerConfiguration> getServerConfiguration();
}
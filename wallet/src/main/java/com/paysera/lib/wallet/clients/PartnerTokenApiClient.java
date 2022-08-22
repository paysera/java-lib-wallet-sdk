package com.paysera.lib.wallet.clients;

import com.paysera.lib.wallet.entities.partners.PartnerToken;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface PartnerTokenApiClient {

    @POST("partner-tokens")
    Call<PartnerToken> getPartnerToken(
        @Query("wallet_id") Integer walletId,
        @Query("partner") String partner
    );
}
package com.paysera.lib.wallet.clients;

import com.paysera.lib.wallet.entities.partneroauth.ConfirmPartnerAllowanceResponse;
import com.paysera.lib.wallet.entities.partneroauth.PartnerAllowanceResponse;
import com.paysera.lib.wallet.entities.partneroauth.PartnerAllowanceRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PartnerOauthApiClient {

    @POST("partner-oauth-requests")
    Call<PartnerAllowanceResponse> createPartnerAllowance(@Body PartnerAllowanceRequest partnerAllowanceRequest);

    @PUT("partner-oauth-requests/{key}/approve")
    Call<ConfirmPartnerAllowanceResponse> confirmPartnerAllowance(@Path("key") String key);
}
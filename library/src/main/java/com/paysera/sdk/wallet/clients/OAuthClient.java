package com.paysera.sdk.wallet.clients;

import com.paysera.sdk.wallet.entities.Credentials;
import retrofit2.Call;
import retrofit2.http.*;

public interface OAuthClient {
    @FormUrlEncoded
    @POST("token")
    Call<Credentials> refreshToken(
        @Field("grant_type") String grantType,
        @Field("refresh_token") String refreshToken,
        @Field("scope") String scopes,
        @Field("code") String code
    );

    @PUT("tokens/{accessToken}/activate")
    Call<Credentials> activate(
        @Path("accessToken") String accessToken
    );

    @FormUrlEncoded
    @POST("token")
    Call<Credentials> exchangeCredentialsForAccessToken(
        @Field("grant_type") String grantType,
        @Field("username") String username,
        @Field("password") String password,
        @Field("scope") String scopes
    );

    @DELETE("token")
    Call<Void> revokeAccessToken(
        @Query("access_token") String accessToken
    );

}

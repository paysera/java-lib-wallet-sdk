package com.paysera.lib.wallet.clients;

import bolts.Task;
import com.paysera.lib.wallet.ClientServerTimeSynchronizationConfiguration;
import com.paysera.lib.wallet.entities.partneroauth.ConfirmPartnerAllowanceResponse;
import com.paysera.lib.wallet.entities.partneroauth.PartnerAllowanceRequest;
import com.paysera.lib.wallet.entities.partneroauth.PartnerAllowanceResponse;
import com.paysera.lib.wallet.helpers.OkHTTPQueryStringConverter;
import com.paysera.lib.wallet.providers.TimestampProvider;
import retrofit2.Retrofit;

public class PartnerOauthAsyncClient extends BaseAsyncClient {

    private PartnerOauthApiClient partnerOauthApiClient;

    public PartnerOauthAsyncClient(
        TimestampProvider timestampProvider,
        ClientServerTimeSynchronizationConfiguration clientServerTimeSynchronizationConfiguration,
        PublicWalletApiClient publicWalletApiClient,
        PartnerOauthApiClient partnerOauthApiClient,
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
        this.partnerOauthApiClient = partnerOauthApiClient;
    }

    public Task<PartnerAllowanceResponse> createPartnerAllowance(PartnerAllowanceRequest partnerAllowanceRequest) {
        return this.execute(this.partnerOauthApiClient.createPartnerAllowance(partnerAllowanceRequest));
    }

    public Task<ConfirmPartnerAllowanceResponse> confirmPartnerAllowance(String key) {
        return this.execute(this.partnerOauthApiClient.confirmPartnerAllowance(key));
    }
}
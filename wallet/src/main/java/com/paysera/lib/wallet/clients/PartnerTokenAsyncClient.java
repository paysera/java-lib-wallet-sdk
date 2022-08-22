package com.paysera.lib.wallet.clients;

import bolts.Task;
import com.paysera.lib.wallet.ClientServerTimeSynchronizationConfiguration;
import com.paysera.lib.wallet.entities.partners.PartnerToken;
import com.paysera.lib.wallet.helpers.OkHTTPQueryStringConverter;
import com.paysera.lib.wallet.providers.TimestampProvider;
import retrofit2.Retrofit;

public class PartnerTokenAsyncClient extends BaseAsyncClient {

    private PartnerTokenApiClient partnerTokenApiClient;

    public PartnerTokenAsyncClient(
        TimestampProvider timestampProvider,
        ClientServerTimeSynchronizationConfiguration clientServerTimeSynchronizationConfiguration,
        PublicWalletApiClient publicWalletApiClient,
        PartnerTokenApiClient partnerTokenApiClient,
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
        this.partnerTokenApiClient = partnerTokenApiClient;
    }

    public Task<PartnerToken> getPartnerToken(Integer walletId, String partner) {
        return this.execute(this.partnerTokenApiClient.getPartnerToken(walletId, partner));
    }
}
package com.paysera.sdk.wallet.requests;

import com.paysera.sdk.wallet.base.BaseRequest;
import com.paysera.sdk.wallet.clients.RefreshingWalletAsyncClient;

import java.math.BigDecimal;

public class CalculateConversionRate extends BaseRequest {

    public CalculateConversionRate(
            RefreshingWalletAsyncClient refreshingWalletAsyncClient,
            String accountNumber,
            String fromCurrency,
            String toCurrency,
            BigDecimal fromAmount,
            BigDecimal toAmount
    ) {
        super(refreshingWalletAsyncClient);

        authorizedRequest.accountNumber(accountNumber);
        authorizedRequest.fromCurrency(fromCurrency);
        authorizedRequest.toCurrency(toCurrency);

        if (toAmount.compareTo(BigDecimal.ZERO) == 0) {
            authorizedRequest.fromAmount(fromAmount);
        } else {
            authorizedRequest.toAmount(toAmount);
        }
    }

    @Override
    protected BaseRequest.HttpMethod getMethod() {
        return BaseRequest.HttpMethod.GET;
    }

    @Override
    protected String getUrl() {
        return "currency-conversion";
    }
}

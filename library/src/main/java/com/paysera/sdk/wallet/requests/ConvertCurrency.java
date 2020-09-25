package com.paysera.sdk.wallet.requests;

import com.paysera.sdk.wallet.base.BaseRequest;
import com.paysera.sdk.wallet.clients.RefreshingWalletAsyncClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;

public class ConvertCurrency extends BaseRequest {

    public ConvertCurrency(
            RefreshingWalletAsyncClient refreshingWalletAsyncClient,
            String accountNumber,
            String fromCurrency,
            String toCurrency,
            BigDecimal fromAmount,
            BigDecimal toAmount,
            String direction
    ) {
        super(refreshingWalletAsyncClient);

        JSONObject body = new JSONObject();
        try {
            body
                    .put("account_number", accountNumber)
                    .put("from_currency", fromCurrency)
                    .put("to_currency", toCurrency);

            if (direction.equals("FROM")) {
                body
                        .put("from_amount_decimal", fromAmount.toString())
                        .put("min_to_amount_decimal", toAmount.toString());
            } else {
                body
                        .put("to_amount_decimal", toAmount.toString())
                        .put("max_from_amount_decimal", fromAmount.toString());
            }

            authorizedRequest.jsonBody(body);
        } catch (JSONException e) {
                    /*Timber.d(e);
                    Timber.e("Api: ConvertCurrency");*/
        }
    }

    @Override
    protected BaseRequest.HttpMethod getMethod() {
        return BaseRequest.HttpMethod.POST;
    }

    @Override
    protected String getUrl() {
        return "currency-conversion";
    }
}
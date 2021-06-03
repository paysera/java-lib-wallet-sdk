package com.paysera.lib.wallet.helpers;

import okhttp3.HttpUrl;

import java.util.Map;

public class OkHTTPQueryStringConverter {

    public String convertToEncodedQueryString(Map<?, ?> map) {
        if (map.size() == 0) {
            return "";
        }
        HttpUrl.Builder urlBuilder =
            (new HttpUrl.Builder()).scheme("https").host("wallet.paysera.com");

        for (Map.Entry<?, ?> entry : map.entrySet()) {
            urlBuilder.addQueryParameter(entry.getKey().toString(), entry.getValue().toString());
        }

        return urlBuilder.build().encodedQuery();
    }
}

package com.paysera.sdk.wallet.factories;

import com.paysera.sdk.wallet.RequestSigner;
import com.paysera.sdk.wallet.entities.Credentials;
import com.paysera.sdk.wallet.providers.TimestampProvider;
import okhttp3.*;
import okio.Buffer;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class HttpClientFactory {
    private TimestampProvider timestampProvider;
    private RequestSigner requestSigner;
    private Logger logger;
    private final List<String> hostnames = Arrays.asList("wallet-api.paysera.com", "wallet.paysera.com");

    public HttpClientFactory(
        RequestSigner requestSigner,
        Logger logger,
        TimestampProvider timestampProvider
    ) {
        this.requestSigner = requestSigner;
        this.logger = logger;
        this.timestampProvider = timestampProvider;
    }

    public OkHttpClient createHttpClient(
        final Credentials credentials,
        final String userAgent
    ) {
        return this.createHttpClient(
            credentials,
            userAgent,
            new HashMap<String, String>()
        );
    }

    public OkHttpClient createHttpClient(
        final Credentials credentials,
        final String userAgent,
        final Map<String, String> parameters
    ) {
        CertificatePinner.Builder certificatePinnerBuilder = new CertificatePinner.Builder();
        for (String hostname : hostnames) {
            certificatePinnerBuilder
                .add(hostname, "sha256/K8WscGYwD51wz79WudzZPDSXFRYrKM+e78Y5YQZJG3k=")
                .add(hostname, "sha256/9ay/M3fmRBbc/7R5Nqts0SuDQK8KjAHUSZlLCxEPsH0=")
            ;
        }

        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.certificatePinner(certificatePinnerBuilder.build());
        httpClient.retryOnConnectionFailure(false);
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                okhttp3.Request original = chain.request();
                byte[] body = null;

                if (original.method().equals("POST") && (original.body() == null || original.body().contentLength() == 0)) {
                    original = original.newBuilder().post(
                            RequestBody.create(MediaType.parse("application/json"), "{}")
                    ).build();
                }

                if (original.body() != null) {
                    Buffer buffer = new Buffer();
                    original.body().writeTo(buffer);

                    body = buffer.readByteArray();
                }
                String timestamp = timestampProvider.getTimestamp();
                try {
                    String signature = requestSigner.generateSignature(
                        credentials.getAccessToken(),
                        credentials.getMacKey(),
                        original,
                        body,
                        timestamp,
                        parameters
                    );

                    okhttp3.Request request = original.newBuilder()
                        .header("User-Agent", userAgent)
                        .header("Authorization", signature)
                        .build();

                    return chain.proceed(request);
                } catch (Exception exception) {
                    if (logger != null) {
                        StringWriter errors = new StringWriter();
                        exception.printStackTrace(new PrintWriter(errors));

                        logger.severe(errors.toString());
                    }

                    throw new IOException("An error occurred while signing the request", exception);
                }
            }
        });

        return httpClient.build();
    }
}

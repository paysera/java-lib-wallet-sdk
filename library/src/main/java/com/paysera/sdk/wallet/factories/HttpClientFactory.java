package com.paysera.sdk.wallet.factories;

import com.babylon.certificatetransparency.CTInterceptorBuilder;
import com.paysera.sdk.wallet.RequestSigner;
import com.paysera.sdk.wallet.entities.Credentials;
import com.paysera.sdk.wallet.providers.TimestampProvider;
import okhttp3.*;
import okio.Buffer;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;
import java.util.logging.Logger;

public class HttpClientFactory {
    private TimestampProvider timestampProvider;
    private RequestSigner requestSigner;
    private Logger logger;
    private String locale;
    private List<String> certifiedHosts;

    public HttpClientFactory(
        RequestSigner requestSigner,
        Logger logger,
        String locale,
        TimestampProvider timestampProvider,
        List<String> certifiedHosts
    ) {
        this.requestSigner = requestSigner;
        this.logger = logger;
        this.locale = locale;
        this.timestampProvider = timestampProvider;
        this.certifiedHosts = certifiedHosts;
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
        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        if (!certifiedHosts.isEmpty()) {
            CTInterceptorBuilder builder = new CTInterceptorBuilder();
            for (String hostname : certifiedHosts) {
                builder.includeHost(hostname);
            }
            httpClient.addNetworkInterceptor(builder.build());
        }

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

                    Request.Builder builder = original.newBuilder();
                    if (locale != null) {
                        builder.header("Accept-Language", locale);
                    }

                    builder.header("User-Agent", userAgent);
                    builder.header("Authorization", signature);

                    return chain.proceed(builder.build());
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

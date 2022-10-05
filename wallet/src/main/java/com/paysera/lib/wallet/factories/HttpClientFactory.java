package com.paysera.lib.wallet.factories;

import com.paysera.lib.wallet.RequestSigner;
import com.paysera.lib.wallet.entities.Credentials;
import com.paysera.lib.wallet.providers.TimestampProvider;
import okhttp3.*;
import okio.Buffer;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class HttpClientFactory {
    private TimestampProvider timestampProvider;
    private RequestSigner requestSigner;
    private Logger logger;
    private String locale;

    public HttpClientFactory(
        RequestSigner requestSigner,
        Logger logger,
        String locale,
        TimestampProvider timestampProvider
    ) {
        this.requestSigner = requestSigner;
        this.logger = logger;
        this.locale = locale;
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
        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.retryOnConnectionFailure(false);
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
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
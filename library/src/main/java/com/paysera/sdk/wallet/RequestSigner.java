package com.paysera.sdk.wallet;

import com.paysera.sdk.wallet.helpers.OkHTTPQueryStringConverter;
import okhttp3.HttpUrl;
import okhttp3.Request;
import org.apache.commons.codec.binary.Base64;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class RequestSigner {

    private NonceGeneratorInterface nonceGenerator;
    private MacDigestGeneratorInterface macDigestGeneratorInterface;
    private OkHTTPQueryStringConverter okHTTPQueryStringConverter;

    public RequestSigner(
        NonceGeneratorInterface nonceGenerator,
        MacDigestGeneratorInterface macDigestGeneratorInterface,
        OkHTTPQueryStringConverter okHTTPQueryStringConverter
    ) {
        this.nonceGenerator = nonceGenerator;
        this.macDigestGeneratorInterface = macDigestGeneratorInterface;
        this.okHTTPQueryStringConverter = okHTTPQueryStringConverter;
    }

    public String generateSignature(
        String macId,
        String macSecret,
        Request originalRequest,
        byte[] body,
        String timestamp,
        Map<String, String> parameters
    ) throws NoSuchAlgorithmException, InvalidKeyException {
        String nonce = this.nonceGenerator.generate();
        String ext = generateExt(body, parameters);

        String host = originalRequest.url().host();
        Integer port = originalRequest.url().port();
        String method = originalRequest.method();
        String path = originalRequest.url().encodedPath();
        String query = originalRequest.url().encodedQuery();

        if (query != null && !query.isEmpty()) {
            HttpUrl url = (new HttpUrl.Builder())
                .scheme("https")
                .host(host)
                .encodedQuery(query)
                .build();

            path += "?" + url.encodedQuery();
        }

        // mac
        String mac = calculateMAC(
            timestamp,
            nonce,
            macSecret,
            method,
            path,
            host,
            port,
            ext
        );

        String authorizationHeader = String.format(
            "MAC id=\"%s\", ts=\"%s\", nonce=\"%s\", mac=\"%s\"",
            macId,
            timestamp,
            nonce,
            mac
        );

        if (ext != null) {
            authorizationHeader += ", ext=\"" + ext + "\"";
        }

        return authorizationHeader;
    }

    private String generateExt(byte[] content, Map<String, String> parameters) throws NoSuchAlgorithmException {
        Map<String, String> extParameters = new HashMap<>();

        if (content != null && content.length > 0) {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] contentHash = digest.digest(content);

            extParameters.put("body_hash", new String(Base64.encodeBase64(contentHash)));
        }

        extParameters.putAll(parameters);

        return okHTTPQueryStringConverter.convertToEncodedQueryString(extParameters);
    }

    private String calculateMAC(String timestamp, String nonce, String secret, String httpMethod, String path, String host, Integer port, String ext) throws NoSuchAlgorithmException, InvalidKeyException {
        final StringBuilder macStringBuilder = new StringBuilder()
            .append(timestamp)
            .append("\n")
            .append(nonce)
            .append("\n")
            .append(httpMethod)
            .append("\n")
            .append(path)
            .append("\n")
            .append(host)
            .append("\n")
            .append(port)
            .append("\n")
            .append(ext)
            .append("\n");

        byte[] macDigest = macDigestGeneratorInterface.generate(
            secret.getBytes(),
            macStringBuilder.toString().getBytes()
        );

        return new String(Base64.encodeBase64(macDigest));
    }
}

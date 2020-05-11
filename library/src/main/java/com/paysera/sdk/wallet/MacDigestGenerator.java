package com.paysera.sdk.wallet;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class MacDigestGenerator implements MacDigestGeneratorInterface {

    @Override
    public byte[] generate(byte[] key, byte[] data) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKey = new SecretKeySpec(key, "HmacSHA256");

        mac.init(secretKey);

        return mac.doFinal(data);
    }
}

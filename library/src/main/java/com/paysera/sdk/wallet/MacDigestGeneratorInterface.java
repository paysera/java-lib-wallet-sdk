package com.paysera.sdk.wallet;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface MacDigestGeneratorInterface {
    byte[] generate(byte[] key, byte[] data) throws NoSuchAlgorithmException, InvalidKeyException;
}
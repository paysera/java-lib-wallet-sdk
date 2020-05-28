package com.paysera.sdk.wallet;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface NonceGeneratorInterface {
    String generate() throws NoSuchAlgorithmException, InvalidKeyException;
}

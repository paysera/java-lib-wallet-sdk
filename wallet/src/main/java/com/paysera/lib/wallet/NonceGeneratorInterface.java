package com.paysera.lib.wallet;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface NonceGeneratorInterface {
    String generate() throws NoSuchAlgorithmException, InvalidKeyException;
}
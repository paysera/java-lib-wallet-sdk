package com.paysera.sdk.wallet;

public interface MacDigestGeneratorInterface {
    byte[] generate(byte[] key, byte[] data);
}
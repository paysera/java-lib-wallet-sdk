package com.paysera.sdk.wallet;

public interface MacDigestGeneratorInterface {
    byte[] generate(StringBuilder data);
}
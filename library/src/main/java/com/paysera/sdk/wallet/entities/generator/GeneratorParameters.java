package com.paysera.sdk.wallet.entities.generator;

import com.google.gson.annotations.SerializedName;

public class GeneratorParameters {
    @SerializedName("secret_iterations")
    public Integer secretIterations;
    @SerializedName("secret_length")
    public Integer secretLength;
    @SerializedName("sign_iterations")
    public Integer signIterations;
    @SerializedName("sign_length")
    public Integer signLength;

    public Integer getSecretIterations() {
        return secretIterations;
    }

    public void setSecretIterations(Integer secretIterations) {
        this.secretIterations = secretIterations;
    }

    public Integer getSecretLength() {
        return secretLength;
    }

    public void setSecretLength(Integer secretLength) {
        this.secretLength = secretLength;
    }

    public Integer getSignIterations() {
        return signIterations;
    }

    public void setSignIterations(Integer signIterations) {
        this.signIterations = signIterations;
    }

    public Integer getSignLength() {
        return signLength;
    }

    public void setSignLength(Integer signLength) {
        this.signLength = signLength;
    }
}
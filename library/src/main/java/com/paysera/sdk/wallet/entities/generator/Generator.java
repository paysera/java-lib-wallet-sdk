package com.paysera.sdk.wallet.entities.generator;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Generator {
    private Integer id;
    private String status;
    @SerializedName("expires_in")
    private Integer expiresIn;
    private List<GeneratorIdentifier> identifiers;
    private String seed;
    private String type;
    private GeneratorParameters params;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public List<GeneratorIdentifier> getIdentifiers() {
        return identifiers;
    }

    public void setIdentifiers(List<GeneratorIdentifier> identifiers) {
        this.identifiers = identifiers;
    }

    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public GeneratorParameters getParams() {
        return params;
    }

    public void setParams(GeneratorParameters params) {
        this.params = params;
    }
}
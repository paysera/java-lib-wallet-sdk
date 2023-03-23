package com.paysera.lib.wallet.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CommonMetadataAwareResponse<T> {
    private List<T> items;
    @SerializedName("_metadata")
    private Metadata metadata;

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }
}
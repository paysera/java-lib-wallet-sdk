package com.paysera.lib.wallet.entities;

import java.util.List;

public class MetadataAwareResponse<T> {
    private List<T> items;
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
package com.paysera.lib.wallet.entities.locations;

import java.util.ArrayList;
import java.util.List;

public class LocationService {
    private boolean available;
    private List<String> types = new ArrayList<>();
    private List<Long> categories = new ArrayList<>();

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public List<Long> getCategories() {
        return categories;
    }

    public void setCategories(List<Long> categories) {
        this.categories = categories;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }
}

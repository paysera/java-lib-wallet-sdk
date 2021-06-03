package com.paysera.lib.wallet.entities.locations;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.paysera.lib.wallet.adapters.DateUnixTimestampSecondsAdapter;
import com.paysera.lib.wallet.adapters.LocationImagesAdapter;
import com.paysera.lib.wallet.adapters.LocationWorkingHoursAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Location {
    private Long id;
    private Integer projectId;
    private String title;
    @JsonAdapter(DateUnixTimestampSecondsAdapter.class)
    private Date updatedAt;
    private String status;
    private String description;
    private String address;
    private float lat;
    private float lng;
    private Integer radius;
    @JsonAdapter(LocationImagesAdapter.class)
    private LocationImages images;
    private List<LocationPrice> prices = new ArrayList<>();
    private LocationRemoteOrders remoteOrders;
    @JsonAdapter(LocationWorkingHoursAdapter.class)
    private List<LocationWorkingHour> workingHours = new ArrayList<>();
    @SerializedName("services")
    private LocationServices locationServices;
    @SerializedName("public")
    private boolean publicLocation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public Integer getRadius() {
        return radius;
    }

    public void setRadius(Integer radius) {
        this.radius = radius;
    }

    public List<LocationPrice> getPrices() {
        return prices;
    }

    public void setPrices(List<LocationPrice> prices) {
        this.prices = prices;
    }

    public LocationImages getImages() {
        return images;
    }

    public void setImages(LocationImages images) {
        this.images = images;
    }

    public List<LocationWorkingHour> getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(List<LocationWorkingHour> workingHours) {
        this.workingHours = workingHours;
    }

    public LocationServices getLocationServices() {
        return locationServices;
    }

    public void setLocationServices(LocationServices locationServices) {
        this.locationServices = locationServices;
    }

    public LocationRemoteOrders getRemoteOrders() {
        return remoteOrders;
    }

    public void setRemoteOrders(LocationRemoteOrders remoteOrders) {
        this.remoteOrders = remoteOrders;
    }

    public boolean isPublicLocation() {
        return publicLocation;
    }

    public void setPublicLocation(boolean publicLocation) {
        this.publicLocation = publicLocation;
    }
}
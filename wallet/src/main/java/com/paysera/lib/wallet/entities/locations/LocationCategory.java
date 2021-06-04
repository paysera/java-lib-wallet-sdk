package com.paysera.lib.wallet.entities.locations;

public class LocationCategory {
    private Long id;
    private String title;
    private Integer parentId;
    private LocationCategoryImages images;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public LocationCategoryImages getImages() {
        return images;
    }

    public void setImages(LocationCategoryImages images) {
        this.images = images;
    }
}

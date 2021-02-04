package com.paysera.sdk.wallet.entities;

/**
 * @author Vytautas Gimbutas <v.gimbutas@evp.lt>
 */
public class IdentityDocument {
    private Integer id;
    private String comment;
    private String reviewStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getReviewStatus() { return reviewStatus; }

    public void setReviewStatus(String reviewStatus) { this.reviewStatus = reviewStatus; }
}

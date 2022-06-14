package com.paysera.lib.wallet.entities;

/**
 * @author Vytautas Gimbutas <v.gimbutas@evp.lt>
 */
public class IdentityDocument {
    private Long id;
    private String comment;
    private String reviewStatus;
    private String type;
    private String countryOfIssue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getType() {
        return type;
    }

    public String getCountryOfIssue() {
        return countryOfIssue;
    }
}
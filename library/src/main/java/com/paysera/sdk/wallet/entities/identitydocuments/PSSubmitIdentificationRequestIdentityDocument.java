package com.paysera.sdk.wallet.entities.identitydocuments;

public class PSSubmitIdentificationRequestIdentityDocument {

    private String reviewStatus;
    private String comment;

    public String getComment() { return comment; }

    public void setComment(String comment) { this.comment = comment; }

    public String getReviewStatus() { return reviewStatus; }

    public void setReviewStatus(String reviewStatus) { this.reviewStatus = reviewStatus; }
}

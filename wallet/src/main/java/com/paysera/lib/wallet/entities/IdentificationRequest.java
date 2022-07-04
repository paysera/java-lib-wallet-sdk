package com.paysera.lib.wallet.entities;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.paysera.lib.wallet.adapters.DateUnixTimestampSecondsAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Vytautas Gimbutas <v.gimbutas@evp.lt>
 */
public class IdentificationRequest {
    public static final String STATUS_PENDING = "pending";
    public static final String STATUS_WAITING = "waiting";
    public static final String STATUS_PROCESSING = "processing";
    public static final String STATUS_REVIEWED = "reviewed";

    private Long id;
    private Integer userId;
    private String status;
    private FaceDocument facePhotoDocument;
    @JsonAdapter(DateUnixTimestampSecondsAdapter.class)
    @SerializedName("created")
    private Date createdAt;
    @SerializedName("identity_documents")
    private List<IdentityDocument> identityDocumentList = new ArrayList<>();
    private IdentityDocument additionalDocument;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isPending() {
        return this.status.equals(STATUS_PENDING);
    }

    public boolean isWaiting() {
        return this.status.equals(STATUS_WAITING);
    }

    public boolean isProcessing() {
        return this.status.equals(STATUS_PROCESSING);
    }

    public boolean isReviewed() {
        return this.status.equals(STATUS_REVIEWED);
    }

    public void setFacePhotoDocument(FaceDocument facePhotoDocument) {
        this.facePhotoDocument = facePhotoDocument;
    }

    public FaceDocument getFacePhotoDocument() {
        return this.facePhotoDocument;
    }

    public List<IdentityDocument> getIdentityDocumentList() {
        return identityDocumentList;
    }

    public void setIdentityDocumentList(List<IdentityDocument> identityDocumentList) {
        this.identityDocumentList.clear();
        this.identityDocumentList.addAll(identityDocumentList);
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public IdentityDocument getAdditionalDocument() {
        return additionalDocument;
    }

    public void setAdditionalDocument(IdentityDocument additionalDocument) {
        this.additionalDocument = additionalDocument;
    }
}
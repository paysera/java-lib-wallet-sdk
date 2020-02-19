package com.paysera.sdk.wallet.entities.allowances;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.paysera.sdk.wallet.adapters.DateUnixTimestampSecondsAdapter;

import java.util.Date;

public class AllowanceValid {

    @SerializedName("for")
    private Long validFor;
    @SerializedName("until")
    @JsonAdapter(DateUnixTimestampSecondsAdapter.class)
    private Date validUntil;

    public Long getValidFor() {
        return validFor;
    }

    public void setValidFor(Long validFor) {
        this.validFor = validFor;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }
}
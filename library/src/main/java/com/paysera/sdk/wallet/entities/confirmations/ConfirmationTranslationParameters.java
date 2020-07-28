package com.paysera.sdk.wallet.entities.confirmations;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class ConfirmationTranslationParameters {

    @SerializedName("%phone%")
    private String phone;
    @SerializedName("%email%")
    private String email;

    public List<Map<String, String>> getTransfersMoneySums() {
        return transfersMoneySums;
    }

    public void setTransfersMoneySums(List<Map<String, String>> transfersMoneySums) {
        this.transfersMoneySums = transfersMoneySums;
    }

    @SerializedName("transfers_money_sums")
    private List<Map<String, String>> transfersMoneySums;
    @SerializedName("beneficiary_account")
    private String beneficiaryAccount;
    @SerializedName("transfers_count")
    private Integer transfersCount;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBeneficiaryAccount() {
        return beneficiaryAccount;
    }

    public void setBeneficiaryAccount(String beneficiaryAccount) {
        this.beneficiaryAccount = beneficiaryAccount;
    }

    public Integer getTransfersCount() {
        return transfersCount;
    }

    public void setTransfersCount(Integer transfersCount) {
        this.transfersCount = transfersCount;
    }
}

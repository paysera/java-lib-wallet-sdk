package com.paysera.lib.wallet.entities.confirmations;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.paysera.lib.wallet.adapters.ListMoneyAdapter;
import org.joda.money.Money;

import java.util.List;

public class ConfirmationTranslationParameters {

    @SerializedName("%phone%")
    private String phone;
    @SerializedName("%email%")
    private String email;
    @SerializedName("transfers_money_sums")
    @JsonAdapter(ListMoneyAdapter.class)
    private List<Money> transfersMoneySums;
    @SerializedName("beneficiary_account")
    private String beneficiaryAccount;
    @SerializedName("transfers_count")
    private Integer transfersCount;
    @SerializedName("card_last_4_digits")
    private String cardLastFourDigits;

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

    public List<Money> getTransfersMoneySums() {
        return transfersMoneySums;
    }

    public void setTransfersMoneySums(List<Money> transfersMoneySums) {
        this.transfersMoneySums = transfersMoneySums;
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

    public String getCardLastFourDigits() {
        return cardLastFourDigits;
    }

    public void setCardLastFourDigits(String cardLastFourDigits) {
        this.cardLastFourDigits = cardLastFourDigits;
    }
}

package com.paysera.lib.wallet.entities;

import com.google.gson.annotations.JsonAdapter;
import com.paysera.lib.wallet.adapters.DateUnixTimestampSecondsAdapter;
import com.paysera.lib.wallet.helpers.MoneyHelper;
import org.joda.money.Money;

import java.util.Date;

public class CurrencyConversionResult {
    private Integer id;
    @JsonAdapter(DateUnixTimestampSecondsAdapter.class)
    private Date date;
    private Integer fromAmount;
    private String fromCurrency;
    private Integer toAmount;
    private String toCurrency;
    private String accountNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getFromAmount() {
        return fromAmount;
    }

    public void setFromAmount(Integer fromAmount) {
        this.fromAmount = fromAmount;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public Money getFromAmountMoney() {
        return MoneyHelper.createFromCents(fromCurrency, fromAmount);
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public Integer getToAmount() {
        return toAmount;
    }

    public Money getToAmountMoney() {
        return MoneyHelper.createFromCents(toCurrency, toAmount);
    }

    public void setToAmount(Integer toAmount) {
        this.toAmount = toAmount;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
package com.paysera.lib.wallet.entities.requests;

public class GetWalletBalanceRequest {
    private int walletId;
    private String convertTo;
    private boolean includeConvertToCurrency;
    private boolean showHistoricalCurrencies;

    public GetWalletBalanceRequest(int walletId) {
        this.walletId = walletId;
    }

    public GetWalletBalanceRequest(int walletId, boolean showHistoricalCurrencies) {
        this.walletId = walletId;
        this.showHistoricalCurrencies = showHistoricalCurrencies;
    }

    public GetWalletBalanceRequest(int walletId, String convertTo) {
        this.walletId = walletId;
        this.convertTo = convertTo;
    }

    public GetWalletBalanceRequest(int walletId, String convertTo, boolean includeConvertToCurrency) {
        this.walletId = walletId;
        this.convertTo = convertTo;
        this.includeConvertToCurrency = includeConvertToCurrency;
    }

    public GetWalletBalanceRequest(int walletId, String convertTo, boolean includeConvertToCurrency, boolean showHistoricalCurrencies) {
        this.walletId = walletId;
        this.convertTo = convertTo;
        this.includeConvertToCurrency = includeConvertToCurrency;
        this.showHistoricalCurrencies = showHistoricalCurrencies;
    }

    public int getWalletId() {
        return walletId;
    }

    public String getConvertTo() {
        return convertTo;
    }

    public boolean isIncludeConvertToCurrency() {
        return includeConvertToCurrency;
    }

    public boolean isShowHistoricalCurrencies() {
        return showHistoricalCurrencies;
    }
}
package com.paysera.sdk.wallet.entities.confirmations;

public class ConfirmationProperties {
    private String translation;
    private String translationSlug;
    private ConfirmationTranslationParameters translationParameters;
    private String code;
    private String type;
    private Boolean acceptanceRequired;

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getTranslationSlug() {
        return translationSlug;
    }

    public void setTranslationSlug(String translationSlug) {
        this.translationSlug = translationSlug;
    }

    public ConfirmationTranslationParameters getTranslationParameters() {
        return translationParameters;
    }

    public void setTranslationParameters(ConfirmationTranslationParameters translationsParameters) {
        this.translationParameters = translationsParameters;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getAcceptanceRequired() {
        return acceptanceRequired;
    }

    public void setAcceptanceRequired(Boolean acceptanceRequired) {
        this.acceptanceRequired = acceptanceRequired;
    }
}

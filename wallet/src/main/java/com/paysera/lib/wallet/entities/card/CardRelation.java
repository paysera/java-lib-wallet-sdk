package com.paysera.lib.wallet.entities.card;

/**
 * @author Vytautas Gimbutas <v.gimbutas@evp.lt>
 */
public class CardRelation {
    protected String redirectUri;
    protected String redirectBackUri;

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public String getRedirectBackUri() {
        return redirectBackUri;
    }

    public void setRedirectBackUri(String redirectBackUri) {
        this.redirectBackUri = redirectBackUri;
    }
}

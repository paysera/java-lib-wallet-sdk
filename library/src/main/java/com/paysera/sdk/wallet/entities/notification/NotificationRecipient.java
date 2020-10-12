package com.paysera.sdk.wallet.entities.notification;

/**
 * @author Vytautas Gimbutas <v.gimbutas@evp.lt>
 */
public class NotificationRecipient {
    protected String identifier;
    protected String type;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getType() {
        return type;
    };

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object object) {
        return
                object instanceof NotificationRecipient
            && this.getType().equals(((NotificationRecipient) object).getType())
        ;
    }
}
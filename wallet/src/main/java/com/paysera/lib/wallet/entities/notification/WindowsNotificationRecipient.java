package com.paysera.lib.wallet.entities.notification;

/**
 * @author Vytautas Gimbutas <v.gimbutas@evp.lt>
 */
public class WindowsNotificationRecipient extends NotificationRecipient {
    protected String uri;
    protected String notificationType;
    protected String tileIdentifier;

    @Override
    public String getType() {
        return "windows";
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getTileIdentifier() {
        return tileIdentifier;
    }

    public void setTileIdentifier(String tileIdentifier) {
        this.tileIdentifier = tileIdentifier;
    }

    @Override
    public boolean equals(Object object) {
        return
               super.equals(object)
            && object instanceof WindowsNotificationRecipient
            && this.getNotificationType().equals(((WindowsNotificationRecipient) object).getNotificationType())
            && this.getTileIdentifier().equals(((WindowsNotificationRecipient) object).getTileIdentifier())
        ;
    }
}
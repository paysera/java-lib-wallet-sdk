package com.paysera.lib.wallet.entities.notification;

import com.paysera.lib.wallet.entities.IdentifierAware;

public class FirebaseNotificationRecipient extends NotificationRecipient implements IdentifierAware {
    @Override
    public String getType() {
        return "firebase";
    }
}
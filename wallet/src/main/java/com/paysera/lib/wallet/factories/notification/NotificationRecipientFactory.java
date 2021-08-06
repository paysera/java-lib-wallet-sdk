package com.paysera.lib.wallet.factories.notification;

import com.paysera.lib.wallet.entities.notification.*;
import com.paysera.lib.wallet.exceptions.WalletSdkException;

/**
 * @author Vytautas Gimbutas <v.gimbutas@evp.lt>
 */
public class NotificationRecipientFactory {
    public NotificationRecipient createNotificationRecipient(String type) throws WalletSdkException {
        if (type.equals("windows")) {
            return new WindowsNotificationRecipient();
        } else if (type.equals("ios")) {
            return new IosNotificationRecipient();
        } else if (type.equals("android")) {
            return new AndroidNotificationRecipient();
        } else if (type.equals("firebase")) {
            return new FirebaseNotificationRecipient();
        }

        throw new WalletSdkException("Unknown notification recipient type");
    }
}

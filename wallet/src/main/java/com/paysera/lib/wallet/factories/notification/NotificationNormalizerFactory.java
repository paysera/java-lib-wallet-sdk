package com.paysera.lib.wallet.factories.notification;

import com.paysera.lib.wallet.factories.GsonFactory;
import com.paysera.lib.wallet.normalizers.NotificationEventNormalizer;
import com.paysera.lib.wallet.normalizers.NotificationRecipientNormalizer;
import com.paysera.lib.wallet.normalizers.NotificationSubscriberNormalizer;

/**
 * @author Vytautas Gimbutas <v.gimbutas@evp.lt>
 */
public class NotificationNormalizerFactory {
    public NotificationSubscriberNormalizer createNotificationSubscriberNormalizer() {
        return new NotificationSubscriberNormalizer(
            this.createNotificationRecipientNormalizer(),
            this.createNotificationEventsNormalizer()
        );
    }

    public NotificationRecipientNormalizer createNotificationRecipientNormalizer() {
        return new NotificationRecipientNormalizer(new NotificationRecipientFactory());
    }

    public NotificationEventNormalizer createNotificationEventsNormalizer() {
        return new NotificationEventNormalizer(GsonFactory.createGson());
    }
}
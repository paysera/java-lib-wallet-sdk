package com.paysera.sdk.wallet.normalizers;

import com.paysera.sdk.wallet.entities.IdentifierAware;
import com.paysera.sdk.wallet.entities.notification.NotificationRecipient;
import com.paysera.sdk.wallet.entities.notification.WindowsNotificationRecipient;
import com.paysera.sdk.wallet.exceptions.NormalizerException;
import com.paysera.sdk.wallet.exceptions.WalletSdkException;
import com.paysera.sdk.wallet.factories.notification.NotificationRecipientFactory;
import org.json.JSONObject;

/**
 * @author Vytautas Gimbutas <v.gimbutas@evp.lt>
 */
public class NotificationRecipientNormalizer implements
    NormalizerInterface<NotificationRecipient>,
    DenormalizerInterface<NotificationRecipient> {

    protected NotificationRecipientFactory notificationRecipientFactory;

    public NotificationRecipientNormalizer(NotificationRecipientFactory notificationRecipientFactory) {
        this.notificationRecipientFactory = notificationRecipientFactory;
    }

    public JSONObject mapFromEntity(NotificationRecipient entity) throws NormalizerException {
        JSONObject data = new JSONObject();

        data.put("type", entity.getType());

        if (entity instanceof WindowsNotificationRecipient) {
            if (((WindowsNotificationRecipient) entity).getNotificationType() == null) {
                throw new NormalizerException("Windows notification type not specified");
            }

            if (((WindowsNotificationRecipient) entity).getUri() == null) {
                throw new NormalizerException("Uri is not specified");
            }

            if (((WindowsNotificationRecipient) entity).getTileIdentifier() != null) {
                data.put("tile_id", ((WindowsNotificationRecipient) entity).getTileIdentifier());
            }

            data.put(
                "notification_type",
                ((WindowsNotificationRecipient) entity).getNotificationType()
            );
            data.put("uri", ((WindowsNotificationRecipient) entity).getUri());
        }

        if (entity instanceof IdentifierAware) {
            data.put("identifier", ((IdentifierAware) entity).getIdentifier());
        }

        return data;
    }

    public NotificationRecipient mapToEntity(JSONObject data) throws NormalizerException {
        NotificationRecipient notificationRecipient;
        try {
            notificationRecipient = this.notificationRecipientFactory.createNotificationRecipient(
                data.getString("type")
            );
        } catch (WalletSdkException exception) {
            throw new NormalizerException("Unrecognized notification recipient type", exception);
        }

        if (notificationRecipient instanceof WindowsNotificationRecipient) {
            ((WindowsNotificationRecipient) notificationRecipient).setNotificationType(
                data.getString("notification_type")
            );

            ((WindowsNotificationRecipient) notificationRecipient).setUri(data.getString("uri"));

            if (data.has("tile_id")) {
                ((WindowsNotificationRecipient) notificationRecipient).setTileIdentifier(data.getString("tile_id"));
            }
        }

        if (data.has("identifier")) {
            ((IdentifierAware) notificationRecipient).setIdentifier(data.getString("identifier"));
        }
        if (data.has("type")) {
            notificationRecipient.setType(data.getString("type"));
        }

        return notificationRecipient;
    }
}

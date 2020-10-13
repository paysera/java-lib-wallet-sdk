package com.paysera.sdk.wallet.normalizers;

import com.paysera.sdk.wallet.entities.notification.NotificationSubscriber;
import com.paysera.sdk.wallet.exceptions.NormalizerException;
import org.json.JSONObject;

/**
 * @author Vytautas Gimbutas <v.gimbutas@evp.lt>
 */
public class NotificationSubscriberNormalizer implements NormalizerInterface<NotificationSubscriber>, DenormalizerInterface<NotificationSubscriber> {
    protected NotificationRecipientNormalizer notificationRecipientNormalizer;
    protected NotificationEventNormalizer notificationEventNormalizer;

    public NotificationSubscriberNormalizer(
        NotificationRecipientNormalizer notificationRecipientNormalizer,
        NotificationEventNormalizer notificationEventNormalizer
    ) {
        this.notificationRecipientNormalizer = notificationRecipientNormalizer;
        this.notificationEventNormalizer = notificationEventNormalizer;
    }

    public JSONObject mapFromEntity(NotificationSubscriber entity) throws NormalizerException {
        if (entity.getRecipient() == null) {
            throw new NormalizerException("Recipient is not specified");
        }

        if (entity.getPrivacyLevel() == null) {
            throw new NormalizerException("Privacy level is not specified");
        }

        JSONObject data = new JSONObject();

        if (entity.getId() != null) {
            data.put("id", entity.getId());
        }

        data.put("status", entity.getStatus());
        data.put("type", entity.getRecipient().getType());
        data.put("recipient", this.notificationRecipientNormalizer.mapFromEntity(entity.getRecipient()));
        data.put("events", this.notificationEventNormalizer.mapFromEntity(entity.getEvents()));

        if (entity.getLocale() != null) {
            data.put("locale", entity.getLocale());
        }

        data.put("privacy_level", entity.getPrivacyLevel());

        return data;
    }

    public NotificationSubscriber mapToEntity(JSONObject data) throws NormalizerException {
        NotificationSubscriber notificationSubscriber = new NotificationSubscriber();

        if (data.has("id")) {
            notificationSubscriber.setId(data.getInt("id"));
        }

        notificationSubscriber.setStatus(data.getString("status"));
        notificationSubscriber.setPrivacyLevel(data.getString("privacy_level"));

        notificationSubscriber.setEvents(
            this.notificationEventNormalizer.mapToEntity(data.getJSONArray("events"))
        );


        notificationSubscriber.setRecipient(
            this.notificationRecipientNormalizer.mapToEntity(data.getJSONObject("recipient"))
        );
        notificationSubscriber.setLocale(data.getString("locale"));
        notificationSubscriber.setType(data.getString("type"));

        return notificationSubscriber;
    }
}
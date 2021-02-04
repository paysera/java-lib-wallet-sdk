package com.paysera.sdk.wallet.normalizers;

import com.google.gson.Gson;
import com.paysera.sdk.wallet.entities.notification.NotificationEvent;
import com.paysera.sdk.wallet.exceptions.NormalizerException;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Vytautas Gimbutas <v.gimbutas@evp.lt>
 */
public class NotificationEventNormalizer implements
    NormalizerInterface<NotificationEvent>,
    DenormalizerInterface<NotificationEvent>,
    ArrayNormalizerInterface<NotificationEvent>,
    ArrayDenormalizerInterface<NotificationEvent> {

    protected Gson jsonSerializer;

    public NotificationEventNormalizer(Gson jsonSerializer) {
        this.jsonSerializer = jsonSerializer;
    }

    public JSONObject mapFromEntity(NotificationEvent entity) throws NormalizerException {
        JSONObject data = new JSONObject();

        if (entity.getEventName() == null) {
            throw new NormalizerException("Event name is not specified");
        }

        if (entity.getObjectName() == null) {
            throw new NormalizerException("Object name is not specified");
        }

        data.put("event", entity.getEventName());
        data.put("object", entity.getObjectName());
        data.put("silent", entity.isSilent());
        data.put("android_channel", entity.getAndroidChannel());
        data.put("priority", entity.getPriority());

        if (entity.getParameters().size() > 0) {
            data.put("parameters", new JSONObject(entity.getParameters()));
        }

        return data;
    }

    public JSONArray mapFromEntity(List<NotificationEvent> entities) throws NormalizerException {
        JSONArray result = new JSONArray();

        for (NotificationEvent entity : entities) {
            result.put(this.mapFromEntity(entity));
        }

        return result;
    }

    public NotificationEvent mapToEntity(JSONObject data) throws NormalizerException {
        NotificationEvent event;
        try {
            event = new NotificationEvent(data.getString("object"), data.getString("event"));
        } catch (IllegalArgumentException exception) {
            event = new NotificationEvent(null, null);
        }

        event.setSilent(data.getBoolean("silent"));
        if (!data.isNull("android_channel")) {
            event.setAndroidChannel(data.getString("android_channel"));
        }
        if (!data.isNull("priority")) {
            event.setPriority(data.getString("priority"));
        }

        if (data.has("parameters")) {
            HashMap<String, Object> parameters = this.jsonSerializer.fromJson(
                data.getJSONObject("parameters").toString(),
                HashMap.class
            );

            event.setParameters(parameters);
        }

        return event;
    }

    public List<NotificationEvent> mapToEntity(JSONArray data) throws NormalizerException {
        List<NotificationEvent> events = new ArrayList<>();

        for (int i = 0; i < data.length(); ++i) {
            events.add(
                this.mapToEntity(data.getJSONObject(i))
            );
        }

        return events;
    }
}

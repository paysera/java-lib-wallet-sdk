package com.paysera.sdk.wallet.entities.notification;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Vytautas Gimbutas <v.gimbutas@evp.lt>
 */
public class NotificationEvent {
    public static final String OBJECT_NAME_USER = "user";
    public static final String OBJECT_NAME_STATEMENT = "statement";
    public static final String OBJECT_NAME_PENDING_PAYMENT = "pending_payment";
    public static final String OBJECT_NAME_TRANSACTION_REQUEST = "transaction_request";
    public static final String OBJECT_NAME_INFORMATION = "information";
    public static final String OBJECT_NAME_CONFIRMATION = "confirmation";
    public static final String OBJECT_NAME_CARD = "card";

    public static final String EVENT_NAME_REGISTERED = "registered";
    public static final String EVENT_NAME_CREATED = "created";
    public static final String EVENT_NAME_ALERT = "alert";
    public static final String EVENT_NAME_TRANSACTION_SUCCESSFUL = "transaction_successful";

    @SerializedName("event")
    protected String eventName;
    @SerializedName("object")
    protected String objectName;
    protected String androidChannel;
    protected String priority;
    protected boolean silent = false;
    protected Map<String, Object> parameters = new HashMap<>();

    public NotificationEvent(String objectName, String eventName) {
        this.objectName = objectName;
        this.eventName = eventName;
    }

    public NotificationEvent(
        String objectName,
        String eventName,
        Map<String, Object> parameters
    ) {
        this.objectName = objectName;
        this.eventName = eventName;
        this.parameters = parameters;
    }

    public NotificationEvent(
        String objectName,
        String eventName,
        Map<String, Object> parameters,
        boolean silent
    ) {
        this.objectName = objectName;
        this.eventName = eventName;
        this.parameters = parameters;
        this.silent = silent;
    }

    public NotificationEvent(
        String objectName,
        String eventName,
        String androidChannel,
        String priority
    ) {
        this.objectName = objectName;
        this.eventName = eventName;
        this.androidChannel = androidChannel;
        this.priority = priority;
    }

    public NotificationEvent(
        String objectName,
        String eventName,
        String androidChannel,
        String priority,
        Map<String, Object> parameters
    ) {
        this.objectName = objectName;
        this.eventName = eventName;
        this.androidChannel = androidChannel;
        this.priority = priority;
        this.parameters = parameters;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getAndroidChannel() {
        return androidChannel;
    }

    public void setAndroidChannel(String androidChannel) {
        this.androidChannel = androidChannel;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public boolean isSilent() {
        return silent;
    }

    public void setSilent(boolean silent) {
        this.silent = silent;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    public void addParameter(String key, Object value) {
        this.parameters.put(key, value);
    }

    @Override
    public boolean equals(Object object) {
        return
               object instanceof NotificationEvent
            && this.getEventName().equals(((NotificationEvent) object).getEventName())
            && this.getObjectName().equals(((NotificationEvent) object).getObjectName())
            && this.isSilent() == ((NotificationEvent) object).isSilent()
            && this.parameters.equals(((NotificationEvent) object).getParameters())
        ;
    }
}
package com.paysera.sdk.wallet.entities.notification;

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

    public static final String EVENT_NAME_REGISTERED = "registered";
    public static final String EVENT_NAME_CREATED = "created";
    public static final String EVENT_NAME_ALERT = "alert";

    protected String eventName;
    protected String objectName;
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

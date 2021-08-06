package com.paysera.lib.wallet.entities.notification;

import com.paysera.lib.wallet.entities.IdentifierAware;

/**
 * @author Vytautas Gimbutas <v.gimbutas@evp.lt>
 */
public class AndroidNotificationRecipient extends NotificationRecipient implements IdentifierAware {
    @Override
    public String getType() {
        return "android";
    }
}
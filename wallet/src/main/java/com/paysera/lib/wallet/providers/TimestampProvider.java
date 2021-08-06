package com.paysera.lib.wallet.providers;

import java.util.Locale;

public class TimestampProvider {
    private Long systemTime;
    private Long serverTime;

    public void setTime(Long systemTime, Long serverTime) {
        this.systemTime = systemTime;
        this.serverTime = serverTime;
    }

    public String getTimestamp() {
        Long time;

        if (systemTime == null || serverTime == null) {
            time = System.currentTimeMillis() / 1000L;
        } else {
            time = (System.currentTimeMillis() + (serverTime - systemTime)) / 1000L;
        }

        return String.format(Locale.ENGLISH, "%d", time);
    }
}
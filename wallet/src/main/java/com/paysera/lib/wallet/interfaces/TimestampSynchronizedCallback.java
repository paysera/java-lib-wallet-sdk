package com.paysera.lib.wallet.interfaces;

import java.util.Date;

public interface TimestampSynchronizedCallback {
    void onTimestampUpdated(Date serverTime, Date currentTime);
}
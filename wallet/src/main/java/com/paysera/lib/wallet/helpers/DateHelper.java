package com.paysera.lib.wallet.helpers;

import java.util.Date;

public class DateHelper {
    public static Date createFromUnixTimestampSeconds(int seconds) {
        return new Date((long) seconds * 1000);
    }

    public static Long convertDateToUnixTimestampSeconds(Date date) {
        if (date != null) {
          return date.getTime() / 1000L;
        }
        return null;
    }
}

package com.paysera.lib.wallet.helpers;

import java.util.List;

public class StringHelper {
    public static String listToString(List<String> list, String separator) {
        if (list == null || list.isEmpty()) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        for (String element : list) {
            sb.append(element);
            sb.append(separator);
        }

        if (sb.length() > 0 && sb.substring(sb.length() - 1).equals(separator)) {
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }
}

package com.paysera.lib.wallet.normalizers;

import com.paysera.lib.wallet.exceptions.NormalizerException;
import org.json.JSONObject;

public interface NormalizerInterface<T> {
    JSONObject mapFromEntity(T entity) throws NormalizerException;
}

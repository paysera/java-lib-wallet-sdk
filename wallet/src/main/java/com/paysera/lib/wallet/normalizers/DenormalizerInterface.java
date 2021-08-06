package com.paysera.lib.wallet.normalizers;

import com.paysera.lib.wallet.exceptions.NormalizerException;
import org.json.JSONObject;

public interface DenormalizerInterface<T> {
    T mapToEntity(JSONObject data) throws NormalizerException;
}
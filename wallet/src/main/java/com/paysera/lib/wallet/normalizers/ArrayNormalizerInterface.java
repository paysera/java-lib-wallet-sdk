package com.paysera.lib.wallet.normalizers;

import com.paysera.lib.wallet.exceptions.NormalizerException;
import org.json.JSONArray;

import java.util.List;

/**
 * @author Vytautas Gimbutas <v.gimbutas@evp.lt>
 */
public interface ArrayNormalizerInterface<T> {
    JSONArray mapFromEntity(List<T> entities) throws NormalizerException;
}
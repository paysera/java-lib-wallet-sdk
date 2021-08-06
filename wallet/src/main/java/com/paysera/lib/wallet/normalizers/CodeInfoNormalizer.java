package com.paysera.lib.wallet.normalizers;

import com.google.gson.Gson;
import com.paysera.lib.wallet.entities.CodeInfo;
import com.paysera.lib.wallet.exceptions.NormalizerException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * @author Vytautas Gimbutas <v.gimbutas@evp.lt>
 */
public class CodeInfoNormalizer implements DenormalizerInterface<CodeInfo> {
    protected Gson jsonSerializer;

    public CodeInfoNormalizer(Gson jsonSerializer) {
        this.jsonSerializer = jsonSerializer;
    }

    public CodeInfo mapToEntity(JSONObject data) throws NormalizerException {
        CodeInfo codeInfo = new CodeInfo();

        HashMap<String, Object> parameters = this.jsonSerializer.fromJson(
            data.getJSONObject("parameters").toString(),
            HashMap.class
        );

        codeInfo.setParameters(parameters);
        codeInfo.setType(data.getString("type"));

        return codeInfo;
    }
}
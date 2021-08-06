package com.paysera.lib.wallet.normalizers;

import com.paysera.lib.wallet.entities.Beacon;
import com.paysera.lib.wallet.exceptions.NormalizerException;
import org.json.JSONObject;

/**
 * @author Vytautas Gimbutas <v.gimbutas@evp.lt>
 */
public class BeaconNormalizer implements DenormalizerInterface<Beacon> {
    protected CodeInfoNormalizer codeInfoNormalizer;

    public BeaconNormalizer(CodeInfoNormalizer codeInfoNormalizer) {
        this.codeInfoNormalizer = codeInfoNormalizer;
    }

    public Beacon mapToEntity(JSONObject data) throws NormalizerException {
        Beacon beacon = new Beacon();

        beacon.setKey(data.getString("key"));

        if (data.has("code")) {
            beacon.setCode(data.getString("code"));
        }

        if (data.has("code_info")) {
            beacon.setCodeInfo(
                this.codeInfoNormalizer.mapToEntity(data.getJSONObject("code_info"))
            );
        }

        return beacon;
    }
}
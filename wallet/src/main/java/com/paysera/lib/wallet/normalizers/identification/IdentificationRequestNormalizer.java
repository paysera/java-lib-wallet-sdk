package com.paysera.lib.wallet.normalizers.identification;

import com.paysera.lib.wallet.entities.IdentificationRequest;
import com.paysera.lib.wallet.exceptions.NormalizerException;
import com.paysera.lib.wallet.normalizers.DenormalizerInterface;
import org.json.JSONObject;

/**
 * @author Vytautas Gimbutas <v.gimbutas@evp.lt>
 */
public class IdentificationRequestNormalizer implements DenormalizerInterface<IdentificationRequest> {

    private final IdentityDocumentNormalizer identityDocumentNormalizer;

    public IdentificationRequestNormalizer(IdentityDocumentNormalizer identityDocumentNormalizer) {
        this.identityDocumentNormalizer = identityDocumentNormalizer;
    }

    public IdentificationRequest mapToEntity(JSONObject data) throws NormalizerException {
        IdentificationRequest identificationRequest = new IdentificationRequest();

        identificationRequest.setId(data.getLong("id"));

        identificationRequest.setStatus(data.getString("status"));

        identificationRequest.setUserId(data.getInt("user_id"));

        if (data.has("identity_documents")) {
            identificationRequest.setIdentityDocumentList(
                identityDocumentNormalizer.mapToEntity(data.getJSONArray("identity_documents"))
            );
        }

        return identificationRequest;
    }
}
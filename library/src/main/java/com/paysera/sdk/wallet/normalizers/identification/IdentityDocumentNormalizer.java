package com.paysera.sdk.wallet.normalizers.identification;

import com.paysera.sdk.wallet.entities.IdentityDocument;
import com.paysera.sdk.wallet.exceptions.NormalizerException;
import com.paysera.sdk.wallet.normalizers.DenormalizerInterface;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Vytautas Gimbutas <v.gimbutas@evp.lt>
 */
public class IdentityDocumentNormalizer implements DenormalizerInterface<IdentityDocument> {

    public IdentityDocument mapToEntity(JSONObject data) throws NormalizerException {
        IdentityDocument identityDocument = new IdentityDocument();

        identityDocument.setId(data.getInt("id"));

        if (data.has("comment")) {
            identityDocument.setComment(data.getString("comment"));
        }

        if (data.has("review_status")) {
            identityDocument.setReviewStatus(data.getString("review_status"));
        }
        return identityDocument;
    }

    public List<IdentityDocument> mapToEntity(JSONArray data) throws NormalizerException {
        List<IdentityDocument> identityDocuments = new ArrayList<>();

        for (int i = 0; i < data.length(); i++) {
            identityDocuments.add(mapToEntity(data.getJSONObject(i)));
        }

        return identityDocuments;
    }
}

package com.paysera.lib.wallet.normalizers;

import com.paysera.lib.wallet.entities.PendingPayment;
import com.paysera.lib.wallet.exceptions.NormalizerException;
import com.paysera.lib.wallet.helpers.DateHelper;
import com.paysera.lib.wallet.helpers.MoneyHelper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vytautas Gimbutas <v.gimbutas@evp.lt>
 */
public class PendingPaymentNormalizer
    implements ArrayDenormalizerInterface<PendingPayment>, DenormalizerInterface<PendingPayment> {

    protected OtherPartyNormalizer otherPartyNormalizer;

    public PendingPaymentNormalizer(OtherPartyNormalizer otherPartyNormalizer) {
        this.otherPartyNormalizer = otherPartyNormalizer;
    }

    public List<PendingPayment> mapToEntity(JSONArray data) throws NormalizerException {
        List<PendingPayment> pendingPayments = new ArrayList<>();

        for (int i = 0; i < data.length(); ++i) {
            pendingPayments.add(this.mapToEntity(data.getJSONObject(i)));
        }

        return pendingPayments;
    }

    public PendingPayment mapToEntity(JSONObject data) throws NormalizerException {
        PendingPayment pendingPayment = new PendingPayment();

        pendingPayment.setId(data.getLong("id"));

        pendingPayment.setAmount(
            MoneyHelper.createFromCents(
                data.getString("currency"),
                data.getLong("amount")
            )
        );

        pendingPayment.setDetails(data.getString("details"));
        pendingPayment.setDirection(data.getString("direction"));

        if (data.has("date")) {
            pendingPayment.setDate(DateHelper.createFromUnixTimestampSeconds(data.getInt("date")));
        }

        if (data.has("other_party")) {
            pendingPayment.setOtherParty(
                this.otherPartyNormalizer.mapToEntity(
                    data.getJSONObject("other_party")
                )
            );
        }

        pendingPayment.setType(data.getString("type"));

        pendingPayment.setCancelable(data.getBoolean("cancelable"));

        if (data.has("valid_until")) {
            pendingPayment.setValidUntil(
                DateHelper.createFromUnixTimestampSeconds(
                    data.getInt("valid_until")
                )
            );
        }

        if (data.has("password")) {
            pendingPayment.setPassword(data.getString("password"));
        }

        return pendingPayment;
    }
}
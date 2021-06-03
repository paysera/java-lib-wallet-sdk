package com.paysera.lib.wallet.normalizers;

import com.paysera.lib.wallet.entities.OtherParty;
import com.paysera.lib.wallet.exceptions.NormalizerException;
import org.json.JSONObject;

/**
 * @author Vytautas Gimbutas <v.gimbutas@evp.lt>
 */
public class OtherPartyNormalizer
    implements DenormalizerInterface<OtherParty>, NormalizerInterface<OtherParty> {

    public OtherParty mapToEntity(JSONObject data) throws NormalizerException {
        OtherParty otherParty = new OtherParty();

        if (data.has("display_name")) {
            otherParty.setDisplayName(data.getString("display_name"));
        }

        if (data.has("wallet_id")) {
            otherParty.setWalletId(data.getInt("wallet_id"));
        }

        if (data.has("user_id")) {
            otherParty.setUserId(data.getInt("user_id"));
        }

        if (data.has("code")) {
            otherParty.setCode(data.getString("code"));
        }

        if (data.has("account_number")) {
            otherParty.setAccountNumber(data.getString("account_number"));
        }

        if (data.has("phone")) {
            otherParty.setPhone(data.getString("phone"));
        }

        if (data.has("email")) {
            otherParty.setEmail(data.getString("email"));
        }
        if (data.has("bic")) {
            otherParty.setBic(data.getString("bic"));
        }

        return otherParty;
    }

    public JSONObject mapFromEntity(OtherParty entity) throws NormalizerException {
        JSONObject data = new JSONObject();

        if (entity.getDisplayName() != null) {
            data.put("display_name", entity.getDisplayName());
        }

        if (entity.getWalletId() != null) {
            data.put("wallet_id", entity.getWalletId());
        }

        if (entity.getUserId() != null) {
            data.put("user_id", entity.getUserId());
        }

        if (entity.getCode() != null) {
            data.put("code", entity.getCode());
        }

        if (entity.getAccountNumber() != null) {
            data.put("account_number", entity.getAccountNumber());
        }

        if (entity.getPhone() != null) {
            data.put("phone", entity.getPhone());
        }

        if (entity.getEmail() != null) {
            data.put("email", entity.getEmail());
        }

        if (entity.getBic() != null) {
            data.put("bic", entity.getBic());
        }

        return data;
    }
}
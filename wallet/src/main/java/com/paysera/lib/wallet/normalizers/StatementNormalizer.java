package com.paysera.lib.wallet.normalizers;

import com.paysera.lib.wallet.entities.Statement;
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
public class StatementNormalizer
    implements ArrayDenormalizerInterface<Statement>, DenormalizerInterface<Statement> {

    protected OtherPartyNormalizer otherPartyNormalizer;

    public StatementNormalizer(OtherPartyNormalizer otherPartyNormalizer) {
        this.otherPartyNormalizer = otherPartyNormalizer;
    }

    public List<Statement> mapToEntity(JSONArray data) throws NormalizerException {
        List<Statement> statements = new ArrayList<>();

        for (int i = 0; i < data.length(); ++i) {
            statements.add(this.mapToEntity(data.getJSONObject(i)));
        }

        return statements;
    }

    public Statement mapToEntity(JSONObject data) throws NormalizerException {
        Statement statement = new Statement();

        statement.setId(data.getLong("id"));

        statement.setAmount(MoneyHelper.createFromCents(
            data.getString("currency"),
            data.getLong("amount")
        ));

        statement.setDetails(data.getString("details"));
        statement.setDirection(data.getString("direction"));
        statement.setDate(DateHelper.createFromUnixTimestampSeconds(data.getInt("date")));

        if (data.has("other_party")) {
            statement.setOtherParty(this.otherPartyNormalizer.mapToEntity(
                data.getJSONObject("other_party")
            ));
        }

        if (data.has("type")) {
            statement.setType(data.getString("type").toUpperCase());
        }

        if (data.has("transfer_id")) {
            statement.setTransferId(data.getInt("transfer_id"));
        }

        return statement;
    }
}
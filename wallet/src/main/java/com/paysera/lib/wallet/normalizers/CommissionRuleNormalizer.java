package com.paysera.lib.wallet.normalizers;

import com.paysera.lib.wallet.entities.CommissionRule;
import com.paysera.lib.wallet.exceptions.NormalizerException;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.json.JSONObject;

import java.math.BigDecimal;

/**
 * @author Vytautas Gimbutas <v.gimbutas@evp.lt>
 */
public class CommissionRuleNormalizer implements
    DenormalizerInterface<CommissionRule>,
    NormalizerInterface<CommissionRule> {

    public CommissionRule mapToEntity(JSONObject data) throws NormalizerException {
        CommissionRule rule = new CommissionRule();

        if (data.has("percent")) {
            rule.setPercent(new BigDecimal(data.getString("percent")));
        }

        if (data.has("fix")) {
            rule.setFix(
                Money.of(CurrencyUnit.of(data.getString("currency")), new BigDecimal(data.getString("fix")))
            );
        }

        if (data.has("min")) {
            rule.setMin(
                Money.of(CurrencyUnit.of(data.getString("currency")), new BigDecimal(data.getString("min")))
            );
        }

        if (data.has("max")) {
            rule.setMax(
                Money.of(CurrencyUnit.of(data.getString("currency")), new BigDecimal(data.getString("max")))
            );
        }

        return rule;
    }

    public JSONObject mapFromEntity(CommissionRule entity) throws NormalizerException {
        JSONObject data = new JSONObject();

        if (entity.getPercent() != null) {
            data.put("percent", entity.getPercent());
        }

        if (entity.getFix() != null) {
            data.put("fix", entity.getFix().getAmount());
            data.put("currency", entity.getFix().getCurrencyUnit().getCode());
        }

        if (entity.getMin() != null) {
            data.put("min", entity.getMin().getAmount());
            data.put("currency", entity.getFix().getCurrencyUnit().getCode());
        }

        if (entity.getMax() != null) {
            data.put("max", entity.getMax().getAmount());
            data.put("currency", entity.getFix().getCurrencyUnit().getCode());
        }

        return data;
    }
}
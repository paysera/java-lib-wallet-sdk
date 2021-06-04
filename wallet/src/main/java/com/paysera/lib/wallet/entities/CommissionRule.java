package com.paysera.lib.wallet.entities;

import org.joda.money.Money;

import java.math.BigDecimal;

/**
 * @author Vytautas Gimbutas <v.gimbutas@evp.lt>
 */
public class CommissionRule {
    private BigDecimal percent;
    private Money fix;
    private Money min;
    private Money max;

    public BigDecimal getPercent() {
        return percent;
    }

    public void setPercent(BigDecimal percent) {
        this.percent = percent;
    }

    public Money getFix() {
        return fix;
    }

    public void setFix(Money fix) {
        this.fix = fix;
    }

    public Money getMin() {
        return min;
    }

    public void setMin(Money min) {
        this.min = min;
    }

    public Money getMax() {
        return max;
    }

    public void setMax(Money max) {
        this.max = max;
    }
}
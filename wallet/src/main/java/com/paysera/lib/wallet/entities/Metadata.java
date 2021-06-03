package com.paysera.lib.wallet.entities;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Vytautas Gimbutas <v.gimbutas@evp.lt>
 */
public class Metadata {
    protected Integer total;
    protected Integer offset;
    protected Integer limit;
    protected Boolean hasNext;
    protected Boolean hasPrevious;
    protected Map<String, String> cursors = new HashMap<>();

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Boolean getHasNext() {
        return hasNext;
    }

    public void setHasNext(Boolean hasNext) {
        this.hasNext = hasNext;
    }

    public Boolean getHasPrevious() {
        return hasPrevious;
    }

    public void setHasPrevious(Boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }

    public String getAfterCursor() {
        return cursors.get("after");
    }

    public String getBeforeCursor() {
        return cursors.get("before");
    }

    public void setCursors(Map<String, String> cursors) {
        this.cursors = cursors;
    }
}
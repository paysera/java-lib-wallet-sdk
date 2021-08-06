package com.paysera.lib.wallet.oauth;

import java.util.ArrayList;
import java.util.List;

public class ScopeBuilder {
    protected List<String> scopes = new ArrayList<>();

    public static ScopeBuilder builder() {
        return new ScopeBuilder();
    }

    public ScopeBuilder all() {
        for (Scope scope : Scope.values()) {
            this.scopes.add(scope.name());
        }
        return this;
    }

    public ScopeBuilder addScope(String scope) {
        this.scopes.add(scope);

        return this;
    }

    public ScopeBuilder currencyConvert() {
        this.scopes.add(ExtendedScope.convert_currency.name());

        return this;
    }

    public ScopeBuilder bankTransfers() {
        this.scopes.add(ExtendedScope.make_bank_transfers.name());

        return this;
    }

    public String build() {
        StringBuilder result = new StringBuilder();
        for (String scope : this.scopes) {
            result.append(scope).append(" ");
        }

        return result.toString().trim();
    }

    public List<String> buildAsList() {
        return this.scopes;
    }

    public static ScopeBuilder fromList(List<String> scopeList) {
        ScopeBuilder builder = ScopeBuilder.builder();

        for (String scope : scopeList) {
            builder.addScope(scope);
        }

        return builder;
    }
}
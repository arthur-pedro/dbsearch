package com.dbsearch.api.config.tenant;

public enum FilePath {
    BLOCKLIST("select_blocklist.txt"),
    TENANTS("api/tenants");

    private final String value;

    FilePath(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

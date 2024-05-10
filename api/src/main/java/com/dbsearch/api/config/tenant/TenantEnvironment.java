package com.dbsearch.api.config.tenant;

public enum TenantEnvironment {
    DEVELOPMENT("development"),
    TEST("test"),
    PRODUCTION("production");

    TenantEnvironment(String development) {}

    public static TenantEnvironment fromString(String environment) {
        return switch (environment) {
            case "development" -> DEVELOPMENT;
            case "test" -> TEST;
            case "production" -> PRODUCTION;
            default -> throw new IllegalArgumentException("Invalid environment: " + environment);
        };
    }
}

package com.dbsearch.api.config.tenant;

import org.springframework.boot.jdbc.DataSourceBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class TenantPropertyFile {
    DataSourceBuilder<?> dataSourceBuilder;
    Properties tenantProperties;
    public TenantPropertyFile(File propertyFile) {
        this.tenantProperties = new Properties();
        this.dataSourceBuilder = DataSourceBuilder.create();
        if (!propertyFile.exists()) {
            throw new RuntimeException("Property file not found");
        }
        try {
            tenantProperties.load(new FileInputStream(propertyFile));
        } catch (Exception e) {
            throw new RuntimeException("Problem in tenant datasource:" + e);
        }
    }

    public String getTenantId() {
        return this.tenantProperties.getProperty("id");
    }

    public String getDatabaseUrl() {
        return tenantProperties.getProperty("datasource.url");
    }

    public String getDatabase() {
        return tenantProperties.getProperty("datasource.database");
    }

    public String getDriverClassName() {
        return tenantProperties.getProperty("datasource.driver-class-name");
    }

    public String getHost() {
        return tenantProperties.getProperty("datasource.host");
    }

    public String getPort() {
        return tenantProperties.getProperty("datasource.port");
    }

    public String getName() {
        return tenantProperties.getProperty("name");
    }

    public String getDescription() {
        return tenantProperties.getProperty("description");
    }

    public TenantEnvironment getEnvironment() {
        return TenantEnvironment.fromString(tenantProperties.getProperty("environment"));
    }
}

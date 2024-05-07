package com.dbsearch.api.config.tenant;

import java.io.File;

public class TenantContext {
    private static final ThreadLocal<String> CURRENT_TENANT = new ThreadLocal<>();
  
    public static String getCurrentTenant() {
        return CURRENT_TENANT.get();
    }
  
    public static void setCurrentTenant(String tenant) {
        CURRENT_TENANT.set(tenant);
    }

    public static File getCurrentTenantFile() {
        return new File("api"+ File.separator + "tenants" + File.separator +  getCurrentTenant() + ".properties");
    }
}
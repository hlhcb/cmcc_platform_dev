package com.cmcc.system.domain;

/**
 * @author:terry
 * @create: 2024-05-12 06:20
 * @Description: tenantFormDataVo
 */
public class TenantFormDataVo {

    private String tenant_name;

    private String tenant_card_id;

    private String tenant_tel;

    public String getTenant_name() {
        return tenant_name;
    }

    public void setTenant_name(String tenant_name) {
        this.tenant_name = tenant_name;
    }

    public String getTenant_card_id() {
        return tenant_card_id;
    }

    public void setTenant_card_id(String tenant_card_id) {
        this.tenant_card_id = tenant_card_id;
    }

    public String getTenant_tel() {
        return tenant_tel;
    }

    public void setTenant_tel(String tenant_tel) {
        this.tenant_tel = tenant_tel;
    }
}

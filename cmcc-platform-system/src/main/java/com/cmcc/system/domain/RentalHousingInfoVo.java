package com.cmcc.system.domain;

import java.util.List;

/**
 * @author:terry
 * @create: 2024-05-11 16:19
 * @Description: community_config出租房
 */
public class RentalHousingInfoVo {

    private RentalHousingInfoBaseVo baseFormData;

    private List<TenantFormDataVo> tenantFormData;

    public RentalHousingInfoBaseVo getBaseFormData() {
        return baseFormData;
    }

    public void setBaseFormData(RentalHousingInfoBaseVo baseFormData) {
        this.baseFormData = baseFormData;
    }

    public List<TenantFormDataVo> getTenantFormData() {
        return tenantFormData;
    }

    public void setTenantFormData(List<TenantFormDataVo> tenantFormData) {
        this.tenantFormData = tenantFormData;
    }
}

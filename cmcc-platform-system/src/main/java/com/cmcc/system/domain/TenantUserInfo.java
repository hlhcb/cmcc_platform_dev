package com.cmcc.system.domain;

import com.cmcc.common.annotation.Excel;
import com.cmcc.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 租客信息对象 tenant_user_info
 * 
 * @author terry
 * @date 2024-05-11
 */
public class TenantUserInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 出租房主键id */
    @Excel(name = "出租房主键id")
    private Long rentalHousingId;

    /** 租客姓名 */
    @Excel(name = "租客姓名")
    private String tenantName;

    /** 租客身份证 */
    @Excel(name = "租客身份证")
    private String tenantCardId;

    /** 租客电话 */
    @Excel(name = "租客电话")
    private String tenantTel;

    /** 排序id */
    @Excel(name = "排序id")
    private Long orderId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setRentalHousingId(Long rentalHousingId) 
    {
        this.rentalHousingId = rentalHousingId;
    }

    public Long getRentalHousingId() 
    {
        return rentalHousingId;
    }
    public void setTenantName(String tenantName) 
    {
        this.tenantName = tenantName;
    }

    public String getTenantName() 
    {
        return tenantName;
    }
    public void setTenantCardId(String tenantCardId) 
    {
        this.tenantCardId = tenantCardId;
    }

    public String getTenantCardId() 
    {
        return tenantCardId;
    }
    public void setTenantTel(String tenantTel) 
    {
        this.tenantTel = tenantTel;
    }

    public String getTenantTel() 
    {
        return tenantTel;
    }
    public void setOrderId(Long orderId) 
    {
        this.orderId = orderId;
    }

    public Long getOrderId() 
    {
        return orderId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("rentalHousingId", getRentalHousingId())
            .append("tenantName", getTenantName())
            .append("tenantCardId", getTenantCardId())
            .append("tenantTel", getTenantTel())
            .append("orderId", getOrderId())
            .toString();
    }
}

package com.cmcc.system.domain;

import com.cmcc.common.annotation.Excel;
import com.cmcc.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 房东访问对象 tenant_inteview_record
 * 
 * @author terry
 * @date 2024-02-24
 */
public class TenantInteviewRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 访问记录id */
    private Long inteviewId;

    /** 房东名称 */
    @Excel(name = "房东名称")
    private String tenantName;

    /** 房东电话 */
    @Excel(name = "房东电话")
    private String tenantTel;

    /** 房东微信 */
    @Excel(name = "房东微信")
    private String tenantWechat;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setInteviewId(Long inteviewId) 
    {
        this.inteviewId = inteviewId;
    }

    public Long getInteviewId() 
    {
        return inteviewId;
    }
    public void setTenantName(String tenantName) 
    {
        this.tenantName = tenantName;
    }

    public String getTenantName() 
    {
        return tenantName;
    }
    public void setTenantTel(String tenantTel) 
    {
        this.tenantTel = tenantTel;
    }

    public String getTenantTel() 
    {
        return tenantTel;
    }
    public void setTenantWechat(String tenantWechat) 
    {
        this.tenantWechat = tenantWechat;
    }

    public String getTenantWechat() 
    {
        return tenantWechat;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("inteviewId", getInteviewId())
            .append("tenantName", getTenantName())
            .append("tenantTel", getTenantTel())
            .append("tenantWechat", getTenantWechat())
            .toString();
    }
}

package com.cmcc.system.domain;

import com.cmcc.common.annotation.Excel;
import com.cmcc.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 网格信息对象 marketing_gard_info
 * 
 * @author terry
 * @date 2023-12-28
 */
public class MarketingGardInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 系统区县编码 */
    @Excel(name = "系统区县编码")
    private String sysCountyCode;

    /** 区县编码 */
    @Excel(name = "区县编码")
    private String countyCode;

    /** 区县名称 */
    @Excel(name = "区县名称")
    private String countyName;

    /** 网格编码 */
    @Excel(name = "网格编码")
    private String centerCode;

    /** 网格名称 */
    @Excel(name = "网格名称")
    private String centerName;

    /** 微格编码 */
    @Excel(name = "微格编码")
    private String townCode;

    /** 微格名称 */
    @Excel(name = "微格名称")
    private String townName;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setSysCountyCode(String sysCountyCode) 
    {
        this.sysCountyCode = sysCountyCode;
    }

    public String getSysCountyCode() 
    {
        return sysCountyCode;
    }
    public void setCountyCode(String countyCode) 
    {
        this.countyCode = countyCode;
    }

    public String getCountyCode() 
    {
        return countyCode;
    }
    public void setCountyName(String countyName) 
    {
        this.countyName = countyName;
    }

    public String getCountyName() 
    {
        return countyName;
    }
    public void setCenterCode(String centerCode) 
    {
        this.centerCode = centerCode;
    }

    public String getCenterCode() 
    {
        return centerCode;
    }
    public void setCenterName(String centerName) 
    {
        this.centerName = centerName;
    }

    public String getCenterName() 
    {
        return centerName;
    }
    public void setTownCode(String townCode) 
    {
        this.townCode = townCode;
    }

    public String getTownCode() 
    {
        return townCode;
    }
    public void setTownName(String townName) 
    {
        this.townName = townName;
    }

    public String getTownName() 
    {
        return townName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("sysCountyCode", getSysCountyCode())
            .append("countyCode", getCountyCode())
            .append("countyName", getCountyName())
            .append("centerCode", getCenterCode())
            .append("centerName", getCenterName())
            .append("townCode", getTownCode())
            .append("townName", getTownName())
            .toString();
    }
}

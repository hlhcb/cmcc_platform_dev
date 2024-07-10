package com.cmcc.system.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.cmcc.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.cmcc.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 业务办理统计表对象 business_processing_count
 * 
 * @author terry
 * @date 2023-12-26
 */
public class BusinessProcessingCount extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private String id;

    /** 业务办理时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "业务办理时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date recordDate;

    /** 1：等效、2：等效带宽、3：5G合约、4：CHN产品 */
    @Excel(name = "1：等效、2：等效带宽、3：5G合约、4：CHN产品")
    private String marketingType;

    /** 区县名称 */
    @Excel(name = "区县名称")
    private String countyName;

    /** 区县id */
    private String countyId;

    /** 业务数量 */
    @Excel(name = "业务数量")
    private BigDecimal businessCount;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setRecordDate(Date recordDate) 
    {
        this.recordDate = recordDate;
    }

    public Date getRecordDate() 
    {
        return recordDate;
    }
    public void setMarketingType(String marketingType) 
    {
        this.marketingType = marketingType;
    }

    public String getMarketingType() 
    {
        return marketingType;
    }
    public void setCountyName(String countyName) 
    {
        this.countyName = countyName;
    }

    public String getCountyName() 
    {
        return countyName;
    }
    public void setCountyId(String countyId) 
    {
        this.countyId = countyId;
    }

    public String getCountyId() 
    {
        return countyId;
    }
    public void setBusinessCount(BigDecimal businessCount) 
    {
        this.businessCount = businessCount;
    }

    public BigDecimal getBusinessCount() 
    {
        return businessCount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("recordDate", getRecordDate())
            .append("marketingType", getMarketingType())
            .append("countyName", getCountyName())
            .append("countyId", getCountyId())
            .append("businessCount", getBusinessCount())
            .toString();
    }
}

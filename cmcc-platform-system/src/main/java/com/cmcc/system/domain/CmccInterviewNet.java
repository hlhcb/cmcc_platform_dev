package com.cmcc.system.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.cmcc.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.cmcc.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 客户走访线路信息对象 cmcc_interview_net
 * 
 * @author terry
 * @date 2024-05-17
 */
public class CmccInterviewNet extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 走访记录id */
    private Long interviewId;

    /** 类型 */
    @Excel(name = "类型")
    private String netType;

    /** 网络运营商 */
    @Excel(name = "网络运营商")
    private String ispName;

    /** 带宽（M） */
    @Excel(name = "带宽", readConverterExp = "M=")
    private Long netSpeed;

    /** 月单价 */
    @Excel(name = "月单价")
    private BigDecimal netPrice;

    /** 到期时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "到期时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setInterviewId(Long interviewId) 
    {
        this.interviewId = interviewId;
    }

    public Long getInterviewId() 
    {
        return interviewId;
    }
    public void setNetType(String netType) 
    {
        this.netType = netType;
    }

    public String getNetType() 
    {
        return netType;
    }
    public void setIspName(String ispName) 
    {
        this.ispName = ispName;
    }

    public String getIspName() 
    {
        return ispName;
    }
    public void setNetSpeed(Long netSpeed) 
    {
        this.netSpeed = netSpeed;
    }

    public Long getNetSpeed() 
    {
        return netSpeed;
    }
    public void setNetPrice(BigDecimal netPrice) 
    {
        this.netPrice = netPrice;
    }

    public BigDecimal getNetPrice() 
    {
        return netPrice;
    }
    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("interviewId", getInterviewId())
            .append("netType", getNetType())
            .append("ispName", getIspName())
            .append("netSpeed", getNetSpeed())
            .append("netPrice", getNetPrice())
            .append("endTime", getEndTime())
            .toString();
    }
}

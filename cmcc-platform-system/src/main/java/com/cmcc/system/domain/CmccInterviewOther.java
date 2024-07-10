package com.cmcc.system.domain;

import com.cmcc.common.annotation.Excel;
import com.cmcc.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 客户走访其他需求对象 cmcc_interview_other
 * 
 * @author terry
 * @date 2024-05-20
 */
public class CmccInterviewOther extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 走访id */
    private Long interviewId;

    /** 类型 */
    @Excel(name = "类型")
    private String businessType;

    /** 详情 */
    @Excel(name = "详情")
    private String businessDetail;

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
    public void setBusinessType(String businessType) 
    {
        this.businessType = businessType;
    }

    public String getBusinessType() 
    {
        return businessType;
    }
    public void setBusinessDetail(String businessDetail) 
    {
        this.businessDetail = businessDetail;
    }

    public String getBusinessDetail() 
    {
        return businessDetail;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("interviewId", getInterviewId())
            .append("businessType", getBusinessType())
            .append("businessDetail", getBusinessDetail())
            .toString();
    }
}

package com.cmcc.system.domain;

import java.util.Date;

import com.cmcc.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.cmcc.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 接触人数统计对象 contact_people_count
 * 
 * @author terry
 * @date 2023-12-26
 */
public class ContactPeopleCount extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private String id;

    /** 记录时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "记录时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date recordDate;

    /** 区县id */
    private String countyId;

    /** 区县名称 */
    @Excel(name = "区县名称")
    private String countyName;

    /** 接触人数 */
    @Excel(name = "接触人数")
    private Long contactNumber;

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
    public void setCountyId(String countyId) 
    {
        this.countyId = countyId;
    }

    public String getCountyId() 
    {
        return countyId;
    }
    public void setCountyName(String countyName) 
    {
        this.countyName = countyName;
    }

    public String getCountyName() 
    {
        return countyName;
    }
    public void setContactNumber(Long contactNumber) 
    {
        this.contactNumber = contactNumber;
    }

    public Long getContactNumber() 
    {
        return contactNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("recordDate", getRecordDate())
            .append("countyId", getCountyId())
            .append("countyName", getCountyName())
            .append("contactNumber", getContactNumber())
            .toString();
    }
}

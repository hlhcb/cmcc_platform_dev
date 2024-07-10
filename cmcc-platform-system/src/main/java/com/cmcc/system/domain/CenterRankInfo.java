package com.cmcc.system.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.cmcc.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.cmcc.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 网格龙虎榜对象 center_rank_info
 * 
 * @author terry
 * @date 2023-12-28
 */
public class CenterRankInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private String id;

    /** 区县id */
    @Excel(name = "区县id")
    private String countyId;

    /** 区县名称 */
    @Excel(name = "区县名称")
    private String countyName;

    /** 网格id */
    @Excel(name = "网格id")
    private String centerId;

    /** 网格名称 */
    @Excel(name = "网格名称")
    private String centerName;

    /** 微格id */
    @Excel(name = "微格id")
    private String townId;

    /** 微格名称 */
    @Excel(name = "微格名称")
    private String townName;

    /** 记录时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "记录时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date recordDate;

    /** 得分 */
    @Excel(name = "得分")
    private BigDecimal score;

    /** 排名 */
    @Excel(name = "排名")
    private Long rank;

    /** 指标类型 */
    @Excel(name = "指标类型")
    private String marketingType;

    /** 指标名称 */
    @Excel(name = "指标名称")
    private String marketingTypeName;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
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
    public void setCenterId(String centerId) 
    {
        this.centerId = centerId;
    }

    public String getCenterId() 
    {
        return centerId;
    }
    public void setCenterName(String centerName) 
    {
        this.centerName = centerName;
    }

    public String getCenterName() 
    {
        return centerName;
    }
    public void setTownId(String townId) 
    {
        this.townId = townId;
    }

    public String getTownId() 
    {
        return townId;
    }
    public void setTownName(String townName) 
    {
        this.townName = townName;
    }

    public String getTownName() 
    {
        return townName;
    }
    public void setRecordDate(Date recordDate) 
    {
        this.recordDate = recordDate;
    }

    public Date getRecordDate() 
    {
        return recordDate;
    }
    public void setScore(BigDecimal score) 
    {
        this.score = score;
    }

    public BigDecimal getScore() 
    {
        return score;
    }
    public void setRank(Long rank) 
    {
        this.rank = rank;
    }

    public Long getRank() 
    {
        return rank;
    }
    public void setMarketingType(String marketingType) 
    {
        this.marketingType = marketingType;
    }

    public String getMarketingType() 
    {
        return marketingType;
    }
    public void setMarketingTypeName(String marketingTypeName) 
    {
        this.marketingTypeName = marketingTypeName;
    }

    public String getMarketingTypeName() 
    {
        return marketingTypeName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("countyId", getCountyId())
            .append("countyName", getCountyName())
            .append("centerId", getCenterId())
            .append("centerName", getCenterName())
            .append("townId", getTownId())
            .append("townName", getTownName())
            .append("recordDate", getRecordDate())
            .append("score", getScore())
            .append("rank", getRank())
            .append("marketingType", getMarketingType())
            .append("marketingTypeName", getMarketingTypeName())
            .toString();
    }
}

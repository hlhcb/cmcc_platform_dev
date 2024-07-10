package com.cmcc.system.domain;

import com.cmcc.common.annotation.Excel;
import com.cmcc.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 归属地管理对象 belonging_place_info
 * 
 * @author terry
 * @date 2024-02-24
 */
public class BelongingPlaceInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 派出所id */
    @Excel(name = "派出所id")
    private String localPoliceStationId;

    /** 派出所名称 */
    @Excel(name = "派出所名称")
    private String localPoliceStationName;

    /** 所属地id */
    @Excel(name = "所属地id")
    private String belongingPlaceId;

    /** 所属地名称 */
    @Excel(name = "所属地名称")
    private String belongingPlaceName;

    private Long parentDeptId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setLocalPoliceStationId(String localPoliceStationId) 
    {
        this.localPoliceStationId = localPoliceStationId;
    }

    public String getLocalPoliceStationId() 
    {
        return localPoliceStationId;
    }
    public void setLocalPoliceStationName(String localPoliceStationName) 
    {
        this.localPoliceStationName = localPoliceStationName;
    }

    public String getLocalPoliceStationName() 
    {
        return localPoliceStationName;
    }
    public void setBelongingPlaceId(String belongingPlaceId) 
    {
        this.belongingPlaceId = belongingPlaceId;
    }

    public String getBelongingPlaceId() 
    {
        return belongingPlaceId;
    }
    public void setBelongingPlaceName(String belongingPlaceName) 
    {
        this.belongingPlaceName = belongingPlaceName;
    }

    public String getBelongingPlaceName() 
    {
        return belongingPlaceName;
    }

    public Long getParentDeptId() {
        return parentDeptId;
    }

    public void setParentDeptId(Long parentDeptId) {
        this.parentDeptId = parentDeptId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("localPoliceStationId", getLocalPoliceStationId())
            .append("localPoliceStationName", getLocalPoliceStationName())
            .append("belongingPlaceId", getBelongingPlaceId())
            .append("belongingPlaceName", getBelongingPlaceName())
            .toString();
    }
}

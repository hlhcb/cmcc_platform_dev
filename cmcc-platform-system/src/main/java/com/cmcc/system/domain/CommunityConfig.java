package com.cmcc.system.domain;

import com.cmcc.common.annotation.Excel;
import com.cmcc.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 归属地管理对象 community_config
 * 
 * @author terry
 * @date 2024-05-11
 */
public class CommunityConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 归属地 */
    @Excel(name = "归属地")
    private String belongingPlace;

    /** 小区 */
    @Excel(name = "小区")
    private String community;

    private Long deptId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setBelongingPlace(String belongingPlace) 
    {
        this.belongingPlace = belongingPlace;
    }

    public String getBelongingPlace() 
    {
        return belongingPlace;
    }
    public void setCommunity(String community) 
    {
        this.community = community;
    }

    public String getCommunity() 
    {
        return community;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("belongingPlace", getBelongingPlace())
            .append("community", getCommunity())
            .toString();
    }
}

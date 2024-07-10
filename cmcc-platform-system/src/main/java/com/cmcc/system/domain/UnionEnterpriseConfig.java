package com.cmcc.system.domain;

import com.cmcc.common.annotation.Excel;
import com.cmcc.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 联防配置对象 union_enterprise_config
 * 
 * @author terry
 * @date 2024-04-18
 */
public class UnionEnterpriseConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 联防区名称 */
    @Excel(name = "联防区名称")
    private String unionName;

    /** 部门id */
    @Excel(name = "部门id")
    private Long deptId;

    /** 部门名称 */
    @Excel(name = "部门名称")
    private String deptName;

    /** 联防长id */
    @Excel(name = "联防长id")
    private Long unionUserId;

    /** 联防长名称 */
    @Excel(name = "联防长名称")
    private String unionUserName;

    /** 联防长电话 */
    @Excel(name = "联防长电话")
    private String unionUserTel;

    /** 联防id */
    @Excel(name = "联防id")
    private Long unionId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUnionName(String unionName) 
    {
        this.unionName = unionName;
    }

    public String getUnionName() 
    {
        return unionName;
    }
    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }
    public void setDeptName(String deptName) 
    {
        this.deptName = deptName;
    }

    public String getDeptName() 
    {
        return deptName;
    }
    public void setUnionUserId(Long unionUserId) 
    {
        this.unionUserId = unionUserId;
    }

    public Long getUnionUserId() 
    {
        return unionUserId;
    }
    public void setUnionUserName(String unionUserName) 
    {
        this.unionUserName = unionUserName;
    }

    public String getUnionUserName() 
    {
        return unionUserName;
    }
    public void setUnionUserTel(String unionUserTel) 
    {
        this.unionUserTel = unionUserTel;
    }

    public String getUnionUserTel() 
    {
        return unionUserTel;
    }
    public void setUnionId(Long unionId) 
    {
        this.unionId = unionId;
    }

    public Long getUnionId() 
    {
        return unionId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("unionName", getUnionName())
            .append("deptId", getDeptId())
            .append("deptName", getDeptName())
            .append("unionUserId", getUnionUserId())
            .append("unionUserName", getUnionUserName())
            .append("unionUserTel", getUnionUserTel())
            .append("remark", getRemark())
            .append("unionId", getUnionId())
            .toString();
    }
}

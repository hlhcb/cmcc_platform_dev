package com.cmcc.system.domain;

import com.cmcc.common.annotation.Excel;
import com.cmcc.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 企业信息对象 cmcc_enterprise_info
 * 
 * @author terry
 * @date 2024-06-20
 */
public class CmccEnterpriseInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 楼宇或者园区id */
    @Excel(name = "楼宇或者园区id")
    private Long groupId;

    /** 企业名称 */
    @Excel(name = "企业名称")
    private String enterpriseName;

    /** 企业地址 */
    @Excel(name = "企业地址")
    private String enterpriseAddr;

    /** 客户名称 */
    @Excel(name = "客户名称")
    private String customerName;

    /** 客户电话 */
    @Excel(name = "客户电话")
    private String customerTel;

    /** 客户职位 */
    @Excel(name = "客户职位")
    private String customerPosition;

    /** 创建人id */
    @Excel(name = "创建人id")
    private Long createUserId;

    /** 创建人姓名 */
    @Excel(name = "创建人姓名")
    private String createUserName;

    /** 更新人id */
    @Excel(name = "更新人id")
    private Long updateUserId;

    /** 更新人姓名 */
    @Excel(name = "更新人姓名")
    private String updateUserName;

    /** 法人姓名 */
    @Excel(name = "法人姓名")
    private String corporation;

    /** 企业状态 */
    @Excel(name = "企业状态")
    private String enterpriseStatus;


    private String groupName;

    private Long deptId;

    /** 企业状态 */
    @Excel(name = "区县名称")
    private String deptName;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setGroupId(Long groupId) 
    {
        this.groupId = groupId;
    }

    public Long getGroupId() 
    {
        return groupId;
    }
    public void setEnterpriseName(String enterpriseName) 
    {
        this.enterpriseName = enterpriseName;
    }

    public String getEnterpriseName() 
    {
        return enterpriseName;
    }
    public void setEnterpriseAddr(String enterpriseAddr) 
    {
        this.enterpriseAddr = enterpriseAddr;
    }

    public String getEnterpriseAddr() 
    {
        return enterpriseAddr;
    }

    public void setCustomerTel(String customerTel) 
    {
        this.customerTel = customerTel;
    }

    public String getCustomerTel() 
    {
        return customerTel;
    }
    public void setCustomerPosition(String customerPosition) 
    {
        this.customerPosition = customerPosition;
    }

    public String getCustomerPosition() 
    {
        return customerPosition;
    }
    public void setCreateUserId(Long createUserId) 
    {
        this.createUserId = createUserId;
    }

    public Long getCreateUserId() 
    {
        return createUserId;
    }
    public void setCreateUserName(String createUserName) 
    {
        this.createUserName = createUserName;
    }

    public String getCreateUserName() 
    {
        return createUserName;
    }
    public void setUpdateUserId(Long updateUserId) 
    {
        this.updateUserId = updateUserId;
    }

    public Long getUpdateUserId() 
    {
        return updateUserId;
    }
    public void setUpdateUserName(String updateUserName) 
    {
        this.updateUserName = updateUserName;
    }

    public String getUpdateUserName() 
    {
        return updateUserName;
    }
    public void setCorporation(String corporation) 
    {
        this.corporation = corporation;
    }

    public String getCorporation() 
    {
        return corporation;
    }
    public void setEnterpriseStatus(String enterpriseStatus) 
    {
        this.enterpriseStatus = enterpriseStatus;
    }

    public String getEnterpriseStatus() 
    {
        return enterpriseStatus;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("groupId", getGroupId())
            .append("enterpriseName", getEnterpriseName())
            .append("enterpriseAddr", getEnterpriseAddr())
            .append(" customerName", getCustomerName())
            .append("customerTel", getCustomerTel())
            .append("customerPosition", getCustomerPosition())
            .append("createUserId", getCreateUserId())
            .append("createUserName", getCreateUserName())
            .append("createTime", getCreateTime())
            .append("updateUserId", getUpdateUserId())
            .append("updateUserName", getUpdateUserName())
            .append("updateTime", getUpdateTime())
            .append("corporation", getCorporation())
            .append("enterpriseStatus", getEnterpriseStatus())
            .toString();
    }
}

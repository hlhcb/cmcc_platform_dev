package com.cmcc.system.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Date;

import com.cmcc.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.cmcc.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 用户走访对象 cmcc_interview_base
 * 
 * @author terry
 * @date 2024-05-17
 */
public class CmccInterviewBase extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 区县名称 */
    @Excel(name = "区县名称")
    private String areaName;

    /** 局向 */
    @Excel(name = "局向")
    private String direction;

    /** 工号 */
    @Excel(name = "工号")
    private String workNo;

    /** 用户名称 */
    @Excel(name = "用户名称")
    private String userName;

    /** 场景选择 */
    @Excel(name = "场景选择")
    private String sceneType;

    /** 单位名称 */
    @Excel(name = "单位名称")
    private String businessName;

    /** 详细地址 */
    @Excel(name = "详细地址")
    private String addrInfo;

    /** 客户姓名 */
    @Excel(name = "客户姓名")
    private String customerName;

    /** 客户手机号 */
    @Excel(name = "客户手机号")
    private String customerTel;

    /** 客户职位 */
    @Excel(name = "客户职位")
    private String customerPosition;

    /** 走访具体事项 */
    @Excel(name = "走访具体事项")
    private String information;

    @Excel(name = "自主楼宇名称")
    private String remarks;

    /** 走访时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "走访时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date recordDate;

    /** 耗时 */
    @Excel(name = "耗时")
    private Long useTime;

    /** 用户id */
    private Long userId;

    /** 部门id */
    private Long deptId;

    /** 走访开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "走访开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date timeBegin;

    /** 走访结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "走访结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date timeOver;

    /** 定位具体地址 */
    @Excel(name = "定位具体地址")
    private String mapAddress;

    /** 经度 */
    @Excel(name = "经度")
    private BigDecimal latitude;

    /** 维度 */
    @Excel(name = "维度")
    private BigDecimal longitude;

    /** 附件1 */
    @Excel(name = "附件1")
    private String attachmentOne;

    /** 附件2 */
    @Excel(name = "附件2")
    private String attachmentTwo;


    private String groupName;

    private Long enterpriseId;

    private Long groupId;

    private Integer queryTimeFlag;


    private String interviewName;

    private Boolean queryAll;

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /** 客户走访线路信息信息 */
    private List<CmccInterviewNet> cmccInterviewNetList;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setAreaName(String areaName) 
    {
        this.areaName = areaName;
    }

    public String getAreaName() 
    {
        return areaName;
    }
    public void setDirection(String direction) 
    {
        this.direction = direction;
    }

    public String getDirection() 
    {
        return direction;
    }
    public void setWorkNo(String workNo) 
    {
        this.workNo = workNo;
    }

    public String getWorkNo() 
    {
        return workNo;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setSceneType(String sceneType) 
    {
        this.sceneType = sceneType;
    }

    public String getSceneType() 
    {
        return sceneType;
    }
    public void setBusinessName(String businessName) 
    {
        this.businessName = businessName;
    }

    public String getBusinessName() 
    {
        return businessName;
    }
    public void setAddrInfo(String addrInfo) 
    {
        this.addrInfo = addrInfo;
    }

    public String getAddrInfo() 
    {
        return addrInfo;
    }
    public void setCustomerName(String customerName) 
    {
        this.customerName = customerName;
    }

    public String getCustomerName() 
    {
        return customerName;
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
    public void setInformation(String information) 
    {
        this.information = information;
    }

    public String getInformation() 
    {
        return information;
    }
    public void setRecordDate(Date recordDate) 
    {
        this.recordDate = recordDate;
    }

    public Date getRecordDate() 
    {
        return recordDate;
    }
    public void setUseTime(Long useTime) 
    {
        this.useTime = useTime;
    }

    public Long getUseTime() 
    {
        return useTime;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }
    public void setTimeBegin(Date timeBegin) 
    {
        this.timeBegin = timeBegin;
    }

    public Date getTimeBegin() 
    {
        return timeBegin;
    }
    public void setTimeOver(Date timeOver) 
    {
        this.timeOver = timeOver;
    }

    public Date getTimeOver() 
    {
        return timeOver;
    }
    public void setMapAddress(String mapAddress) 
    {
        this.mapAddress = mapAddress;
    }

    public String getMapAddress() 
    {
        return mapAddress;
    }
    public void setLatitude(BigDecimal latitude) 
    {
        this.latitude = latitude;
    }

    public BigDecimal getLatitude() 
    {
        return latitude;
    }
    public void setLongitude(BigDecimal longitude) 
    {
        this.longitude = longitude;
    }

    public BigDecimal getLongitude() 
    {
        return longitude;
    }
    public void setAttachmentOne(String attachmentOne) 
    {
        this.attachmentOne = attachmentOne;
    }

    public String getAttachmentOne() 
    {
        return attachmentOne;
    }
    public void setAttachmentTwo(String attachmentTwo) 
    {
        this.attachmentTwo = attachmentTwo;
    }

    public String getAttachmentTwo() 
    {
        return attachmentTwo;
    }

    public List<CmccInterviewNet> getCmccInterviewNetList()
    {
        return cmccInterviewNetList;
    }

    public void setCmccInterviewNetList(List<CmccInterviewNet> cmccInterviewNetList)
    {
        this.cmccInterviewNetList = cmccInterviewNetList;
    }

    public Integer getQueryTimeFlag() {
        return queryTimeFlag;
    }

    public Boolean getQueryAll() {
        return queryAll;
    }

    public void setQueryAll(Boolean queryAll) {
        this.queryAll = queryAll;
    }

    public void setQueryTimeFlag(Integer queryTimeFlag) {
        this.queryTimeFlag = queryTimeFlag;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getInterviewName() {
        return interviewName;
    }

    public void setInterviewName(String interviewName) {
        this.interviewName = interviewName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("areaName", getAreaName())
            .append("direction", getDirection())
            .append("workNo", getWorkNo())
            .append("userName", getUserName())
            .append("sceneType", getSceneType())
            .append("businessName", getBusinessName())
            .append("addrInfo", getAddrInfo())
            .append("customerName", getCustomerName())
            .append("customerTel", getCustomerTel())
            .append("customerPosition", getCustomerPosition())
            .append("information", getInformation())
            .append("recordDate", getRecordDate())
            .append("useTime", getUseTime())
            .append("userId", getUserId())
            .append("deptId", getDeptId())
            .append("timeBegin", getTimeBegin())
            .append("timeOver", getTimeOver())
            .append("mapAddress", getMapAddress())
            .append("latitude", getLatitude())
            .append("longitude", getLongitude())
            .append("attachmentOne", getAttachmentOne())
            .append("attachmentTwo", getAttachmentTwo())
            .append("cmccInterviewNetList", getCmccInterviewNetList())
            .toString();
    }
}

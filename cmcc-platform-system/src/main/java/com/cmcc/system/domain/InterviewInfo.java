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
 * 访问记录对象 interview_info
 * 
 * @author terry
 * @date 2024-02-24
 */
public class InterviewInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 走访人id */
    private Long officerId;

    /** 走访人 */
    @Excel(name = "走访人")
    private String officerName;

    /** 被走访人 */
    @Excel(name = "被走访人")
    private String interviewName;

    /** 被走访人电话 */
    @Excel(name = "被走访人电话")
    private String interviewPhone;

    /** 派出所 */
    @Excel(name = "派出所")
    private String localPoliceStation;

    /** 归属地 */
    @Excel(name = "归属地")
    private String belongingPlace;

    /** 走访地址 */
    @Excel(name = "走访地址")
    private String interviewAddr;

    /** 是否矛盾 */
    @Excel(name = "是否矛盾")
    private Integer contradictionFlag;

    /** 矛盾内容 */
    @Excel(name = "矛盾内容")
    private String contradictionText;

    /** 走访开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "走访开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date interviewBeginTime;

    /** 走访结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "走访结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date interviewEndTime;

    /** 附件1 */
    @Excel(name = "附件1")
    private String attachmentOne;

    /** 附件2 */
    @Excel(name = "附件2")
    private String attachmentTwo;

    /** 是否满意 */
    @Excel(name = "是否满意")
    private Integer satisfyFlag;

    /** 满意内容 */
    @Excel(name = "满意内容")
    private String satisfyText;

    /** 走访情况 */
    @Excel(name = "走访情况")
    private String interviewText;

    /** 租户标识 */
    @Excel(name = "租户标识")
    private Integer tenantFlag;


    /** 地图定位 */
    @Excel(name = "地图定位")
    private String mapAddress;

    /** 精度 */
    @Excel(name = "精度")
    private BigDecimal latitude;

    /** 维度 */
    @Excel(name = "维度")
    private BigDecimal longitude;

    /** 是否重复*/
    @Excel(name = "是否重复走访")
    private Integer repeatFlag;

    /** 房东姓名*/
    @Excel(name = "房东姓名")
    private String tenantName;

    /** 身份证号*/
    @Excel(name = "身份证号")
    private String tenantTel;

    /** 联系方式*/
    @Excel(name = "房东联系方式")
    private String tenantWechat;

    /** 父类部门id */
    private Long deptParentId;


    /** 房东访问信息 */
    private List<TenantInteviewRecord> tenantInteviewRecordList;

    private String compareBeginTime;

    private String compareEndTime;

    private Boolean queryAll;

    private Integer queryTimeFlag;

    private String localPoliceStationId;

    private String belongingPlaceId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setOfficerId(Long officerId) 
    {
        this.officerId = officerId;
    }

    public Long getOfficerId() 
    {
        return officerId;
    }
    public void setOfficerName(String officerName) 
    {
        this.officerName = officerName;
    }

    public String getOfficerName() 
    {
        return officerName;
    }
    public void setInterviewName(String interviewName) 
    {
        this.interviewName = interviewName;
    }

    public String getInterviewName() 
    {
        return interviewName;
    }
    public void setInterviewPhone(String interviewPhone) 
    {
        this.interviewPhone = interviewPhone;
    }

    public String getInterviewPhone() 
    {
        return interviewPhone;
    }
    public void setLocalPoliceStation(String localPoliceStation) 
    {
        this.localPoliceStation = localPoliceStation;
    }

    public String getLocalPoliceStation() 
    {
        return localPoliceStation;
    }
    public void setBelongingPlace(String belongingPlace) 
    {
        this.belongingPlace = belongingPlace;
    }

    public String getBelongingPlace() 
    {
        return belongingPlace;
    }
    public void setInterviewAddr(String interviewAddr) 
    {
        this.interviewAddr = interviewAddr;
    }

    public String getInterviewAddr() 
    {
        return interviewAddr;
    }
    public void setContradictionFlag(Integer contradictionFlag) 
    {
        this.contradictionFlag = contradictionFlag;
    }

    public Integer getContradictionFlag() 
    {
        return contradictionFlag;
    }
    public void setContradictionText(String contradictionText) 
    {
        this.contradictionText = contradictionText;
    }

    public String getContradictionText() 
    {
        return contradictionText;
    }
    public void setInterviewBeginTime(Date interviewBeginTime) 
    {
        this.interviewBeginTime = interviewBeginTime;
    }

    public Date getInterviewBeginTime() 
    {
        return interviewBeginTime;
    }
    public void setInterviewEndTime(Date interviewEndTime) 
    {
        this.interviewEndTime = interviewEndTime;
    }

    public Date getInterviewEndTime() 
    {
        return interviewEndTime;
    }
    public void setattachmentOne(String  attachmentOne)
    {
        this.attachmentOne =  attachmentOne;
    }

    public String getattachmentOne()
    {
        return  attachmentOne;
    }
    public void setAttachmentTwo(String attachmentTwo) 
    {
        this.attachmentTwo = attachmentTwo;
    }

    public String getAttachmentTwo() 
    {
        return attachmentTwo;
    }
    public void setSatisfyFlag(Integer satisfyFlag) 
    {
        this.satisfyFlag = satisfyFlag;
    }

    public Integer getSatisfyFlag() 
    {
        return satisfyFlag;
    }
    public void setSatisfyText(String satisfyText) 
    {
        this.satisfyText = satisfyText;
    }

    public String getSatisfyText() 
    {
        return satisfyText;
    }
    public void setInterviewText(String interviewText) 
    {
        this.interviewText = interviewText;
    }

    public String getInterviewText() 
    {
        return interviewText;
    }
    public void setTenantFlag(Integer tenantFlag) 
    {
        this.tenantFlag = tenantFlag;
    }

    public Integer getTenantFlag() 
    {
        return tenantFlag;
    }

    public String getCompareBeginTime() {
        return compareBeginTime;
    }

    public void setCompareBeginTime(String compareBeginTime) {
        this.compareBeginTime = compareBeginTime;
    }

    public List<TenantInteviewRecord> getTenantInteviewRecordList()
    {
        return tenantInteviewRecordList;
    }

    public void setTenantInteviewRecordList(List<TenantInteviewRecord> tenantInteviewRecordList)
    {
        this.tenantInteviewRecordList = tenantInteviewRecordList;
    }

    public String getCompareEndTime() {
        return compareEndTime;
    }

    public void setCompareEndTime(String compareEndTime) {
        this.compareEndTime = compareEndTime;
    }

    public Boolean getQueryAll() {
        return queryAll;
    }

    public void setQueryAll(Boolean queryAll) {
        this.queryAll = queryAll;
    }

    public String getMapAddress() {
        return mapAddress;
    }

    public void setMapAddress(String mapAddress) {
        this.mapAddress = mapAddress;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public Integer getRepeatFlag() {
        return repeatFlag;
    }

    public void setRepeatFlag(Integer repeatFlag) {
        this.repeatFlag = repeatFlag;
    }

    public Integer getQueryTimeFlag() {
        return queryTimeFlag;
    }

    public void setQueryTimeFlag(Integer queryTimeFlag) {
        this.queryTimeFlag = queryTimeFlag;
    }

    public String getLocalPoliceStationId() {
        return localPoliceStationId;
    }

    public void setLocalPoliceStationId(String localPoliceStationId) {
        this.localPoliceStationId = localPoliceStationId;
    }

    public String getBelongingPlaceId() {
        return belongingPlaceId;
    }

    public void setBelongingPlaceId(String belongingPlaceId) {
        this.belongingPlaceId = belongingPlaceId;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getTenantTel() {
        return tenantTel;
    }

    public void setTenantTel(String tenantTel) {
        this.tenantTel = tenantTel;
    }

    public String getTenantWechat() {
        return tenantWechat;
    }

    public void setTenantWechat(String tenantWechat) {
        this.tenantWechat = tenantWechat;
    }

    public Long getDeptParentId() {
        return deptParentId;
    }

    public void setDeptParentId(Long deptParentId) {
        this.deptParentId = deptParentId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("officerId", getOfficerId())
            .append("officerName", getOfficerName())
            .append("interviewName", getInterviewName())
            .append("interviewPhone", getInterviewPhone())
            .append("localPoliceStation", getLocalPoliceStation())
            .append("belongingPlace", getBelongingPlace())
            .append("interviewAddr", getInterviewAddr())
            .append("contradictionFlag", getContradictionFlag())
            .append("contradictionText", getContradictionText())
            .append("interviewBeginTime", getInterviewBeginTime())
            .append("interviewEndTime", getInterviewEndTime())
            .append("attachmentOne", getattachmentOne())
            .append("attachmentTwo", getAttachmentTwo())
            .append("satisfyFlag", getSatisfyFlag())
            .append("satisfyText", getSatisfyText())
            .append("interviewText", getInterviewText())
            .append("tenantFlag", getTenantFlag())
            .append("tenantInteviewRecordList", getTenantInteviewRecordList())
            .toString();
    }
}

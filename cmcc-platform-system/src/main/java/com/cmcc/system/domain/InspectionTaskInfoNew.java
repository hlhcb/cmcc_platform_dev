package com.cmcc.system.domain;

import java.util.Date;

import com.cmcc.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.cmcc.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 巡检记录新对象 inspection_task_info_new
 * 
 * @author terry
 * @date 2024-04-23
 */
public class InspectionTaskInfoNew extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 联防区id */
    @Excel(name = "联防区id")
    private Long unionId;

    /** 联防区名称 */
    @Excel(name = "联防区名称")
    private String unionName;

    /** 部门id */
    @Excel(name = "部门id")
    private Long deptId;

    /** 部门名称 */
    @Excel(name = "部门名称")
    private String deptName;

    /** 任务类型 */
    @Excel(name = "任务类型")
    private Long taskType;

    /** 走访人员id */
    @Excel(name = "走访人员id")
    private Long taskUserId;

    /** 走访人员名称 */
    @Excel(name = "走访人员名称")
    private String taskUserName;

    /** 走访地址 */
    @Excel(name = "走访地址")
    private String taskAddress;

    /** 附件地址1 */
    @Excel(name = "附件地址1")
    private String attachmentOne;

    /** 附件地址2 */
    @Excel(name = "附件地址2")
    private String attachmentTwo;

    /** 是否存在治安问题 */
    @Excel(name = "是否存在治安问题")
    private String problem1Flag;

    /** 走访情况 */
    @Excel(name = "走访情况")
    private String recordInfo;

    /** 隐患信息 */
    @Excel(name = "隐患信息")
    private String troubleInfo;

    /** 记录时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "记录时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date recordTime;

    /** 上报人 */
    @Excel(name = "上报人")
    private String reportUser;

    /** 上报人id */
    @Excel(name = "上报人id")
    private Long reportUserId;

    /** 上报人部门id */
    @Excel(name = "上报人部门id")
    private Long reportDeptId;

    /** 是否存在生产问题 */
    @Excel(name = "是否存在生产问题")
    private String problem2Flag;

    /** 是否存在消防问题 */
    @Excel(name = "是否存在消防问题")
    private String problem3Flag;

    /** 是否存在环境问题 */
    @Excel(name = "是否存在矛盾问题")
    private String problem4Flag;

    /** 是否存在违规经营 */
    @Excel(name = "是否存在违规经营")
    private String problem5Flag;

    /** 是否存在车辆违规停放 */
    @Excel(name = "是否存在车辆违规停放")
    private String problem6Flag;

    /** 治安问题详细地址 */
    @Excel(name = "治安问题详细地址")
    private String problem1Add;

    /** 治安问题情况 */
    @Excel(name = "治安问题情况")
    private String problem1Info;

    /** 治安问题附件1 */
    @Excel(name = "治安问题附件1")
    private String problem1UrlOne;

    /** 治安问题附件2 */
    @Excel(name = "治安问题附件2")
    private String problem1UrlTwo;

    /** 安全生产详细地址 */
    @Excel(name = "安全生产详细地址")
    private String problem2Add;

    /** 安全生产问题情况 */
    @Excel(name = "安全生产问题情况")
    private String problem2Info;

    /** 安全生产附件1 */
    @Excel(name = "安全生产附件1")
    private String problem2UrlOne;

    /** 安全生产附件2 */
    @Excel(name = "安全生产附件2")
    private String problem2UrlTwo;

    /** 消防安全详细地址 */
    @Excel(name = "消防安全详细地址")
    private String problem3Add;

    /** 消防安全问题情况 */
    @Excel(name = "消防安全问题情况")
    private String problem3Info;

    /** 消防安全附件1 */
    @Excel(name = "消防安全附件1")
    private String problem3UrlOne;

    /** 消防安全附件2 */
    @Excel(name = "消防安全附件2")
    private String problem3UrlTwo;

    /** 矛盾纠纷详情地址 */
    @Excel(name = "矛盾纠纷详情地址")
    private String problem4Add;

    /** 矛盾纠纷问题情况 */
    @Excel(name = "矛盾纠纷问题情况")
    private String problem4Info;

    /** 矛盾纠纷附件1 */
    @Excel(name = "矛盾纠纷附件1")
    private String problem4UrlOne;

    /** 矛盾纠纷附件2 */
    @Excel(name = "矛盾纠纷附件2")
    private String problem4UrlTwo;

    /** 违规经营详情地址 */
    @Excel(name = "违规经营详情地址")
    private String problem5Add;

    /** 违规经营问题情况 */
    @Excel(name = "违规经营问题情况")
    private String problem5Info;

    /** 违规经营附件1 */
    @Excel(name = "违规经营附件1")
    private String problem5UrlOne;

    /** 违规经营附件2 */
    @Excel(name = "违规经营附件2")
    private String problem5UrlTwo;

    /** 车辆违规停放详情地址 */
    @Excel(name = "车辆违规停放详情地址")
    private String problem6Add;

    /** 车辆违规停放问题情况 */
    @Excel(name = "车辆违规停放问题情况")
    private String problem6Info;

    /** 车辆违规停放附件1 */
    @Excel(name = "车辆违规停放附件1")
    private String problem6UrlOne;

    /** 车辆违规停放附件2 */
    @Excel(name = "车辆违规停放附件2")
    private String problem6UrlTwo;

    private String compareBeginTime;

    private String compareEndTime;



    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUnionId(Long unionId) 
    {
        this.unionId = unionId;
    }

    public Long getUnionId() 
    {
        return unionId;
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
    public void setTaskType(Long taskType) 
    {
        this.taskType = taskType;
    }

    public Long getTaskType() 
    {
        return taskType;
    }
    public void setTaskUserId(Long taskUserId) 
    {
        this.taskUserId = taskUserId;
    }

    public Long getTaskUserId() 
    {
        return taskUserId;
    }
    public void setTaskUserName(String taskUserName) 
    {
        this.taskUserName = taskUserName;
    }

    public String getTaskUserName() 
    {
        return taskUserName;
    }
    public void setTaskAddress(String taskAddress) 
    {
        this.taskAddress = taskAddress;
    }

    public String getTaskAddress() 
    {
        return taskAddress;
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
    public void setProblem1Flag(String problem1Flag) 
    {
        this.problem1Flag = problem1Flag;
    }

    public String getProblem1Flag() 
    {
        return problem1Flag;
    }
    public void setRecordInfo(String recordInfo) 
    {
        this.recordInfo = recordInfo;
    }

    public String getRecordInfo() 
    {
        return recordInfo;
    }
    public void setTroubleInfo(String troubleInfo) 
    {
        this.troubleInfo = troubleInfo;
    }

    public String getTroubleInfo() 
    {
        return troubleInfo;
    }
    public void setRecordTime(Date recordTime) 
    {
        this.recordTime = recordTime;
    }

    public Date getRecordTime() 
    {
        return recordTime;
    }
    public void setReportUser(String reportUser) 
    {
        this.reportUser = reportUser;
    }

    public String getReportUser() 
    {
        return reportUser;
    }
    public void setReportUserId(Long reportUserId) 
    {
        this.reportUserId = reportUserId;
    }

    public Long getReportUserId() 
    {
        return reportUserId;
    }
    public void setReportDeptId(Long reportDeptId) 
    {
        this.reportDeptId = reportDeptId;
    }

    public Long getReportDeptId() 
    {
        return reportDeptId;
    }
    public void setProblem2Flag(String problem2Flag) 
    {
        this.problem2Flag = problem2Flag;
    }

    public String getProblem2Flag() 
    {
        return problem2Flag;
    }
    public void setProblem3Flag(String problem3Flag) 
    {
        this.problem3Flag = problem3Flag;
    }

    public String getProblem3Flag() 
    {
        return problem3Flag;
    }
    public void setProblem4Flag(String problem4Flag) 
    {
        this.problem4Flag = problem4Flag;
    }

    public String getProblem4Flag() 
    {
        return problem4Flag;
    }
    public void setProblem5Flag(String problem5Flag) 
    {
        this.problem5Flag = problem5Flag;
    }

    public String getProblem5Flag() 
    {
        return problem5Flag;
    }
    public void setProblem1Add(String problem1Add) 
    {
        this.problem1Add = problem1Add;
    }

    public String getProblem1Add() 
    {
        return problem1Add;
    }
    public void setProblem1Info(String problem1Info) 
    {
        this.problem1Info = problem1Info;
    }

    public String getProblem1Info() 
    {
        return problem1Info;
    }
    public void setProblem1UrlOne(String problem1UrlOne) 
    {
        this.problem1UrlOne = problem1UrlOne;
    }

    public String getProblem1UrlOne() 
    {
        return problem1UrlOne;
    }
    public void setProblem1UrlTwo(String problem1UrlTwo) 
    {
        this.problem1UrlTwo = problem1UrlTwo;
    }

    public String getProblem1UrlTwo() 
    {
        return problem1UrlTwo;
    }
    public void setProblem2Add(String problem2Add) 
    {
        this.problem2Add = problem2Add;
    }

    public String getProblem2Add() 
    {
        return problem2Add;
    }
    public void setProblem2Info(String problem2Info) 
    {
        this.problem2Info = problem2Info;
    }

    public String getProblem2Info() 
    {
        return problem2Info;
    }
    public void setProblem2UrlOne(String problem2UrlOne) 
    {
        this.problem2UrlOne = problem2UrlOne;
    }

    public String getProblem2UrlOne() 
    {
        return problem2UrlOne;
    }
    public void setProblem2UrlTwo(String problem2UrlTwo) 
    {
        this.problem2UrlTwo = problem2UrlTwo;
    }

    public String getProblem2UrlTwo() 
    {
        return problem2UrlTwo;
    }
    public void setProblem3Add(String problem3Add) 
    {
        this.problem3Add = problem3Add;
    }

    public String getProblem3Add() 
    {
        return problem3Add;
    }
    public void setProblem3Info(String problem3Info) 
    {
        this.problem3Info = problem3Info;
    }

    public String getProblem3Info() 
    {
        return problem3Info;
    }
    public void setProblem3UrlOne(String problem3UrlOne) 
    {
        this.problem3UrlOne = problem3UrlOne;
    }

    public String getProblem3UrlOne() 
    {
        return problem3UrlOne;
    }
    public void setProblem3UrlTwo(String problem3UrlTwo) 
    {
        this.problem3UrlTwo = problem3UrlTwo;
    }

    public String getProblem3UrlTwo() 
    {
        return problem3UrlTwo;
    }
    public void setProblem4Add(String problem4Add) 
    {
        this.problem4Add = problem4Add;
    }

    public String getProblem4Add() 
    {
        return problem4Add;
    }
    public void setProblem4Info(String problem4Info) 
    {
        this.problem4Info = problem4Info;
    }

    public String getProblem4Info() 
    {
        return problem4Info;
    }
    public void setProblem4UrlOne(String problem4UrlOne) 
    {
        this.problem4UrlOne = problem4UrlOne;
    }

    public String getProblem4UrlOne() 
    {
        return problem4UrlOne;
    }
    public void setProblem4UrlTwo(String problem4UrlTwo) 
    {
        this.problem4UrlTwo = problem4UrlTwo;
    }

    public String getProblem4UrlTwo() 
    {
        return problem4UrlTwo;
    }
    public void setProblem5Add(String problem5Add) 
    {
        this.problem5Add = problem5Add;
    }

    public String getProblem5Add() 
    {
        return problem5Add;
    }
    public void setProblem5Info(String problem5Info) 
    {
        this.problem5Info = problem5Info;
    }

    public String getProblem5Info() 
    {
        return problem5Info;
    }
    public void setProblem5UrlOne(String problem5UrlOne) 
    {
        this.problem5UrlOne = problem5UrlOne;
    }

    public String getProblem5UrlOne() 
    {
        return problem5UrlOne;
    }
    public void setProblem5UrlTwo(String problem5UrlTwo) 
    {
        this.problem5UrlTwo = problem5UrlTwo;
    }

    public String getProblem5UrlTwo() 
    {
        return problem5UrlTwo;
    }

    public String getProblem6Flag() {
        return problem6Flag;
    }

    public void setProblem6Flag(String problem6Flag) {
        this.problem6Flag = problem6Flag;
    }

    public String getProblem6Add() {
        return problem6Add;
    }

    public void setProblem6Add(String problem6Add) {
        this.problem6Add = problem6Add;
    }

    public String getProblem6Info() {
        return problem6Info;
    }

    public void setProblem6Info(String problem6Info) {
        this.problem6Info = problem6Info;
    }

    public String getProblem6UrlOne() {
        return problem6UrlOne;
    }

    public void setProblem6UrlOne(String problem6UrlOne) {
        this.problem6UrlOne = problem6UrlOne;
    }

    public String getProblem6UrlTwo() {
        return problem6UrlTwo;
    }

    public void setProblem6UrlTwo(String problem6UrlTwo) {
        this.problem6UrlTwo = problem6UrlTwo;
    }

    public String getCompareBeginTime() {
        return compareBeginTime;
    }

    public void setCompareBeginTime(String compareBeginTime) {
        this.compareBeginTime = compareBeginTime;
    }

    public String getCompareEndTime() {
        return compareEndTime;
    }

    public void setCompareEndTime(String compareEndTime) {
        this.compareEndTime = compareEndTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("unionId", getUnionId())
            .append("unionName", getUnionName())
            .append("deptId", getDeptId())
            .append("deptName", getDeptName())
            .append("taskType", getTaskType())
            .append("taskUserId", getTaskUserId())
            .append("taskUserName", getTaskUserName())
            .append("taskAddress", getTaskAddress())
            .append("attachmentOne", getAttachmentOne())
            .append("attachmentTwo", getAttachmentTwo())
            .append("problem1Flag", getProblem1Flag())
            .append("recordInfo", getRecordInfo())
            .append("troubleInfo", getTroubleInfo())
            .append("remark", getRemark())
            .append("recordTime", getRecordTime())
            .append("reportUser", getReportUser())
            .append("reportUserId", getReportUserId())
            .append("reportDeptId", getReportDeptId())
            .append("problem2Flag", getProblem2Flag())
            .append("problem3Flag", getProblem3Flag())
            .append("problem4Flag", getProblem4Flag())
            .append("problem5Flag", getProblem5Flag())
            .append("problem1Add", getProblem1Add())
            .append("problem1Info", getProblem1Info())
            .append("problem1UrlOne", getProblem1UrlOne())
            .append("problem1UrlTwo", getProblem1UrlTwo())
            .append("problem2Add", getProblem2Add())
            .append("problem2Info", getProblem2Info())
            .append("problem2UrlOne", getProblem2UrlOne())
            .append("problem2UrlTwo", getProblem2UrlTwo())
            .append("problem3Add", getProblem3Add())
            .append("problem3Info", getProblem3Info())
            .append("problem3UrlOne", getProblem3UrlOne())
            .append("problem3UrlTwo", getProblem3UrlTwo())
            .append("problem4Add", getProblem4Add())
            .append("problem4Info", getProblem4Info())
            .append("problem4UrlOne", getProblem4UrlOne())
            .append("problem4UrlTwo", getProblem4UrlTwo())
            .append("problem5Add", getProblem5Add())
            .append("problem5Info", getProblem5Info())
            .append("problem5UrlOne", getProblem5UrlOne())
            .append("problem5UrlTwo", getProblem5UrlTwo())
            .toString();
    }
}

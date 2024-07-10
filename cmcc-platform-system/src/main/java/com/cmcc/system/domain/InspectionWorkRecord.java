package com.cmcc.system.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.cmcc.common.annotation.Excel;

import com.cmcc.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 工单记录对象 inspection_work_record
 * 
 * @author terry
 * @date 2024-04-18
 */
public class InspectionWorkRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 工单编号 */
    @Excel(name = "工单编号")
    private String workId;

    /** 工单类型 */
    @Excel(name = "工单类型")
    private String workType;

    /** 部门id */
    @Excel(name = "部门id")
    private Long deptId;

    /** 部门名称 */
    @Excel(name = "部门名称")
    private String deptName;

    /** 工单状态 */
    @Excel(name = "工单状态")
    private String workStatus;

    /** 联防id */
    @Excel(name = "联防id")
    private Long unionId;

    /** 联防区名称 */
    @Excel(name = "联防区名称")
    private String unionName;

    /** 走访记录id */
    @Excel(name = "走访记录id")
    private Long taskId;

    /** 走访人id */
    @Excel(name = "走访人id")
    private Long taskUserId;

    /** 走访人姓名 */
    @Excel(name = "走访人姓名")
    private String taskUserName;

    /** 处理人id */
    @Excel(name = "处理人id")
    private Long accpetUserId;

    /** 处理人姓名 */
    @Excel(name = "处理人姓名")
    private String accpetUserName;

    /** 隐患信息 */
    @Excel(name = "隐患信息")
    private String troubleInfo;

    /** 附件1 */
    @Excel(name = "附件1")
    private String attachmentOne;

    /** 附件2 */
    @Excel(name = "附件2")
    private String attachmentTwo;

    /** 接收时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "接收时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date accpetTime;

    /** 处理结果 */
    @Excel(name = "处理结果")
    private String resultInfo;

    /** 处理结果拍照1 */
    @Excel(name = "处理结果拍照1")
    private String returnAttachmentOne;

    /** 处理结果拍照2 */
    @Excel(name = "处理结果拍照2")
    private String returnAttachmentTwo;

    /** 处理时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "处理时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dealTime;

    /** 走访地址 */
    @Excel(name = "走访地址")
    private String address;

    /** 详细位置 */
    private String detailPosition;

    /** 转派标识：0：未转派、1：待转派、2：已转派  */
    private String transferFlag;

    private String queryTransferFlag;

    private Long queryTransferDeptId;


    private Long unionDeptId;

    private String unionDeptName;

    private Long changeUserId;

    private String changeUserName;

    private Long changeDeptId;

    private String changeDeptName;

    private List<Long> deptList;

    public Long getUnionDeptId() {
        return unionDeptId;
    }

    public void setUnionDeptId(Long unionDeptId) {
        this.unionDeptId = unionDeptId;
    }

    public String getUnionDeptName() {
        return unionDeptName;
    }

    public void setUnionDeptName(String unionDeptName) {
        this.unionDeptName = unionDeptName;
    }

    public Date getDealTime() {
        return dealTime;
    }

    public void setDealTime(Date dealTime) {
        this.dealTime = dealTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setWorkId(String workId) 
    {
        this.workId = workId;
    }

    public String getWorkId() 
    {
        return workId;
    }
    public void setWorkType(String workType) 
    {
        this.workType = workType;
    }

    public String getWorkType() 
    {
        return workType;
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
    public void setWorkStatus(String workStatus) 
    {
        this.workStatus = workStatus;
    }

    public String getWorkStatus() 
    {
        return workStatus;
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
    public void setTaskId(Long taskId) 
    {
        this.taskId = taskId;
    }

    public Long getTaskId() 
    {
        return taskId;
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
    public void setAccpetUserId(Long accpetUserId) 
    {
        this.accpetUserId = accpetUserId;
    }

    public Long getAccpetUserId() 
    {
        return accpetUserId;
    }
    public void setAccpetUserName(String accpetUserName) 
    {
        this.accpetUserName = accpetUserName;
    }

    public String getAccpetUserName() 
    {
        return accpetUserName;
    }
    public void setTroubleInfo(String troubleInfo) 
    {
        this.troubleInfo = troubleInfo;
    }

    public String getTroubleInfo() 
    {
        return troubleInfo;
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
    public void setAccpetTime(Date accpetTime) 
    {
        this.accpetTime = accpetTime;
    }

    public Date getAccpetTime() 
    {
        return accpetTime;
    }
    public void setResultInfo(String resultInfo) 
    {
        this.resultInfo = resultInfo;
    }

    public String getResultInfo() 
    {
        return resultInfo;
    }
    public void setReturnAttachmentOne(String returnAttachmentOne) 
    {
        this.returnAttachmentOne = returnAttachmentOne;
    }

    public String getReturnAttachmentOne() 
    {
        return returnAttachmentOne;
    }
    public void setReturnAttachmentTwo(String returnAttachmentTwo) 
    {
        this.returnAttachmentTwo = returnAttachmentTwo;
    }

    public String getReturnAttachmentTwo() 
    {
        return returnAttachmentTwo;
    }

    public String getDetailPosition() {
        return detailPosition;
    }

    public void setDetailPosition(String detailPosition) {
        this.detailPosition = detailPosition;
    }

    public String getTransferFlag() {
        return transferFlag;
    }

    public void setTransferFlag(String transferFlag) {
        this.transferFlag = transferFlag;
    }

    public String getQueryTransferFlag() {
        return queryTransferFlag;
    }

    public void setQueryTransferFlag(String queryTransferFlag) {
        this.queryTransferFlag = queryTransferFlag;
    }

    public Long getQueryTransferDeptId() {
        return queryTransferDeptId;
    }

    public void setQueryTransferDeptId(Long queryTransferDeptId) {
        this.queryTransferDeptId = queryTransferDeptId;
    }


    public Long getChangeUserId() {
        return changeUserId;
    }

    public void setChangeUserId(Long changeUserId) {
        this.changeUserId = changeUserId;
    }

    public String getChangeUserName() {
        return changeUserName;
    }

    public void setChangeUserName(String changeUserName) {
        this.changeUserName = changeUserName;
    }

    public Long getChangeDeptId() {
        return changeDeptId;
    }

    public void setChangeDeptId(Long changeDeptId) {
        this.changeDeptId = changeDeptId;
    }

    public String getChangeDeptName() {
        return changeDeptName;
    }

    public void setChangeDeptName(String changeDeptName) {
        this.changeDeptName = changeDeptName;
    }

    public List<Long> getDeptList() {
        return deptList;
    }

    public void setDeptList(List<Long> deptList) {
        this.deptList = deptList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("workId", getWorkId())
            .append("workType", getWorkType())
            .append("deptId", getDeptId())
            .append("deptName", getDeptName())
            .append("workStatus", getWorkStatus())
            .append("unionId", getUnionId())
            .append("unionName", getUnionName())
            .append("taskId", getTaskId())
            .append("taskUserId", getTaskUserId())
            .append("taskUserName", getTaskUserName())
            .append("accpetUserId", getAccpetUserId())
            .append("accpetUserName", getAccpetUserName())
            .append("troubleInfo", getTroubleInfo())
            .append("attachmentOne", getAttachmentOne())
            .append("attachmentTwo", getAttachmentTwo())
            .append("accpetTime", getAccpetTime())
            .append("resultInfo", getResultInfo())
            .append("returnAttachmentOne", getReturnAttachmentOne())
            .append("returnAttachmentTwo", getReturnAttachmentTwo())
            .append("transferFlag", getTransferFlag())
            .append("detailPosition", getDetailPosition())
            .toString();
    }
}

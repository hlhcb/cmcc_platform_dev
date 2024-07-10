package com.cmcc.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.cmcc.common.annotation.Excel;
import com.cmcc.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 巡检记录对象 inspection_task_info
 * 
 * @author terry
 * @date 2024-04-18
 */
public class InspectionTaskInfo extends BaseEntity
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

    /** 是否隐患 */
    @Excel(name = "是否隐患")
    private String troubleFlag;

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
    public void setTroubleFlag(String troubleFlag) 
    {
        this.troubleFlag = troubleFlag;
    }

    public String getTroubleFlag() 
    {
        return troubleFlag;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
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
            .append("troubleFlag", getTroubleFlag())
            .append("recordInfo", getRecordInfo())
            .append("troubleInfo", getTroubleInfo())
            .append("remark", getRemark())
            .append("recordTime", getRecordTime())
            .toString();
    }
}

package com.cmcc.system.domain;

import com.cmcc.common.annotation.Excel;
import com.cmcc.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 用户任务对象 cmcc_user_task
 * 
 * @author terry
 * @date 2024-06-20
 */
public class CmccUserTask extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 区县id */
    @Excel(name = "区县id")
    private Long deptId;

    /** 区县名称 */
    @Excel(name = "区县名称")
    private String deptName;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 用户名字 */
    @Excel(name = "用户名字")
    private String userName;

    /** 集团id */
    @Excel(name = "集团id")
    private Long groupId;

    /** 集团名称 */
    @Excel(name = "集团名称")
    private String groupName;

    /** 任务状态 */
    @Excel(name = "任务状态")
    private String taskStatus;

    /** 集团类型 */
    @Excel(name = "集团类型")
    private String groupType;

    /** 创建人id */
    @Excel(name = "创建人id")
    private Long createUserId;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createUserName;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date taskBeginTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date taskEndTime;

    private Integer taskType;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
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
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setGroupId(Long groupId) 
    {
        this.groupId = groupId;
    }

    public Long getGroupId() 
    {
        return groupId;
    }
    public void setGroupName(String groupName) 
    {
        this.groupName = groupName;
    }

    public String getGroupName() 
    {
        return groupName;
    }
    public void setTaskStatus(String taskStatus) 
    {
        this.taskStatus = taskStatus;
    }

    public String getTaskStatus() 
    {
        return taskStatus;
    }
    public void setGroupType(String groupType) 
    {
        this.groupType = groupType;
    }

    public String getGroupType() 
    {
        return groupType;
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

    public Date getTaskBeginTime() {
        return taskBeginTime;
    }

    public void setTaskBeginTime(Date taskBeginTime) {
        this.taskBeginTime = taskBeginTime;
    }

    public Date getTaskEndTime() {
        return taskEndTime;
    }

    public void setTaskEndTime(Date taskEndTime) {
        this.taskEndTime = taskEndTime;
    }

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("deptId", getDeptId())
            .append("deptName", getDeptName())
            .append("userId", getUserId())
            .append("userName", getUserName())
            .append("groupId", getGroupId())
            .append("groupName", getGroupName())
            .append("taskStatus", getTaskStatus())
            .append("groupType", getGroupType())
            .append("createUserId", getCreateUserId())
            .append("createUserName", getCreateUserName())
            .append("createTime", getCreateTime())
            .toString();
    }
}

package com.cmcc.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.cmcc.common.annotation.Excel;

import com.cmcc.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 值班配置对象 task_scheduling_info
 * 
 * @author terry
 * @date 2024-04-18
 */
public class TaskSchedulingInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 任务日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "任务日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date taskDate;


    /** 任务开始时间 */
    private String beginTaskDate;

    /** 任务结束时间 */
    private String endTaskDate;

    /** 值班人员id */
    @Excel(name = "值班人员id")
    private Long userId;

    /** 值班人员姓名 */
    @Excel(name = "值班人员姓名")
    private String userName;

    /** 部门id */
    @Excel(name = "部门id")
    private Long deptId;

    /** 部门名称 */
    @Excel(name = "部门名称")
    private String deptName;

    /** 任务状态 */
    @Excel(name = "任务状态")
    private Long taskStatus;

    /** 联防长id */
    @Excel(name = "联防长id")
    private Long unionUserId;

    /** 联防长名称 */
    @Excel(name = "联防长名称")
    private String unionUserName;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTaskDate(Date taskDate) 
    {
        this.taskDate = taskDate;
    }

    public Date getTaskDate() 
    {
        return taskDate;
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
    public void setTaskStatus(Long taskStatus) 
    {
        this.taskStatus = taskStatus;
    }

    public Long getTaskStatus() 
    {
        return taskStatus;
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

    public String getBeginTaskDate() {
        return beginTaskDate;
    }

    public void setBeginTaskDate(String beginTaskDate) {
        this.beginTaskDate = beginTaskDate;
    }

    public String getEndTaskDate() {
        return endTaskDate;
    }

    public void setEndTaskDate(String endTaskDate) {
        this.endTaskDate = endTaskDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("taskDate", getTaskDate())
            .append("userId", getUserId())
            .append("userName", getUserName())
            .append("deptId", getDeptId())
            .append("deptName", getDeptName())
            .append("taskStatus", getTaskStatus())
            .append("unionUserId", getUnionUserId())
            .append("unionUserName", getUnionUserName())
            .toString();
    }
}

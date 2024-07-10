package com.cmcc.system.domain;

import java.util.Date;

/**
 * @author:terry
 * @create: 2024-06-25 21:30
 * @Description: 园区走访列表
 */
public class AppGroupTaskList {

    private String groupId;

    private String groupName;

    private String groupType;

    private String deptName;

    private Integer total;

    private Integer interviewTotal;
    private Date recordDate;
    private Date taskEndTime;
    private Date taskBeginTime;

    private String dualTime;

    private Integer taskEndHours;


    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getInterviewTotal() {
        return interviewTotal;
    }

    public void setInterviewTotal(Integer interviewTotal) {
        this.interviewTotal = interviewTotal;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public Date getTaskEndTime() {
        return taskEndTime;
    }

    public void setTaskEndTime(Date taskEndTime) {
        this.taskEndTime = taskEndTime;
    }

    public Date getTaskBeginTime() {
        return taskBeginTime;
    }

    public void setTaskBeginTime(Date taskBeginTime) {
        this.taskBeginTime = taskBeginTime;
    }

    public String getDualTime() {
        return dualTime;
    }

    public void setDualTime(String dualTime) {
        this.dualTime = dualTime;
    }

    public Integer getTaskEndHours() {
        return taskEndHours;
    }

    public void setTaskEndHours(Integer taskEndHours) {
        this.taskEndHours = taskEndHours;
    }
}

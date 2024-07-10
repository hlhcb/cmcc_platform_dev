package com.cmcc.system.domain;

import com.cmcc.common.utils.StringUtils;

public enum TaskStatusEnum {
    未巡查("1", "未巡查"),
    巡查中("2", "巡查中"),
    已巡查("3", "已巡查");

    private String taskStatus;

    private String statusName;

    TaskStatusEnum(String taskStatus, String statusName){
        this.taskStatus = taskStatus;
        this.statusName = statusName;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public static String getStatusByName(String name)
    {
        if (StringUtils.isEmpty(name))
        {
            return "";
        }
        for (TaskStatusEnum item : TaskStatusEnum.values())
        {
            if (name.equals(item.getStatusName()))
            {
                return item.getTaskStatus();
            }
        }
        return "";
    }

    public static String getNameByStatus(String status)
    {
        if (StringUtils.isEmpty(status))
        {
            return "";
        }
        for (TaskStatusEnum item : TaskStatusEnum.values())
        {
            if (status.equals(item.getTaskStatus()))
            {
                return item.getStatusName();
            }
        }
        return "";
    }
}

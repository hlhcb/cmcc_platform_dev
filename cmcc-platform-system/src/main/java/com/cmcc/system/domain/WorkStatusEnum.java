package com.cmcc.system.domain;

import com.cmcc.common.utils.StringUtils;

public enum WorkStatusEnum {
    待处理("1", "待接收"),
    处理中("2", "处理中"),
    已完成("3", "已完成");

    private String workStatus;

    private String statusName;

    WorkStatusEnum(String workStatus, String statusName){
        this.workStatus = workStatus;
        this.statusName = statusName;
    }

    public String getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(String workStatus) {
        this.workStatus = workStatus;
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
        for (WorkStatusEnum item : WorkStatusEnum.values())
        {
            if (name.equals(item.getStatusName()))
            {
                return item.getWorkStatus();
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
        for (WorkStatusEnum item : WorkStatusEnum.values())
        {
            if (status.equals(item.getWorkStatus()))
            {
                return item.getStatusName();
            }
        }
        return "";
    }
}

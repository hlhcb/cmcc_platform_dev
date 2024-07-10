package com.cmcc.common.core.domain.entity.dto;

import com.cmcc.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * @author RenHang
 * @date 2024-07-08 14:03
 * @description:用户任务excel模板
 * @version:
 */
public class UserTaskTemplate {
    private static final long serialVersionUID = 1L;

    /** 区县名称 */
    @Excel(name = "区县名称")
    private String deptName;

    /** 客户经理名称 */
    @Excel(name = "客户经理名称")
    private String customerManagerName;

    /** 集团名称 */
    @Excel(name = "集团名称")
    private String groupName;

    /** 任务开始时间 */
    @Excel(name = "任务开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startTime;

    /** 任务结束时间 */
    @Excel(name = "任务结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getCustomerManagerName() {
        return customerManagerName;
    }

    public void setCustomerManagerName(String customerManagerName) {
        this.customerManagerName = customerManagerName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public UserTaskTemplate()
    {

    }

    public UserTaskTemplate(String deptName, String customerManagerName, String groupName, Date startTime, Date endTime) {
        this.deptName = deptName;
        this.customerManagerName = customerManagerName;
        this.groupName = groupName;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("deptName", getDeptName())
                .append("customerManagerName", getCustomerManagerName())
                .append("groupName", getGroupName())
                .append("startTime", getStartTime())
                .append("endTime", getEndTime())
                .toString();
    }
}

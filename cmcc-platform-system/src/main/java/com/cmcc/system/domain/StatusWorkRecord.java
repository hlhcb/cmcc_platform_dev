package com.cmcc.system.domain;

/**
 * @author:terry
 * @create: 2024-04-19 01:24
 * @Description: 工单状态统计
 */
public class StatusWorkRecord {

    private String workStatus;

    private Integer totalCount;

    public String getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(String workStatus) {
        this.workStatus = workStatus;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
}

package com.cmcc.system.domain;

/**
 * @author:terry
 * @create: 2024-03-01 10:41
 * @Description: 个人次数统计
 */
public class AppCountInfo {

    // 总次数
    private Integer totalCount;
    // 今日次数
    private Integer todayCount;
    // 每月次数
    private Integer monthCount;
    // 周次数
    private Integer weekCount;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTodayCount() {
        return todayCount;
    }

    public void setTodayCount(Integer todayCount) {
        this.todayCount = todayCount;
    }

    public Integer getMonthCount() {
        return monthCount;
    }

    public void setMonthCount(Integer monthCount) {
        this.monthCount = monthCount;
    }

    public Integer getWeekCount() {
        return weekCount;
    }

    public void setWeekCount(Integer weekCount) {
        this.weekCount = weekCount;
    }
}

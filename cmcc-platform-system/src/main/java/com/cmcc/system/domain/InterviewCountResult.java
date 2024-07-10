package com.cmcc.system.domain;

/**
 * @author:terry
 * @create: 2024-02-27 19:28
 * @Description: 走访统计
 */
public class InterviewCountResult {

    // 当前排名
    private Integer currentRank;

    // 走访人
    private Integer officerId;

    // 走访总数
    private Integer interviewCount;

    // 矛盾纠纷人数
    private Integer contradictionCount;

    // 群众满意人数
    private Integer satisfyCount;

    // 走访任务量
    private Integer interviewGoal;

    public Integer getCurrentRank() {
        return currentRank;
    }

    public void setCurrentRank(Integer currentRank) {
        this.currentRank = currentRank;
    }

    public Integer getOfficerId() {
        return officerId;
    }

    public void setOfficerId(Integer officerId) {
        this.officerId = officerId;
    }

    public Integer getInterviewCount() {
        return interviewCount;
    }

    public void setInterviewCount(Integer interviewCount) {
        this.interviewCount = interviewCount;
    }

    public Integer getContradictionCount() {
        return contradictionCount;
    }

    public void setContradictionCount(Integer contradictionCount) {
        this.contradictionCount = contradictionCount;
    }

    public Integer getSatisfyCount() {
        return satisfyCount;
    }

    public void setSatisfyCount(Integer satisfyCount) {
        this.satisfyCount = satisfyCount;
    }

    public Integer getInterviewGoal() {
        return interviewGoal;
    }

    public void setInterviewGoal(Integer interviewGoal) {
        this.interviewGoal = interviewGoal;
    }
}

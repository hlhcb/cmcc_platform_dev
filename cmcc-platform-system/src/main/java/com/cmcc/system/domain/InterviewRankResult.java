package com.cmcc.system.domain;

import java.io.Serializable;

/**
 * @author:terry
 * @create: 2024-03-10 14:51
 * @Description: 走访排行榜
 */
public class InterviewRankResult  implements Serializable {

    // 走访人id
    private Integer officerId;

    // 走访次数
    private Integer interviewCount;

    // 问题次数
    private Integer contradictionCount;

    // 解决问题次数
    private Integer satisfyCount;

    // 当前排名
    private Integer rank;

    // 姓名
    private String nickName;

    // 头像
    private String avatar;

    private Long postId;

    private Long deptId;

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

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }


}

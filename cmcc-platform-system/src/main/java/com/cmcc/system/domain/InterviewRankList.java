package com.cmcc.system.domain;

import java.util.List;

/**
 * @author:terry
 * @create: 2024-03-12 21:05
 * @Description: 走访榜list
 */
public class InterviewRankList {

    private List<InterviewRankResult> deptList;

    private Integer deptRank = 0;
    private Integer deptCount = 0;

    private List<InterviewRankResult> postList;

    private Integer postRank = 0;
    private Integer postCount = 0;

    private String nickName;

    private String avater;

    private Boolean needPostRank;

    private String postName;

    public List<InterviewRankResult> getDeptList() {
        return deptList;
    }

    public void setDeptList(List<InterviewRankResult> deptList) {
        this.deptList = deptList;
    }

    public Integer getDeptRank() {
        return deptRank;
    }

    public void setDeptRank(Integer deptRank) {
        this.deptRank = deptRank;
    }

    public Integer getDeptCount() {
        return deptCount;
    }

    public void setDeptCount(Integer deptCount) {
        this.deptCount = deptCount;
    }

    public List<InterviewRankResult> getPostList() {
        return postList;
    }

    public void setPostList(List<InterviewRankResult> postList) {
        this.postList = postList;
    }

    public Integer getPostRank() {
        return postRank;
    }

    public void setPostRank(Integer postRank) {
        this.postRank = postRank;
    }

    public Integer getPostCount() {
        return postCount;
    }

    public void setPostCount(Integer postCount) {
        this.postCount = postCount;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvater() {
        return avater;
    }

    public void setAvater(String avater) {
        this.avater = avater;
    }

    public Boolean getNeedPostRank() {
        return needPostRank;
    }

    public void setNeedPostRank(Boolean needPostRank) {
        this.needPostRank = needPostRank;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }
}

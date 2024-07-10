package com.cmcc.system.domain;

import java.util.List;

/**
 * @author:terry
 * @create: 2024-03-14 12:51
 * @Description: 走访反馈列表
 */
public class InterviewRankRes {

    // 排名列表
    private List<InterviewRankResult> rankList;

    // 排名分类
    private String rankName;

    // 是否显示自己
    private Boolean showFlag;

    // 用户昵称
    private String nickName;

    // 用户头像
    private String avater;

    // 排名
    private Integer rank = 0;

    // 走访次数
    private Integer count = 0;

    public List<InterviewRankResult> getRankList() {
        return rankList;
    }

    public void setRankList(List<InterviewRankResult> rankList) {
        this.rankList = rankList;
    }

    public String getRankName() {
        return rankName;
    }

    public void setRankName(String rankName) {
        this.rankName = rankName;
    }

    public Boolean getShowFlag() {
        return showFlag;
    }

    public void setShowFlag(Boolean showFlag) {
        this.showFlag = showFlag;
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

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}

package com.cmcc.system.domain;

import org.apache.poi.hpsf.Decimal;

/**
 * @author:terry
 * @create: 2023-12-30 16:36
 * @Description: 区县业务行表格实体
 */
public class CountyBusinessItemVo {

    private String businessname;
    private int[] scoreList;
    private int[] rankList;

    public String getBusinessname() {
        return businessname;
    }

    public void setBusinessname(String businessname) {
        this.businessname = businessname;
    }

    public int[] getScoreList() {
        return scoreList;
    }

    public void setScoreList(int[] scoreList) {
        this.scoreList = scoreList;
    }

    public int[] getRankList() {
        return rankList;
    }

    public void setRankList(int[] rankList) {
        this.rankList = rankList;
    }
}

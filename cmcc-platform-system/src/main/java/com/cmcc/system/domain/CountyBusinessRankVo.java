package com.cmcc.system.domain;

/**
 * @author:terry
 * @create: 2023-12-28 09:27
 * @Description: 区县业务排名
 */
public class CountyBusinessRankVo {

    private String countyId;

    private String countyName;

    private int[] scoreArray;

    private int[] rankArray;


    public String getCountyId() {
        return countyId;
    }

    public void setCountyId(String countyId) {
        this.countyId = countyId;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public int[] getScorearray() {
        return scoreArray;
    }

    public void setScorearray(int[] scoreArray) {
        this.scoreArray = scoreArray;
    }

    public int[] getRankArray() {
        return rankArray;
    }

    public void setRankArray(int[] rankArray) {
        this.rankArray = rankArray;
    }
}

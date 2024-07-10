package com.cmcc.system.domain;

import java.math.BigDecimal;

/**
 * @author:terry
 * @create: 2023-12-29 00:23
 * @Description: 网格排名实体类
 */
public class CenterRankListVo {
    private String countyName;
    private String centerId;
    private String centerName;
    private BigDecimal score;
    private Integer rank;

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getCenterId() {
        return centerId;
    }

    public void setCenterId(String centerId) {
        this.centerId = centerId;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }
}

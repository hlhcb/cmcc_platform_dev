package com.cmcc.system.domain;

/**
 * @author:terry
 * @create: 2023-12-28 10:03
 * @Description: 区县业务实体
 */
public class CountyBusinessVo {

    private String countyId;
    private String marketingType;
    private Integer total;
    private Integer rank;

    public String getCountyId() {
        return countyId;
    }

    public void setCountyId(String countyId) {
        this.countyId = countyId;
    }

    public String getMarketingType() {
        return marketingType;
    }

    public void setMarketingType(String marketingType) {
        this.marketingType = marketingType;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }
}

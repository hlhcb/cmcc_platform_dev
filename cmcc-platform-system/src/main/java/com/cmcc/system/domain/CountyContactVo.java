package com.cmcc.system.domain;

/**
 * @author:terry
 * @create: 2023-12-28 07:43
 * @Description: 区县接触人数
 */
public class CountyContactVo {
    private String countyId;

    private Integer total;

    public String getCountyId() {
        return countyId;
    }

    public void setCountyId(String countyId) {
        this.countyId = countyId;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}

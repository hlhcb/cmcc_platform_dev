package com.cmcc.system.domain;

/**
 * @author:terry
 * @create: 2023-12-26 23:57
 * @Description: 业务统计实体类
 */
public class BusinessCountResult {

    private String marketingType;

    private String marketingTypeName;

    private int total;

    public String getMarketingType() {
        return marketingType;
    }

    public void setMarketingType(String marketingType) {
        this.marketingType = marketingType;
    }

    public String getMarketingTypeName() {
        return marketingTypeName;
    }

    public void setMarketingTypeName(String marketingTypeName) {
        this.marketingTypeName = marketingTypeName;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}

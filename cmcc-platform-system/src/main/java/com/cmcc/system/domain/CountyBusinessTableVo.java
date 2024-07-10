package com.cmcc.system.domain;

import java.util.List;

/**
 * @author:terry
 * @create: 2023-12-30 16:31
 * @Description: 区县业务表格实体
 */
public class CountyBusinessTableVo {

    private List<String> businessCounty;

    private List<CountyBusinessItemVo> businessCountyScore;

    public List<String> getBusinessCounty() {
        return businessCounty;
    }

    public void setBusinessCounty(List<String> businessCounty) {
        this.businessCounty = businessCounty;
    }

    public List<CountyBusinessItemVo> getBusinessCountyScore() {
        return businessCountyScore;
    }

    public void setBusinessCountyScore(List<CountyBusinessItemVo> businessCountyScore) {
        this.businessCountyScore = businessCountyScore;
    }
}

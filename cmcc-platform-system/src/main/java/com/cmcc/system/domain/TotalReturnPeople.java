package com.cmcc.system.domain;

/**
 * @author:terry
 * @create: 2023-12-26 21:33
 * @Description: 人员返回总数
 */
public class TotalReturnPeople {

    private String currMonth;

    private Integer totalReturnNumber;

    public String getCurrMonth() {
        return currMonth;
    }

    public void setCurrMonth(String currMonth) {
        this.currMonth = currMonth;
    }

    public Integer getTotalReturnNumber() {
        return totalReturnNumber;
    }

    public void setTotalReturnNumber(Integer totalReturnNumber) {
        this.totalReturnNumber = totalReturnNumber;
    }
}

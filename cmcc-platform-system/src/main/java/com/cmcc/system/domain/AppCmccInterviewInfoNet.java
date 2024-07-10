package com.cmcc.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author:terry
 * @create: 2024-05-17 16:25
 * @Description: 走访对象网络app
 */
public class AppCmccInterviewInfoNet {

    private String netType;

    private String ispName;

    private Long netSpeed;

    private BigDecimal netPrice;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    public String getNetType() {
        return netType;
    }

    public void setNetType(String netType) {
        this.netType = netType;
    }

    public String getIspName() {
        return ispName;
    }

    public void setIspName(String ispName) {
        this.ispName = ispName;
    }

    public Long getNetSpeed() {
        return netSpeed;
    }

    public void setNetSpeed(Long netSpeed) {
        this.netSpeed = netSpeed;
    }

    public BigDecimal getNetPrice() {
        return netPrice;
    }

    public void setNetPrice(BigDecimal netPrice) {
        this.netPrice = netPrice;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}

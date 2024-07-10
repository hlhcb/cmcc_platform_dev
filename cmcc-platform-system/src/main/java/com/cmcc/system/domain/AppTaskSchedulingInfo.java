package com.cmcc.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author:terry
 * @create: 2024-04-18 03:19
 * @Description: app端对象
 */
public class AppTaskSchedulingInfo {
    /** 日期*/
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date  time;

    /** 联防员 */
    private String name1;

    /** 所属企业 */
    private String area2;

    /** 联防长 */
    private String name2;

    /** 巡查状态 */
    private String type;

    /** 电话1 */
    private String phone1;

    /** 电话2 */
    private String phone2;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getArea2() {
        return area2;
    }

    public void setArea2(String area2) {
        this.area2 = area2;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }
}

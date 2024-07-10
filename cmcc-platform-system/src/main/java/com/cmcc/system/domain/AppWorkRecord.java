package com.cmcc.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

/**
 * @author:terry
 * @create: 2024-04-18 21:53
 * @Description: app端工单记录
 */
public class AppWorkRecord {

    /** 工单编号*/
    private String num;

    /** 工单类型*/
    private String type;

    /** 巡查人员*/
    private String name;

    /** 联防区域*/
    private String area1;

    /** 巡查企业*/
    private String area2;

    /** 详细位置*/
    private String add;

    /** 接收人员 */
    private String name2;

    /** 处理人员 */
    private String name3;

    /** 情况说明 */
    private String introduction;

    /** 上报时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;

    /** 问题隐患照片*/
    private List<String> urls;


    /** 处理照片*/
    private List<String> urls2;


    /** 处理说明 */
    private String introduction2;

    /** 处理时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time2;

    private String add2;


    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea1() {
        return area1;
    }

    public void setArea1(String area1) {
        this.area1 = area1;
    }

    public String getArea2() {
        return area2;
    }

    public void setArea2(String area2) {
        this.area2 = area2;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    public List<String> getUrls2() {
        return urls2;
    }

    public void setUrls2(List<String> urls2) {
        this.urls2 = urls2;
    }

    public String getIntroduction2() {
        return introduction2;
    }

    public void setIntroduction2(String introduction2) {
        this.introduction2 = introduction2;
    }

    public Date getTime2() {
        return time2;
    }

    public void setTime2(Date time2) {
        this.time2 = time2;
    }

    public String getAdd2() {
        return add2;
    }

    public void setAdd2(String add2) {
        this.add2 = add2;
    }

    public String getName3() {
        return name3;
    }

    public void setName3(String name3) {
        this.name3 = name3;
    }
}

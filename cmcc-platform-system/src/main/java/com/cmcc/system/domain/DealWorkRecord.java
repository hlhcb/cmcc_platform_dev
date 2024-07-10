package com.cmcc.system.domain;

import java.util.List;

/**
 * @author:terry
 * @create: 2024-04-19 01:01
 * @Description: 处理工单对象
 */
public class DealWorkRecord {
    /** 工单编号*/
    private String num;


    /** 处理照片 */
    private List<String> urls2;

    /** 处理说明*/
    private String introduction2;


    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
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
}

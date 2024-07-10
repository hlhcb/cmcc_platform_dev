package com.cmcc.system.domain;

import java.util.List;

/**
 * @author:terry
 * @create: 2024-04-18 04:14
 * @Description: App巡查记录对象
 */
public class AppInspectionTaskInfo {

    /** 巡查类型*/
    private String type;

    /** 巡查人员*/
    private String name;

    /** 联防区域*/
    private String area1;

    /** 区域id*/
    private Long area1id;

    /** 巡查企业 */
    private String area2;

    /** 企业id */
    private Long area2id;

    /** 详细位置 */
    private String addr;


    /** 是否存在隐患问题 */
    private String problem;


    /** 巡查情况说明 */
    private String  introduction;

    /** 照片（最多2张）*/
    private List<String> urls;

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

    public Long getArea1id() {
        return area1id;
    }

    public void setArea1id(Long area1id) {
        this.area1id = area1id;
    }

    public String getArea2() {
        return area2;
    }

    public void setArea2(String area2) {
        this.area2 = area2;
    }

    public Long getArea2id() {
        return area2id;
    }

    public void setArea2id(Long area2id) {
        this.area2id = area2id;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }
}

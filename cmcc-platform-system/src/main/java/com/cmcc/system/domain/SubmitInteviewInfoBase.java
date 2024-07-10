package com.cmcc.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author:terry
 * @create: 2024-02-27 09:22
 * @Description: 提交走访信息base
 */
public class SubmitInteviewInfoBase {

    // 记录id
    private Long id;

    // 被走访人姓名
    private String name;

    // 被走访人联系方式
    private String phone;

    // 派出所
    private String affiliation1;

    // 归属地
    private String affiliation2;

    // 走访地址
    private String address;

    // 定位地址
    private String mapAddress;

    // 纬度
    private BigDecimal latitude;

    // 维度
    private BigDecimal longitude;

    // 走访记录
    private String introduction;

    // 矛盾纠纷，0：否，1：是
    private Integer problem;

    // 群众满意，0：否，1：是
    private Integer problem1;

    // 群众满意，0：否，1：是
    private Boolean tenantflag;

    // 走访开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timebegin;

    // 走访结束时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timeover;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAffiliation1() {
        return affiliation1;
    }

    public void setAffiliation1(String affiliation1) {
        this.affiliation1 = affiliation1;
    }

    public String getAffiliation2() {
        return affiliation2;
    }

    public void setAffiliation2(String affiliation2) {
        this.affiliation2 = affiliation2;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getProblem() {
        return problem;
    }

    public void setProblem(Integer problem) {
        this.problem = problem;
    }

    public Integer getProblem1() {
        return problem1;
    }

    public void setProblem1(Integer problem1) {
        this.problem1 = problem1;
    }

    public Date getTimebegin() {
        return timebegin;
    }

    public void setTimebegin(Date timebegin) {
        this.timebegin = timebegin;
    }

    public Date getTimeover() {
        return timeover;
    }

    public void setTimeover(Date timeover) {
        this.timeover = timeover;
    }

    public Boolean getTenantflag() {
        return tenantflag;
    }

    public void setTenantflag(Boolean tenantflag) {
        this.tenantflag = tenantflag;
    }

    public String getMapAddress() {
        return mapAddress;
    }

    public void setMapAddress(String mapAddress) {
        this.mapAddress = mapAddress;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

package com.cmcc.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author:terry
 * @create: 2024-05-17 16:04
 * @Description: 走访对象app
 */
public class AppCmccInterviewInfo {

    private String sceneType;

    private String businessName;

    private String addrInfo;

    private String customerName;

    private String customerTel;

    private String customerPosition;

    private String information;

    private Integer problem;

    private String groupName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timebegin;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timeover;

    private String mapAddress;
    private String remarks;

    private BigDecimal latitude;

    private BigDecimal longitude;

    private List<AppCmccInterviewInfoOther> needFormData;

    private List<AppCmccInterviewInfoNet> netFormData;

    List<String> urls;

    private Long groupId;

    private Long enterpriseId;

    public String getSceneType() {
        return sceneType;
    }

    public void setSceneType(String sceneType) {
        this.sceneType = sceneType;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getAddrInfo() {
        return addrInfo;
    }

    public void setAddrInfo(String addrInfo) {
        this.addrInfo = addrInfo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerTel() {
        return customerTel;
    }

    public void setCustomerTel(String customerTel) {
        this.customerTel = customerTel;
    }

    public String getCustomerPosition() {
        return customerPosition;
    }

    public void setCustomerPosition(String customerPosition) {
        this.customerPosition = customerPosition;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public Integer getProblem() {
        return problem;
    }

    public void setProblem(Integer problem) {
        this.problem = problem;
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

    public List<AppCmccInterviewInfoOther> getNeedFormData() {
        return needFormData;
    }

    public void setNeedFormData(List<AppCmccInterviewInfoOther> needFormData) {
        this.needFormData = needFormData;
    }

    public List<AppCmccInterviewInfoNet> getNetFormData() {
        return netFormData;
    }

    public void setNetFormData(List<AppCmccInterviewInfoNet> netFormData) {
        this.netFormData = netFormData;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}

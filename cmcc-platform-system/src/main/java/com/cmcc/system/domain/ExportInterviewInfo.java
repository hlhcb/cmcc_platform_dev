package com.cmcc.system.domain;

import com.cmcc.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author:terry
 * @create: 2024-03-07 10:16
 * @Description: 到处对象
 */
public class ExportInterviewInfo {

    /** 主键id */
    @Excel(name = "序号")
    private Long id;

    /** 被走访人电话 */
    @Excel(name = "电话")
    private String interviewPhone;

    /** 被走访人 */
    @Excel(name = "姓名")
    private String interviewName;

    /** 区县*/
    @Excel(name = "区县")
    private String countyName="高新区";

    /** 乡镇（街道） */
    @Excel(name = "乡镇（街道）")
    private String streesName="高新区";

    /** 派出所 */
    @Excel(name = "派出所")
    private String localPoliceStation;

    /** 走访人id */
    private Long officerId;

    /** 归属地 */
    @Excel(name = "社区（村）")
    private String belongingPlace;

    /** 走访地址 */
    @Excel(name = "居住地址")
    private String interviewAddr;

    /** 走访人 */
    @Excel(name = "走访民警")
    private String officerName;

    /** 走访开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "走访时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date interviewBeginTime;


    /** 重复标记*/
    @Excel(name = "重复标记")
    private Integer repeatFlag;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInterviewPhone() {
        return interviewPhone;
    }

    public void setInterviewPhone(String interviewPhone) {
        this.interviewPhone = interviewPhone;
    }

    public String getInterviewName() {
        return interviewName;
    }

    public void setInterviewName(String interviewName) {
        this.interviewName = interviewName;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getStreesName() {
        return streesName;
    }

    public void setStreesName(String streesName) {
        this.streesName = streesName;
    }

    public String getLocalPoliceStation() {
        return localPoliceStation;
    }

    public void setLocalPoliceStation(String localPoliceStation) {
        this.localPoliceStation = localPoliceStation;
    }

    public Long getOfficerId() {
        return officerId;
    }

    public void setOfficerId(Long officerId) {
        this.officerId = officerId;
    }

    public String getBelongingPlace() {
        return belongingPlace;
    }

    public void setBelongingPlace(String belongingPlace) {
        this.belongingPlace = belongingPlace;
    }

    public String getInterviewAddr() {
        return interviewAddr;
    }

    public void setInterviewAddr(String interviewAddr) {
        this.interviewAddr = interviewAddr;
    }

    public String getOfficerName() {
        return officerName;
    }

    public void setOfficerName(String officerName) {
        this.officerName = officerName;
    }

    public Date getInterviewBeginTime() {
        return interviewBeginTime;
    }

    public void setInterviewBeginTime(Date interviewBeginTime) {
        this.interviewBeginTime = interviewBeginTime;
    }

    public Integer getRepeatFlag() {
        return repeatFlag;
    }

    public void setRepeatFlag(Integer repeatFlag) {
        this.repeatFlag = repeatFlag;
    }
}

package com.cmcc.system.domain;

import com.cmcc.system.domain.vo.ContactDetail;

import java.util.List;

/**
 * @author:terry
 * @create: 2023-12-24 19:23
 * @Description: 返乡人员首页数据
 */
public class PeoPleReturnHome {

    private  int[] beforeYearData;

    private  int[] currentYearData;

//    private String[] totalRankName;
//
//    private int[] totalRankPeople;

    private List<ReturnRankRecord> returnRankList;

    private ContactDetail dayContact;

    private ContactDetail mouthContact;

    private List<ReturnTypeDeatail> returnTypeList;

    private  int[] dayBusiness;

    private  int[] mouthBusiness;

    private String recordDate;

    private List<List<MapFlightVo>> sHData;

    public int[] getBeforeYearData() {
        return beforeYearData;
    }

    public void setBeforeYearData(int[] beforeYearData) {
        this.beforeYearData = beforeYearData;
    }

    public int[] getCurrentYearData() {
        return currentYearData;
    }

    public void setCurrentYearData(int[] currentYearData) {
        this.currentYearData = currentYearData;
    }

    public List<ReturnTypeDeatail> getReturnTypeList() {
        return returnTypeList;
    }

    public void setReturnTypeList(List<ReturnTypeDeatail> returnTypeList) {
        this.returnTypeList = returnTypeList;
    }

    public ContactDetail getDayContact() {
        return dayContact;
    }

    public void setDayContact(ContactDetail dayContact) {
        this.dayContact = dayContact;
    }

    public ContactDetail getMouthContact() {
        return mouthContact;
    }

    public void setMouthContact(ContactDetail mouthContact) {
        this.mouthContact = mouthContact;
    }

    public int[] getDayBusiness() {
        return dayBusiness;
    }

    public void setDayBusiness(int[] dayBusiness) {
        this.dayBusiness = dayBusiness;
    }

    public int[] getMouthBusiness() {
        return mouthBusiness;
    }

    public void setMouthBusiness(int[] mouthBusiness) {
        this.mouthBusiness = mouthBusiness;
    }

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }

    public List<ReturnRankRecord> getReturnRankList() {
        return returnRankList;
    }

    public void setReturnRankList(List<ReturnRankRecord> returnRankList) {
        this.returnRankList = returnRankList;
    }

    public List<List<MapFlightVo>> getsHData() {
        return sHData;
    }

    public void setsHData(List<List<MapFlightVo>> sHData) {
        this.sHData = sHData;
    }
}

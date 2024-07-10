package com.cmcc.system.domain;

import java.util.List;

/**
 * @author:terry
 * @create: 2024-02-27 08:48
 * @Description: 提交走访信息
 */
public class SubmitInteviewInfo {

    // 基础表单数据
    private SubmitInteviewInfoBase baseFormData;

    // 矛盾反馈数据
    private String problemFromData;

    // 满意度反馈数据
    private String problemFromData1;

    // 归属地
    private String affiliation2;

    // 房租信息
    private TenantFormData tenantFormData;

    private List<String> urls;

    public SubmitInteviewInfoBase getBaseFormData() {
        return baseFormData;
    }

    public void setBaseFormData(SubmitInteviewInfoBase baseFormData) {
        this.baseFormData = baseFormData;
    }

    public String getProblemFromData() {
        return problemFromData;
    }

    public void setProblemFromData(String problemFromData) {
        this.problemFromData = problemFromData;
    }

    public String getProblemFromData1() {
        return problemFromData1;
    }

    public void setProblemFromData1(String problemFromData1) {
        this.problemFromData1 = problemFromData1;
    }

    public TenantFormData getTenantFormData() {
        return tenantFormData;
    }

    public void setTenantFormData(TenantFormData tenantFormData) {
        this.tenantFormData = tenantFormData;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    public String getAffiliation2() {
        return affiliation2;
    }

    public void setAffiliation2(String affiliation2) {
        this.affiliation2 = affiliation2;
    }
}

package com.cmcc.system.domain;

/**
 * @author:terry
 * @create: 2024-02-24 17:20
 * @Description: 归属地集合
 */
public class BelongPlaceCode {

    private String code;

    private String name;

    private String parentCode;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }
}

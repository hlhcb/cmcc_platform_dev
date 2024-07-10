package com.cmcc.system.domain;

/**
 * @author:terry
 * @create: 2023-12-28 21:10
 * @Description: 区域编码
 */
public class CenterAreaCode {

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

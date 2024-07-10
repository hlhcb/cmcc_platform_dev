package com.cmcc.system.domain;

/**
 * @author:terry
 * @create: 2024-02-27 14:33
 * @Description: 租户表单
 */
public class TenantFormData {

    // 姓名
    private String name;

    // 联系方式
    private String phone;

    // 身份证
    private String number;

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}

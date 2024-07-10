package com.cmcc.system.domain.vo;

/**
 * @author:terry
 * @create: 2023-12-24 23:16
 * @Description: 接触明细
 */
public class ContactDetail {

    private String name;
    private Integer total;
    private Integer contact;
    private Double contactPercentage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getContact() {
        return contact;
    }

    public void setContact(Integer contact) {
        this.contact = contact;
    }

    public Double getContactPercentage() {
        return contactPercentage;
    }

    public void setContactPercentage(Double contactPercentage) {
        this.contactPercentage = contactPercentage;
    }
}

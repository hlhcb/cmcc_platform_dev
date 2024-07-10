package com.cmcc.system.domain;

/**
 * @author:terry
 * @create: 2024-05-11 15:10
 * @Description: app归属地管理表
 */
public class CommunityConfigVo {

    /** text */
    private String text;

    /** value */
    private Integer value;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}

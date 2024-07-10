package com.cmcc.system.domain;

/**
 * @author:terry
 * @create: 2023-12-24 23:07
 * @Description: 返乡人员类型对象
 */
public class ReturnTypeDeatail {
    public ReturnTypeDeatail(String name, int value)
    {
        this.name = name;
        this.value = value;
    }

    public ReturnTypeDeatail()
    {

    }
    private String name;

    private Integer value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}

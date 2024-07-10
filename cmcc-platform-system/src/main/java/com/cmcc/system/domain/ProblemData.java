package com.cmcc.system.domain;

import java.util.List;

/**
 * @author:terry
 * @create: 2024-04-23 13:05
 * @Description: 隐患数据
 */
public class ProblemData {

    private String add;

    private String introduction;

    List<String> urls;

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }
}

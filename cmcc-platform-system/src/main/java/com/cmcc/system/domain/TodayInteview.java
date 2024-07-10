package com.cmcc.system.domain;

import java.util.List;

/**
 * @author:terry
 * @create: 2024-02-27 01:23
 * @Description: 今日访问对象
 */
public class TodayInteview {

    private Long id;

    private String 	name;
    private String 	time;
    private String 	address;
    private String date;
    private List<String> url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<String> getUrl() {
        return url;
    }

    public void setUrl(List<String> url) {
        this.url = url;
    }
}


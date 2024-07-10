package com.cmcc.system.domain;

/**
 * @author:terry
 * @create: 2024-04-21 09:00
 * @Description: 接收用户
 */
public class AppAccpetUser {
    private String nickname;

    private Long  uid;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }
}

package com.cmcc.system.domain;

import java.math.BigInteger;

/**
 * @author:terry
 * @create: 2024-02-25 20:13
 * @Description: 查询用户信息
 */
public class SearchUserInfo {

    private Long userId;

    private String userName;

    private String deptName;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}

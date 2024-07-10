package com.cmcc.system.domain;

/**
 * @author:terry
 * @create: 2024-06-26 11:42
 * @Description: 查询对象
 */
public class CmccAppTaskQueryVo {

    private String interviewStatus;

    private String queryName;

    private Integer groupId;

    private Integer id;

    public String getInterviewStatus() {
        return interviewStatus;
    }

    public String getQueryName() {
        return queryName;
    }

    public void setQueryName(String queryName) {
        this.queryName = queryName;
    }

    public void setInterviewStatus(String interviewStatus) {
        this.interviewStatus = interviewStatus;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

package com.cmcc.system.service;

import java.util.List;
import java.util.Map;

import com.cmcc.system.domain.AppCountInfo;
import com.cmcc.system.domain.InterviewCountResult;
import com.cmcc.system.domain.InterviewInfo;
import com.cmcc.system.domain.InterviewRankResult;

/**
 * 访问记录Service接口
 * 
 * @author terry
 * @date 2024-02-24
 */
public interface IInterviewInfoService 
{

    public List<InterviewCountResult> selectCountInterview(Map<String, Object> map);

    public List<InterviewRankResult> selectRankInterview(InterviewInfo interviewInfo);

    public AppCountInfo selectAppCountInfo(Map<String, Object> map);
    /**
     * 查询访问记录
     * 
     * @param id 访问记录主键
     * @return 访问记录
     */
    public InterviewInfo selectInterviewInfoById(Long id);

    /**
     * 查询访问记录列表
     * 
     * @param interviewInfo 访问记录
     * @return 访问记录集合
     */
    public List<InterviewInfo> selectInterviewInfoList(InterviewInfo interviewInfo);

    public int updateInterviewInfoRepeatFlag(InterviewInfo interviewInfo);

    /**
     * 新增访问记录
     * 
     * @param interviewInfo 访问记录
     * @return 结果
     */
    public int insertInterviewInfo(InterviewInfo interviewInfo);

    /**
     * 修改访问记录
     * 
     * @param interviewInfo 访问记录
     * @return 结果
     */
    public int updateInterviewInfo(InterviewInfo interviewInfo);

    /**
     * 批量删除访问记录
     * 
     * @param ids 需要删除的访问记录主键集合
     * @return 结果
     */
    public int deleteInterviewInfoByIds(Long[] ids);

    /**
     * 删除访问记录信息
     * 
     * @param id 访问记录主键
     * @return 结果
     */
    public int deleteInterviewInfoById(Long id);
}

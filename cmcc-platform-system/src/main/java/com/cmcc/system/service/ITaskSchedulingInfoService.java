package com.cmcc.system.service;

import java.util.List;
import com.cmcc.system.domain.TaskSchedulingInfo;

/**
 * 值班配置Service接口
 * 
 * @author terry
 * @date 2024-04-18
 */
public interface ITaskSchedulingInfoService 
{
    /**
     * 查询值班配置
     * 
     * @param id 值班配置主键
     * @return 值班配置
     */
    public TaskSchedulingInfo selectTaskSchedulingInfoById(Long id);

    /**
     * 查询值班配置列表
     * 
     * @param taskSchedulingInfo 值班配置
     * @return 值班配置集合
     */
    public List<TaskSchedulingInfo> selectTaskSchedulingInfoList(TaskSchedulingInfo taskSchedulingInfo);

    /**
     * 新增值班配置
     * 
     * @param taskSchedulingInfo 值班配置
     * @return 结果
     */
    public int insertTaskSchedulingInfo(TaskSchedulingInfo taskSchedulingInfo);

    /**
     * 修改值班配置
     * 
     * @param taskSchedulingInfo 值班配置
     * @return 结果
     */
    public int updateTaskSchedulingInfo(TaskSchedulingInfo taskSchedulingInfo);

    /**
     * 批量删除值班配置
     * 
     * @param ids 需要删除的值班配置主键集合
     * @return 结果
     */
    public int deleteTaskSchedulingInfoByIds(Long[] ids);

    /**
     * 删除值班配置信息
     * 
     * @param id 值班配置主键
     * @return 结果
     */
    public int deleteTaskSchedulingInfoById(Long id);
}

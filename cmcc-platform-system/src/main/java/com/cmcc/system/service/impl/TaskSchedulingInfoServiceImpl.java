package com.cmcc.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cmcc.system.mapper.TaskSchedulingInfoMapper;
import com.cmcc.system.domain.TaskSchedulingInfo;
import com.cmcc.system.service.ITaskSchedulingInfoService;

/**
 * 值班配置Service业务层处理
 * 
 * @author terry
 * @date 2024-04-18
 */
@Service
public class TaskSchedulingInfoServiceImpl implements ITaskSchedulingInfoService 
{
    @Autowired
    private TaskSchedulingInfoMapper taskSchedulingInfoMapper;

    /**
     * 查询值班配置
     * 
     * @param id 值班配置主键
     * @return 值班配置
     */
    @Override
    public TaskSchedulingInfo selectTaskSchedulingInfoById(Long id)
    {
        return taskSchedulingInfoMapper.selectTaskSchedulingInfoById(id);
    }

    /**
     * 查询值班配置列表
     * 
     * @param taskSchedulingInfo 值班配置
     * @return 值班配置
     */
    @Override
    public List<TaskSchedulingInfo> selectTaskSchedulingInfoList(TaskSchedulingInfo taskSchedulingInfo)
    {
        return taskSchedulingInfoMapper.selectTaskSchedulingInfoList(taskSchedulingInfo);
    }

    /**
     * 新增值班配置
     * 
     * @param taskSchedulingInfo 值班配置
     * @return 结果
     */
    @Override
    public int insertTaskSchedulingInfo(TaskSchedulingInfo taskSchedulingInfo)
    {
        return taskSchedulingInfoMapper.insertTaskSchedulingInfo(taskSchedulingInfo);
    }

    /**
     * 修改值班配置
     * 
     * @param taskSchedulingInfo 值班配置
     * @return 结果
     */
    @Override
    public int updateTaskSchedulingInfo(TaskSchedulingInfo taskSchedulingInfo)
    {
        return taskSchedulingInfoMapper.updateTaskSchedulingInfo(taskSchedulingInfo);
    }

    /**
     * 批量删除值班配置
     * 
     * @param ids 需要删除的值班配置主键
     * @return 结果
     */
    @Override
    public int deleteTaskSchedulingInfoByIds(Long[] ids)
    {
        return taskSchedulingInfoMapper.deleteTaskSchedulingInfoByIds(ids);
    }

    /**
     * 删除值班配置信息
     * 
     * @param id 值班配置主键
     * @return 结果
     */
    @Override
    public int deleteTaskSchedulingInfoById(Long id)
    {
        return taskSchedulingInfoMapper.deleteTaskSchedulingInfoById(id);
    }
}

package com.cmcc.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cmcc.system.mapper.InspectionTaskInfoMapper;
import com.cmcc.system.domain.InspectionTaskInfo;
import com.cmcc.system.service.IInspectionTaskInfoService;

/**
 * 巡检记录Service业务层处理
 * 
 * @author terry
 * @date 2024-04-18
 */
@Service
public class InspectionTaskInfoServiceImpl implements IInspectionTaskInfoService 
{
    @Autowired
    private InspectionTaskInfoMapper inspectionTaskInfoMapper;

    /**
     * 查询巡检记录
     * 
     * @param id 巡检记录主键
     * @return 巡检记录
     */
    @Override
    public InspectionTaskInfo selectInspectionTaskInfoById(Long id)
    {
        return inspectionTaskInfoMapper.selectInspectionTaskInfoById(id);
    }

    /**
     * 查询巡检记录列表
     * 
     * @param inspectionTaskInfo 巡检记录
     * @return 巡检记录
     */
    @Override
    public List<InspectionTaskInfo> selectInspectionTaskInfoList(InspectionTaskInfo inspectionTaskInfo)
    {
        return inspectionTaskInfoMapper.selectInspectionTaskInfoList(inspectionTaskInfo);
    }

    /**
     * 新增巡检记录
     * 
     * @param inspectionTaskInfo 巡检记录
     * @return 结果
     */
    @Override
    public int insertInspectionTaskInfo(InspectionTaskInfo inspectionTaskInfo)
    {
        return inspectionTaskInfoMapper.insertInspectionTaskInfo(inspectionTaskInfo);
    }

    /**
     * 修改巡检记录
     * 
     * @param inspectionTaskInfo 巡检记录
     * @return 结果
     */
    @Override
    public int updateInspectionTaskInfo(InspectionTaskInfo inspectionTaskInfo)
    {
        return inspectionTaskInfoMapper.updateInspectionTaskInfo(inspectionTaskInfo);
    }

    /**
     * 批量删除巡检记录
     * 
     * @param ids 需要删除的巡检记录主键
     * @return 结果
     */
    @Override
    public int deleteInspectionTaskInfoByIds(Long[] ids)
    {
        return inspectionTaskInfoMapper.deleteInspectionTaskInfoByIds(ids);
    }

    /**
     * 删除巡检记录信息
     * 
     * @param id 巡检记录主键
     * @return 结果
     */
    @Override
    public int deleteInspectionTaskInfoById(Long id)
    {
        return inspectionTaskInfoMapper.deleteInspectionTaskInfoById(id);
    }
}

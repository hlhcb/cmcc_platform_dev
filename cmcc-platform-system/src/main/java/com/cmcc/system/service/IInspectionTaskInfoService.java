package com.cmcc.system.service;

import java.util.List;
import com.cmcc.system.domain.InspectionTaskInfo;

/**
 * 巡检记录Service接口
 * 
 * @author terry
 * @date 2024-04-18
 */
public interface IInspectionTaskInfoService 
{
    /**
     * 查询巡检记录
     * 
     * @param id 巡检记录主键
     * @return 巡检记录
     */
    public InspectionTaskInfo selectInspectionTaskInfoById(Long id);

    /**
     * 查询巡检记录列表
     * 
     * @param inspectionTaskInfo 巡检记录
     * @return 巡检记录集合
     */
    public List<InspectionTaskInfo> selectInspectionTaskInfoList(InspectionTaskInfo inspectionTaskInfo);

    /**
     * 新增巡检记录
     * 
     * @param inspectionTaskInfo 巡检记录
     * @return 结果
     */
    public int insertInspectionTaskInfo(InspectionTaskInfo inspectionTaskInfo);

    /**
     * 修改巡检记录
     * 
     * @param inspectionTaskInfo 巡检记录
     * @return 结果
     */
    public int updateInspectionTaskInfo(InspectionTaskInfo inspectionTaskInfo);

    /**
     * 批量删除巡检记录
     * 
     * @param ids 需要删除的巡检记录主键集合
     * @return 结果
     */
    public int deleteInspectionTaskInfoByIds(Long[] ids);

    /**
     * 删除巡检记录信息
     * 
     * @param id 巡检记录主键
     * @return 结果
     */
    public int deleteInspectionTaskInfoById(Long id);
}

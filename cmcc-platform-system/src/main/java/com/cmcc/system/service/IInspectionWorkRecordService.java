package com.cmcc.system.service;

import java.util.List;
import java.util.Map;

import com.cmcc.system.domain.InspectionWorkRecord;
import com.cmcc.system.domain.StatusWorkRecord;
import com.cmcc.system.domain.TypeWorkRecord;

/**
 * 工单记录Service接口
 * 
 * @author terry
 * @date 2024-04-18
 */
public interface IInspectionWorkRecordService 
{

    public List<TypeWorkRecord> selectAppCountByType(Map<String, Object> map);
    public List<StatusWorkRecord> selectAppCountByStatus(Map<String, Object> map);
    /**
     * 查询工单记录
     * 
     * @param id 工单记录主键
     * @return 工单记录
     */
    public InspectionWorkRecord selectInspectionWorkRecordById(Long id);

    /**
     * 查询工单记录列表
     * 
     * @param inspectionWorkRecord 工单记录
     * @return 工单记录集合
     */
    public List<InspectionWorkRecord> selectInspectionWorkRecordList(InspectionWorkRecord inspectionWorkRecord);

    /**
     * 新增工单记录
     * 
     * @param inspectionWorkRecord 工单记录
     * @return 结果
     */
    public int insertInspectionWorkRecord(InspectionWorkRecord inspectionWorkRecord);

    /**
     * 修改工单记录
     * 
     * @param inspectionWorkRecord 工单记录
     * @return 结果
     */
    public int updateInspectionWorkRecord(InspectionWorkRecord inspectionWorkRecord);

    /**
     * 批量删除工单记录
     * 
     * @param ids 需要删除的工单记录主键集合
     * @return 结果
     */
    public int deleteInspectionWorkRecordByIds(Long[] ids);

    /**
     * 删除工单记录信息
     * 
     * @param id 工单记录主键
     * @return 结果
     */
    public int deleteInspectionWorkRecordById(Long id);
}

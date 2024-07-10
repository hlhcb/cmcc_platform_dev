package com.cmcc.system.service.impl;

import java.util.List;
import java.util.Map;

import com.cmcc.system.domain.StatusWorkRecord;
import com.cmcc.system.domain.TypeWorkRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cmcc.system.mapper.InspectionWorkRecordMapper;
import com.cmcc.system.domain.InspectionWorkRecord;
import com.cmcc.system.service.IInspectionWorkRecordService;

/**
 * 工单记录Service业务层处理
 * 
 * @author terry
 * @date 2024-04-18
 */
@Service
public class InspectionWorkRecordServiceImpl implements IInspectionWorkRecordService 
{
    @Autowired
    private InspectionWorkRecordMapper inspectionWorkRecordMapper;

    @Override
    public List<TypeWorkRecord> selectAppCountByType(Map<String, Object> map)
    {
        return inspectionWorkRecordMapper.selectAppCountByType(map);
    }

    @Override
    public List<StatusWorkRecord> selectAppCountByStatus(Map<String, Object> map)
    {
        return inspectionWorkRecordMapper.selectAppCountByStatus(map);
    }

    /**
     * 查询工单记录
     * 
     * @param id 工单记录主键
     * @return 工单记录
     */
    @Override
    public InspectionWorkRecord selectInspectionWorkRecordById(Long id)
    {
        return inspectionWorkRecordMapper.selectInspectionWorkRecordById(id);
    }

    /**
     * 查询工单记录列表
     * 
     * @param inspectionWorkRecord 工单记录
     * @return 工单记录
     */
    @Override
    public List<InspectionWorkRecord> selectInspectionWorkRecordList(InspectionWorkRecord inspectionWorkRecord)
    {
        return inspectionWorkRecordMapper.selectInspectionWorkRecordList(inspectionWorkRecord);
    }

    /**
     * 新增工单记录
     * 
     * @param inspectionWorkRecord 工单记录
     * @return 结果
     */
    @Override
    public int insertInspectionWorkRecord(InspectionWorkRecord inspectionWorkRecord)
    {
        return inspectionWorkRecordMapper.insertInspectionWorkRecord(inspectionWorkRecord);
    }

    /**
     * 修改工单记录
     * 
     * @param inspectionWorkRecord 工单记录
     * @return 结果
     */
    @Override
    public int updateInspectionWorkRecord(InspectionWorkRecord inspectionWorkRecord)
    {
        return inspectionWorkRecordMapper.updateInspectionWorkRecord(inspectionWorkRecord);
    }


    /**
     * 批量删除工单记录
     * 
     * @param ids 需要删除的工单记录主键
     * @return 结果
     */
    @Override
    public int deleteInspectionWorkRecordByIds(Long[] ids)
    {
        return inspectionWorkRecordMapper.deleteInspectionWorkRecordByIds(ids);
    }

    /**
     * 删除工单记录信息
     * 
     * @param id 工单记录主键
     * @return 结果
     */
    @Override
    public int deleteInspectionWorkRecordById(Long id)
    {
        return inspectionWorkRecordMapper.deleteInspectionWorkRecordById(id);
    }
}

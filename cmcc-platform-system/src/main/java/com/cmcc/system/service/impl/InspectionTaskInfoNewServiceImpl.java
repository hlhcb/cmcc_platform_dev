package com.cmcc.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cmcc.system.mapper.InspectionTaskInfoNewMapper;
import com.cmcc.system.domain.InspectionTaskInfoNew;
import com.cmcc.system.service.IInspectionTaskInfoNewService;

/**
 * 巡检记录新Service业务层处理
 * 
 * @author terry
 * @date 2024-04-23
 */
@Service
public class InspectionTaskInfoNewServiceImpl implements IInspectionTaskInfoNewService 
{
    @Autowired
    private InspectionTaskInfoNewMapper inspectionTaskInfoNewMapper;

    /**
     * 查询巡检记录新
     * 
     * @param id 巡检记录新主键
     * @return 巡检记录新
     */
    @Override
    public InspectionTaskInfoNew selectInspectionTaskInfoNewById(Long id)
    {
        return inspectionTaskInfoNewMapper.selectInspectionTaskInfoNewById(id);
    }

    /**
     * 查询巡检记录新列表
     * 
     * @param inspectionTaskInfoNew 巡检记录新
     * @return 巡检记录新
     */
    @Override
    public List<InspectionTaskInfoNew> selectInspectionTaskInfoNewList(InspectionTaskInfoNew inspectionTaskInfoNew)
    {
        return inspectionTaskInfoNewMapper.selectInspectionTaskInfoNewList(inspectionTaskInfoNew);
    }

    /**
     * 新增巡检记录新
     * 
     * @param inspectionTaskInfoNew 巡检记录新
     * @return 结果
     */
    @Override
    public int insertInspectionTaskInfoNew(InspectionTaskInfoNew inspectionTaskInfoNew)
    {
        return inspectionTaskInfoNewMapper.insertInspectionTaskInfoNew(inspectionTaskInfoNew);
    }

    /**
     * 修改巡检记录新
     * 
     * @param inspectionTaskInfoNew 巡检记录新
     * @return 结果
     */
    @Override
    public int updateInspectionTaskInfoNew(InspectionTaskInfoNew inspectionTaskInfoNew)
    {
        return inspectionTaskInfoNewMapper.updateInspectionTaskInfoNew(inspectionTaskInfoNew);
    }

    /**
     * 批量删除巡检记录新
     * 
     * @param ids 需要删除的巡检记录新主键
     * @return 结果
     */
    @Override
    public int deleteInspectionTaskInfoNewByIds(Long[] ids)
    {
        return inspectionTaskInfoNewMapper.deleteInspectionTaskInfoNewByIds(ids);
    }

    /**
     * 删除巡检记录新信息
     * 
     * @param id 巡检记录新主键
     * @return 结果
     */
    @Override
    public int deleteInspectionTaskInfoNewById(Long id)
    {
        return inspectionTaskInfoNewMapper.deleteInspectionTaskInfoNewById(id);
    }
}

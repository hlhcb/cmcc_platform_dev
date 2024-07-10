package com.cmcc.system.service;

import java.util.List;
import com.cmcc.system.domain.InspectionTaskInfoNew;

/**
 * 巡检记录新Service接口
 * 
 * @author terry
 * @date 2024-04-23
 */
public interface IInspectionTaskInfoNewService 
{
    /**
     * 查询巡检记录新
     * 
     * @param id 巡检记录新主键
     * @return 巡检记录新
     */
    public InspectionTaskInfoNew selectInspectionTaskInfoNewById(Long id);

    /**
     * 查询巡检记录新列表
     * 
     * @param inspectionTaskInfoNew 巡检记录新
     * @return 巡检记录新集合
     */
    public List<InspectionTaskInfoNew> selectInspectionTaskInfoNewList(InspectionTaskInfoNew inspectionTaskInfoNew);

    /**
     * 新增巡检记录新
     * 
     * @param inspectionTaskInfoNew 巡检记录新
     * @return 结果
     */
    public int insertInspectionTaskInfoNew(InspectionTaskInfoNew inspectionTaskInfoNew);

    /**
     * 修改巡检记录新
     * 
     * @param inspectionTaskInfoNew 巡检记录新
     * @return 结果
     */
    public int updateInspectionTaskInfoNew(InspectionTaskInfoNew inspectionTaskInfoNew);

    /**
     * 批量删除巡检记录新
     * 
     * @param ids 需要删除的巡检记录新主键集合
     * @return 结果
     */
    public int deleteInspectionTaskInfoNewByIds(Long[] ids);

    /**
     * 删除巡检记录新信息
     * 
     * @param id 巡检记录新主键
     * @return 结果
     */
    public int deleteInspectionTaskInfoNewById(Long id);
}

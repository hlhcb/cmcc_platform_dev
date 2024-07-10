package com.cmcc.system.mapper;

import java.util.List;

import com.cmcc.system.domain.BusinessCountResult;
import com.cmcc.system.domain.BusinessProcessingCount;
import com.cmcc.system.domain.CountyBusinessVo;

/**
 * 业务办理统计表Mapper接口
 * 
 * @author terry
 * @date 2023-12-26
 */
public interface BusinessProcessingCountMapper 
{
    /**
     * 查询业务办理统计表
     * 
     * @param id 业务办理统计表主键
     * @return 业务办理统计表
     */
    public BusinessProcessingCount selectBusinessProcessingCountById(String id);

    /**
     * 查询业务办理统计表列表
     * 
     * @param businessProcessingCount 业务办理统计表
     * @return 业务办理统计表集合
     */
    public List<BusinessProcessingCount> selectBusinessProcessingCountList(BusinessProcessingCount businessProcessingCount);


    public List<BusinessCountResult> selectBusinessCountByDate(String currYear);

    public List<CountyBusinessVo> selectCountyBusinessByDate(String currYear);

    /**
     * 新增业务办理统计表
     * 
     * @param businessProcessingCount 业务办理统计表
     * @return 结果
     */
    public int insertBusinessProcessingCount(BusinessProcessingCount businessProcessingCount);

    /**
     * 修改业务办理统计表
     * 
     * @param businessProcessingCount 业务办理统计表
     * @return 结果
     */
    public int updateBusinessProcessingCount(BusinessProcessingCount businessProcessingCount);

    /**
     * 删除业务办理统计表
     * 
     * @param id 业务办理统计表主键
     * @return 结果
     */
    public int deleteBusinessProcessingCountById(String id);

    /**
     * 批量删除业务办理统计表
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBusinessProcessingCountByIds(String[] ids);
}

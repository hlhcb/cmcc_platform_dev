package com.cmcc.system.service.impl;

import java.util.List;

import com.cmcc.common.utils.uuid.UUID;
import com.cmcc.system.domain.BusinessCountResult;
import com.cmcc.system.domain.CountyBusinessVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cmcc.system.mapper.BusinessProcessingCountMapper;
import com.cmcc.system.domain.BusinessProcessingCount;
import com.cmcc.system.service.IBusinessProcessingCountService;

/**
 * 业务办理统计表Service业务层处理
 * 
 * @author terry
 * @date 2023-12-26
 */
@Service
public class BusinessProcessingCountServiceImpl implements IBusinessProcessingCountService 
{
    @Autowired
    private BusinessProcessingCountMapper businessProcessingCountMapper;

    /**
     * 查询业务办理统计表
     * 
     * @param id 业务办理统计表主键
     * @return 业务办理统计表
     */
    @Override
    public BusinessProcessingCount selectBusinessProcessingCountById(String id)
    {
        return businessProcessingCountMapper.selectBusinessProcessingCountById(id);
    }

    /**
     * 查询业务办理统计表列表
     * 
     * @param businessProcessingCount 业务办理统计表
     * @return 业务办理统计表
     */
    @Override
    public List<BusinessProcessingCount> selectBusinessProcessingCountList(BusinessProcessingCount businessProcessingCount)
    {
        return businessProcessingCountMapper.selectBusinessProcessingCountList(businessProcessingCount);
    }


    @Override
    public List<BusinessCountResult> selectBusinessCountByDate(String currYear)
    {
        return businessProcessingCountMapper.selectBusinessCountByDate(currYear);
    }

    @Override
    public List<CountyBusinessVo> selectCountyBusinessByDate(String currYear)
    {
        return businessProcessingCountMapper.selectCountyBusinessByDate(currYear);
    }
    /**
     * 新增业务办理统计表
     * 
     * @param businessProcessingCount 业务办理统计表
     * @return 结果
     */
    @Override
    public int insertBusinessProcessingCount(BusinessProcessingCount businessProcessingCount)
    {
        businessProcessingCount.setId(UUID.randomUUID().toString());
        return businessProcessingCountMapper.insertBusinessProcessingCount(businessProcessingCount);
    }

    /**
     * 修改业务办理统计表
     * 
     * @param businessProcessingCount 业务办理统计表
     * @return 结果
     */
    @Override
    public int updateBusinessProcessingCount(BusinessProcessingCount businessProcessingCount)
    {
        return businessProcessingCountMapper.updateBusinessProcessingCount(businessProcessingCount);
    }

    /**
     * 批量删除业务办理统计表
     * 
     * @param ids 需要删除的业务办理统计表主键
     * @return 结果
     */
    @Override
    public int deleteBusinessProcessingCountByIds(String[] ids)
    {
        return businessProcessingCountMapper.deleteBusinessProcessingCountByIds(ids);
    }

    /**
     * 删除业务办理统计表信息
     * 
     * @param id 业务办理统计表主键
     * @return 结果
     */
    @Override
    public int deleteBusinessProcessingCountById(String id)
    {
        return businessProcessingCountMapper.deleteBusinessProcessingCountById(id);
    }
}

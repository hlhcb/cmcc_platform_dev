package com.cmcc.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cmcc.system.mapper.MarketingGardInfoMapper;
import com.cmcc.system.domain.MarketingGardInfo;
import com.cmcc.system.service.IMarketingGardInfoService;

/**
 * 网格信息Service业务层处理
 * 
 * @author terry
 * @date 2023-12-28
 */
@Service
public class MarketingGardInfoServiceImpl implements IMarketingGardInfoService 
{
    @Autowired
    private MarketingGardInfoMapper marketingGardInfoMapper;

    /**
     * 查询网格信息
     * 
     * @param id 网格信息主键
     * @return 网格信息
     */
    @Override
    public MarketingGardInfo selectMarketingGardInfoById(Long id)
    {
        return marketingGardInfoMapper.selectMarketingGardInfoById(id);
    }

    /**
     * 查询网格信息列表
     * 
     * @param marketingGardInfo 网格信息
     * @return 网格信息
     */
    @Override
    public List<MarketingGardInfo> selectMarketingGardInfoList(MarketingGardInfo marketingGardInfo)
    {
        return marketingGardInfoMapper.selectMarketingGardInfoList(marketingGardInfo);
    }

    /**
     * 新增网格信息
     * 
     * @param marketingGardInfo 网格信息
     * @return 结果
     */
    @Override
    public int insertMarketingGardInfo(MarketingGardInfo marketingGardInfo)
    {
        return marketingGardInfoMapper.insertMarketingGardInfo(marketingGardInfo);
    }

    /**
     * 修改网格信息
     * 
     * @param marketingGardInfo 网格信息
     * @return 结果
     */
    @Override
    public int updateMarketingGardInfo(MarketingGardInfo marketingGardInfo)
    {
        return marketingGardInfoMapper.updateMarketingGardInfo(marketingGardInfo);
    }

    /**
     * 批量删除网格信息
     * 
     * @param ids 需要删除的网格信息主键
     * @return 结果
     */
    @Override
    public int deleteMarketingGardInfoByIds(Long[] ids)
    {
        return marketingGardInfoMapper.deleteMarketingGardInfoByIds(ids);
    }

    /**
     * 删除网格信息信息
     * 
     * @param id 网格信息主键
     * @return 结果
     */
    @Override
    public int deleteMarketingGardInfoById(Long id)
    {
        return marketingGardInfoMapper.deleteMarketingGardInfoById(id);
    }
}

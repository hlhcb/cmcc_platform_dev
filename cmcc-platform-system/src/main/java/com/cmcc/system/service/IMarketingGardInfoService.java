package com.cmcc.system.service;

import java.util.List;
import com.cmcc.system.domain.MarketingGardInfo;

/**
 * 网格信息Service接口
 * 
 * @author terry
 * @date 2023-12-28
 */
public interface IMarketingGardInfoService 
{
    /**
     * 查询网格信息
     * 
     * @param id 网格信息主键
     * @return 网格信息
     */
    public MarketingGardInfo selectMarketingGardInfoById(Long id);

    /**
     * 查询网格信息列表
     * 
     * @param marketingGardInfo 网格信息
     * @return 网格信息集合
     */
    public List<MarketingGardInfo> selectMarketingGardInfoList(MarketingGardInfo marketingGardInfo);

    /**
     * 新增网格信息
     * 
     * @param marketingGardInfo 网格信息
     * @return 结果
     */
    public int insertMarketingGardInfo(MarketingGardInfo marketingGardInfo);

    /**
     * 修改网格信息
     * 
     * @param marketingGardInfo 网格信息
     * @return 结果
     */
    public int updateMarketingGardInfo(MarketingGardInfo marketingGardInfo);

    /**
     * 批量删除网格信息
     * 
     * @param ids 需要删除的网格信息主键集合
     * @return 结果
     */
    public int deleteMarketingGardInfoByIds(Long[] ids);

    /**
     * 删除网格信息信息
     * 
     * @param id 网格信息主键
     * @return 结果
     */
    public int deleteMarketingGardInfoById(Long id);

}

package com.cmcc.system.mapper;

import java.util.List;
import com.cmcc.system.domain.CenterRankInfo;

/**
 * 网格龙虎榜Mapper接口
 * 
 * @author terry
 * @date 2023-12-28
 */
public interface CenterRankInfoMapper 
{
    /**
     * 查询网格龙虎榜
     * 
     * @param id 网格龙虎榜主键
     * @return 网格龙虎榜
     */
    public CenterRankInfo selectCenterRankInfoById(String id);

    /**
     * 查询网格龙虎榜列表
     * 
     * @param centerRankInfo 网格龙虎榜
     * @return 网格龙虎榜集合
     */
    public List<CenterRankInfo> selectCenterRankInfoList(CenterRankInfo centerRankInfo);

    public List<CenterRankInfo> selectRankList(CenterRankInfo centerRankInfo);

    /**
     * 新增网格龙虎榜
     * 
     * @param centerRankInfo 网格龙虎榜
     * @return 结果
     */
    public int insertCenterRankInfo(CenterRankInfo centerRankInfo);

    /**
     * 修改网格龙虎榜
     * 
     * @param centerRankInfo 网格龙虎榜
     * @return 结果
     */
    public int updateCenterRankInfo(CenterRankInfo centerRankInfo);

    /**
     * 删除网格龙虎榜
     * 
     * @param id 网格龙虎榜主键
     * @return 结果
     */
    public int deleteCenterRankInfoById(String id);

    /**
     * 批量删除网格龙虎榜
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCenterRankInfoByIds(String[] ids);
}

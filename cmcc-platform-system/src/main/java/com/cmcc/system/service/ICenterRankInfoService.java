package com.cmcc.system.service;

import java.util.List;
import com.cmcc.system.domain.CenterRankInfo;

/**
 * 网格龙虎榜Service接口
 * 
 * @author terry
 * @date 2023-12-28
 */
public interface ICenterRankInfoService 
{
    /**
     * 查询网格龙虎榜
     * 
     * @param id 网格龙虎榜主键
     * @return 网格龙虎榜
     */
    public CenterRankInfo selectCenterRankInfoById(String id);

    /**
     * 新增网格龙虎榜
     *
     * @param centerRankInfo 网格龙虎榜
     * @return 结果
     */
    public int insertCenterRankInfo(CenterRankInfo centerRankInfo);

    /**
     * 查询网格龙虎榜列表
     *
     * @param centerRankInfo 网格龙虎榜
     * @return 网格龙虎榜集合
     */
    public List<CenterRankInfo> selectCenterRankInfoList(CenterRankInfo centerRankInfo);
    public List<CenterRankInfo> selectRankList(CenterRankInfo centerRankInfo);

    /**
     * 修改网格龙虎榜
     * 
     * @param centerRankInfo 网格龙虎榜
     * @return 结果
     */
    public int updateCenterRankInfo(CenterRankInfo centerRankInfo);

    /**
     * 批量删除网格龙虎榜
     * 
     * @param ids 需要删除的网格龙虎榜主键集合
     * @return 结果
     */
    public int deleteCenterRankInfoByIds(String[] ids);

    /**
     * 删除网格龙虎榜信息
     * 
     * @param id 网格龙虎榜主键
     * @return 结果
     */
    public int deleteCenterRankInfoById(String id);

    public String importData(List<CenterRankInfo> centerList, Boolean isUpdateSupport, String operName);
}

package com.cmcc.system.service;

import java.util.List;
import com.cmcc.system.domain.BelongingPlaceInfo;

/**
 * 归属地管理Service接口
 * 
 * @author terry
 * @date 2024-02-24
 */
public interface IBelongingPlaceInfoService 
{
    /**
     * 查询归属地管理
     * 
     * @param id 归属地管理主键
     * @return 归属地管理
     */
    public BelongingPlaceInfo selectBelongingPlaceInfoById(Long id);

    /**
     * 查询归属地管理列表
     * 
     * @param belongingPlaceInfo 归属地管理
     * @return 归属地管理集合
     */
    public List<BelongingPlaceInfo> selectBelongingPlaceInfoList(BelongingPlaceInfo belongingPlaceInfo);

    /**
     * 新增归属地管理
     * 
     * @param belongingPlaceInfo 归属地管理
     * @return 结果
     */
    public int insertBelongingPlaceInfo(BelongingPlaceInfo belongingPlaceInfo);

    /**
     * 修改归属地管理
     * 
     * @param belongingPlaceInfo 归属地管理
     * @return 结果
     */
    public int updateBelongingPlaceInfo(BelongingPlaceInfo belongingPlaceInfo);

    /**
     * 批量删除归属地管理
     * 
     * @param ids 需要删除的归属地管理主键集合
     * @return 结果
     */
    public int deleteBelongingPlaceInfoByIds(Long[] ids);

    /**
     * 删除归属地管理信息
     * 
     * @param id 归属地管理主键
     * @return 结果
     */
    public int deleteBelongingPlaceInfoById(Long id);
}

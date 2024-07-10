package com.cmcc.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cmcc.system.mapper.BelongingPlaceInfoMapper;
import com.cmcc.system.domain.BelongingPlaceInfo;
import com.cmcc.system.service.IBelongingPlaceInfoService;

/**
 * 归属地管理Service业务层处理
 * 
 * @author terry
 * @date 2024-02-24
 */
@Service
public class BelongingPlaceInfoServiceImpl implements IBelongingPlaceInfoService 
{
    @Autowired
    private BelongingPlaceInfoMapper belongingPlaceInfoMapper;

    /**
     * 查询归属地管理
     * 
     * @param id 归属地管理主键
     * @return 归属地管理
     */
    @Override
    public BelongingPlaceInfo selectBelongingPlaceInfoById(Long id)
    {
        return belongingPlaceInfoMapper.selectBelongingPlaceInfoById(id);
    }

    /**
     * 查询归属地管理列表
     * 
     * @param belongingPlaceInfo 归属地管理
     * @return 归属地管理
     */
    @Override
    public List<BelongingPlaceInfo> selectBelongingPlaceInfoList(BelongingPlaceInfo belongingPlaceInfo)
    {
        return belongingPlaceInfoMapper.selectBelongingPlaceInfoList(belongingPlaceInfo);
    }

    /**
     * 新增归属地管理
     * 
     * @param belongingPlaceInfo 归属地管理
     * @return 结果
     */
    @Override
    public int insertBelongingPlaceInfo(BelongingPlaceInfo belongingPlaceInfo)
    {
        return belongingPlaceInfoMapper.insertBelongingPlaceInfo(belongingPlaceInfo);
    }

    /**
     * 修改归属地管理
     * 
     * @param belongingPlaceInfo 归属地管理
     * @return 结果
     */
    @Override
    public int updateBelongingPlaceInfo(BelongingPlaceInfo belongingPlaceInfo)
    {
        return belongingPlaceInfoMapper.updateBelongingPlaceInfo(belongingPlaceInfo);
    }

    /**
     * 批量删除归属地管理
     * 
     * @param ids 需要删除的归属地管理主键
     * @return 结果
     */
    @Override
    public int deleteBelongingPlaceInfoByIds(Long[] ids)
    {
        return belongingPlaceInfoMapper.deleteBelongingPlaceInfoByIds(ids);
    }

    /**
     * 删除归属地管理信息
     * 
     * @param id 归属地管理主键
     * @return 结果
     */
    @Override
    public int deleteBelongingPlaceInfoById(Long id)
    {
        return belongingPlaceInfoMapper.deleteBelongingPlaceInfoById(id);
    }
}

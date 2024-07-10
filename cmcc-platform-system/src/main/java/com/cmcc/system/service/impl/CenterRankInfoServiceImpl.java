package com.cmcc.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.cmcc.common.utils.uuid.UUID;
import com.cmcc.system.domain.BusinessTypeEnum;
import com.cmcc.system.service.CenterGardCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cmcc.system.mapper.CenterRankInfoMapper;
import com.cmcc.system.domain.CenterRankInfo;
import com.cmcc.system.service.ICenterRankInfoService;

/**
 * 网格龙虎榜Service业务层处理
 * 
 * @author terry
 * @date 2023-12-28
 */
@Service
public class CenterRankInfoServiceImpl implements ICenterRankInfoService 
{
    @Autowired
    private CenterRankInfoMapper centerRankInfoMapper;

    @Autowired
    private CenterGardCacheService centerGardCacheService;

    /**
     * 查询网格龙虎榜
     * 
     * @param id 网格龙虎榜主键
     * @return 网格龙虎榜
     */
    @Override
    public CenterRankInfo selectCenterRankInfoById(String id)
    {
        return centerRankInfoMapper.selectCenterRankInfoById(id);
    }

    /**
     * 查询网格龙虎榜列表
     * 
     * @param centerRankInfo 网格龙虎榜
     * @return 网格龙虎榜
     */
    @Override
    public List<CenterRankInfo> selectCenterRankInfoList(CenterRankInfo centerRankInfo)
    {
        return centerRankInfoMapper.selectCenterRankInfoList(centerRankInfo);
    }

    /**
     * 新增网格龙虎榜
     * 
     * @param centerRankInfo 网格龙虎榜
     * @return 结果
     */
    @Override
    public int insertCenterRankInfo(CenterRankInfo centerRankInfo)
    {
        return centerRankInfoMapper.insertCenterRankInfo(centerRankInfo);
    }

    /**
     * 修改网格龙虎榜
     * 
     * @param centerRankInfo 网格龙虎榜
     * @return 结果
     */
    @Override
    public int updateCenterRankInfo(CenterRankInfo centerRankInfo)
    {
        return centerRankInfoMapper.updateCenterRankInfo(centerRankInfo);
    }

    /**
     * 批量删除网格龙虎榜
     * 
     * @param ids 需要删除的网格龙虎榜主键
     * @return 结果
     */
    @Override
    public int deleteCenterRankInfoByIds(String[] ids)
    {
        return centerRankInfoMapper.deleteCenterRankInfoByIds(ids);
    }

    /**
     * 删除网格龙虎榜信息
     * 
     * @param id 网格龙虎榜主键
     * @return 结果
     */
    @Override
    public int deleteCenterRankInfoById(String id)
    {
        return centerRankInfoMapper.deleteCenterRankInfoById(id);
    }

    @Override
    public String importData(List<CenterRankInfo> centerList, Boolean isUpdateSupport, String operName)
    {

        List<CenterRankInfo> list = new ArrayList<>();
        for (CenterRankInfo vo : centerList)
        {
            CenterRankInfo temp = new CenterRankInfo();
            temp.setId(UUID.randomUUID().toString());
            temp.setCountyId(centerGardCacheService.getCountyId(vo.getCountyName()));
            temp.setCenterId(centerGardCacheService.getCenterId(vo.getCenterName()));
            temp.setTownId(centerGardCacheService.getTownId(vo.getTownName()));
            temp.setCountyName(vo.getCountyName());
            temp.setCenterName(vo.getCenterName());
            temp.setTownName(vo.getTownName());
            temp.setRecordDate(vo.getRecordDate());
            temp.setScore(vo.getScore());
            temp.setMarketingTypeName(vo.getMarketingTypeName());
            temp.setMarketingType(BusinessTypeEnum.getValueByName(vo.getMarketingTypeName()));
            centerRankInfoMapper.insertCenterRankInfo(temp);
            list.add(temp);
        }
        return "导入成功";
    }

    public List<CenterRankInfo> selectRankList(CenterRankInfo centerRankInfo)
    {
        return centerRankInfoMapper.selectRankList(centerRankInfo);
    }
}

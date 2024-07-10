package com.cmcc.system.service.impl;

import java.util.List;
import com.cmcc.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cmcc.system.mapper.CmccEnterpriseInfoMapper;
import com.cmcc.system.domain.CmccEnterpriseInfo;
import com.cmcc.system.service.ICmccEnterpriseInfoService;

/**
 * 企业信息Service业务层处理
 * 
 * @author terry
 * @date 2024-06-20
 */
@Service
public class CmccEnterpriseInfoServiceImpl implements ICmccEnterpriseInfoService 
{
    @Autowired
    private CmccEnterpriseInfoMapper cmccEnterpriseInfoMapper;

    /**
     * 查询企业信息
     * 
     * @param id 企业信息主键
     * @return 企业信息
     */
    @Override
    public CmccEnterpriseInfo selectCmccEnterpriseInfoById(Long id)
    {
        return cmccEnterpriseInfoMapper.selectCmccEnterpriseInfoById(id);
    }

    /**
     * 查询企业信息列表
     * 
     * @param cmccEnterpriseInfo 企业信息
     * @return 企业信息
     */
    @Override
    public List<CmccEnterpriseInfo> selectCmccEnterpriseInfoList(CmccEnterpriseInfo cmccEnterpriseInfo)
    {
        return cmccEnterpriseInfoMapper.selectCmccEnterpriseInfoList(cmccEnterpriseInfo);
    }

    /**
     * 新增企业信息
     * 
     * @param cmccEnterpriseInfo 企业信息
     * @return 结果
     */
    @Override
    public int insertCmccEnterpriseInfo(CmccEnterpriseInfo cmccEnterpriseInfo)
    {
        cmccEnterpriseInfo.setCreateTime(DateUtils.getNowDate());
        return cmccEnterpriseInfoMapper.insertCmccEnterpriseInfo(cmccEnterpriseInfo);
    }

    /**
     * 修改企业信息
     * 
     * @param cmccEnterpriseInfo 企业信息
     * @return 结果
     */
    @Override
    public int updateCmccEnterpriseInfo(CmccEnterpriseInfo cmccEnterpriseInfo)
    {
        cmccEnterpriseInfo.setUpdateTime(DateUtils.getNowDate());
        return cmccEnterpriseInfoMapper.updateCmccEnterpriseInfo(cmccEnterpriseInfo);
    }

    /**
     * 批量删除企业信息
     * 
     * @param ids 需要删除的企业信息主键
     * @return 结果
     */
    @Override
    public int deleteCmccEnterpriseInfoByIds(Long[] ids)
    {
        return cmccEnterpriseInfoMapper.deleteCmccEnterpriseInfoByIds(ids);
    }

    /**
     * 删除企业信息信息
     * 
     * @param id 企业信息主键
     * @return 结果
     */
    @Override
    public int deleteCmccEnterpriseInfoById(Long id)
    {
        return cmccEnterpriseInfoMapper.deleteCmccEnterpriseInfoById(id);
    }
}

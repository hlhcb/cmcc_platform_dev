package com.cmcc.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cmcc.system.mapper.UnionEnterpriseConfigMapper;
import com.cmcc.system.domain.UnionEnterpriseConfig;
import com.cmcc.system.service.IUnionEnterpriseConfigService;

/**
 * 联防配置Service业务层处理
 * 
 * @author terry
 * @date 2024-04-18
 */
@Service
public class UnionEnterpriseConfigServiceImpl implements IUnionEnterpriseConfigService 
{
    @Autowired
    private UnionEnterpriseConfigMapper unionEnterpriseConfigMapper;

    /**
     * 查询联防配置
     * 
     * @param id 联防配置主键
     * @return 联防配置
     */
    @Override
    public UnionEnterpriseConfig selectUnionEnterpriseConfigById(Long id)
    {
        return unionEnterpriseConfigMapper.selectUnionEnterpriseConfigById(id);
    }

    /**
     * 查询联防配置列表
     * 
     * @param unionEnterpriseConfig 联防配置
     * @return 联防配置
     */
    @Override
    public List<UnionEnterpriseConfig> selectUnionEnterpriseConfigList(UnionEnterpriseConfig unionEnterpriseConfig)
    {
        return unionEnterpriseConfigMapper.selectUnionEnterpriseConfigList(unionEnterpriseConfig);
    }

    /**
     * 新增联防配置
     * 
     * @param unionEnterpriseConfig 联防配置
     * @return 结果
     */
    @Override
    public int insertUnionEnterpriseConfig(UnionEnterpriseConfig unionEnterpriseConfig)
    {
        return unionEnterpriseConfigMapper.insertUnionEnterpriseConfig(unionEnterpriseConfig);
    }

    /**
     * 修改联防配置
     * 
     * @param unionEnterpriseConfig 联防配置
     * @return 结果
     */
    @Override
    public int updateUnionEnterpriseConfig(UnionEnterpriseConfig unionEnterpriseConfig)
    {
        return unionEnterpriseConfigMapper.updateUnionEnterpriseConfig(unionEnterpriseConfig);
    }

    /**
     * 批量删除联防配置
     * 
     * @param ids 需要删除的联防配置主键
     * @return 结果
     */
    @Override
    public int deleteUnionEnterpriseConfigByIds(Long[] ids)
    {
        return unionEnterpriseConfigMapper.deleteUnionEnterpriseConfigByIds(ids);
    }

    /**
     * 删除联防配置信息
     * 
     * @param id 联防配置主键
     * @return 结果
     */
    @Override
    public int deleteUnionEnterpriseConfigById(Long id)
    {
        return unionEnterpriseConfigMapper.deleteUnionEnterpriseConfigById(id);
    }
}

package com.cmcc.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cmcc.system.mapper.CommunityConfigMapper;
import com.cmcc.system.domain.CommunityConfig;
import com.cmcc.system.service.ICommunityConfigService;

/**
 * 归属地管理Service业务层处理
 * 
 * @author terry
 * @date 2024-05-11
 */
@Service
public class CommunityConfigServiceImpl implements ICommunityConfigService 
{
    @Autowired
    private CommunityConfigMapper communityConfigMapper;

    /**
     * 查询归属地管理
     * 
     * @param id 归属地管理主键
     * @return 归属地管理
     */
    @Override
    public CommunityConfig selectCommunityConfigById(Long id)
    {
        return communityConfigMapper.selectCommunityConfigById(id);
    }

    /**
     * 查询归属地管理列表
     * 
     * @param communityConfig 归属地管理
     * @return 归属地管理
     */
    @Override
    public List<CommunityConfig> selectCommunityConfigList(CommunityConfig communityConfig)
    {
        return communityConfigMapper.selectCommunityConfigList(communityConfig);
    }

    /**
     * 新增归属地管理
     * 
     * @param communityConfig 归属地管理
     * @return 结果
     */
    @Override
    public int insertCommunityConfig(CommunityConfig communityConfig)
    {
        return communityConfigMapper.insertCommunityConfig(communityConfig);
    }

    /**
     * 修改归属地管理
     * 
     * @param communityConfig 归属地管理
     * @return 结果
     */
    @Override
    public int updateCommunityConfig(CommunityConfig communityConfig)
    {
        return communityConfigMapper.updateCommunityConfig(communityConfig);
    }

    /**
     * 批量删除归属地管理
     * 
     * @param ids 需要删除的归属地管理主键
     * @return 结果
     */
    @Override
    public int deleteCommunityConfigByIds(Long[] ids)
    {
        return communityConfigMapper.deleteCommunityConfigByIds(ids);
    }

    /**
     * 删除归属地管理信息
     * 
     * @param id 归属地管理主键
     * @return 结果
     */
    @Override
    public int deleteCommunityConfigById(Long id)
    {
        return communityConfigMapper.deleteCommunityConfigById(id);
    }
}

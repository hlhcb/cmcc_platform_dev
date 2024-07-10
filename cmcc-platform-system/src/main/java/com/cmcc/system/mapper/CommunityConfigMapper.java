package com.cmcc.system.mapper;

import java.util.List;
import com.cmcc.system.domain.CommunityConfig;

/**
 * 归属地管理Mapper接口
 * 
 * @author terry
 * @date 2024-05-11
 */
public interface CommunityConfigMapper 
{
    /**
     * 查询归属地管理
     * 
     * @param id 归属地管理主键
     * @return 归属地管理
     */
    public CommunityConfig selectCommunityConfigById(Long id);

    /**
     * 查询归属地管理列表
     * 
     * @param communityConfig 归属地管理
     * @return 归属地管理集合
     */
    public List<CommunityConfig> selectCommunityConfigList(CommunityConfig communityConfig);

    /**
     * 新增归属地管理
     * 
     * @param communityConfig 归属地管理
     * @return 结果
     */
    public int insertCommunityConfig(CommunityConfig communityConfig);

    /**
     * 修改归属地管理
     * 
     * @param communityConfig 归属地管理
     * @return 结果
     */
    public int updateCommunityConfig(CommunityConfig communityConfig);

    /**
     * 删除归属地管理
     * 
     * @param id 归属地管理主键
     * @return 结果
     */
    public int deleteCommunityConfigById(Long id);

    /**
     * 批量删除归属地管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCommunityConfigByIds(Long[] ids);
}

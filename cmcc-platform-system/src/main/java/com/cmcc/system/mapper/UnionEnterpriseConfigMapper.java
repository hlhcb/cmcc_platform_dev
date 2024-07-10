package com.cmcc.system.mapper;

import java.util.List;
import com.cmcc.system.domain.UnionEnterpriseConfig;

/**
 * 联防配置Mapper接口
 * 
 * @author terry
 * @date 2024-04-18
 */
public interface UnionEnterpriseConfigMapper 
{
    /**
     * 查询联防配置
     * 
     * @param id 联防配置主键
     * @return 联防配置
     */
    public UnionEnterpriseConfig selectUnionEnterpriseConfigById(Long id);

    /**
     * 查询联防配置列表
     * 
     * @param unionEnterpriseConfig 联防配置
     * @return 联防配置集合
     */
    public List<UnionEnterpriseConfig> selectUnionEnterpriseConfigList(UnionEnterpriseConfig unionEnterpriseConfig);

    /**
     * 新增联防配置
     * 
     * @param unionEnterpriseConfig 联防配置
     * @return 结果
     */
    public int insertUnionEnterpriseConfig(UnionEnterpriseConfig unionEnterpriseConfig);

    /**
     * 修改联防配置
     * 
     * @param unionEnterpriseConfig 联防配置
     * @return 结果
     */
    public int updateUnionEnterpriseConfig(UnionEnterpriseConfig unionEnterpriseConfig);

    /**
     * 删除联防配置
     * 
     * @param id 联防配置主键
     * @return 结果
     */
    public int deleteUnionEnterpriseConfigById(Long id);

    /**
     * 批量删除联防配置
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUnionEnterpriseConfigByIds(Long[] ids);
}

package com.cmcc.system.mapper;

import java.util.List;
import com.cmcc.system.domain.CmccEnterpriseInfo;

/**
 * 企业信息Mapper接口
 * 
 * @author terry
 * @date 2024-06-20
 */
public interface CmccEnterpriseInfoMapper 
{
    /**
     * 查询企业信息
     * 
     * @param id 企业信息主键
     * @return 企业信息
     */
    public CmccEnterpriseInfo selectCmccEnterpriseInfoById(Long id);

    /**
     * 查询企业信息列表
     * 
     * @param cmccEnterpriseInfo 企业信息
     * @return 企业信息集合
     */
    public List<CmccEnterpriseInfo> selectCmccEnterpriseInfoList(CmccEnterpriseInfo cmccEnterpriseInfo);

    /**
     * 新增企业信息
     * 
     * @param cmccEnterpriseInfo 企业信息
     * @return 结果
     */
    public int insertCmccEnterpriseInfo(CmccEnterpriseInfo cmccEnterpriseInfo);

    /**
     * 修改企业信息
     * 
     * @param cmccEnterpriseInfo 企业信息
     * @return 结果
     */
    public int updateCmccEnterpriseInfo(CmccEnterpriseInfo cmccEnterpriseInfo);

    /**
     * 删除企业信息
     * 
     * @param id 企业信息主键
     * @return 结果
     */
    public int deleteCmccEnterpriseInfoById(Long id);

    /**
     * 批量删除企业信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCmccEnterpriseInfoByIds(Long[] ids);
}

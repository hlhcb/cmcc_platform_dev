package com.cmcc.system.service;

import java.util.List;
import java.util.Map;

import com.cmcc.system.domain.AppCountInfo;
import com.cmcc.system.domain.CmccInterviewBase;

/**
 * 用户走访Service接口
 * 
 * @author terry
 * @date 2024-05-17
 */
public interface ICmccInterviewBaseService 
{
    /**
     * 查询用户走访
     * 
     * @param id 用户走访主键
     * @return 用户走访
     */
    public CmccInterviewBase selectCmccInterviewBaseById(Long id);


    public Long selectLeastByEnterprise(Long enterpriseId);

    /**
     * 查询用户走访列表
     * 
     * @param cmccInterviewBase 用户走访
     * @return 用户走访集合
     */
    public List<CmccInterviewBase> selectCmccInterviewBaseList(CmccInterviewBase cmccInterviewBase);


    public AppCountInfo selectAppCountInfo(Map<String, Object> map);
    /**
     * 新增用户走访
     * 
     * @param cmccInterviewBase 用户走访
     * @return 结果
     */
    public int insertCmccInterviewBase(CmccInterviewBase cmccInterviewBase);

    /**
     * 修改用户走访
     * 
     * @param cmccInterviewBase 用户走访
     * @return 结果
     */
    public int updateCmccInterviewBase(CmccInterviewBase cmccInterviewBase);

    /**
     * 批量删除用户走访
     * 
     * @param ids 需要删除的用户走访主键集合
     * @return 结果
     */
    public int deleteCmccInterviewBaseByIds(Long[] ids);

    /**
     * 删除用户走访信息
     * 
     * @param id 用户走访主键
     * @return 结果
     */
    public int deleteCmccInterviewBaseById(Long id);
}

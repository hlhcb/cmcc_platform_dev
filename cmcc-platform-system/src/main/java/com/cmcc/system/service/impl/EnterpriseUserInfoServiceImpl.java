package com.cmcc.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cmcc.system.mapper.EnterpriseUserInfoMapper;
import com.cmcc.system.domain.EnterpriseUserInfo;
import com.cmcc.system.service.IEnterpriseUserInfoService;

/**
 * 联防员信息Service业务层处理
 * 
 * @author terry
 * @date 2024-04-18
 */
@Service
public class EnterpriseUserInfoServiceImpl implements IEnterpriseUserInfoService 
{
    @Autowired
    private EnterpriseUserInfoMapper enterpriseUserInfoMapper;

    /**
     * 查询联防员信息
     * 
     * @param id 联防员信息主键
     * @return 联防员信息
     */
    @Override
    public EnterpriseUserInfo selectEnterpriseUserInfoById(Long id)
    {
        return enterpriseUserInfoMapper.selectEnterpriseUserInfoById(id);
    }

    /**
     * 查询联防员信息列表
     * 
     * @param enterpriseUserInfo 联防员信息
     * @return 联防员信息
     */
    @Override
    public List<EnterpriseUserInfo> selectEnterpriseUserInfoList(EnterpriseUserInfo enterpriseUserInfo)
    {
        return enterpriseUserInfoMapper.selectEnterpriseUserInfoList(enterpriseUserInfo);
    }

    /**
     * 新增联防员信息
     * 
     * @param enterpriseUserInfo 联防员信息
     * @return 结果
     */
    @Override
    public int insertEnterpriseUserInfo(EnterpriseUserInfo enterpriseUserInfo)
    {
        return enterpriseUserInfoMapper.insertEnterpriseUserInfo(enterpriseUserInfo);
    }

    /**
     * 修改联防员信息
     * 
     * @param enterpriseUserInfo 联防员信息
     * @return 结果
     */
    @Override
    public int updateEnterpriseUserInfo(EnterpriseUserInfo enterpriseUserInfo)
    {
        return enterpriseUserInfoMapper.updateEnterpriseUserInfo(enterpriseUserInfo);
    }

    /**
     * 批量删除联防员信息
     * 
     * @param ids 需要删除的联防员信息主键
     * @return 结果
     */
    @Override
    public int deleteEnterpriseUserInfoByIds(Long[] ids)
    {
        return enterpriseUserInfoMapper.deleteEnterpriseUserInfoByIds(ids);
    }

    /**
     * 删除联防员信息信息
     * 
     * @param id 联防员信息主键
     * @return 结果
     */
    @Override
    public int deleteEnterpriseUserInfoById(Long id)
    {
        return enterpriseUserInfoMapper.deleteEnterpriseUserInfoById(id);
    }
}

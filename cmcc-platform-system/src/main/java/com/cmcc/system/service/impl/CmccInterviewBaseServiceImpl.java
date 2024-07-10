package com.cmcc.system.service.impl;

import java.util.List;

import com.cmcc.system.domain.AppCountInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Map;

import com.cmcc.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.cmcc.system.domain.CmccInterviewNet;
import com.cmcc.system.mapper.CmccInterviewBaseMapper;
import com.cmcc.system.domain.CmccInterviewBase;
import com.cmcc.system.service.ICmccInterviewBaseService;

/**
 * 用户走访Service业务层处理
 * 
 * @author terry
 * @date 2024-05-17
 */
@Service
public class CmccInterviewBaseServiceImpl implements ICmccInterviewBaseService 
{
    @Autowired
    private CmccInterviewBaseMapper cmccInterviewBaseMapper;

    /**
     * 查询用户走访
     * 
     * @param id 用户走访主键
     * @return 用户走访
     */
    @Override
    public CmccInterviewBase selectCmccInterviewBaseById(Long id)
    {
        return cmccInterviewBaseMapper.selectCmccInterviewBaseById(id);
    }

    @Override
    public Long selectLeastByEnterprise(Long enterpriseId){
        return cmccInterviewBaseMapper.selectLeastByEnterprise(enterpriseId);
    }

    /**
     * 查询用户走访列表
     * 
     * @param cmccInterviewBase 用户走访
     * @return 用户走访
     */
    @Override
    public List<CmccInterviewBase> selectCmccInterviewBaseList(CmccInterviewBase cmccInterviewBase)
    {
        return cmccInterviewBaseMapper.selectCmccInterviewBaseList(cmccInterviewBase);
    }

    @Override
    public AppCountInfo selectAppCountInfo(Map<String, Object> map)
    {
        return cmccInterviewBaseMapper.selectAppCountInfo(map);
    }

    /**
     * 新增用户走访
     * 
     * @param cmccInterviewBase 用户走访
     * @return 结果
     */
    @Transactional
    @Override
    public int insertCmccInterviewBase(CmccInterviewBase cmccInterviewBase)
    {
        int rows = cmccInterviewBaseMapper.insertCmccInterviewBase(cmccInterviewBase);
        insertCmccInterviewNet(cmccInterviewBase);
        return rows;
    }

    /**
     * 修改用户走访
     * 
     * @param cmccInterviewBase 用户走访
     * @return 结果
     */
    @Transactional
    @Override
    public int updateCmccInterviewBase(CmccInterviewBase cmccInterviewBase)
    {
        cmccInterviewBaseMapper.deleteCmccInterviewNetByInterviewId(cmccInterviewBase.getId());
        insertCmccInterviewNet(cmccInterviewBase);
        return cmccInterviewBaseMapper.updateCmccInterviewBase(cmccInterviewBase);
    }

    /**
     * 批量删除用户走访
     * 
     * @param ids 需要删除的用户走访主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteCmccInterviewBaseByIds(Long[] ids)
    {
        cmccInterviewBaseMapper.deleteCmccInterviewNetByInterviewIds(ids);
        return cmccInterviewBaseMapper.deleteCmccInterviewBaseByIds(ids);
    }

    /**
     * 删除用户走访信息
     * 
     * @param id 用户走访主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteCmccInterviewBaseById(Long id)
    {
        cmccInterviewBaseMapper.deleteCmccInterviewNetByInterviewId(id);
        return cmccInterviewBaseMapper.deleteCmccInterviewBaseById(id);
    }

    /**
     * 新增客户走访线路信息信息
     * 
     * @param cmccInterviewBase 用户走访对象
     */
    public void insertCmccInterviewNet(CmccInterviewBase cmccInterviewBase)
    {
        List<CmccInterviewNet> cmccInterviewNetList = cmccInterviewBase.getCmccInterviewNetList();
        Long id = cmccInterviewBase.getId();
        if (StringUtils.isNotNull(cmccInterviewNetList))
        {
            List<CmccInterviewNet> list = new ArrayList<CmccInterviewNet>();
            for (CmccInterviewNet cmccInterviewNet : cmccInterviewNetList)
            {
                cmccInterviewNet.setInterviewId(id);
                list.add(cmccInterviewNet);
            }
            if (list.size() > 0)
            {
                cmccInterviewBaseMapper.batchCmccInterviewNet(list);
            }
        }
    }
}

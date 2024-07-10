package com.cmcc.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cmcc.system.mapper.CmccInterviewNetMapper;
import com.cmcc.system.domain.CmccInterviewNet;
import com.cmcc.system.service.ICmccInterviewNetService;

/**
 * 客户走访线路信息Service业务层处理
 * 
 * @author terry
 * @date 2024-05-20
 */
@Service
public class CmccInterviewNetServiceImpl implements ICmccInterviewNetService 
{
    @Autowired
    private CmccInterviewNetMapper cmccInterviewNetMapper;

    /**
     * 查询客户走访线路信息
     * 
     * @param id 客户走访线路信息主键
     * @return 客户走访线路信息
     */
    @Override
    public CmccInterviewNet selectCmccInterviewNetById(Long id)
    {
        return cmccInterviewNetMapper.selectCmccInterviewNetById(id);
    }

    /**
     * 查询客户走访线路信息列表
     * 
     * @param cmccInterviewNet 客户走访线路信息
     * @return 客户走访线路信息
     */
    @Override
    public List<CmccInterviewNet> selectCmccInterviewNetList(CmccInterviewNet cmccInterviewNet)
    {
        return cmccInterviewNetMapper.selectCmccInterviewNetList(cmccInterviewNet);
    }

    /**
     * 新增客户走访线路信息
     * 
     * @param cmccInterviewNet 客户走访线路信息
     * @return 结果
     */
    @Override
    public int insertCmccInterviewNet(CmccInterviewNet cmccInterviewNet)
    {
        return cmccInterviewNetMapper.insertCmccInterviewNet(cmccInterviewNet);
    }

    /**
     * 修改客户走访线路信息
     * 
     * @param cmccInterviewNet 客户走访线路信息
     * @return 结果
     */
    @Override
    public int updateCmccInterviewNet(CmccInterviewNet cmccInterviewNet)
    {
        return cmccInterviewNetMapper.updateCmccInterviewNet(cmccInterviewNet);
    }

    /**
     * 批量删除客户走访线路信息
     * 
     * @param ids 需要删除的客户走访线路信息主键
     * @return 结果
     */
    @Override
    public int deleteCmccInterviewNetByIds(Long[] ids)
    {
        return cmccInterviewNetMapper.deleteCmccInterviewNetByIds(ids);
    }

    /**
     * 删除客户走访线路信息信息
     * 
     * @param id 客户走访线路信息主键
     * @return 结果
     */
    @Override
    public int deleteCmccInterviewNetById(Long id)
    {
        return cmccInterviewNetMapper.deleteCmccInterviewNetById(id);
    }
}

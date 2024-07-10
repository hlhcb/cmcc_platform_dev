package com.cmcc.system.service;

import java.util.List;
import com.cmcc.system.domain.CmccInterviewNet;

/**
 * 客户走访线路信息Service接口
 * 
 * @author terry
 * @date 2024-05-20
 */
public interface ICmccInterviewNetService 
{
    /**
     * 查询客户走访线路信息
     * 
     * @param id 客户走访线路信息主键
     * @return 客户走访线路信息
     */
    public CmccInterviewNet selectCmccInterviewNetById(Long id);

    /**
     * 查询客户走访线路信息列表
     * 
     * @param cmccInterviewNet 客户走访线路信息
     * @return 客户走访线路信息集合
     */
    public List<CmccInterviewNet> selectCmccInterviewNetList(CmccInterviewNet cmccInterviewNet);

    /**
     * 新增客户走访线路信息
     * 
     * @param cmccInterviewNet 客户走访线路信息
     * @return 结果
     */
    public int insertCmccInterviewNet(CmccInterviewNet cmccInterviewNet);

    /**
     * 修改客户走访线路信息
     * 
     * @param cmccInterviewNet 客户走访线路信息
     * @return 结果
     */
    public int updateCmccInterviewNet(CmccInterviewNet cmccInterviewNet);

    /**
     * 批量删除客户走访线路信息
     * 
     * @param ids 需要删除的客户走访线路信息主键集合
     * @return 结果
     */
    public int deleteCmccInterviewNetByIds(Long[] ids);

    /**
     * 删除客户走访线路信息信息
     * 
     * @param id 客户走访线路信息主键
     * @return 结果
     */
    public int deleteCmccInterviewNetById(Long id);
}

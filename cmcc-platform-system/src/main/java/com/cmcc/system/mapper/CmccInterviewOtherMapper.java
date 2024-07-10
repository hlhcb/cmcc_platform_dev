package com.cmcc.system.mapper;

import java.util.List;
import com.cmcc.system.domain.CmccInterviewOther;

/**
 * 客户走访其他需求Mapper接口
 * 
 * @author terry
 * @date 2024-05-20
 */
public interface CmccInterviewOtherMapper 
{
    /**
     * 查询客户走访其他需求
     * 
     * @param id 客户走访其他需求主键
     * @return 客户走访其他需求
     */
    public CmccInterviewOther selectCmccInterviewOtherById(Long id);

    /**
     * 查询客户走访其他需求列表
     * 
     * @param cmccInterviewOther 客户走访其他需求
     * @return 客户走访其他需求集合
     */
    public List<CmccInterviewOther> selectCmccInterviewOtherList(CmccInterviewOther cmccInterviewOther);

    /**
     * 新增客户走访其他需求
     * 
     * @param cmccInterviewOther 客户走访其他需求
     * @return 结果
     */
    public int insertCmccInterviewOther(CmccInterviewOther cmccInterviewOther);

    /**
     * 修改客户走访其他需求
     * 
     * @param cmccInterviewOther 客户走访其他需求
     * @return 结果
     */
    public int updateCmccInterviewOther(CmccInterviewOther cmccInterviewOther);

    /**
     * 删除客户走访其他需求
     * 
     * @param id 客户走访其他需求主键
     * @return 结果
     */
    public int deleteCmccInterviewOtherById(Long id);

    /**
     * 批量删除客户走访其他需求
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCmccInterviewOtherByIds(Long[] ids);
}

package com.cmcc.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cmcc.system.mapper.CmccInterviewOtherMapper;
import com.cmcc.system.domain.CmccInterviewOther;
import com.cmcc.system.service.ICmccInterviewOtherService;

/**
 * 客户走访其他需求Service业务层处理
 * 
 * @author terry
 * @date 2024-05-20
 */
@Service
public class CmccInterviewOtherServiceImpl implements ICmccInterviewOtherService 
{
    @Autowired
    private CmccInterviewOtherMapper cmccInterviewOtherMapper;

    /**
     * 查询客户走访其他需求
     * 
     * @param id 客户走访其他需求主键
     * @return 客户走访其他需求
     */
    @Override
    public CmccInterviewOther selectCmccInterviewOtherById(Long id)
    {
        return cmccInterviewOtherMapper.selectCmccInterviewOtherById(id);
    }

    /**
     * 查询客户走访其他需求列表
     * 
     * @param cmccInterviewOther 客户走访其他需求
     * @return 客户走访其他需求
     */
    @Override
    public List<CmccInterviewOther> selectCmccInterviewOtherList(CmccInterviewOther cmccInterviewOther)
    {
        return cmccInterviewOtherMapper.selectCmccInterviewOtherList(cmccInterviewOther);
    }

    /**
     * 新增客户走访其他需求
     * 
     * @param cmccInterviewOther 客户走访其他需求
     * @return 结果
     */
    @Override
    public int insertCmccInterviewOther(CmccInterviewOther cmccInterviewOther)
    {
        return cmccInterviewOtherMapper.insertCmccInterviewOther(cmccInterviewOther);
    }

    /**
     * 修改客户走访其他需求
     * 
     * @param cmccInterviewOther 客户走访其他需求
     * @return 结果
     */
    @Override
    public int updateCmccInterviewOther(CmccInterviewOther cmccInterviewOther)
    {
        return cmccInterviewOtherMapper.updateCmccInterviewOther(cmccInterviewOther);
    }

    /**
     * 批量删除客户走访其他需求
     * 
     * @param ids 需要删除的客户走访其他需求主键
     * @return 结果
     */
    @Override
    public int deleteCmccInterviewOtherByIds(Long[] ids)
    {
        return cmccInterviewOtherMapper.deleteCmccInterviewOtherByIds(ids);
    }

    /**
     * 删除客户走访其他需求信息
     * 
     * @param id 客户走访其他需求主键
     * @return 结果
     */
    @Override
    public int deleteCmccInterviewOtherById(Long id)
    {
        return cmccInterviewOtherMapper.deleteCmccInterviewOtherById(id);
    }
}

package com.cmcc.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cmcc.system.mapper.AccpetWorkRecordUserMapper;
import com.cmcc.system.domain.AccpetWorkRecordUser;
import com.cmcc.system.service.IAccpetWorkRecordUserService;

/**
 * 接收工单人员Service业务层处理
 * 
 * @author terry
 * @date 2024-04-21
 */
@Service
public class AccpetWorkRecordUserServiceImpl implements IAccpetWorkRecordUserService 
{
    @Autowired
    private AccpetWorkRecordUserMapper accpetWorkRecordUserMapper;

    /**
     * 查询接收工单人员
     * 
     * @param id 接收工单人员主键
     * @return 接收工单人员
     */
    @Override
    public AccpetWorkRecordUser selectAccpetWorkRecordUserById(Long id)
    {
        return accpetWorkRecordUserMapper.selectAccpetWorkRecordUserById(id);
    }

    /**
     * 查询接收工单人员列表
     * 
     * @param accpetWorkRecordUser 接收工单人员
     * @return 接收工单人员
     */
    @Override
    public List<AccpetWorkRecordUser> selectAccpetWorkRecordUserList(AccpetWorkRecordUser accpetWorkRecordUser)
    {
        return accpetWorkRecordUserMapper.selectAccpetWorkRecordUserList(accpetWorkRecordUser);
    }

    /**
     * 新增接收工单人员
     * 
     * @param accpetWorkRecordUser 接收工单人员
     * @return 结果
     */
    @Override
    public int insertAccpetWorkRecordUser(AccpetWorkRecordUser accpetWorkRecordUser)
    {
        return accpetWorkRecordUserMapper.insertAccpetWorkRecordUser(accpetWorkRecordUser);
    }

    /**
     * 修改接收工单人员
     * 
     * @param accpetWorkRecordUser 接收工单人员
     * @return 结果
     */
    @Override
    public int updateAccpetWorkRecordUser(AccpetWorkRecordUser accpetWorkRecordUser)
    {
        return accpetWorkRecordUserMapper.updateAccpetWorkRecordUser(accpetWorkRecordUser);
    }

    /**
     * 批量删除接收工单人员
     * 
     * @param ids 需要删除的接收工单人员主键
     * @return 结果
     */
    @Override
    public int deleteAccpetWorkRecordUserByIds(Long[] ids)
    {
        return accpetWorkRecordUserMapper.deleteAccpetWorkRecordUserByIds(ids);
    }

    /**
     * 删除接收工单人员信息
     * 
     * @param id 接收工单人员主键
     * @return 结果
     */
    @Override
    public int deleteAccpetWorkRecordUserById(Long id)
    {
        return accpetWorkRecordUserMapper.deleteAccpetWorkRecordUserById(id);
    }
}

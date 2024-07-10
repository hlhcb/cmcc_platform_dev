package com.cmcc.system.service;

import java.util.List;
import com.cmcc.system.domain.AccpetWorkRecordUser;

/**
 * 接收工单人员Service接口
 * 
 * @author terry
 * @date 2024-04-21
 */
public interface IAccpetWorkRecordUserService 
{
    /**
     * 查询接收工单人员
     * 
     * @param id 接收工单人员主键
     * @return 接收工单人员
     */
    public AccpetWorkRecordUser selectAccpetWorkRecordUserById(Long id);

    /**
     * 查询接收工单人员列表
     * 
     * @param accpetWorkRecordUser 接收工单人员
     * @return 接收工单人员集合
     */
    public List<AccpetWorkRecordUser> selectAccpetWorkRecordUserList(AccpetWorkRecordUser accpetWorkRecordUser);

    /**
     * 新增接收工单人员
     * 
     * @param accpetWorkRecordUser 接收工单人员
     * @return 结果
     */
    public int insertAccpetWorkRecordUser(AccpetWorkRecordUser accpetWorkRecordUser);

    /**
     * 修改接收工单人员
     * 
     * @param accpetWorkRecordUser 接收工单人员
     * @return 结果
     */
    public int updateAccpetWorkRecordUser(AccpetWorkRecordUser accpetWorkRecordUser);

    /**
     * 批量删除接收工单人员
     * 
     * @param ids 需要删除的接收工单人员主键集合
     * @return 结果
     */
    public int deleteAccpetWorkRecordUserByIds(Long[] ids);

    /**
     * 删除接收工单人员信息
     * 
     * @param id 接收工单人员主键
     * @return 结果
     */
    public int deleteAccpetWorkRecordUserById(Long id);
}

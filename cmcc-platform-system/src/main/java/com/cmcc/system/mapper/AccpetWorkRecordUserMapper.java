package com.cmcc.system.mapper;

import java.util.List;
import com.cmcc.system.domain.AccpetWorkRecordUser;

/**
 * 接收工单人员Mapper接口
 * 
 * @author terry
 * @date 2024-04-21
 */
public interface AccpetWorkRecordUserMapper 
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
     * 删除接收工单人员
     * 
     * @param id 接收工单人员主键
     * @return 结果
     */
    public int deleteAccpetWorkRecordUserById(Long id);

    /**
     * 批量删除接收工单人员
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAccpetWorkRecordUserByIds(Long[] ids);
}

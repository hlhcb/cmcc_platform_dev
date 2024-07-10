package com.cmcc.system.service;

import java.util.List;
import com.cmcc.system.domain.ContactPeopleCount;
import com.cmcc.system.domain.CountyContactVo;

/**
 * 接触人数统计Service接口
 * 
 * @author terry
 * @date 2023-12-26
 */
public interface IContactPeopleCountService 
{
    /**
     * 查询接触人数统计
     * 
     * @param id 接触人数统计主键
     * @return 接触人数统计
     */
    public ContactPeopleCount selectContactPeopleCountById(String id);

    /**
     * 查询接触人数统计列表
     * 
     * @param contactPeopleCount 接触人数统计
     * @return 接触人数统计集合
     */
    public List<ContactPeopleCount> selectContactPeopleCountList(ContactPeopleCount contactPeopleCount);

    /**
     * 新增接触人数统计
     * 
     * @param contactPeopleCount 接触人数统计
     * @return 结果
     */
    public int insertContactPeopleCount(ContactPeopleCount contactPeopleCount);

    /**
     * 修改接触人数统计
     * 
     * @param contactPeopleCount 接触人数统计
     * @return 结果
     */
    public int updateContactPeopleCount(ContactPeopleCount contactPeopleCount);

    /**
     * 批量删除接触人数统计
     * 
     * @param ids 需要删除的接触人数统计主键集合
     * @return 结果
     */
    public int deleteContactPeopleCountByIds(String[] ids);

    /**
     * 删除接触人数统计信息
     * 
     * @param id 接触人数统计主键
     * @return 结果
     */
    public int deleteContactPeopleCountById(String id);

    public int selectPeopleCountByDate(String currYear);

    public List<CountyContactVo> selectCountyContactByDate(String currYear);
}

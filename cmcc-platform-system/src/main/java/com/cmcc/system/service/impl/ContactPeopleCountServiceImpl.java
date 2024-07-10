package com.cmcc.system.service.impl;

import java.util.List;

import com.cmcc.common.utils.uuid.UUID;
import com.cmcc.system.domain.CountyContactVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cmcc.system.mapper.ContactPeopleCountMapper;
import com.cmcc.system.domain.ContactPeopleCount;
import com.cmcc.system.service.IContactPeopleCountService;

/**
 * 接触人数统计Service业务层处理
 * 
 * @author terry
 * @date 2023-12-26
 */
@Service
public class ContactPeopleCountServiceImpl implements IContactPeopleCountService 
{
    @Autowired
    private ContactPeopleCountMapper contactPeopleCountMapper;

    /**
     * 查询接触人数统计
     * 
     * @param id 接触人数统计主键
     * @return 接触人数统计
     */
    @Override
    public ContactPeopleCount selectContactPeopleCountById(String id)
    {
        return contactPeopleCountMapper.selectContactPeopleCountById(id);
    }

    /**
     * 查询接触人数统计列表
     * 
     * @param contactPeopleCount 接触人数统计
     * @return 接触人数统计
     */
    @Override
    public List<ContactPeopleCount> selectContactPeopleCountList(ContactPeopleCount contactPeopleCount)
    {
        return contactPeopleCountMapper.selectContactPeopleCountList(contactPeopleCount);
    }

    /**
     * 新增接触人数统计
     * 
     * @param contactPeopleCount 接触人数统计
     * @return 结果
     */
    @Override
    public int insertContactPeopleCount(ContactPeopleCount contactPeopleCount)
    {
        contactPeopleCount.setId(UUID.randomUUID().toString());
        return contactPeopleCountMapper.insertContactPeopleCount(contactPeopleCount);
    }

    @Override
    public int selectPeopleCountByDate(String currYear)
    {
        return contactPeopleCountMapper.selectPeopleCountByDate(currYear);
    }

    /**
     * 修改接触人数统计
     * 
     * @param contactPeopleCount 接触人数统计
     * @return 结果
     */
    @Override
    public int updateContactPeopleCount(ContactPeopleCount contactPeopleCount)
    {
        return contactPeopleCountMapper.updateContactPeopleCount(contactPeopleCount);
    }

    /**
     * 批量删除接触人数统计
     * 
     * @param ids 需要删除的接触人数统计主键
     * @return 结果
     */
    @Override
    public int deleteContactPeopleCountByIds(String[] ids)
    {
        return contactPeopleCountMapper.deleteContactPeopleCountByIds(ids);
    }

    /**
     * 删除接触人数统计信息
     * 
     * @param id 接触人数统计主键
     * @return 结果
     */
    @Override
    public int deleteContactPeopleCountById(String id)
    {
        return contactPeopleCountMapper.deleteContactPeopleCountById(id);
    }

    public List<CountyContactVo> selectCountyContactByDate(String currYear)
    {
        return contactPeopleCountMapper.selectCountyContactByDate(currYear);
    }
}

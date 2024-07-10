package com.cmcc.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cmcc.system.mapper.HolidayRecordMapper;
import com.cmcc.system.domain.HolidayRecord;
import com.cmcc.system.service.IHolidayRecordService;

/**
 * 休假记录Service业务层处理
 * 
 * @author terry
 * @date 2024-02-25
 */
@Service
public class HolidayRecordServiceImpl implements IHolidayRecordService 
{
    @Autowired
    private HolidayRecordMapper holidayRecordMapper;

    /**
     * 查询休假记录
     * 
     * @param id 休假记录主键
     * @return 休假记录
     */
    @Override
    public HolidayRecord selectHolidayRecordById(Long id)
    {
        return holidayRecordMapper.selectHolidayRecordById(id);
    }

    /**
     * 查询休假记录列表
     * 
     * @param holidayRecord 休假记录
     * @return 休假记录
     */
    @Override
    public List<HolidayRecord> selectHolidayRecordList(HolidayRecord holidayRecord)
    {
        return holidayRecordMapper.selectHolidayRecordList(holidayRecord);
    }

    /**
     * 新增休假记录
     * 
     * @param holidayRecord 休假记录
     * @return 结果
     */
    @Override
    public int insertHolidayRecord(HolidayRecord holidayRecord)
    {
        return holidayRecordMapper.insertHolidayRecord(holidayRecord);
    }

    /**
     * 修改休假记录
     * 
     * @param holidayRecord 休假记录
     * @return 结果
     */
    @Override
    public int updateHolidayRecord(HolidayRecord holidayRecord)
    {
        return holidayRecordMapper.updateHolidayRecord(holidayRecord);
    }

    /**
     * 批量删除休假记录
     * 
     * @param ids 需要删除的休假记录主键
     * @return 结果
     */
    @Override
    public int deleteHolidayRecordByIds(Long[] ids)
    {
        return holidayRecordMapper.deleteHolidayRecordByIds(ids);
    }

    /**
     * 删除休假记录信息
     * 
     * @param id 休假记录主键
     * @return 结果
     */
    @Override
    public int deleteHolidayRecordById(Long id)
    {
        return holidayRecordMapper.deleteHolidayRecordById(id);
    }
}

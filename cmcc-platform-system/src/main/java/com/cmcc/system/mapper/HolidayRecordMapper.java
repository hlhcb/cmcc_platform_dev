package com.cmcc.system.mapper;

import java.util.List;
import com.cmcc.system.domain.HolidayRecord;

/**
 * 休假记录Mapper接口
 * 
 * @author terry
 * @date 2024-02-25
 */
public interface HolidayRecordMapper 
{
    /**
     * 查询休假记录
     * 
     * @param id 休假记录主键
     * @return 休假记录
     */
    public HolidayRecord selectHolidayRecordById(Long id);

    /**
     * 查询休假记录列表
     * 
     * @param holidayRecord 休假记录
     * @return 休假记录集合
     */
    public List<HolidayRecord> selectHolidayRecordList(HolidayRecord holidayRecord);

    /**
     * 新增休假记录
     * 
     * @param holidayRecord 休假记录
     * @return 结果
     */
    public int insertHolidayRecord(HolidayRecord holidayRecord);

    /**
     * 修改休假记录
     * 
     * @param holidayRecord 休假记录
     * @return 结果
     */
    public int updateHolidayRecord(HolidayRecord holidayRecord);

    /**
     * 删除休假记录
     * 
     * @param id 休假记录主键
     * @return 结果
     */
    public int deleteHolidayRecordById(Long id);

    /**
     * 批量删除休假记录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHolidayRecordByIds(Long[] ids);
}

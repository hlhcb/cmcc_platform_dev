package com.cmcc.web.controller.system;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.cmcc.common.core.domain.entity.SysUser;
import com.cmcc.common.utils.StringUtils;
import com.cmcc.system.domain.SearchUserInfo;
import com.cmcc.system.service.ISysUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cmcc.common.annotation.Log;
import com.cmcc.common.core.controller.BaseController;
import com.cmcc.common.core.domain.AjaxResult;
import com.cmcc.common.enums.BusinessType;
import com.cmcc.system.domain.HolidayRecord;
import com.cmcc.system.service.IHolidayRecordService;
import com.cmcc.common.utils.poi.ExcelUtil;
import com.cmcc.common.core.page.TableDataInfo;

/**
 * 休假记录Controller
 * 
 * @author terry
 * @date 2024-02-25
 */
@RestController
@RequestMapping("/officer/holidayrecord")
public class HolidayRecordController extends BaseController
{
    @Autowired
    private IHolidayRecordService holidayRecordService;

    @Autowired
    private ISysUserService userService;


    @GetMapping("/listUserInfo")
    public List<SearchUserInfo> list()
    {
//        startPage();
        List<SearchUserInfo> returnlist = new ArrayList<>();
        List<SysUser> list = userService.selectUserList(new SysUser());
//        return getDataTable(list);
        if (list != null && list.size() > 0)
        {
            for (SysUser obj : list)
            {
                SearchUserInfo info = new SearchUserInfo();
                info.setUserId(obj.getUserId());
                info.setUserName(obj.getNickName());
                if (obj.getDept() != null)
                {
                    info.setDeptName(obj.getDept().getDeptName());
                }
                returnlist.add(info);
            }
        }
        return returnlist;
    }
    /**
     * 查询休假记录列表
     */
    @PreAuthorize("@ss.hasPermi('officer:holidayrecord:list')")
    @GetMapping("/list")
    public TableDataInfo list(HolidayRecord holidayRecord)
    {
        startPage();
        List<HolidayRecord> list = holidayRecordService.selectHolidayRecordList(holidayRecord);
        return getDataTable(list);
    }

    /**
     * 导出休假记录列表
     */
    @PreAuthorize("@ss.hasPermi('officer:holidayrecord:export')")
    @Log(title = "休假记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HolidayRecord holidayRecord)
    {
        List<HolidayRecord> list = holidayRecordService.selectHolidayRecordList(holidayRecord);
        ExcelUtil<HolidayRecord> util = new ExcelUtil<HolidayRecord>(HolidayRecord.class);
        util.exportExcel(response, list, "休假记录数据");
    }

    /**
     * 获取休假记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('officer:holidayrecord:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(holidayRecordService.selectHolidayRecordById(id));
    }

    /**
     * 新增休假记录
     */
    @PreAuthorize("@ss.hasPermi('officer:holidayrecord:add')")
    @Log(title = "休假记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HolidayRecord holidayRecord)
    {
        if (holidayRecord.getBeginTime().after(holidayRecord.getEndTime()))
        {
            return AjaxResult.error("开始时间不能晚于结束时间");
        }
        return toAjax(holidayRecordService.insertHolidayRecord(holidayRecord));
    }

    /**
     * 修改休假记录
     */
    @PreAuthorize("@ss.hasPermi('officer:holidayrecord:edit')")
    @Log(title = "休假记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HolidayRecord holidayRecord)
    {
        if (holidayRecord.getBeginTime().after(holidayRecord.getEndTime()))
        {
            return AjaxResult.error("开始时间不能晚于结束时间");
        }
        return toAjax(holidayRecordService.updateHolidayRecord(holidayRecord));
    }

    /**
     * 删除休假记录
     */
    @PreAuthorize("@ss.hasPermi('officer:holidayrecord:remove')")
    @Log(title = "休假记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(holidayRecordService.deleteHolidayRecordByIds(ids));
    }
}

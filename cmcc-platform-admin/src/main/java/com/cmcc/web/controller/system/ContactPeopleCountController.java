package com.cmcc.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.cmcc.system.domain.ContactPeopleCount;
import com.cmcc.system.service.IContactPeopleCountService;
import com.cmcc.common.utils.poi.ExcelUtil;
import com.cmcc.common.core.page.TableDataInfo;

/**
 * 接触人数统计Controller
 * 
 * @author terry
 * @date 2023-12-26
 */
@RestController
@RequestMapping("/springmarketing/contactcount")
public class ContactPeopleCountController extends BaseController
{
    @Autowired
    private IContactPeopleCountService contactPeopleCountService;

    /**
     * 查询接触人数统计列表
     */
    @PreAuthorize("@ss.hasPermi('springmarketing:contactcount:list')")
    @GetMapping("/list")
    public TableDataInfo list(ContactPeopleCount contactPeopleCount)
    {
        startPage();
        List<ContactPeopleCount> list = contactPeopleCountService.selectContactPeopleCountList(contactPeopleCount);
        return getDataTable(list);
    }

    /**
     * 导出接触人数统计列表
     */
    @PreAuthorize("@ss.hasPermi('springmarketing:contactcount:export')")
    @Log(title = "接触人数统计", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ContactPeopleCount contactPeopleCount)
    {
        List<ContactPeopleCount> list = contactPeopleCountService.selectContactPeopleCountList(contactPeopleCount);
        ExcelUtil<ContactPeopleCount> util = new ExcelUtil<ContactPeopleCount>(ContactPeopleCount.class);
        util.exportExcel(response, list, "接触人数统计数据");
    }

    /**
     * 获取接触人数统计详细信息
     */
    @PreAuthorize("@ss.hasPermi('springmarketing:contactcount:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(contactPeopleCountService.selectContactPeopleCountById(id));
    }

    /**
     * 新增接触人数统计
     */
    @PreAuthorize("@ss.hasPermi('springmarketing:contactcount:add')")
    @Log(title = "接触人数统计", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ContactPeopleCount contactPeopleCount)
    {
        return toAjax(contactPeopleCountService.insertContactPeopleCount(contactPeopleCount));
    }

    /**
     * 修改接触人数统计
     */
    @PreAuthorize("@ss.hasPermi('springmarketing:contactcount:edit')")
    @Log(title = "接触人数统计", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ContactPeopleCount contactPeopleCount)
    {
        return toAjax(contactPeopleCountService.updateContactPeopleCount(contactPeopleCount));
    }

    /**
     * 删除接触人数统计
     */
    @PreAuthorize("@ss.hasPermi('springmarketing:contactcount:remove')")
    @Log(title = "接触人数统计", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(contactPeopleCountService.deleteContactPeopleCountByIds(ids));
    }
}

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
import com.cmcc.system.domain.CmccInterviewOther;
import com.cmcc.system.service.ICmccInterviewOtherService;
import com.cmcc.common.utils.poi.ExcelUtil;
import com.cmcc.common.core.page.TableDataInfo;

/**
 * 客户走访其他需求Controller
 * 
 * @author terry
 * @date 2024-05-20
 */
@RestController
@RequestMapping("/customer/interviewother")
public class CmccInterviewOtherController extends BaseController
{
    @Autowired
    private ICmccInterviewOtherService cmccInterviewOtherService;

    /**
     * 查询客户走访其他需求列表
     */
    @PreAuthorize("@ss.hasPermi('customer:interviewother:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmccInterviewOther cmccInterviewOther)
    {
        startPage();
        List<CmccInterviewOther> list = cmccInterviewOtherService.selectCmccInterviewOtherList(cmccInterviewOther);
        return getDataTable(list);
    }

    /**
     * 导出客户走访其他需求列表
     */
    @PreAuthorize("@ss.hasPermi('customer:interviewother:export')")
    @Log(title = "客户走访其他需求", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmccInterviewOther cmccInterviewOther)
    {
        List<CmccInterviewOther> list = cmccInterviewOtherService.selectCmccInterviewOtherList(cmccInterviewOther);
        ExcelUtil<CmccInterviewOther> util = new ExcelUtil<CmccInterviewOther>(CmccInterviewOther.class);
        util.exportExcel(response, list, "客户走访其他需求数据");
    }

    /**
     * 获取客户走访其他需求详细信息
     */
    @PreAuthorize("@ss.hasPermi('customer:interviewother:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(cmccInterviewOtherService.selectCmccInterviewOtherById(id));
    }

    /**
     * 新增客户走访其他需求
     */
    @PreAuthorize("@ss.hasPermi('customer:interviewother:add')")
    @Log(title = "客户走访其他需求", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmccInterviewOther cmccInterviewOther)
    {
        return toAjax(cmccInterviewOtherService.insertCmccInterviewOther(cmccInterviewOther));
    }

    /**
     * 修改客户走访其他需求
     */
    @PreAuthorize("@ss.hasPermi('customer:interviewother:edit')")
    @Log(title = "客户走访其他需求", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmccInterviewOther cmccInterviewOther)
    {
        return toAjax(cmccInterviewOtherService.updateCmccInterviewOther(cmccInterviewOther));
    }

    /**
     * 删除客户走访其他需求
     */
    @PreAuthorize("@ss.hasPermi('customer:interviewother:remove')")
    @Log(title = "客户走访其他需求", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(cmccInterviewOtherService.deleteCmccInterviewOtherByIds(ids));
    }
}

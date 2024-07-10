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
import com.cmcc.system.domain.BusinessProcessingCount;
import com.cmcc.system.service.IBusinessProcessingCountService;
import com.cmcc.common.utils.poi.ExcelUtil;
import com.cmcc.common.core.page.TableDataInfo;

/**
 * 业务办理统计表Controller
 * 
 * @author terry
 * @date 2023-12-26
 */
@RestController
@RequestMapping("/springmarketing/businesscount")
public class BusinessProcessingCountController extends BaseController
{
    @Autowired
    private IBusinessProcessingCountService businessProcessingCountService;

    /**
     * 查询业务办理统计表列表
     */
    @PreAuthorize("@ss.hasPermi('springmarketing:businesscount:list')")
    @GetMapping("/list")
    public TableDataInfo list(BusinessProcessingCount businessProcessingCount)
    {
        startPage();
        List<BusinessProcessingCount> list = businessProcessingCountService.selectBusinessProcessingCountList(businessProcessingCount);
        return getDataTable(list);
    }

    /**
     * 导出业务办理统计表列表
     */
    @PreAuthorize("@ss.hasPermi('springmarketing:businesscount:export')")
    @Log(title = "业务办理统计表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BusinessProcessingCount businessProcessingCount)
    {
        List<BusinessProcessingCount> list = businessProcessingCountService.selectBusinessProcessingCountList(businessProcessingCount);
        ExcelUtil<BusinessProcessingCount> util = new ExcelUtil<BusinessProcessingCount>(BusinessProcessingCount.class);
        util.exportExcel(response, list, "业务办理统计表数据");
    }

    /**
     * 获取业务办理统计表详细信息
     */
    @PreAuthorize("@ss.hasPermi('springmarketing:businesscount:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(businessProcessingCountService.selectBusinessProcessingCountById(id));
    }

    /**
     * 新增业务办理统计表
     */
    @PreAuthorize("@ss.hasPermi('springmarketing:businesscount:add')")
    @Log(title = "业务办理统计表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BusinessProcessingCount businessProcessingCount)
    {
        return toAjax(businessProcessingCountService.insertBusinessProcessingCount(businessProcessingCount));
    }

    /**
     * 修改业务办理统计表
     */
    @PreAuthorize("@ss.hasPermi('springmarketing:businesscount:edit')")
    @Log(title = "业务办理统计表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusinessProcessingCount businessProcessingCount)
    {
        return toAjax(businessProcessingCountService.updateBusinessProcessingCount(businessProcessingCount));
    }

    /**
     * 删除业务办理统计表
     */
    @PreAuthorize("@ss.hasPermi('springmarketing:businesscount:remove')")
    @Log(title = "业务办理统计表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(businessProcessingCountService.deleteBusinessProcessingCountByIds(ids));
    }
}

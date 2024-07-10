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
import com.cmcc.system.domain.CmccInterviewNet;
import com.cmcc.system.service.ICmccInterviewNetService;
import com.cmcc.common.utils.poi.ExcelUtil;
import com.cmcc.common.core.page.TableDataInfo;

/**
 * 客户走访线路信息Controller
 * 
 * @author terry
 * @date 2024-05-20
 */
@RestController
@RequestMapping("/customer/interviewnet")
public class CmccInterviewNetController extends BaseController
{
    @Autowired
    private ICmccInterviewNetService cmccInterviewNetService;

    /**
     * 查询客户走访线路信息列表
     */
    @PreAuthorize("@ss.hasPermi('customer:interviewnet:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmccInterviewNet cmccInterviewNet)
    {
        startPage();
        List<CmccInterviewNet> list = cmccInterviewNetService.selectCmccInterviewNetList(cmccInterviewNet);
        return getDataTable(list);
    }

    /**
     * 导出客户走访线路信息列表
     */
    @PreAuthorize("@ss.hasPermi('customer:interviewnet:export')")
    @Log(title = "客户走访线路信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmccInterviewNet cmccInterviewNet)
    {
        List<CmccInterviewNet> list = cmccInterviewNetService.selectCmccInterviewNetList(cmccInterviewNet);
        ExcelUtil<CmccInterviewNet> util = new ExcelUtil<CmccInterviewNet>(CmccInterviewNet.class);
        util.exportExcel(response, list, "客户走访线路信息数据");
    }

    /**
     * 获取客户走访线路信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('customer:interviewnet:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(cmccInterviewNetService.selectCmccInterviewNetById(id));
    }

    /**
     * 新增客户走访线路信息
     */
    @PreAuthorize("@ss.hasPermi('customer:interviewnet:add')")
    @Log(title = "客户走访线路信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmccInterviewNet cmccInterviewNet)
    {
        return toAjax(cmccInterviewNetService.insertCmccInterviewNet(cmccInterviewNet));
    }

    /**
     * 修改客户走访线路信息
     */
    @PreAuthorize("@ss.hasPermi('customer:interviewnet:edit')")
    @Log(title = "客户走访线路信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmccInterviewNet cmccInterviewNet)
    {
        return toAjax(cmccInterviewNetService.updateCmccInterviewNet(cmccInterviewNet));
    }

    /**
     * 删除客户走访线路信息
     */
    @PreAuthorize("@ss.hasPermi('customer:interviewnet:remove')")
    @Log(title = "客户走访线路信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(cmccInterviewNetService.deleteCmccInterviewNetByIds(ids));
    }
}

package com.cmcc.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.cmcc.system.domain.CenterAreaCode;
import com.cmcc.system.service.CenterGardCacheService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.cmcc.common.annotation.Log;
import com.cmcc.common.core.controller.BaseController;
import com.cmcc.common.core.domain.AjaxResult;
import com.cmcc.common.enums.BusinessType;
import com.cmcc.system.domain.MarketingGardInfo;
import com.cmcc.system.service.IMarketingGardInfoService;
import com.cmcc.common.utils.poi.ExcelUtil;
import com.cmcc.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 网格信息Controller
 * 
 * @author terry
 * @date 2023-12-28
 */
@RestController
@RequestMapping("/springmarketing/gardinfo")
public class MarketingGardInfoController extends BaseController
{
    @Autowired
    private IMarketingGardInfoService marketingGardInfoService;

    @Autowired
    private CenterGardCacheService centerGardCacheService;


    @GetMapping("getAreaCodeList")
    public  List<CenterAreaCode> getAreaCodeList(@RequestParam String parentCode, @RequestParam String type)
    {
        if ("center".equals(type))
        {
            return centerGardCacheService.GetCenterList(parentCode);
        }
        return centerGardCacheService.GetTownList(parentCode);
    }


    /**
     * 查询网格信息列表
     */
    @PreAuthorize("@ss.hasPermi('springmarketing:gardinfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(MarketingGardInfo marketingGardInfo)
    {
        startPage();
        List<MarketingGardInfo> list = marketingGardInfoService.selectMarketingGardInfoList(marketingGardInfo);
        return getDataTable(list);
    }

    /**
     * 导出网格信息列表
     */
    @PreAuthorize("@ss.hasPermi('springmarketing:gardinfo:export')")
    @Log(title = "网格信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MarketingGardInfo marketingGardInfo)
    {
        List<MarketingGardInfo> list = marketingGardInfoService.selectMarketingGardInfoList(marketingGardInfo);
        ExcelUtil<MarketingGardInfo> util = new ExcelUtil<MarketingGardInfo>(MarketingGardInfo.class);
        util.exportExcel(response, list, "网格信息数据");
    }

    /**
     * 获取网格信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('springmarketing:gardinfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(marketingGardInfoService.selectMarketingGardInfoById(id));
    }

    /**
     * 新增网格信息
     */
    @PreAuthorize("@ss.hasPermi('springmarketing:gardinfo:add')")
    @Log(title = "网格信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MarketingGardInfo marketingGardInfo)
    {
        return toAjax(marketingGardInfoService.insertMarketingGardInfo(marketingGardInfo));
    }

    /**
     * 修改网格信息
     */
    @PreAuthorize("@ss.hasPermi('springmarketing:gardinfo:edit')")
    @Log(title = "网格信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MarketingGardInfo marketingGardInfo)
    {
        return toAjax(marketingGardInfoService.updateMarketingGardInfo(marketingGardInfo));
    }

    /**
     * 删除网格信息
     */
    @PreAuthorize("@ss.hasPermi('springmarketing:gardinfo:remove')")
    @Log(title = "网格信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(marketingGardInfoService.deleteMarketingGardInfoByIds(ids));
    }
}

package com.cmcc.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.cmcc.system.domain.BusinessTypeEnum;
import com.cmcc.system.service.CenterGardCacheService;
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
import com.cmcc.system.domain.CenterRankInfo;
import com.cmcc.system.service.ICenterRankInfoService;
import com.cmcc.common.utils.poi.ExcelUtil;
import com.cmcc.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 网格龙虎榜Controller
 * 
 * @author terry
 * @date 2023-12-28
 */
@RestController
@RequestMapping("/springmarketing/centerrank")
public class CenterRankInfoController extends BaseController
{
    @Autowired
    private ICenterRankInfoService centerRankInfoService;

    @Autowired
    private CenterGardCacheService centerGardCacheService;

    /**
     * 查询网格龙虎榜列表
     */
    @PreAuthorize("@ss.hasPermi('springmarketing:centerrank:list')")
    @GetMapping("/list")
    public TableDataInfo list(CenterRankInfo centerRankInfo)
    {
        startPage();
        List<CenterRankInfo> list = centerRankInfoService.selectCenterRankInfoList(centerRankInfo);
        return getDataTable(list);
    }

    /**
     * 导出网格龙虎榜列表
     */
    @PreAuthorize("@ss.hasPermi('springmarketing:centerrank:export')")
    @Log(title = "网格龙虎榜", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CenterRankInfo centerRankInfo)
    {
        List<CenterRankInfo> list = centerRankInfoService.selectCenterRankInfoList(centerRankInfo);
        ExcelUtil<CenterRankInfo> util = new ExcelUtil<CenterRankInfo>(CenterRankInfo.class);
        util.exportExcel(response, list, "网格龙虎榜数据");
    }

    /**
     * 获取网格龙虎榜详细信息
     */
    @PreAuthorize("@ss.hasPermi('springmarketing:centerrank:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(centerRankInfoService.selectCenterRankInfoById(id));
    }

    /**
     * 新增网格龙虎榜
     */
    @PreAuthorize("@ss.hasPermi('springmarketing:centerrank:add')")
    @Log(title = "网格龙虎榜", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CenterRankInfo centerRankInfo)
    {
        return toAjax(centerRankInfoService.insertCenterRankInfo(centerRankInfo));
    }

    /**
     * 修改网格龙虎榜
     */
    @PreAuthorize("@ss.hasPermi('springmarketing:centerrank:edit')")
    @Log(title = "网格龙虎榜", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CenterRankInfo centerRankInfo)
    {
        centerRankInfo.setCountyName(centerGardCacheService.getCountyName(centerRankInfo.getCountyId()));
        centerRankInfo.setCenterName(centerGardCacheService.getCenterName(centerRankInfo.getCenterId()));
        centerRankInfo.setTownName(centerGardCacheService.getTownName(centerRankInfo.getTownId()));
        centerRankInfo.setMarketingTypeName(BusinessTypeEnum.getNameByValue(centerRankInfo.getMarketingType()));
        return toAjax(centerRankInfoService.updateCenterRankInfo(centerRankInfo));
    }

    /**
     * 删除网格龙虎榜
     */
    @PreAuthorize("@ss.hasPermi('springmarketing:centerrank:remove')")
    @Log(title = "网格龙虎榜", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(centerRankInfoService.deleteCenterRankInfoByIds(ids));
    }

    @Log(title = "网格信息", businessType = BusinessType.IMPORT)
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<CenterRankInfo> util = new ExcelUtil<CenterRankInfo>(CenterRankInfo.class);
        List<CenterRankInfo> centerList = util.importExcel(file.getInputStream());
        String operName = getUsername();
        String message = centerRankInfoService.importData(centerList, updateSupport, operName);
        return success(message);
    }
}

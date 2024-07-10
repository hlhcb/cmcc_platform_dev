package com.cmcc.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.cmcc.system.domain.CommunityConfigVo;
import com.cmcc.system.service.CommunityConfigCacheService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.cmcc.common.annotation.Log;
import com.cmcc.common.core.controller.BaseController;
import com.cmcc.common.core.domain.AjaxResult;
import com.cmcc.common.enums.BusinessType;
import com.cmcc.system.domain.CommunityConfig;
import com.cmcc.system.service.ICommunityConfigService;
import com.cmcc.common.utils.poi.ExcelUtil;
import com.cmcc.common.core.page.TableDataInfo;

/**
 * 归属地管理Controller
 * 
 * @author terry
 * @date 2024-05-11
 */
@RestController
@RequestMapping("/consumer/communityconfig")
public class CommunityConfigController extends BaseController
{
    @Autowired
    private ICommunityConfigService communityConfigService;

    @Autowired
    private CommunityConfigCacheService communityConfigCacheService;

    @GetMapping("/appBelongingPlace")
    public AjaxResult appBelongingPlace()
    {
        List<CommunityConfigVo> belongingPlaceList = communityConfigCacheService.getBelongingPlaceList();
        return success(belongingPlaceList);
    }

    @GetMapping("/appCommunity")
    public AjaxResult appCommunity(@RequestParam String belongingPlace)
    {
        List<CommunityConfigVo> belongingPlaceList = communityConfigCacheService.getCommunityByPlace(belongingPlace);
        return success(belongingPlaceList);
    }

    /**
     * 查询归属地管理列表
     */
    @PreAuthorize("@ss.hasPermi('consumer:communityconfig:list')")
    @GetMapping("/list")
    public TableDataInfo list(CommunityConfig communityConfig)
    {
        startPage();
        List<CommunityConfig> list = communityConfigService.selectCommunityConfigList(communityConfig);
        return getDataTable(list);
    }

    /**
     * 导出归属地管理列表
     */
    @PreAuthorize("@ss.hasPermi('consumer:communityconfig:export')")
    @Log(title = "归属地管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CommunityConfig communityConfig)
    {
        List<CommunityConfig> list = communityConfigService.selectCommunityConfigList(communityConfig);
        ExcelUtil<CommunityConfig> util = new ExcelUtil<CommunityConfig>(CommunityConfig.class);
        util.exportExcel(response, list, "归属地管理数据");
    }

    /**
     * 获取归属地管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('consumer:communityconfig:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(communityConfigService.selectCommunityConfigById(id));
    }

    /**
     * 新增归属地管理
     */
    @PreAuthorize("@ss.hasPermi('consumer:communityconfig:add')")
    @Log(title = "归属地管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CommunityConfig communityConfig)
    {
        return toAjax(communityConfigService.insertCommunityConfig(communityConfig));
    }

    /**
     * 修改归属地管理
     */
    @PreAuthorize("@ss.hasPermi('consumer:communityconfig:edit')")
    @Log(title = "归属地管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CommunityConfig communityConfig)
    {
        return toAjax(communityConfigService.updateCommunityConfig(communityConfig));
    }

    /**
     * 删除归属地管理
     */
    @PreAuthorize("@ss.hasPermi('consumer:communityconfig:remove')")
    @Log(title = "归属地管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(communityConfigService.deleteCommunityConfigByIds(ids));
    }
}

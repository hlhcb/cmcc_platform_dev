package com.cmcc.web.controller.system;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.cmcc.common.core.domain.entity.SysRole;
import com.cmcc.common.core.domain.entity.SysUser;
import com.cmcc.common.core.domain.model.LoginUser;
import com.cmcc.system.domain.BelongPlaceCode;
import com.cmcc.system.domain.CenterAreaCode;
import com.cmcc.system.service.BelongingPlaceInfoCacheService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.cmcc.common.annotation.Log;
import com.cmcc.common.core.controller.BaseController;
import com.cmcc.common.core.domain.AjaxResult;
import com.cmcc.common.enums.BusinessType;
import com.cmcc.system.domain.BelongingPlaceInfo;
import com.cmcc.system.service.IBelongingPlaceInfoService;
import com.cmcc.common.utils.poi.ExcelUtil;
import com.cmcc.common.core.page.TableDataInfo;

/**
 * 归属地管理Controller
 * 
 * @author terry
 * @date 2024-02-24
 */
@RestController
@RequestMapping("/officer/belongingplaceinfo")
public class BelongingPlaceInfoController extends BaseController
{
    @Autowired
    private IBelongingPlaceInfoService belongingPlaceInfoService;

    @Autowired
    private BelongingPlaceInfoCacheService belongingPlaceInfoCacheService;

    /**
     * 查询当前派出所列表
     */
    @GetMapping("getCurrentPlaceCodeList")
    public  List<BelongPlaceCode> getBelongPlaceCodeList()
    {
        List<BelongPlaceCode> list = new ArrayList<>();
        LoginUser loginUser = getLoginUser();
        if (loginUser == null || loginUser.getDeptId() == null)
        {
            return list;
        }
        String localPoliceStationName = belongingPlaceInfoCacheService.getLocalPoliceStationName(loginUser.getDeptId().toString());
        BelongPlaceCode belongPlaceCode = new BelongPlaceCode();
        belongPlaceCode.setParentCode("0");
        belongPlaceCode.setCode(loginUser.getDeptId().toString());
        belongPlaceCode.setName(localPoliceStationName);
        list.add(belongPlaceCode);
        return list;
    }

    /**
     * 查询归属地列表
     */
    @GetMapping("getBelongPlaceCodeList")
    public  List<BelongPlaceCode> getBelongPlaceCodeList(@RequestParam String parentCode, @RequestParam String type)
    {
        List<BelongPlaceCode> list = new ArrayList<>();
        SysUser user = getLoginUser().getUser();
        if (user == null)
        {
            return list;
        }
        if ("belong".equals(type))
        {

            if (user.getDept() == null || user.getDept().getParentId() == null)
            {
                return belongingPlaceInfoCacheService.getBelongList(parentCode);
            }
            else
            {
                int parent = user.getDept().getParentId().intValue();
                if (parent  == 100)
                {
                    parent = user.getDeptId().intValue();
                }
                return belongingPlaceInfoCacheService.getBelongListByParentId(parentCode,parent);
            }
        }


        boolean currentCode = true;
        boolean isadmin = false;
        List<SysRole> roles = user.getRoles();
        for (SysRole a : roles)
        {
            if ("xjadmin".equals(a.getRoleKey())  || "areaadmin".equals(a.getRoleKey()) )
            {
                currentCode = false;

            }

            if ("admin".equals(a.getRoleKey()))
            {
                currentCode = false;
                isadmin = true;
            }
        }
        if (currentCode && !isadmin)
        {
            return belongingPlaceInfoCacheService.getStationListPro(String.valueOf(user.getDeptId()));
        }

        if (isadmin)
        {
            return belongingPlaceInfoCacheService.getStationList();
        }

        if (user.getDept() == null || user.getDept().getParentId() == null)
        {
//            return belongingPlaceInfoCacheService.getBelongList(parentCode);
        }
        else
        {
            int parent = user.getDept().getParentId().intValue();
            if (parent  == 100)
            {
                parent = user.getDeptId().intValue();
            }
            return belongingPlaceInfoCacheService.getStationListByParent(parent);
        }
        return belongingPlaceInfoCacheService.getStationList();
    }

    /**
     * 查询归属地管理列表
     */
    @PreAuthorize("@ss.hasPermi('officer:belongingplaceinfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(BelongingPlaceInfo belongingPlaceInfo)
    {
        startPage();
        List<BelongingPlaceInfo> list = belongingPlaceInfoService.selectBelongingPlaceInfoList(belongingPlaceInfo);
        return getDataTable(list);
    }

    /**
     * 导出归属地管理列表
     */
    @PreAuthorize("@ss.hasPermi('officer:belongingplaceinfo:export')")
    @Log(title = "归属地管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BelongingPlaceInfo belongingPlaceInfo)
    {
        List<BelongingPlaceInfo> list = belongingPlaceInfoService.selectBelongingPlaceInfoList(belongingPlaceInfo);
        ExcelUtil<BelongingPlaceInfo> util = new ExcelUtil<BelongingPlaceInfo>(BelongingPlaceInfo.class);
        util.exportExcel(response, list, "归属地管理数据");
    }

    /**
     * 获取归属地管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('officer:belongingplaceinfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(belongingPlaceInfoService.selectBelongingPlaceInfoById(id));
    }

    /**
     * 新增归属地管理
     */
    @PreAuthorize("@ss.hasPermi('officer:belongingplaceinfo:add')")
    @Log(title = "归属地管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BelongingPlaceInfo belongingPlaceInfo)
    {
        return toAjax(belongingPlaceInfoService.insertBelongingPlaceInfo(belongingPlaceInfo));
    }

    /**
     * 修改归属地管理
     */
    @PreAuthorize("@ss.hasPermi('officer:belongingplaceinfo:edit')")
    @Log(title = "归属地管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BelongingPlaceInfo belongingPlaceInfo)
    {
        return toAjax(belongingPlaceInfoService.updateBelongingPlaceInfo(belongingPlaceInfo));
    }

    /**
     * 删除归属地管理
     */
    @PreAuthorize("@ss.hasPermi('officer:belongingplaceinfo:remove')")
    @Log(title = "归属地管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(belongingPlaceInfoService.deleteBelongingPlaceInfoByIds(ids));
    }
}

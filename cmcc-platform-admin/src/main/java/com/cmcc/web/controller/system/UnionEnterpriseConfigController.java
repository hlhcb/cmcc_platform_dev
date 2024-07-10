package com.cmcc.web.controller.system;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.cmcc.common.core.domain.entity.SysDept;
import com.cmcc.common.core.domain.entity.SysRole;
import com.cmcc.common.core.domain.model.LoginUser;
import com.cmcc.system.domain.AccpetWorkRecordUser;
import com.cmcc.system.domain.AppUnionConfig;
import com.cmcc.system.domain.AppUnionConfigTwo;
import com.cmcc.system.service.IAccpetWorkRecordUserService;
import com.cmcc.system.service.ISysDeptService;
import com.cmcc.system.service.UnionEnterpriseConfigCacheService;
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
import com.cmcc.system.domain.UnionEnterpriseConfig;
import com.cmcc.system.service.IUnionEnterpriseConfigService;
import com.cmcc.common.utils.poi.ExcelUtil;
import com.cmcc.common.core.page.TableDataInfo;

/**
 * 联防配置Controller
 * 
 * @author terry
 * @date 2024-04-18
 */
@RestController
@RequestMapping("/inspection/enterpriseconfig")
public class UnionEnterpriseConfigController extends BaseController
{
    @Autowired
    private IUnionEnterpriseConfigService unionEnterpriseConfigService;

    @Autowired
    private UnionEnterpriseConfigCacheService unionEnterpriseConfigCacheService;

    @Autowired
    private IAccpetWorkRecordUserService iAccpetWorkRecordUserService;

    @Autowired
    private ISysDeptService iSysDeptService;

    /**
     * 查询联防配置列表
     */
    @PreAuthorize("@ss.hasPermi('inspection:enterpriseconfig:list')")
    @GetMapping("/list")
    public TableDataInfo list(UnionEnterpriseConfig unionEnterpriseConfig)
    {
        startPage();
        List<UnionEnterpriseConfig> list = unionEnterpriseConfigService.selectUnionEnterpriseConfigList(unionEnterpriseConfig);
        return getDataTable(list);
    }

    @GetMapping("/getUnionOne")
    public AjaxResult getUnionOne()
    {
        LoginUser loginUser = getLoginUser();
        if (loginUser == null || loginUser.getDeptId() == null)
        {
            return AjaxResult.error("获取用户登录信息失败。");
        }

        List<AppUnionConfig> configs  = new ArrayList<>();

        SysDept sysDept = iSysDeptService.selectDeptById(loginUser.getDeptId());
        boolean isAdmin = false;
        if (sysDept != null)
        {
            String ancestors = sysDept.getAncestors();
            if (ancestors.startsWith("0,100,"))
            {
                isAdmin =true;
            }
        }

        if (!isAdmin){
            List<SysRole> roles = loginUser.getUser().getRoles();
            if (roles != null)
            {
                for (SysRole a : roles)
                {
                    if ("lfgdpfy".equals(a.getRoleKey()))
                    {
                        isAdmin = true;
                        break;
                    }
                }
            }
        }

        if (isAdmin) {
            List<AppUnionConfig> allUnion = unionEnterpriseConfigCacheService.getAllUnion();
            return AjaxResult.success(allUnion);
        } else {
            AppUnionConfig appUnionConfig = unionEnterpriseConfigCacheService.getAppUnionConfigByDeptId(loginUser.getDeptId());
            configs.add(appUnionConfig);
        }
       return AjaxResult.success(configs);
    }

    @GetMapping("/getUnionByRoles")
    public AjaxResult getUnionByRoles()
    {
        LoginUser loginUser = getLoginUser();
        if (loginUser == null || loginUser.getDeptId() == null)
        {
            return AjaxResult.error("获取用户登录信息失败。");
        }
        List<AppUnionConfig> configs  = new ArrayList<>();
        boolean unionAdmin = unionEnterpriseConfigCacheService.isUnionAdmin(loginUser.getUserId());
        if (!unionAdmin) {
            SysDept sysDept = iSysDeptService.selectDeptById(loginUser.getDeptId());
            if (sysDept != null)
            {
                String ancestors = sysDept.getAncestors();
                if (ancestors.startsWith("0,100,"))
                {
                    unionAdmin =true;
                }
            }

            if (!unionAdmin){
                List<SysRole> roles = loginUser.getUser().getRoles();
                if (roles != null)
                {
                    for (SysRole a : roles)
                    {
                        if ("lfgdpfy".equals(a.getRoleKey()))
                        {
                            unionAdmin = true;
                            break;
                        }
                    }
                }
            }
        }
//        if (!unionAdmin) {
//            AccpetWorkRecordUser query = new AccpetWorkRecordUser();
//            query.setUserId(loginUser.getUserId());
//            List<AccpetWorkRecordUser> accpetWorkRecordUsers = iAccpetWorkRecordUserService.selectAccpetWorkRecordUserList(query);
//            if (accpetWorkRecordUsers != null && accpetWorkRecordUsers.size() > 0)
//            {
//                unionAdmin =true;
//            }
//        }
        if (unionAdmin) {
            List<AppUnionConfig> allUnion = unionEnterpriseConfigCacheService.getAllUnion();
            return AjaxResult.success(allUnion);
        } else {
            AppUnionConfig appUnionConfig = unionEnterpriseConfigCacheService.getAppUnionConfigByDeptId(loginUser.getDeptId());
            if (appUnionConfig == null)
            {
                List<AppUnionConfig> allUnion = unionEnterpriseConfigCacheService.getAllUnion();
                return AjaxResult.success(allUnion);
            }
            configs.add(appUnionConfig);
        }

        return AjaxResult.success(configs);
    }

    @GetMapping("/getUnionTwo")
    public AjaxResult getUnionTwo(long area1id)
    {
        LoginUser loginUser = getLoginUser();
        if (loginUser == null)
        {
            return AjaxResult.error("获取用户登录信息失败。");
        }

        List<AppUnionConfigTwo> appUnionConfig2ByUserId = unionEnterpriseConfigCacheService.getAppUnionConfig2ByUserId(area1id);
        return AjaxResult.success(appUnionConfig2ByUserId);
    }

    /**
     * 导出联防配置列表
     */
    @PreAuthorize("@ss.hasPermi('inspection:enterpriseconfig:export')")
    @Log(title = "联防配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UnionEnterpriseConfig unionEnterpriseConfig)
    {
        List<UnionEnterpriseConfig> list = unionEnterpriseConfigService.selectUnionEnterpriseConfigList(unionEnterpriseConfig);
        ExcelUtil<UnionEnterpriseConfig> util = new ExcelUtil<UnionEnterpriseConfig>(UnionEnterpriseConfig.class);
        util.exportExcel(response, list, "联防配置数据");
    }

    /**
     * 获取联防配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('inspection:enterpriseconfig:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(unionEnterpriseConfigService.selectUnionEnterpriseConfigById(id));
    }

    /**
     * 新增联防配置
     */
    @PreAuthorize("@ss.hasPermi('inspection:enterpriseconfig:add')")
    @Log(title = "联防配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UnionEnterpriseConfig unionEnterpriseConfig)
    {
        return toAjax(unionEnterpriseConfigService.insertUnionEnterpriseConfig(unionEnterpriseConfig));
    }

    /**
     * 修改联防配置
     */
    @PreAuthorize("@ss.hasPermi('inspection:enterpriseconfig:edit')")
    @Log(title = "联防配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UnionEnterpriseConfig unionEnterpriseConfig)
    {
        return toAjax(unionEnterpriseConfigService.updateUnionEnterpriseConfig(unionEnterpriseConfig));
    }



    /**
     * 删除联防配置
     */
    @PreAuthorize("@ss.hasPermi('inspection:enterpriseconfig:remove')")
    @Log(title = "联防配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(unionEnterpriseConfigService.deleteUnionEnterpriseConfigByIds(ids));
    }
}

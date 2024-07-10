package com.cmcc.web.controller.system;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.cmcc.system.domain.AppAccpetUser;
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
import com.cmcc.system.domain.AccpetWorkRecordUser;
import com.cmcc.system.service.IAccpetWorkRecordUserService;
import com.cmcc.common.utils.poi.ExcelUtil;
import com.cmcc.common.core.page.TableDataInfo;

/**
 * 接收工单人员Controller
 * 
 * @author terry
 * @date 2024-04-21
 */
@RestController
    @RequestMapping("/inspection/accpetuser")
public class AccpetWorkRecordUserController extends BaseController
{
    @Autowired
    private IAccpetWorkRecordUserService accpetWorkRecordUserService;

    @GetMapping("/applist")
    public AjaxResult applist()
    {
        List<AppAccpetUser> returnList = new ArrayList<>();
        List<AccpetWorkRecordUser> list = accpetWorkRecordUserService.selectAccpetWorkRecordUserList(new AccpetWorkRecordUser());
       if (list == null || list.size() < 1){
           return AjaxResult.success(returnList);
       }

       for (AccpetWorkRecordUser au: list)
       {
           AppAccpetUser appAccpetUser = changeAppUser(au);
           returnList.add(appAccpetUser);
       }
        return AjaxResult.success(returnList);
    }

    private AppAccpetUser changeAppUser(AccpetWorkRecordUser info)
    {
        AppAccpetUser appAccpetUser = new AppAccpetUser();
        if (info == null) {
            return appAccpetUser;
        }
        appAccpetUser.setNickname(info.getNickName());
        appAccpetUser.setUid(info.getUserId());
        return appAccpetUser;
    }


    /**
     * 查询接收工单人员列表
     */
    @PreAuthorize("@ss.hasPermi('inspection:accpetuser:list')")
    @GetMapping("/list")
    public TableDataInfo list(AccpetWorkRecordUser accpetWorkRecordUser)
    {
        startPage();
        List<AccpetWorkRecordUser> list = accpetWorkRecordUserService.selectAccpetWorkRecordUserList(accpetWorkRecordUser);
        return getDataTable(list);
    }

    /**
     * 导出接收工单人员列表
     */
    @PreAuthorize("@ss.hasPermi('inspection:accpetuser:export')")
    @Log(title = "接收工单人员", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AccpetWorkRecordUser accpetWorkRecordUser)
    {
        List<AccpetWorkRecordUser> list = accpetWorkRecordUserService.selectAccpetWorkRecordUserList(accpetWorkRecordUser);
        ExcelUtil<AccpetWorkRecordUser> util = new ExcelUtil<AccpetWorkRecordUser>(AccpetWorkRecordUser.class);
        util.exportExcel(response, list, "接收工单人员数据");
    }

    /**
     * 获取接收工单人员详细信息
     */
    @PreAuthorize("@ss.hasPermi('inspection:accpetuser:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(accpetWorkRecordUserService.selectAccpetWorkRecordUserById(id));
    }

    /**
     * 新增接收工单人员
     */
    @PreAuthorize("@ss.hasPermi('inspection:accpetuser:add')")
    @Log(title = "接收工单人员", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AccpetWorkRecordUser accpetWorkRecordUser)
    {
        return toAjax(accpetWorkRecordUserService.insertAccpetWorkRecordUser(accpetWorkRecordUser));
    }

    /**
     * 修改接收工单人员
     */
    @PreAuthorize("@ss.hasPermi('inspection:accpetuser:edit')")
    @Log(title = "接收工单人员", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AccpetWorkRecordUser accpetWorkRecordUser)
    {
        return toAjax(accpetWorkRecordUserService.updateAccpetWorkRecordUser(accpetWorkRecordUser));
    }

    /**
     * 删除接收工单人员
     */
    @PreAuthorize("@ss.hasPermi('inspection:accpetuser:remove')")
    @Log(title = "接收工单人员", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(accpetWorkRecordUserService.deleteAccpetWorkRecordUserByIds(ids));
    }
}

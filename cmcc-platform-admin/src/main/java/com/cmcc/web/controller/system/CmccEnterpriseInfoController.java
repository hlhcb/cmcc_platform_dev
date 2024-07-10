package com.cmcc.web.controller.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.cmcc.common.constant.HttpStatus;
import com.cmcc.common.core.domain.model.LoginUser;
import com.cmcc.system.domain.AppDictDataNew;
import com.cmcc.system.domain.AreaTypeEnum;
import com.cmcc.system.domain.CmccGroupInfo;
import com.cmcc.system.service.ICmccGroupInfoService;
import com.cmcc.system.service.ICmccInterviewBaseService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.cmcc.common.annotation.Log;
import com.cmcc.common.core.controller.BaseController;
import com.cmcc.common.core.domain.AjaxResult;
import com.cmcc.common.enums.BusinessType;
import com.cmcc.system.domain.CmccEnterpriseInfo;
import com.cmcc.system.service.ICmccEnterpriseInfoService;
import com.cmcc.common.utils.poi.ExcelUtil;
import com.cmcc.common.core.page.TableDataInfo;

/**
 * 企业信息Controller
 * 
 * @author terry
 * @date 2024-06-20
 */
@RestController
@RequestMapping("/customer/cmccenterpriseinfo")
public class CmccEnterpriseInfoController extends BaseController
{
    @Autowired
    private ICmccEnterpriseInfoService cmccEnterpriseInfoService;

    @Autowired
    private ICmccGroupInfoService cmccGroupInfoService;


    public boolean checkIsCounty()
    {
        LoginUser loginUser = getLoginUser();
        boolean b =  false;
        if (loginUser.getUser().getDept() != null) {
            b = AreaTypeEnum.东湖区.checkAreaId(loginUser.getUser().getDept().getDeptId());
            if (!b && loginUser.getUser().getDept().getParentId() != null) {
                b = AreaTypeEnum.东湖区.checkAreaId(loginUser.getUser().getDept().getParentId());
            }
        }
        return b;
    }

    /**
     * 查询企业信息列表
     */
    @PreAuthorize("@ss.hasPermi('customer:cmccenterpriseinfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmccEnterpriseInfo cmccEnterpriseInfo)
    {
        LoginUser loginUser = getLoginUser();
        if (loginUser == null){
            TableDataInfo rspData = new TableDataInfo();
            rspData.setCode(HttpStatus.ERROR);
            rspData.setMsg("未获取到登录信息");
            return rspData;
        }
        startPage();
        if (checkIsCounty() && cmccEnterpriseInfo.getDeptId()== null) {
            cmccEnterpriseInfo.setDeptId(loginUser.getDeptId());
        }
        List<CmccEnterpriseInfo> list = cmccEnterpriseInfoService.selectCmccEnterpriseInfoList(cmccEnterpriseInfo);
        return getDataTable(list);
    }

    /**
     * 导出企业信息列表
     */
    @PreAuthorize("@ss.hasPermi('customer:cmccenterpriseinfo:export')")
    @Log(title = "企业信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmccEnterpriseInfo cmccEnterpriseInfo)
    {
        List<CmccEnterpriseInfo> list = cmccEnterpriseInfoService.selectCmccEnterpriseInfoList(cmccEnterpriseInfo);
        ExcelUtil<CmccEnterpriseInfo> util = new ExcelUtil<CmccEnterpriseInfo>(CmccEnterpriseInfo.class);
        util.exportExcel(response, list, "企业信息数据");
    }

    /**
     * 获取企业信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('customer:cmccenterpriseinfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(cmccEnterpriseInfoService.selectCmccEnterpriseInfoById(id));
    }

    @PostMapping("/addAdd")
    public AjaxResult appAdd(@RequestBody CmccEnterpriseInfo cmccEnterpriseInfo)
    {
        LoginUser loginUser = getLoginUser();
        if (loginUser == null  || loginUser.getDeptId() == null) {
            return AjaxResult.error("获取登录用户失败");
        }
        if (cmccEnterpriseInfo.getGroupId() == null) {
            return AjaxResult.error("楼宇不能为空");
        }
        CmccGroupInfo cmccGroupInfo = cmccGroupInfoService.selectCmccGroupInfoById(cmccEnterpriseInfo.getGroupId());
        if (cmccGroupInfo == null) {
            return  AjaxResult.error("楼宇不存在");
        }
//        cmccEnterpriseInfo.setCorporation(cmccGroupInfo.getGroupType());
        cmccEnterpriseInfo.setDeptId(loginUser.getDeptId());
        cmccEnterpriseInfo.setDeptName(AreaTypeEnum.getNameByValue(loginUser.getDeptId()));
        cmccEnterpriseInfo.setEnterpriseStatus("0");
        cmccEnterpriseInfo.setCreateUserId(loginUser.getUserId());
        cmccEnterpriseInfo.setCreateUserName(loginUser.getUser().getNickName());
        cmccEnterpriseInfo.setUpdateUserId(loginUser.getUserId());
        cmccEnterpriseInfo.setUpdateUserName(loginUser.getUser().getNickName());
        cmccEnterpriseInfo.setCreateTime(new Date());
        return toAjax(cmccEnterpriseInfoService.insertCmccEnterpriseInfo(cmccEnterpriseInfo));
    }

    @PostMapping("/dealEdit")
    public AjaxResult dealEdit(@RequestBody CmccEnterpriseInfo cmccEnterpriseInfo) {
        LoginUser loginUser = getLoginUser();
        if (loginUser == null ) {
            return AjaxResult.error("获取登录用户失败");
        }
        cmccEnterpriseInfo.setEnterpriseStatus("2");
        cmccEnterpriseInfo.setUpdateUserId(loginUser.getUserId());
        cmccEnterpriseInfo.setUpdateUserName(loginUser.getUser().getNickName());
        cmccEnterpriseInfo.setUpdateTime(new Date());
        return toAjax(cmccEnterpriseInfoService.updateCmccEnterpriseInfo(cmccEnterpriseInfo));

    }


    @PostMapping("/appEdit")
    public AjaxResult appEdit(@RequestBody CmccEnterpriseInfo cmccEnterpriseInfo)
    {
        LoginUser loginUser = getLoginUser();
        if (loginUser == null ) {
            return AjaxResult.error("获取登录用户失败");
        }
        cmccEnterpriseInfo.setUpdateUserId(loginUser.getUserId());
        cmccEnterpriseInfo.setUpdateUserName(loginUser.getUser().getNickName());
        cmccEnterpriseInfo.setUpdateTime(new Date());
        return toAjax(cmccEnterpriseInfoService.updateCmccEnterpriseInfo(cmccEnterpriseInfo));
    }

    /**
     * 新增企业信息
     */
    @PreAuthorize("@ss.hasPermi('customer:cmccenterpriseinfo:add')")
    @Log(title = "企业信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmccEnterpriseInfo cmccEnterpriseInfo)
    {
        LoginUser loginUser = getLoginUser();
        if (loginUser == null){
            return  AjaxResult.error("用户未登录");
        }
        if (cmccEnterpriseInfo.getDeptId() == null) {
            cmccEnterpriseInfo.setDeptId(loginUser.getDeptId());
        }
        cmccEnterpriseInfo.setDeptName(AreaTypeEnum.getNameByValue(cmccEnterpriseInfo.getDeptId()));
        cmccEnterpriseInfo.setEnterpriseStatus("0");
        cmccEnterpriseInfo.setCreateUserId(loginUser.getUserId());
        cmccEnterpriseInfo.setCreateUserName(loginUser.getUser().getNickName());
        cmccEnterpriseInfo.setUpdateUserId(loginUser.getUserId());
        cmccEnterpriseInfo.setUpdateUserName(loginUser.getUser().getNickName());
        cmccEnterpriseInfo.setCreateTime(new Date());
        return toAjax(cmccEnterpriseInfoService.insertCmccEnterpriseInfo(cmccEnterpriseInfo));
    }

    @GetMapping("/getRenterpriseText")
    public AjaxResult getRenterpriseText(@RequestParam(required = false) Long queryGroupId)
    {
        LoginUser loginUser = getLoginUser();
        if (loginUser == null) {
            return AjaxResult.error("用户未登录");
        }
        CmccEnterpriseInfo query = new CmccEnterpriseInfo();
        query.setEnterpriseStatus("0");
        if (queryGroupId != null) {
            query.setGroupId(queryGroupId);
        }

//        boolean b = checkIsCounty();
//        if (!b) {
//
//        }
        List<CmccEnterpriseInfo> cmccEnterpriseInfos = cmccEnterpriseInfoService.selectCmccEnterpriseInfoList(query);

        List<AppDictDataNew> returnList = new ArrayList();
        if (cmccEnterpriseInfos != null && cmccEnterpriseInfos.size() > 0 ) {
            for (CmccEnterpriseInfo groupInfo : cmccEnterpriseInfos) {
                AppDictDataNew data = new AppDictDataNew();
                data.setValue(groupInfo.getId());
                data.setText(groupInfo.getEnterpriseName());
                returnList.add(data);
            }
        }
        return AjaxResult.success(returnList);
    }

    /**
     * 修改企业信息
     */
    @PreAuthorize("@ss.hasPermi('customer:cmccenterpriseinfo:edit')")
    @Log(title = "企业信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmccEnterpriseInfo cmccEnterpriseInfo)
    {
        LoginUser loginUser = getLoginUser();
        if (loginUser == null ) {
            return AjaxResult.error("获取登录用户失败");
        }
        cmccEnterpriseInfo.setUpdateUserId(loginUser.getUserId());
        cmccEnterpriseInfo.setUpdateUserName(loginUser.getUser().getNickName());
        cmccEnterpriseInfo.setUpdateTime(new Date());
        return toAjax(cmccEnterpriseInfoService.updateCmccEnterpriseInfo(cmccEnterpriseInfo));
    }

    /**
     * 删除企业信息
     */
    @PreAuthorize("@ss.hasPermi('customer:cmccenterpriseinfo:remove')")
    @Log(title = "企业信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(cmccEnterpriseInfoService.deleteCmccEnterpriseInfoByIds(ids));
    }
}

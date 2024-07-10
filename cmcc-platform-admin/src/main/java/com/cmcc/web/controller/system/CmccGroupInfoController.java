package com.cmcc.web.controller.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import com.cmcc.common.constant.HttpStatus;
import com.cmcc.common.core.domain.entity.SysDictData;
import com.cmcc.common.core.domain.model.LoginUser;
import com.cmcc.common.utils.StringUtils;
import com.cmcc.generator.domain.GenTableColumn;
import com.cmcc.system.domain.AppDictData;
import com.cmcc.system.domain.AppDictDataNew;
import com.cmcc.system.domain.AreaTypeEnum;
import com.cmcc.system.service.ISysDictDataService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.cmcc.common.annotation.Log;
import com.cmcc.common.core.controller.BaseController;
import com.cmcc.common.core.domain.AjaxResult;
import com.cmcc.common.enums.BusinessType;
import com.cmcc.system.domain.CmccGroupInfo;
import com.cmcc.system.service.ICmccGroupInfoService;
import com.cmcc.common.utils.poi.ExcelUtil;
import com.cmcc.common.core.page.TableDataInfo;

/**
 * 楼宇信息维护Controller
 * 
 * @author terry
 * @date 2024-06-20
 */
@RestController
@RequestMapping("/customer/cmccgroupinfo")
public class CmccGroupInfoController extends BaseController
{
    @Autowired
    private ICmccGroupInfoService cmccGroupInfoService;

    @Autowired
    private ISysDictDataService iSysDictDataService;

    /**
     * 查询楼宇信息维护列表
     */
    @PreAuthorize("@ss.hasPermi('customer:cmccgroupinfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmccGroupInfo cmccGroupInfo)
    {

        LoginUser loginUser = getLoginUser();
        if (loginUser == null){
            TableDataInfo rspData = new TableDataInfo();
            rspData.setCode(HttpStatus.ERROR);
            rspData.setMsg("未获取到登录信息");
            return rspData;
        }
        startPage();
        if (checkIsCounty() && cmccGroupInfo.getDeptId()== null) {
            cmccGroupInfo.setDeptId(loginUser.getDeptId());
        }

        List<CmccGroupInfo> list = cmccGroupInfoService.selectCmccGroupInfoList(cmccGroupInfo);
        return getDataTable(list);
    }

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

    @GetMapping("/getGroupText")
    public AjaxResult getGroupText(@RequestParam(required = false) Long queryDeptId)
    {
        LoginUser loginUser = getLoginUser();
        if (loginUser == null) {
            return AjaxResult.error("用户未登录");
        }
        CmccGroupInfo cmccGroupInfo = new CmccGroupInfo();
        if (queryDeptId == null ) {
            long deptId = loginUser.getDeptId().longValue();
            if (AreaTypeEnum.东湖区.checkAreaId(deptId)) {
                cmccGroupInfo.setDeptId(loginUser.getDeptId());
            } else {
                if (loginUser.getUser().getDept() != null && loginUser.getUser().getDept().getParentId() != null) {
                    if (AreaTypeEnum.东湖区.checkAreaId(loginUser.getUser().getDept().getParentId())){
                        cmccGroupInfo.setDeptId(loginUser.getDeptId());
                    }
                }
            }
        } else {
            cmccGroupInfo.setDeptId(queryDeptId);
        }
        List<CmccGroupInfo> list = cmccGroupInfoService.selectCmccGroupInfoList(cmccGroupInfo);

        SysDictData sysDictData = new SysDictData();
        sysDictData.setDictType("scene_type");
        List<SysDictData> sysDictData1 = iSysDictDataService.selectDictDataList(sysDictData);
        Map<String, SysDictData> tableColumnMap = sysDictData1.stream().collect(Collectors.toMap(SysDictData::getDictValue, a->a));
//        for (){}
        List<AppDictDataNew> returnList = new ArrayList();
        if (list != null && list.size() > 0 ) {
            for (CmccGroupInfo groupInfo : list) {
                AppDictDataNew data = new AppDictDataNew();
                data.setValue(groupInfo.getId());
                SysDictData sysDictData2 = tableColumnMap.get(groupInfo.getGroupType());
                if ( sysDictData2 != null) {
                    data.setType(sysDictData2.getDictLabel());
                }
                data.setText(groupInfo.getGroupName());
                returnList.add(data);
            }
        }
        return AjaxResult.success(returnList);
    }

    @GetMapping("/getCmccGroupList")
    public AjaxResult getCmccGroupList(@RequestParam(required = false) Long queryDeptId)
    {
        LoginUser loginUser = getLoginUser();
        if (loginUser == null) {
            return AjaxResult.error("用户未登录");
        }
        CmccGroupInfo cmccGroupInfo = new CmccGroupInfo();
        if (queryDeptId == null ) {
            long deptId = loginUser.getDeptId().longValue();
            if (AreaTypeEnum.东湖区.checkAreaId(deptId)) {
                cmccGroupInfo.setDeptId(loginUser.getDeptId());
            } else {
                if (loginUser.getUser().getDept() != null && loginUser.getUser().getDept().getParentId() != null) {
                    if (AreaTypeEnum.东湖区.checkAreaId(loginUser.getUser().getDept().getParentId())){
                        cmccGroupInfo.setDeptId(loginUser.getDeptId());
                    }
                }
            }
        } else {
            cmccGroupInfo.setDeptId(queryDeptId);
        }
        List<CmccGroupInfo> list = cmccGroupInfoService.selectCmccGroupInfoList(cmccGroupInfo);
        return AjaxResult.success(list);
    }

    @GetMapping("/getAreaList")
    public AjaxResult getAreaList()
    {
        LoginUser loginUser = getLoginUser();
        if (loginUser == null )
        {
            return AjaxResult.error("获取登录信息失败");
        }
        boolean b =  false;
        if (loginUser.getUser().getDept() != null) {
           b = AreaTypeEnum.东湖区.checkAreaId(loginUser.getUser().getDept().getDeptId());
           if (!b && loginUser.getUser().getDept().getParentId() != null) {
               b = AreaTypeEnum.东湖区.checkAreaId(loginUser.getUser().getDept().getParentId());
           }
        }
        List<AppDictData> list = new ArrayList<>();
        for (AreaTypeEnum value : AreaTypeEnum.values()) {
            if ( b ) {
                if (value.getAreaId().intValue() == loginUser.getDeptId().intValue()) {
                    AppDictData dict = new AppDictData();
                    dict.setValue(String.valueOf(value.getAreaId()));
                    dict.setText(value.getAreaName());
                    list.add(dict);
                }
            } else {
                AppDictData dict = new AppDictData();
                dict.setValue(String.valueOf(value.getAreaId()));
                dict.setText(value.getAreaName());
                list.add(dict);
            }
        }
        return AjaxResult.success(list);
    }



    /**
     * 导出楼宇信息维护列表
     */
    @PreAuthorize("@ss.hasPermi('customer:cmccgroupinfo:export')")
    @Log(title = "楼宇信息维护", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmccGroupInfo cmccGroupInfo)
    {
        List<CmccGroupInfo> list = cmccGroupInfoService.selectCmccGroupInfoList(cmccGroupInfo);
        ExcelUtil<CmccGroupInfo> util = new ExcelUtil<CmccGroupInfo>(CmccGroupInfo.class);
        util.exportExcel(response, list, "楼宇信息维护数据");
    }

    /**
     * 获取楼宇信息维护详细信息
     */
    @PreAuthorize("@ss.hasPermi('customer:cmccgroupinfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(cmccGroupInfoService.selectCmccGroupInfoById(id));
    }

    /**
     * 新增楼宇信息维护
     */
    @PreAuthorize("@ss.hasPermi('customer:cmccgroupinfo:add')")
    @Log(title = "楼宇信息维护", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmccGroupInfo cmccGroupInfo)
    {
        return toAjax(cmccGroupInfoService.insertCmccGroupInfo(cmccGroupInfo));
    }

    /**
     * 修改楼宇信息维护
     */
    @PreAuthorize("@ss.hasPermi('customer:cmccgroupinfo:edit')")
    @Log(title = "楼宇信息维护", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmccGroupInfo cmccGroupInfo)
    {
        return toAjax(cmccGroupInfoService.updateCmccGroupInfo(cmccGroupInfo));
    }

    /**
     * 删除楼宇信息维护
     */
    @PreAuthorize("@ss.hasPermi('customer:cmccgroupinfo:remove')")
    @Log(title = "楼宇信息维护", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(cmccGroupInfoService.deleteCmccGroupInfoByIds(ids));
    }
}

package com.cmcc.web.controller.system;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.cmcc.common.core.domain.model.LoginUser;
import com.cmcc.system.domain.*;
import com.cmcc.system.service.IInspectionWorkRecordService;
import com.cmcc.system.service.IdGeneratorSerivce;
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
import com.cmcc.system.service.IInspectionTaskInfoService;
import com.cmcc.common.utils.poi.ExcelUtil;
import com.cmcc.common.core.page.TableDataInfo;

/**
 * 巡检记录Controller
 * 
 * @author terry
 * @date 2024-04-18
 */
@RestController
@RequestMapping("/inspection/taskinfo")
public class InspectionTaskInfoController extends BaseController
{
    @Autowired
    private IInspectionTaskInfoService inspectionTaskInfoService;

    @Autowired
    private IInspectionWorkRecordService iInspectionWorkRecordService;

    @Autowired
    private IdGeneratorSerivce idGeneratorSerivce;

    @Autowired
    private UnionEnterpriseConfigCacheService unionEnterpriseConfigCacheService;

    /**
     * 查询巡检记录列表
     */
    @PreAuthorize("@ss.hasPermi('inspection:taskinfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(InspectionTaskInfo inspectionTaskInfo)
    {
        startPage();
        List<InspectionTaskInfo> list = inspectionTaskInfoService.selectInspectionTaskInfoList(inspectionTaskInfo);
        return getDataTable(list);
    }

    /**
     * 导出巡检记录列表
     */
    @PreAuthorize("@ss.hasPermi('inspection:taskinfo:export')")
    @Log(title = "巡检记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, InspectionTaskInfo inspectionTaskInfo)
    {
        List<InspectionTaskInfo> list = inspectionTaskInfoService.selectInspectionTaskInfoList(inspectionTaskInfo);
        ExcelUtil<InspectionTaskInfo> util = new ExcelUtil<InspectionTaskInfo>(InspectionTaskInfo.class);
        util.exportExcel(response, list, "巡检记录数据");
    }

    /**
     * 获取巡检记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('inspection:taskinfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(inspectionTaskInfoService.selectInspectionTaskInfoById(id));
    }

    /**
     * 新增巡检记录
     */
    @PreAuthorize("@ss.hasPermi('inspection:taskinfo:add')")
    @Log(title = "巡检记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody InspectionTaskInfo inspectionTaskInfo)
    {
        return toAjax(inspectionTaskInfoService.insertInspectionTaskInfo(inspectionTaskInfo));
    }

    private InspectionTaskInfo changeByAppInfo(AppInspectionTaskInfo appInspectionTaskInfo, LoginUser loginUser)
    {
        InspectionTaskInfo inspectionTaskInfo = new InspectionTaskInfo();
        if (appInspectionTaskInfo == null)
        {
            return null;
        }

        if (appInspectionTaskInfo.getUrls() != null && appInspectionTaskInfo.getUrls().size() > 0)
        {
            inspectionTaskInfo.setAttachmentOne(appInspectionTaskInfo.getUrls().get(0));
        }

        if (appInspectionTaskInfo.getUrls() != null && appInspectionTaskInfo.getUrls().size() > 1)
        {
            inspectionTaskInfo.setAttachmentTwo(appInspectionTaskInfo.getUrls().get(1));
        }

        AppUnionConfig appUnionConfigByDeptId = unionEnterpriseConfigCacheService.getAppUnionConfigByDeptId(loginUser.getDeptId());

        inspectionTaskInfo.setDeptId(appInspectionTaskInfo.getArea2id());
//        if (loginUser.getUser() != null && loginUser.getUser().getDept() != null)
//        {
        inspectionTaskInfo.setDeptName(appInspectionTaskInfo.getArea2());
//        }
        inspectionTaskInfo.setRecordInfo(appInspectionTaskInfo.getIntroduction());
        inspectionTaskInfo.setRecordTime(new Date());
        inspectionTaskInfo.setTaskAddress(appInspectionTaskInfo.getAddr());
        inspectionTaskInfo.setTaskType(Long.valueOf(appInspectionTaskInfo.getType()));
        inspectionTaskInfo.setTaskUserId(loginUser.getUserId());
        inspectionTaskInfo.setTaskUserName(loginUser.getUser().getNickName());
        inspectionTaskInfo.setTroubleFlag(appInspectionTaskInfo.getProblem());
//        inspectionTaskInfo.setUnionId(appInspectionTaskInfo.getArea1id());
        inspectionTaskInfo.setUnionId(appUnionConfigByDeptId.getArea1id());
        inspectionTaskInfo.setUnionName(appInspectionTaskInfo.getArea1());
        return inspectionTaskInfo;
    }

    private InspectionWorkRecord changeByTaskInfo(InspectionTaskInfo task)
    {
        InspectionWorkRecord work = new InspectionWorkRecord();
        work.setAttachmentOne(task.getAttachmentOne());
        work.setAttachmentTwo(task.getAttachmentTwo());
        work.setDeptId(task.getDeptId());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String currentDate = sdf.format(Calendar.getInstance().getTime());
        long l = idGeneratorSerivce.generateId(currentDate + ":" + task.getTaskType());
        String format = String.format("%s%d%06d", currentDate, task.getTaskType(), l);
        work.setWorkId(format);
        work.setDeptName(task.getDeptName());
        work.setTaskUserId(task.getTaskUserId());
        work.setTaskUserName(task.getTaskUserName());
        work.setTroubleInfo(task.getRecordInfo());
        work.setTaskId(task.getId());
        work.setUnionId(task.getUnionId());
        work.setUnionName(task.getUnionName());
        work.setWorkStatus(WorkStatusEnum.待处理.getWorkStatus());
        work.setWorkType(String.valueOf(task.getTaskType()));
        work.setAddress(task.getTaskAddress());
        work.setAccpetTime(new Date());
        return work;
    }

    @PostMapping("/appAdd")
    public AjaxResult appAdd(@RequestBody AppInspectionTaskInfo appInspectionTaskInfo)
    {
        LoginUser loginUser = getLoginUser();
        if (loginUser == null)
        {
            return AjaxResult.error("获取用户登录信息失败");
        }

        InspectionTaskInfo inspectionTaskInfo = changeByAppInfo(appInspectionTaskInfo,loginUser);
        if (inspectionTaskInfo == null)
        {
            return AjaxResult.error("提交信息为空");
        }


        int ret = inspectionTaskInfoService.insertInspectionTaskInfo(inspectionTaskInfo);

        // 有问题
        if ("1".equals(inspectionTaskInfo.getTroubleFlag()))
        {
            InspectionWorkRecord inspectionWorkRecord = changeByTaskInfo(inspectionTaskInfo);
            ret = iInspectionWorkRecordService.insertInspectionWorkRecord(inspectionWorkRecord);
        }
        return toAjax(ret);
    }

    /**
     * 修改巡检记录
     */
    @PreAuthorize("@ss.hasPermi('inspection:taskinfo:edit')")
    @Log(title = "巡检记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody InspectionTaskInfo inspectionTaskInfo)
    {
        return toAjax(inspectionTaskInfoService.updateInspectionTaskInfo(inspectionTaskInfo));
    }

    /**
     * 删除巡检记录
     */
    @PreAuthorize("@ss.hasPermi('inspection:taskinfo:remove')")
    @Log(title = "巡检记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(inspectionTaskInfoService.deleteInspectionTaskInfoByIds(ids));
    }
}

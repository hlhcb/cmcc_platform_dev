package com.cmcc.web.controller.system;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.cmcc.common.constant.HttpStatus;
import com.cmcc.common.core.domain.entity.dto.ImportErrorItem;
import com.cmcc.common.core.domain.entity.dto.UserTaskTemplate;
import com.cmcc.common.core.domain.model.LoginUser;
import com.cmcc.common.utils.DateUtils;
import com.cmcc.system.domain.*;
import com.cmcc.system.service.ICmccGroupInfoService;
import com.github.pagehelper.PageInfo;
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
import com.cmcc.system.service.ICmccUserTaskService;
import com.cmcc.common.utils.poi.ExcelUtil;
import com.cmcc.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 用户任务Controller
 * 
 * @author terry
 * @date 2024-06-20
 */
@RestController
@RequestMapping("/customer/cmccusertask")
public class CmccUserTaskController extends BaseController
{
    @Autowired
    private ICmccUserTaskService cmccUserTaskService;

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


    @PostMapping("/appenterprisetask")
    public TableDataInfo appenterprisetask(@RequestBody CmccAppTaskQueryVo query) {

        LoginUser loginUser = getLoginUser();
        TableDataInfo rspData = new TableDataInfo();
        if (loginUser == null){
            rspData.setCode(HttpStatus.ERROR);
            rspData.setMsg("当前用户未登录");
            return rspData;
        }

        if (query.getGroupId() == null) {
            rspData.setCode(HttpStatus.ERROR);
            rspData.setMsg("园区不能为空");
            return rspData;
        }

        startPage();
        Map<String, Object> map  = new HashMap<>();
        map.put("userId", loginUser.getUserId());
        map.put("weekBeginDate", DateUtils.getCurrentWeekFirstDay());
        map.put("weekEndDate", DateUtils.getNextWeekFirstDay());
        map.put("interviewStatus", query.getInterviewStatus());
        map.put("groupId", query.getGroupId());
        map.put("id", query.getId());
        map.put("queryName", query.getQueryName());

        List<AppEnterpriseTask> appEnterpriseTasks = cmccUserTaskService.selectCmccEnterpriseTsk(map);
        return getDataTable(appEnterpriseTasks);
    }

    @GetMapping("/appweektasklist")
    public AjaxResult appweektasklist() {
        LoginUser loginUser = getLoginUser();
        if (loginUser == null) {
            return AjaxResult.error("当前用户未登录");
        }

        Map<String, Object> map  = new HashMap<>();
        map.put("userId", loginUser.getUserId());
        map.put("currentDate", DateUtils.getDate());
        map.put("weekBeginDate", DateUtils.getCurrentWeekFirstDay());
        map.put("weekEndDate", DateUtils.getNextWeekFirstDay());

        List<AppGroupTaskList> appGroupTaskLists = cmccUserTaskService.selectCmccUserTask(map);
        Date date = new Date();
        for (AppGroupTaskList appGroupTaskList : appGroupTaskLists) {

            int i = DateUtils.differentHoursByMillisecond(appGroupTaskList.getTaskEndTime(), date);
            String dualTime = String.format("%s小时",i);
            appGroupTaskList.setTaskEndHours(i);
            if (i >= 24) {
                i = DateUtils.differentDaysByMillisecond(appGroupTaskList.getTaskEndTime(), date);
                dualTime = String.format("%s天",i+1);
            }
            appGroupTaskList.setDualTime(dualTime);
        }

        return AjaxResult.success(appGroupTaskLists);
    }

    /**
     * 查询用户任务列表
     */
    @PreAuthorize("@ss.hasPermi('customer:cmccusertask:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmccUserTask cmccUserTask)
    {
        LoginUser loginUser = getLoginUser();
        if (loginUser == null){
            TableDataInfo rspData = new TableDataInfo();
            rspData.setCode(HttpStatus.ERROR);
            rspData.setMsg("未获取到登录信息");
            return rspData;
        }
        startPage();
        if (checkIsCounty() && cmccUserTask.getDeptId()== null) {
            cmccUserTask.setDeptId(loginUser.getDeptId());
        }
        List<CmccUserTask> list = cmccUserTaskService.selectCmccUserTaskList(cmccUserTask);
        return getDataTable(list);
    }

    /**
     * 导出用户任务列表
     */
    @PreAuthorize("@ss.hasPermi('customer:cmccusertask:export')")
    @Log(title = "用户任务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmccUserTask cmccUserTask)
    {
        List<CmccUserTask> list = cmccUserTaskService.selectCmccUserTaskList(cmccUserTask);
        ExcelUtil<CmccUserTask> util = new ExcelUtil<CmccUserTask>(CmccUserTask.class);
        util.exportExcel(response, list, "用户任务数据");
    }

    /**
     * 获取用户任务详细信息
     */
    @PreAuthorize("@ss.hasPermi('customer:cmccusertask:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(cmccUserTaskService.selectCmccUserTaskById(id));
    }

    /**
     * 新增用户任务
     */
    @PreAuthorize("@ss.hasPermi('customer:cmccusertask:add')")
    @Log(title = "用户任务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmccUserTask cmccUserTask)
    {

        LoginUser loginUser = getLoginUser();
        if (loginUser == null) {
            return AjaxResult.error("用户未登录");
        }

        if (cmccUserTask.getGroupId() == null){
            return AjaxResult.error("集团不能为空");
        }

        CmccGroupInfo cmccGroupInfo = cmccGroupInfoService.selectCmccGroupInfoById(cmccUserTask.getGroupId());
        if (cmccGroupInfo == null){
            return AjaxResult.error("集团不能为空");
        }


        updateTaskTime(cmccUserTask);
        cmccUserTask.setTaskStatus("0");
        cmccUserTask.setGroupType(cmccGroupInfo.getGroupType());
        cmccUserTask.setCreateUserId(loginUser.getUserId());
        cmccUserTask.setCreateUserName(loginUser.getUser().getNickName());
        return toAjax(cmccUserTaskService.insertCmccUserTask(cmccUserTask));
    }

    private void updateTaskTime(CmccUserTask cmccUserTask) {
        if (cmccUserTask.getTaskType() != null) {
            if (0 == cmccUserTask.getTaskType().intValue()) {
                cmccUserTask.setTaskBeginTime(DateUtils.getCurrentWeekFirstDayTime());
                cmccUserTask.setTaskEndTime(DateUtils.getCurrentWeekEndDayTime());
            } else if (1 == cmccUserTask.getTaskType().intValue()) {
                cmccUserTask.setTaskBeginTime(DateUtils.getCurrentDayTime());
                cmccUserTask.setTaskEndTime(DateUtils.getNextDayTime(1));
            } else if (2 == cmccUserTask.getTaskType().intValue()) {
                cmccUserTask.setTaskBeginTime(DateUtils.getCurrentDayTime());
                cmccUserTask.setTaskEndTime(DateUtils.getNextDayTime(3));
            }
        }
    }

    /**
     * 修改用户任务
     */
    @PreAuthorize("@ss.hasPermi('customer:cmccusertask:edit')")
    @Log(title = "用户任务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmccUserTask cmccUserTask)
    {

        updateTaskTime(cmccUserTask);
        return toAjax(cmccUserTaskService.updateCmccUserTask(cmccUserTask));
    }

    /**
     * 删除用户任务
     */
    @PreAuthorize("@ss.hasPermi('customer:cmccusertask:remove')")
    @Log(title = "用户任务", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(cmccUserTaskService.deleteCmccUserTaskByIds(ids));
    }


    /**
     * 批量导入用户任务
     */
    @PreAuthorize("@ss.hasPermi('customer:cmccusertask:add')")
    @Log(title = "用户任务", businessType = BusinessType.INSERT)
    @PostMapping("/addBatch")
    public AjaxResult addBatch(MultipartFile file, boolean updateSupport){
        LoginUser loginUser = getLoginUser();
        if (loginUser == null) {
            return AjaxResult.error("用户未登录");
        }
        List<ImportErrorItem> importErrorItems = cmccUserTaskService.importUserTask(file, loginUser);
        return AjaxResult.success(importErrorItems);
    }

    /**
     * 模板下载
     */
    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response)
    {
        ExcelUtil<UserTaskTemplate> util = new ExcelUtil<>(UserTaskTemplate.class);
        util.importTemplateExcel(response, "user_task_template");
    }
}

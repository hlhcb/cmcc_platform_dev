package com.cmcc.web.controller.system;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import javax.servlet.http.HttpServletResponse;

import com.cmcc.common.core.domain.entity.SysDept;
import com.cmcc.common.core.domain.entity.SysRole;
import com.cmcc.common.core.domain.entity.SysUser;
import com.cmcc.common.core.domain.model.LoginUser;
import com.cmcc.common.utils.StringUtils;
import com.cmcc.system.domain.*;
import com.cmcc.system.service.IAccpetWorkRecordUserService;
import com.cmcc.system.service.ISysDeptService;
import com.cmcc.system.service.UnionEnterpriseConfigCacheService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.cmcc.common.annotation.Log;
import com.cmcc.common.core.controller.BaseController;
import com.cmcc.common.core.domain.AjaxResult;
import com.cmcc.common.enums.BusinessType;
import com.cmcc.system.service.IInspectionWorkRecordService;
import com.cmcc.common.utils.poi.ExcelUtil;
import com.cmcc.common.core.page.TableDataInfo;

/**
 * 工单记录Controller
 * 
 * @author terry
 * @date 2024-04-18
 */
@RestController
@RequestMapping("/inspection/workrecord")
public class InspectionWorkRecordController extends BaseController
{
    @Autowired
    private IInspectionWorkRecordService inspectionWorkRecordService;

    @Autowired
    private IAccpetWorkRecordUserService iAccpetWorkRecordUserService;

    @Autowired
    private ISysDeptService iSysDeptService;

    @Autowired
    private UnionEnterpriseConfigCacheService unionEnterpriseConfigCacheService;


    /**
     * 查询工单记录列表
     */
    @PreAuthorize("@ss.hasPermi('inspection:workrecord:list')")
    @GetMapping("/list")
    public TableDataInfo list(InspectionWorkRecord inspectionWorkRecord)
    {
        startPage();
        List<InspectionWorkRecord> list = inspectionWorkRecordService.selectInspectionWorkRecordList(inspectionWorkRecord);
        return getDataTable(list);
    }

    @GetMapping("/getWorkRecord")
    public AjaxResult getWorkRecord(@RequestParam String num){
        InspectionWorkRecord query = new InspectionWorkRecord();
        query.setWorkId(num);
        List<InspectionWorkRecord> listWork = inspectionWorkRecordService.selectInspectionWorkRecordList(query);
        if (listWork == null || listWork.size() < 1) {
            return AjaxResult.error("未查询到工单");
        }
        InspectionWorkRecord current = listWork.get(0);
        AppWorkRecord appWorkRecord = changeAppRecord(current);
        return AjaxResult.success(appWorkRecord);
    }

    private AppWorkRecord changeAppRecord(InspectionWorkRecord record) {
        AppWorkRecord appRecord = new AppWorkRecord();
        if (record == null ){
            return appRecord;
        }
        appRecord.setAdd(record.getAddress());
        appRecord.setArea1(record.getUnionName());
//        appRecord.setArea2(record.getDeptName());
        appRecord.setArea2(record.getUnionDeptName());
        appRecord.setIntroduction(record.getTroubleInfo());
        appRecord.setIntroduction2(record.getResultInfo());
        appRecord.setName(record.getTaskUserName());
        appRecord.setName2(record.getAccpetUserName());
        appRecord.setName3(record.getChangeDeptName());
        appRecord.setTime(record.getAccpetTime());
        appRecord.setNum(record.getWorkId());
        appRecord.setTime2(record.getDealTime());
        appRecord.setType(record.getWorkType());
        appRecord.setAdd2(record.getDetailPosition());
        List<String> url = new ArrayList<>();
        if (StringUtils.isNotBlank(record.getAttachmentOne()))
        {
            url.add(record.getAttachmentOne());
            if (StringUtils.isNotBlank(record.getAttachmentTwo()))
            {
                url.add(record.getAttachmentTwo());
            }
            appRecord.setUrls(url);
        }

        List<String> url1 = new ArrayList<>();
        if (StringUtils.isNotBlank(record.getReturnAttachmentOne()))
        {
            url1.add(record.getReturnAttachmentOne());
            if (StringUtils.isNotBlank(record.getReturnAttachmentTwo()))
            {
                url1.add(record.getReturnAttachmentTwo());
            }
            appRecord.setUrls2(url1);
        }
        return appRecord;
    }

    @GetMapping("/accpetWorkRecord")
    public AjaxResult accpetWorkRecord(@RequestParam String num){
        LoginUser loginUser = getLoginUser();
        if (loginUser == null)
        {
            return AjaxResult.error("获取用户登录信息失败");
        }

        InspectionWorkRecord queryWorkRecord = new InspectionWorkRecord();
        queryWorkRecord.setWorkId(num);
        List<InspectionWorkRecord> list = inspectionWorkRecordService.selectInspectionWorkRecordList(queryWorkRecord);
        if (list == null || list.size() < 1) {
            return AjaxResult.error("未查询到工单");
        }
        InspectionWorkRecord current = list.get(0);
        current.setWorkStatus(WorkStatusEnum.处理中.getWorkStatus());
        current.setAccpetUserId(loginUser.getUserId());
        current.setAccpetUserName(loginUser.getUser().getNickName());
        return toAjax(inspectionWorkRecordService.updateInspectionWorkRecord(current));
    }

    @PostMapping("/dealWorkRecord")
    public AjaxResult dealWorkRecord(@RequestBody DealWorkRecord dealWorkRecord){
        LoginUser loginUser = getLoginUser();
        if (loginUser == null || loginUser.getUser() == null)
        {
            return AjaxResult.error("获取用户登录信息失败");
        }

        if (StringUtils.isBlank(dealWorkRecord.getNum()))
        {
            return AjaxResult.error("订单编号为空");
        }

        InspectionWorkRecord queryWorkRecord = new InspectionWorkRecord();
        queryWorkRecord.setWorkId(dealWorkRecord.getNum());
        List<InspectionWorkRecord> list = inspectionWorkRecordService.selectInspectionWorkRecordList(queryWorkRecord);
        if (list == null || list.size() < 1) {
            return AjaxResult.error("未查询到工单");
        }
        InspectionWorkRecord current = list.get(0);
        current.setWorkStatus(WorkStatusEnum.已完成.getWorkStatus());
        current.setDealTime(new Date());
        current.setResultInfo(dealWorkRecord.getIntroduction2());
        current.setChangeDeptId(loginUser.getUserId());
        current.setChangeDeptName(loginUser.getUser().getNickName());
        if (dealWorkRecord.getUrls2() != null && dealWorkRecord.getUrls2().size() > 0)
        {
            current.setReturnAttachmentOne(dealWorkRecord.getUrls2().get(0));
            if (dealWorkRecord.getUrls2().size() > 1) {
                current.setReturnAttachmentTwo(dealWorkRecord.getUrls2().get(1));
            }
        }
        return toAjax(inspectionWorkRecordService.updateInspectionWorkRecord(current));
    }

    @GetMapping("/appTypeCount")
    public AjaxResult appTypeCount(@RequestParam String time, @RequestParam Long area1id)
    {
        if (StringUtils.isBlank(time)) {
            return AjaxResult.error("查询时间不能为空");
        }
        Map<String, Object> queryMap = new HashMap<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate now = LocalDate.parse(time, formatter);
        String firstDayOfWeek = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).format(formatter)+" 00:00:00";
        String lastDayOfWeek = now.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)).format(formatter)+" 23:59:59";
        queryMap.put("accpetBegingTime",firstDayOfWeek);
        queryMap.put("accpetEndTime",lastDayOfWeek);

        queryMap.put("unionId",area1id);
        List<TypeWorkRecord> typeWorkRecords = inspectionWorkRecordService.selectAppCountByType(queryMap);

        List<TypeWorkRecord> allList = new ArrayList<>();

        for (WorkTypeEnum item : WorkTypeEnum.values())
        {
            TypeWorkRecord countByType = getCountByType(typeWorkRecords, item.getWorkType());
            allList.add(countByType);
        }
        return AjaxResult.success(allList);
    }

    private TypeWorkRecord getCountByType(List<TypeWorkRecord> list, String workType)
    {
        if (list == null || list.size() < 1) {
            TypeWorkRecord a = new TypeWorkRecord();
            a.setWorkType(workType);
            a.setTotalCount(0);
            return a;
        }
        for (TypeWorkRecord record : list)
        {
            if (workType.equals(record.getWorkType()))
            {
                return record;
            }
        }

        TypeWorkRecord a = new TypeWorkRecord();
        a.setWorkType(workType);
        a.setTotalCount(0);
        return a;
    }

    @GetMapping("/appStatusCount")
    public AjaxResult appStatusCount(@RequestParam String time, @RequestParam Long area1id)
    {
        if (StringUtils.isBlank(time)) {
            return AjaxResult.error("查询时间不能为空");
        }
        Map<String, Object> queryMap = new HashMap<>();
//        queryMap.put("accpetTime",time);
        queryMap.put("unionId",area1id);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate now = LocalDate.parse(time, formatter);
        String firstDayOfWeek = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).format(formatter)+" 00:00:00";
        String lastDayOfWeek = now.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)).format(formatter)+" 23:59:59";
        queryMap.put("accpetBegingTime",firstDayOfWeek);
        queryMap.put("accpetEndTime",lastDayOfWeek);
        List<StatusWorkRecord> statusWorkRecords = inspectionWorkRecordService.selectAppCountByStatus(queryMap);

        List<StatusWorkRecord> allList = new ArrayList<>();

        for (WorkStatusEnum item : WorkStatusEnum.values())
        {
            StatusWorkRecord countByStatus = getCountByStatus(statusWorkRecords, item.getWorkStatus());
            allList.add(countByStatus);
        }
        return AjaxResult.success(allList);
    }

    private StatusWorkRecord getCountByStatus(List<StatusWorkRecord> list, String workStatus)
    {
        if (list == null || list.size() < 1) {
            StatusWorkRecord a = new StatusWorkRecord();
            a.setWorkStatus(workStatus);
            a.setTotalCount(0);
            return a;
        }
        for (StatusWorkRecord record : list)
        {
            if (workStatus.equals(record.getWorkStatus()))
            {
                return record;
            }
        }

        StatusWorkRecord a = new StatusWorkRecord();
        a.setWorkStatus(workStatus);
        a.setTotalCount(0);
        return a;
    }

    @GetMapping("/appcheckpfy")
    public AjaxResult appcheckpfy()
    {
        SysUser user = getLoginUser().getUser();
        if (user == null)
        {
            return AjaxResult.success(false);
        }
        List<SysRole> roles = user.getRoles();
        if (roles != null)
        {
            for (SysRole a : roles)
            {
                if ("lfgdpfy".equals(a.getRoleKey()))
                {
                    return AjaxResult.success(true);
                }
            }
        }

//        if (!isZpy) {
        SysDept sysDept = iSysDeptService.selectDeptById(user.getDeptId());
        if (sysDept != null)
        {
            String ancestors = sysDept.getAncestors();
            if (ancestors.startsWith("0,100,"))
            {
                return AjaxResult.success(true);
            }
        }
//        }
        return AjaxResult.success(false);
    }

    @PostMapping("/changeUser")
    public AjaxResult changeUser(@RequestBody ChangeUserVo changeUserVo)
    {
        if (StringUtils.isBlank(changeUserVo.getNum()) || changeUserVo.getUid() == null)
        {
            return AjaxResult.error("工单编号或者转派人不能为空");
        }
        InspectionWorkRecord queryWorkRecord = new InspectionWorkRecord();
        queryWorkRecord.setWorkId(changeUserVo.getNum());
        List<InspectionWorkRecord> list = inspectionWorkRecordService.selectInspectionWorkRecordList(queryWorkRecord);
        if (list == null || list.size() < 1) {
            return AjaxResult.error("未查询到工单");
        }

        AccpetWorkRecordUser queryUser = new AccpetWorkRecordUser();
        queryUser.setUserId(changeUserVo.getUid());
        List<AccpetWorkRecordUser> accpetWorkRecordUsers = iAccpetWorkRecordUserService.selectAccpetWorkRecordUserList(queryUser);
        if (accpetWorkRecordUsers == null || accpetWorkRecordUsers.size() < 1) {
            return AjaxResult.error("未查询到接收工单用户");
        }

        AccpetWorkRecordUser accpetWorkRecordUser = accpetWorkRecordUsers.get(0);
        InspectionWorkRecord current = list.get(0);
//        if (!WorkTypeEnum.矛盾纠纷排除.getWorkType().equals(current.getWorkType()))
//        {
//            return AjaxResult.error("不是矛盾纠纷排除工单");
//        }

        current.setWorkStatus(WorkStatusEnum.待处理.getWorkStatus());
//        current.setAccpetUserId(accpetWorkRecordUser.getUserId());
//        current.setAccpetUserName(accpetWorkRecordUser.getNickName());
        current.setAccpetUserId(0l);
        current.setAccpetUserName("");
        current.setChangeUserId(accpetWorkRecordUser.getUserId());
        current.setChangeUserName(accpetWorkRecordUser.getNickName());
        current.setDeptId(accpetWorkRecordUser.getDpetId());
        current.setDeptName(accpetWorkRecordUser.getDeptName());
        current.setTransferFlag("2");
        return toAjax(inspectionWorkRecordService.updateInspectionWorkRecord(current));
    }

    @GetMapping("/setTransferWork")
    public AjaxResult setTransferWork(@RequestParam String num){
        LoginUser loginUser = getLoginUser();
        if (loginUser == null)
        {
            return AjaxResult.error("获取用户登录信息失败");
        }

        InspectionWorkRecord queryWorkRecord = new InspectionWorkRecord();
        queryWorkRecord.setWorkId(num);
        List<InspectionWorkRecord> list = inspectionWorkRecordService.selectInspectionWorkRecordList(queryWorkRecord);
        if (list == null || list.size() < 1) {
            return AjaxResult.error("未查询到工单");
        }
        InspectionWorkRecord current = list.get(0);
        current.setWorkStatus(WorkStatusEnum.待处理.getWorkStatus());
        current.setAccpetUserId(0L);
        current.setAccpetUserName("");
        current.setDeptId(0L);
        current.setDeptName("");
        current.setTransferFlag("1");
        return toAjax(inspectionWorkRecordService.updateInspectionWorkRecord(current));
    }

    @GetMapping("/applistByType")
    public AjaxResult applistByType(@RequestParam String status)
    {
        LoginUser loginUser = getLoginUser();
        if (loginUser == null || loginUser.getDeptId() == null) {
            return AjaxResult.error("获取用户信息失败。");
        }

        InspectionWorkRecord queryWorkRecord = new InspectionWorkRecord();
        queryWorkRecord.setWorkStatus(status);
        boolean isZpy = false;
        List<SysRole> roles = loginUser.getUser().getRoles();
        if (roles != null)
        {
            for (SysRole a : roles)
            {
                if ("lfgdpfy".equals(a.getRoleKey()))
                {
                    isZpy = true;
                    break;
                }
            }
        }
        if (!isZpy) {
            SysDept sysDept = iSysDeptService.selectDeptById(loginUser.getDeptId());
            if (sysDept != null)
            {
                String ancestors = sysDept.getAncestors();
                if (ancestors.startsWith("0,100,"))
                {
                    isZpy =true;
                }
            }
        }

//        if (status.equals(WorkStatusEnum.待处理.getWorkStatus()))
        {

            if (isZpy) {
//                queryWorkRecord.setQueryTransferFlag("1");
//                queryWorkRecord.setQueryTransferDeptId(loginUser.getDeptId());
            }
            else {
//                queryWorkRecord.setDeptId(loginUser.getDeptId());
//                queryWorkRecord.setQueryTransferFlag("2");
                // 再判断是否是被转派员
                boolean isNeed = false;

                AccpetWorkRecordUser accpetWorkRecordUser = new AccpetWorkRecordUser();
                accpetWorkRecordUser.setUserId(loginUser.getUserId());
                List<AccpetWorkRecordUser> list = iAccpetWorkRecordUserService.selectAccpetWorkRecordUserList(accpetWorkRecordUser);
                if (list != null && list.size() >= 1){
                    isNeed = true;
                }
                if (isNeed)
                {
                    queryWorkRecord.setChangeUserId(loginUser.getUserId());
                }
                else
                {
                    // 判断是否市联防长
                    boolean unionAdmin = unionEnterpriseConfigCacheService.isUnionAdmin(loginUser.getUserId());
                   AppUnionConfig appUnionConfigByUserId = unionEnterpriseConfigCacheService.getAppUnionConfigByUserId(loginUser.getUserId());
//                    List<Long> allDeptId = unionEnterpriseConfigCacheService.getAllDeptId(loginUser.getUserId());
                    if (unionAdmin && appUnionConfigByUserId != null)
                    {
//                        queryWorkRecord.setDeptList(allDeptId);
                        queryWorkRecord.setUnionId(appUnionConfigByUserId.getArea1id());
                    }
                    else
                    {
                        queryWorkRecord.setUnionDeptId(loginUser.getDeptId());
                    }

                }
            }
        }
//        else {
////            queryWorkRecord.setAccpetUserId(loginUser.getUserId());
//
//            queryWorkRecord.setUnionDeptId(loginUser.getDeptId());
//        }
//        if ("4".equals(status)){
//            boolean isZpy = false;
//            List<SysRole> roles = loginUser.getUser().getRoles();
//            if (roles != null)
//            {
//                for (SysRole a : roles)
//                {
//                    if ("lfgdpfy".equals(a.getRoleKey()))
//                    {
//                        isZpy = true;
//                        break;
//                    }
//                }
//            }
//            if (isZpy)
//            {
//                queryWorkRecord.setTransferFlag("1");
//            }
//        } else {
//            queryWorkRecord.setWorkStatus(status);
//            if (!status.equals(WorkStatusEnum.待处理.getWorkStatus()))
//            {
//                queryWorkRecord.setAccpetUserId(loginUser.getUserId());
//            } else {
//                queryWorkRecord.setDeptId(loginUser.getDeptId());
//                queryWorkRecord.setQueryTransferFlag("1");
//            }
//        }

        List<InspectionWorkRecord> list = inspectionWorkRecordService.selectInspectionWorkRecordList(queryWorkRecord);
        List<AppWorkRecord> appList = new ArrayList<>();
        if (list != null)
        {
            for (InspectionWorkRecord record : list) {
//                AppWorkRecord appRecord = new AppWorkRecord();
                AppWorkRecord appWorkRecord = changeAppRecord(record);
                appList.add(appWorkRecord);
            }
        }
        return AjaxResult.success(appList);
    }

    /**
     * 导出工单记录列表
     */
    @PreAuthorize("@ss.hasPermi('inspection:workrecord:export')")
    @Log(title = "工单记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, InspectionWorkRecord inspectionWorkRecord)
    {
        List<InspectionWorkRecord> list = inspectionWorkRecordService.selectInspectionWorkRecordList(inspectionWorkRecord);
        ExcelUtil<InspectionWorkRecord> util = new ExcelUtil<InspectionWorkRecord>(InspectionWorkRecord.class);
        util.exportExcel(response, list, "工单记录数据");
    }

    /**
     * 获取工单记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('inspection:workrecord:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(inspectionWorkRecordService.selectInspectionWorkRecordById(id));
    }

    /**
     * 新增工单记录
     */
    @PreAuthorize("@ss.hasPermi('inspection:workrecord:add')")
    @Log(title = "工单记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody InspectionWorkRecord inspectionWorkRecord)
    {
        return toAjax(inspectionWorkRecordService.insertInspectionWorkRecord(inspectionWorkRecord));
    }

    /**
     * 修改工单记录
     */
    @PreAuthorize("@ss.hasPermi('inspection:workrecord:edit')")
    @Log(title = "工单记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody InspectionWorkRecord inspectionWorkRecord)
    {
        return toAjax(inspectionWorkRecordService.updateInspectionWorkRecord(inspectionWorkRecord));
    }

    /**
     * 删除工单记录
     */
    @PreAuthorize("@ss.hasPermi('inspection:workrecord:remove')")
    @Log(title = "工单记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(inspectionWorkRecordService.deleteInspectionWorkRecordByIds(ids));
    }
}

package com.cmcc.web.controller.system;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.cmcc.common.core.domain.model.LoginUser;
import com.cmcc.common.utils.StringUtils;
import com.cmcc.system.AppInspectionTaskInfoList;
import com.cmcc.system.domain.*;
import com.cmcc.system.service.IInspectionWorkRecordService;
import com.cmcc.system.service.IdGeneratorSerivce;
import com.cmcc.system.service.UnionEnterpriseConfigCacheService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.cmcc.common.annotation.Log;
import com.cmcc.common.core.controller.BaseController;
import com.cmcc.common.core.domain.AjaxResult;
import com.cmcc.common.enums.BusinessType;
import com.cmcc.system.service.IInspectionTaskInfoNewService;
import com.cmcc.common.utils.poi.ExcelUtil;
import com.cmcc.common.core.page.TableDataInfo;

/**
 * 巡检记录新Controller
 * 
 * @author terry
 * @date 2024-04-23
 */
@RestController
@RequestMapping("/inspection/taskinfonew")
public class InspectionTaskInfoNewController extends BaseController
{
    @Autowired
    private IInspectionTaskInfoNewService inspectionTaskInfoNewService;

    @Autowired
    private IInspectionWorkRecordService iInspectionWorkRecordService;

    @Autowired
    private IdGeneratorSerivce idGeneratorSerivce;

    @Autowired
    private UnionEnterpriseConfigCacheService unionEnterpriseConfigCacheService;

    /**
     * 查询巡检记录新列表
     */
    @PreAuthorize("@ss.hasPermi('inspection:taskinfonew:list')")
    @GetMapping("/list")
    public TableDataInfo list(InspectionTaskInfoNew inspectionTaskInfoNew)
    {
        startPage();
        List<InspectionTaskInfoNew> list = inspectionTaskInfoNewService.selectInspectionTaskInfoNewList(inspectionTaskInfoNew);
        return getDataTable(list);
    }

    private InspectionTaskInfoNew changeByAppInfo(AppInspectionTaskInfoNew appInspectionTaskInfoNew, LoginUser loginUser)
    {

        if (appInspectionTaskInfoNew == null)
        {
            return null;
        }
        InspectionTaskInfoNew inspectionTaskInfo = new InspectionTaskInfoNew();

        if (appInspectionTaskInfoNew.getUrls() != null && appInspectionTaskInfoNew.getUrls().size() > 0)
        {
            inspectionTaskInfo.setAttachmentOne(appInspectionTaskInfoNew.getUrls().get(0));
        }

        if (appInspectionTaskInfoNew.getUrls() != null && appInspectionTaskInfoNew.getUrls().size() > 1)
        {
            inspectionTaskInfo.setAttachmentTwo(appInspectionTaskInfoNew.getUrls().get(1));
        }

//        AppUnionConfig appUnionConfigByDeptId = unionEnterpriseConfigCacheService.getAppUnionConfigByDeptId(loginUser.getDeptId());

        inspectionTaskInfo.setDeptId(appInspectionTaskInfoNew.getArea2id());
//        if (loginUser.getUser() != null && loginUser.getUser().getDept() != null)
//        {
        inspectionTaskInfo.setDeptName(appInspectionTaskInfoNew.getArea2());
//        }
        inspectionTaskInfo.setRecordInfo(appInspectionTaskInfoNew.getIntroduction());
        inspectionTaskInfo.setRecordTime(new Date());
        inspectionTaskInfo.setTaskAddress(appInspectionTaskInfoNew.getAdd());
//        inspectionTaskInfo.setTaskType(Long.valueOf(appInspectionTaskInfo.getType()));
        inspectionTaskInfo.setTaskUserId(loginUser.getUserId());
        inspectionTaskInfo.setTaskUserName(loginUser.getUser().getNickName());
        inspectionTaskInfo.setProblem1Flag(String.valueOf(appInspectionTaskInfoNew.getProblem1()));
        inspectionTaskInfo.setProblem2Flag(String.valueOf(appInspectionTaskInfoNew.getProblem2()));
        inspectionTaskInfo.setProblem3Flag(String.valueOf(appInspectionTaskInfoNew.getProblem3()));
        inspectionTaskInfo.setProblem4Flag(String.valueOf(appInspectionTaskInfoNew.getProblem4()));
        inspectionTaskInfo.setProblem5Flag(String.valueOf(appInspectionTaskInfoNew.getProblem5()));
        inspectionTaskInfo.setProblem6Flag(String.valueOf(appInspectionTaskInfoNew.getProblem6()));
        inspectionTaskInfo.setUnionId(appInspectionTaskInfoNew.getArea1id());
//        inspectionTaskInfo.setUnionId(appUnionConfigByDeptId.getArea1id());
        inspectionTaskInfo.setUnionName(appInspectionTaskInfoNew.getArea1());
        if (appInspectionTaskInfoNew.getProblem1Data() != null && 1 == appInspectionTaskInfoNew.getProblem1().intValue() )
        {
            inspectionTaskInfo.setProblem1Add(appInspectionTaskInfoNew.getProblem1Data().getAdd());
            inspectionTaskInfo.setProblem1Info(appInspectionTaskInfoNew.getProblem1Data().getIntroduction());
            if (appInspectionTaskInfoNew.getProblem1Data().getUrls() != null && appInspectionTaskInfoNew.getProblem1Data().getUrls().size() > 0)
            {
                inspectionTaskInfo.setProblem1UrlOne(appInspectionTaskInfoNew.getProblem1Data().getUrls().get(0));
            }

            if (appInspectionTaskInfoNew.getProblem1Data().getUrls() != null && appInspectionTaskInfoNew.getProblem1Data().getUrls().size() > 1)
            {
                inspectionTaskInfo.setProblem1UrlTwo(appInspectionTaskInfoNew.getProblem1Data().getUrls().get(1));
            }
        }
        if (appInspectionTaskInfoNew.getProblem2Data() != null && 1 == appInspectionTaskInfoNew.getProblem2().intValue())
        {
            inspectionTaskInfo.setProblem2Add(appInspectionTaskInfoNew.getProblem2Data().getAdd());
            inspectionTaskInfo.setProblem2Info(appInspectionTaskInfoNew.getProblem2Data().getIntroduction());
            if (appInspectionTaskInfoNew.getProblem2Data().getUrls() != null && appInspectionTaskInfoNew.getProblem2Data().getUrls().size() > 0)
            {
                inspectionTaskInfo.setProblem2UrlOne(appInspectionTaskInfoNew.getProblem2Data().getUrls().get(0));
            }

            if (appInspectionTaskInfoNew.getProblem2Data().getUrls() != null && appInspectionTaskInfoNew.getProblem2Data().getUrls().size() > 1)
            {
                inspectionTaskInfo.setProblem2UrlTwo(appInspectionTaskInfoNew.getProblem2Data().getUrls().get(1));
            }
        }
        if (appInspectionTaskInfoNew.getProblem3Data() != null && 1 == appInspectionTaskInfoNew.getProblem3().intValue() )
        {
            inspectionTaskInfo.setProblem3Add(appInspectionTaskInfoNew.getProblem3Data().getAdd());
            inspectionTaskInfo.setProblem3Info(appInspectionTaskInfoNew.getProblem3Data().getIntroduction());
            if (appInspectionTaskInfoNew.getProblem3Data().getUrls() != null && appInspectionTaskInfoNew.getProblem3Data().getUrls().size() > 0)
            {
                inspectionTaskInfo.setProblem3UrlOne(appInspectionTaskInfoNew.getProblem3Data().getUrls().get(0));
            }

            if (appInspectionTaskInfoNew.getProblem3Data().getUrls() != null && appInspectionTaskInfoNew.getProblem3Data().getUrls().size() > 1)
            {
                inspectionTaskInfo.setProblem1UrlTwo(appInspectionTaskInfoNew.getProblem3Data().getUrls().get(1));
            }
        }
        if ( appInspectionTaskInfoNew.getProblem4Data() != null && 1 == appInspectionTaskInfoNew.getProblem4().intValue())
        {
            inspectionTaskInfo.setProblem4Add(appInspectionTaskInfoNew.getProblem4Data().getAdd());
            inspectionTaskInfo.setProblem4Info(appInspectionTaskInfoNew.getProblem4Data().getIntroduction());
            if (appInspectionTaskInfoNew.getProblem4Data().getUrls() != null && appInspectionTaskInfoNew.getProblem4Data().getUrls().size() > 0)
            {
                inspectionTaskInfo.setProblem4UrlOne(appInspectionTaskInfoNew.getProblem4Data().getUrls().get(0));
            }

            if (appInspectionTaskInfoNew.getProblem4Data().getUrls() != null && appInspectionTaskInfoNew.getProblem4Data().getUrls().size() > 1)
            {
                inspectionTaskInfo.setProblem4UrlTwo(appInspectionTaskInfoNew.getProblem4Data().getUrls().get(1));
            }
        }
        if (appInspectionTaskInfoNew.getProblem5Data() != null && 1 == appInspectionTaskInfoNew.getProblem5().intValue())
        {
            inspectionTaskInfo.setProblem5Add(appInspectionTaskInfoNew.getProblem5Data().getAdd());
            inspectionTaskInfo.setProblem5Info(appInspectionTaskInfoNew.getProblem5Data().getIntroduction());
            if (appInspectionTaskInfoNew.getProblem5Data().getUrls() != null && appInspectionTaskInfoNew.getProblem5Data().getUrls().size() > 0)
            {
                inspectionTaskInfo.setProblem5UrlOne(appInspectionTaskInfoNew.getProblem5Data().getUrls().get(0));
            }

            if (appInspectionTaskInfoNew.getProblem5Data().getUrls() != null && appInspectionTaskInfoNew.getProblem5Data().getUrls().size() > 1)
            {
                inspectionTaskInfo.setProblem5UrlTwo(appInspectionTaskInfoNew.getProblem5Data().getUrls().get(1));
            }
        }
        if (appInspectionTaskInfoNew.getProblem6Data() != null && 1 == appInspectionTaskInfoNew.getProblem6().intValue())
        {
            inspectionTaskInfo.setProblem6Add(appInspectionTaskInfoNew.getProblem6Data().getAdd());
            inspectionTaskInfo.setProblem6Info(appInspectionTaskInfoNew.getProblem6Data().getIntroduction());
            if (appInspectionTaskInfoNew.getProblem6Data().getUrls() != null && appInspectionTaskInfoNew.getProblem6Data().getUrls().size() > 0)
            {
                inspectionTaskInfo.setProblem6UrlOne(appInspectionTaskInfoNew.getProblem6Data().getUrls().get(0));
            }

            if (appInspectionTaskInfoNew.getProblem6Data().getUrls() != null && appInspectionTaskInfoNew.getProblem6Data().getUrls().size() > 1)
            {
                inspectionTaskInfo.setProblem6UrlTwo(appInspectionTaskInfoNew.getProblem6Data().getUrls().get(1));
            }
        }
        return inspectionTaskInfo;
    }

    @GetMapping("/getAppList")
    public AjaxResult getAppList(@RequestParam Integer type)
    {
        if (getLoginUser() == null)
        {
            return AjaxResult.error("获取用户信息失败。");
        }
        InspectionTaskInfoNew queryInfo =new InspectionTaskInfoNew();
        queryInfo.setTaskUserId(getLoginUser().getUserId());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if (1 == type.intValue())
        {
            LocalDate today = LocalDate.now();
            LocalDateTime startOfToday = LocalDateTime.of(today, LocalTime.MIN);
            LocalDateTime endOfToday = LocalDateTime.of(today, LocalTime.MAX);
            queryInfo.setCompareBeginTime(startOfToday.format(formatter));
            queryInfo.setCompareEndTime(endOfToday.format(formatter));
        }
        // 本周
        else if (2 == type.intValue())
        {
            LocalDate thisMonday = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
            LocalDate nextSunday = LocalDate.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
            LocalDateTime startOfToday = LocalDateTime.of(thisMonday, LocalTime.MIN);
            LocalDateTime endOfToday = LocalDateTime.of(nextSunday, LocalTime.MAX);
            queryInfo.setCompareBeginTime(startOfToday.format(formatter));
            queryInfo.setCompareEndTime(endOfToday.format(formatter));
        }
        // 本月
        else if (3 == type.intValue())
        {
//            LocalDate firstDayOfThisMonth = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
//            LocalDate lastDayOfThisMonth = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
//            LocalDateTime startOfToday = LocalDateTime.of(firstDayOfThisMonth, LocalTime.MIN);
//            LocalDateTime endOfToday = LocalDateTime.of(lastDayOfThisMonth, LocalTime.MAX);
//            queryInfo.setCompareBeginTime(startOfToday.format(formatter));
//            queryInfo.setCompareEndTime(endOfToday.format(formatter));
        } else {
            return AjaxResult.error("类型不正确");
        }

        List<InspectionTaskInfoNew> inspectionTaskInfoNews = inspectionTaskInfoNewService.selectInspectionTaskInfoNewList(queryInfo);
        List<AppInspectionTaskInfoList> list = new ArrayList<>();
        if (inspectionTaskInfoNews == null || inspectionTaskInfoNews.size()<1) {
            return AjaxResult.success(list);
        }

        for (InspectionTaskInfoNew a : inspectionTaskInfoNews)
        {
            AppInspectionTaskInfoList aa = new AppInspectionTaskInfoList();
            aa.setId(a.getId());
            aa.setArea2(a.getDeptName());
            aa.setArea1(a.getUnionName());
            aa.setName(a.getTaskUserName());
            aa.setTime(a.getRecordTime());
            List<String> urls = new ArrayList<>();
            if (StringUtils.isNotBlank(a.getAttachmentOne()))
            {
                urls.add(a.getAttachmentOne());
            }

            if (StringUtils.isNotBlank(a.getAttachmentTwo()))
            {
                urls.add(a.getAttachmentTwo());
            }
            aa.setUrls1(urls);
            list.add(aa);
        }
        return AjaxResult.success(list);
    }

    @PostMapping("/appAdd")
    public AjaxResult appAdd(@RequestBody AppInspectionTaskInfoNew appInspectionTaskInfoNew)
    {
        LoginUser loginUser = getLoginUser();
        if (loginUser == null)
        {
            return AjaxResult.error("获取用户登录信息失败");
        }

        InspectionTaskInfoNew inspectionTaskInfo = changeByAppInfo(appInspectionTaskInfoNew,loginUser);
        if (inspectionTaskInfo == null)
        {
            return AjaxResult.error("提交信息为空");
        }


        int ret = inspectionTaskInfoNewService.insertInspectionTaskInfoNew(inspectionTaskInfo);


        // 有问题
//        if ("1".equals(inspectionTaskInfo.getTroubleFlag()))
//        {
            List<InspectionWorkRecord> inspectionWorkRecord = changeByTaskInfo(inspectionTaskInfo);
            if (inspectionWorkRecord != null && inspectionWorkRecord.size() > 0)
            {
                for (InspectionWorkRecord a : inspectionWorkRecord)
                {
                    ret = iInspectionWorkRecordService.insertInspectionWorkRecord(a);
                }
            }
//        }
        return toAjax(ret);
    }

    private List<InspectionWorkRecord> changeByTaskInfo(InspectionTaskInfoNew task)
    {
        List<InspectionWorkRecord> list = new ArrayList<>();
        if ("1".equals(task.getProblem1Flag()))
        {
            InspectionWorkRecord work = new InspectionWorkRecord();
            work.setAttachmentOne(task.getProblem1UrlOne());
            work.setAttachmentTwo(task.getProblem1UrlTwo());
            work.setDeptId(task.getDeptId());
            work.setUnionDeptId(task.getDeptId());
            work.setUnionDeptName(task.getDeptName());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String currentDate = sdf.format(Calendar.getInstance().getTime());
            long l = idGeneratorSerivce.generateId(currentDate + ":" + WorkTypeEnum.治安隐患.getWorkType());
            String format = String.format("%s%s%06d", currentDate, WorkTypeEnum.治安隐患.getWorkType(), l);
            work.setWorkId(format);
            work.setDeptName(task.getDeptName());
            work.setTaskUserId(task.getTaskUserId());
            work.setTaskUserName(task.getTaskUserName());
            work.setTroubleInfo(task.getProblem1Info());
            work.setDetailPosition(task.getProblem1Add());
            work.setTransferFlag("0");
            work.setTaskId(task.getId());
            work.setUnionId(task.getUnionId());
            work.setUnionName(task.getUnionName());
            work.setWorkStatus(WorkStatusEnum.待处理.getWorkStatus());
            work.setWorkType(WorkTypeEnum.治安隐患.getWorkType());
            work.setAddress(task.getTaskAddress());
            work.setAccpetTime(new Date());
            list.add(work);
        }

        if ("1".equals(task.getProblem2Flag()))
        {
            InspectionWorkRecord work = new InspectionWorkRecord();
            work.setAttachmentOne(task.getProblem2UrlOne());
            work.setAttachmentTwo(task.getProblem2UrlTwo());
            work.setTroubleInfo(task.getProblem2Info());
            work.setDetailPosition(task.getProblem2Add());
            work.setUnionDeptId(task.getDeptId());
            work.setUnionDeptName(task.getDeptName());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String currentDate = sdf.format(Calendar.getInstance().getTime());
            long l = idGeneratorSerivce.generateId(currentDate + ":" + WorkTypeEnum.安全生产隐患.getWorkType());
            String format = String.format("%s%s%06d", currentDate, WorkTypeEnum.安全生产隐患.getWorkType(), l);
            work.setWorkStatus(WorkStatusEnum.待处理.getWorkStatus());
            work.setWorkType(WorkTypeEnum.安全生产隐患.getWorkType());

            work.setDeptId(task.getDeptId());
            work.setWorkId(format);
            work.setDeptName(task.getDeptName());
            work.setTaskUserId(task.getTaskUserId());
            work.setTaskUserName(task.getTaskUserName());

            work.setTransferFlag("0");
            work.setTaskId(task.getId());
            work.setUnionId(task.getUnionId());
            work.setUnionName(task.getUnionName());

            work.setAddress(task.getTaskAddress());
            work.setAccpetTime(new Date());
            list.add(work);
        }

        if ("1".equals(task.getProblem3Flag()))
        {
            InspectionWorkRecord work = new InspectionWorkRecord();
            work.setAttachmentOne(task.getProblem3UrlOne());
            work.setAttachmentTwo(task.getProblem3UrlTwo());
            work.setUnionDeptId(task.getDeptId());
            work.setUnionDeptName(task.getDeptName());
            work.setTroubleInfo(task.getProblem3Info());
            work.setDetailPosition(task.getProblem3Add());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String currentDate = sdf.format(Calendar.getInstance().getTime());
            long l = idGeneratorSerivce.generateId(currentDate + ":" + WorkTypeEnum.消防安全隐患.getWorkType());
            String format = String.format("%s%s%06d", currentDate, WorkTypeEnum.消防安全隐患.getWorkType(), l);
            work.setWorkStatus(WorkStatusEnum.待处理.getWorkStatus());
            work.setWorkType(WorkTypeEnum.消防安全隐患.getWorkType());

            work.setDeptId(task.getDeptId());
            work.setWorkId(format);
            work.setDeptName(task.getDeptName());
            work.setTaskUserId(task.getTaskUserId());
            work.setTaskUserName(task.getTaskUserName());

            work.setTransferFlag("0");
            work.setTaskId(task.getId());
            work.setUnionId(task.getUnionId());
            work.setUnionName(task.getUnionName());

            work.setAddress(task.getTaskAddress());
            work.setAccpetTime(new Date());
            list.add(work);
        }


        if ("1".equals(task.getProblem4Flag()))
        {
            InspectionWorkRecord work = new InspectionWorkRecord();
            work.setAttachmentOne(task.getProblem4UrlOne());
            work.setAttachmentTwo(task.getProblem4UrlTwo());
            work.setUnionDeptId(task.getDeptId());
            work.setUnionDeptName(task.getDeptName());
            work.setTroubleInfo(task.getProblem4Info());
            work.setDetailPosition(task.getProblem4Add());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String currentDate = sdf.format(Calendar.getInstance().getTime());
            long l = idGeneratorSerivce.generateId(currentDate + ":" + WorkTypeEnum.矛盾纠纷排除.getWorkType());
            String format = String.format("%s%s%06d", currentDate, WorkTypeEnum.矛盾纠纷排除.getWorkType(), l);
            work.setWorkStatus(WorkStatusEnum.待处理.getWorkStatus());
            work.setWorkType(WorkTypeEnum.矛盾纠纷排除.getWorkType());

            work.setDeptId(task.getDeptId());
            work.setWorkId(format);
            work.setDeptName(task.getDeptName());
            work.setTaskUserId(task.getTaskUserId());
            work.setTaskUserName(task.getTaskUserName());

            work.setTransferFlag("0");
            work.setTaskId(task.getId());
            work.setUnionId(task.getUnionId());
            work.setUnionName(task.getUnionName());

            work.setAddress(task.getTaskAddress());
            work.setAccpetTime(new Date());
            list.add(work);
        }


        if ("1".equals(task.getProblem5Flag()))
        {
            InspectionWorkRecord work = new InspectionWorkRecord();
            work.setAttachmentOne(task.getProblem5UrlOne());
            work.setAttachmentTwo(task.getProblem5UrlTwo());
            work.setUnionDeptId(task.getDeptId());
            work.setUnionDeptName(task.getDeptName());
            work.setTroubleInfo(task.getProblem5Info());
            work.setDetailPosition(task.getProblem5Add());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String currentDate = sdf.format(Calendar.getInstance().getTime());
            long l = idGeneratorSerivce.generateId(currentDate + ":" + WorkTypeEnum.违规经营.getWorkType());
            String format = String.format("%s%s%06d", currentDate, WorkTypeEnum.违规经营.getWorkType(), l);
            work.setWorkStatus(WorkStatusEnum.待处理.getWorkStatus());
            work.setWorkType(WorkTypeEnum.违规经营.getWorkType());

            work.setDeptId(task.getDeptId());
            work.setWorkId(format);
            work.setDeptName(task.getDeptName());
            work.setTaskUserId(task.getTaskUserId());
            work.setTaskUserName(task.getTaskUserName());

            work.setTransferFlag("0");
            work.setTaskId(task.getId());
            work.setUnionId(task.getUnionId());
            work.setUnionName(task.getUnionName());

            work.setAddress(task.getTaskAddress());
            work.setAccpetTime(new Date());
            list.add(work);
        }

        if ("1".equals(task.getProblem6Flag()))
        {
            InspectionWorkRecord work = new InspectionWorkRecord();
            work.setAttachmentOne(task.getProblem6UrlOne());
            work.setAttachmentTwo(task.getProblem6UrlTwo());
            work.setUnionDeptId(task.getDeptId());
            work.setUnionDeptName(task.getDeptName());
            work.setTroubleInfo(task.getProblem6Info());
            work.setDetailPosition(task.getProblem6Add());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String currentDate = sdf.format(Calendar.getInstance().getTime());
            long l = idGeneratorSerivce.generateId(currentDate + ":" + WorkTypeEnum.车辆违规停放.getWorkType());
            String format = String.format("%s%s%06d", currentDate, WorkTypeEnum.车辆违规停放.getWorkType(), l);
            work.setWorkStatus(WorkStatusEnum.待处理.getWorkStatus());
            work.setWorkType(WorkTypeEnum.车辆违规停放.getWorkType());

            work.setDeptId(task.getDeptId());
            work.setWorkId(format);
            work.setDeptName(task.getDeptName());
            work.setTaskUserId(task.getTaskUserId());
            work.setTaskUserName(task.getTaskUserName());

            work.setTransferFlag("0");
            work.setTaskId(task.getId());
            work.setUnionId(task.getUnionId());
            work.setUnionName(task.getUnionName());

            work.setAddress(task.getTaskAddress());
            work.setAccpetTime(new Date());
            list.add(work);
        }
        return list;
    }

    /**
     * 导出巡检记录新列表
     */
    @PreAuthorize("@ss.hasPermi('inspection:taskinfonew:export')")
    @Log(title = "巡检记录新", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, InspectionTaskInfoNew inspectionTaskInfoNew)
    {
        List<InspectionTaskInfoNew> list = inspectionTaskInfoNewService.selectInspectionTaskInfoNewList(inspectionTaskInfoNew);
        ExcelUtil<InspectionTaskInfoNew> util = new ExcelUtil<InspectionTaskInfoNew>(InspectionTaskInfoNew.class);
        util.exportExcel(response, list, "巡检记录新数据");
    }

//    @PreAuthorize("@ss.hasPermi('inspection:taskinfonew:query')")
    @GetMapping(value = "/getAppDetail")
    public AjaxResult getAppDetail(@RequestParam Long id)
    {
        InspectionTaskInfoNew inspectionTaskInfoNew = inspectionTaskInfoNewService.selectInspectionTaskInfoNewById(id);
        if (inspectionTaskInfoNew == null)
        {
            return AjaxResult.error("获取明细失败");
        }
        return success(changeAppDetail(inspectionTaskInfoNew));
    }

    private AppInspectionTaskInfoNew changeAppDetail(InspectionTaskInfoNew a)
    {
        AppInspectionTaskInfoNew appObj = new AppInspectionTaskInfoNew();
        appObj.setAdd(a.getTaskAddress());
        appObj.setArea1(a.getUnionName());
        appObj.setArea1id(a.getUnionId());
        appObj.setArea2(a.getDeptName());
        appObj.setArea2id(a.getDeptId());
        appObj.setIntroduction(a.getRecordInfo());
        appObj.setName(a.getTaskUserName());

        if (a.getProblem1Flag() != null) {
            appObj.setProblem1(Integer.valueOf(a.getProblem1Flag()));
        }
        if (a.getProblem2Flag() != null) {
            appObj.setProblem2(Integer.valueOf(a.getProblem2Flag()));
        }
        if (a.getProblem3Flag() != null) {
            appObj.setProblem3(Integer.valueOf(a.getProblem3Flag()));
        }
        if (a.getProblem4Flag() != null) {
            appObj.setProblem4(Integer.valueOf(a.getProblem4Flag()));
        }
        if (a.getProblem5Flag() != null)
        {
            appObj.setProblem5(Integer.valueOf(a.getProblem5Flag()));
        }
        if (a.getProblem6Flag() != null)
        {
            appObj.setProblem6(Integer.valueOf(a.getProblem6Flag()));
        }
        List<String> urls = new ArrayList<>();
        if (StringUtils.isNotBlank(a.getAttachmentOne()))
        {
            urls.add(a.getAttachmentOne());
        }
        if (StringUtils.isNotBlank(a.getAttachmentTwo()))
        {
            urls.add(a.getAttachmentTwo());
        }
        appObj.setUrls(urls);


        ProblemData problem1Data = new ProblemData();
        problem1Data.setAdd(a.getProblem1Add());
        problem1Data.setIntroduction(a.getProblem1Info());
        List<String> urls1 = new ArrayList<>();
        if (StringUtils.isNotBlank(a.getProblem1UrlOne()))
        {
            urls1.add(a.getProblem1UrlOne());
        }
        if (StringUtils.isNotBlank(a.getProblem1UrlTwo()))
        {
            urls1.add(a.getProblem1UrlTwo());
        }
        problem1Data.setUrls(urls1);
        appObj.setProblem1Data(problem1Data);

        ProblemData problem2Data = new ProblemData();
        problem2Data.setAdd(a.getProblem2Add());
        problem2Data.setIntroduction(a.getProblem2Info());
        List<String> urls2 = new ArrayList<>();
        if (StringUtils.isNotBlank(a.getProblem2UrlOne()))
        {
            urls2.add(a.getProblem2UrlOne());
        }
        if (StringUtils.isNotBlank(a.getProblem2UrlTwo()))
        {
            urls2.add(a.getProblem2UrlTwo());
        }
        problem2Data.setUrls(urls2);
        appObj.setProblem2Data(problem2Data);

        ProblemData problem3Data = new ProblemData();
        problem3Data.setAdd(a.getProblem3Add());
        problem3Data.setIntroduction(a.getProblem3Info());
        List<String> urls3 = new ArrayList<>();
        if (StringUtils.isNotBlank(a.getProblem3UrlOne()))
        {
            urls3.add(a.getProblem3UrlOne());
        }
        if (StringUtils.isNotBlank(a.getProblem3UrlTwo()))
        {
            urls3.add(a.getProblem3UrlTwo());
        }
        problem3Data.setUrls(urls3);
        appObj.setProblem3Data(problem3Data);

        ProblemData problem4Data = new ProblemData();
        problem4Data.setAdd(a.getProblem4Add());
        problem4Data.setIntroduction(a.getProblem4Info());
        List<String> urls4 = new ArrayList<>();
        if (StringUtils.isNotBlank(a.getProblem4UrlOne()))
        {
            urls4.add(a.getProblem4UrlOne());
        }
        if (StringUtils.isNotBlank(a.getProblem4UrlTwo()))
        {
            urls4.add(a.getProblem4UrlTwo());
        }
        problem4Data.setUrls(urls4);
        appObj.setProblem4Data(problem4Data);

        ProblemData problem5Data = new ProblemData();
        problem5Data.setAdd(a.getProblem5Add());
        problem5Data.setIntroduction(a.getProblem5Info());
        List<String> urls5 = new ArrayList<>();
        if (StringUtils.isNotBlank(a.getProblem5UrlOne()))
        {
            urls5.add(a.getProblem5UrlOne());
        }
        if (StringUtils.isNotBlank(a.getProblem5UrlTwo()))
        {
            urls5.add(a.getProblem5UrlTwo());
        }
        problem5Data.setUrls(urls5);
        appObj.setProblem5Data(problem5Data);

        ProblemData problem6Data = new ProblemData();
        problem6Data.setAdd(a.getProblem6Add());
        problem6Data.setIntroduction(a.getProblem6Info());
        List<String> urls6 = new ArrayList<>();
        if (StringUtils.isNotBlank(a.getProblem6UrlOne()))
        {
            urls6.add(a.getProblem6UrlOne());
        }
        if (StringUtils.isNotBlank(a.getProblem6UrlTwo()))
        {
            urls6.add(a.getProblem6UrlTwo());
        }
        problem6Data.setUrls(urls6);
        appObj.setProblem6Data(problem6Data);
        return appObj;
    }

    /**
     * 获取巡检记录新详细信息
     */
    @PreAuthorize("@ss.hasPermi('inspection:taskinfonew:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(inspectionTaskInfoNewService.selectInspectionTaskInfoNewById(id));
    }

    /**
     * 新增巡检记录新
     */
    @PreAuthorize("@ss.hasPermi('inspection:taskinfonew:add')")
    @Log(title = "巡检记录新", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody InspectionTaskInfoNew inspectionTaskInfoNew)
    {
        return toAjax(inspectionTaskInfoNewService.insertInspectionTaskInfoNew(inspectionTaskInfoNew));
    }

    /**
     * 修改巡检记录新
     */
    @PreAuthorize("@ss.hasPermi('inspection:taskinfonew:edit')")
    @Log(title = "巡检记录新", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody InspectionTaskInfoNew inspectionTaskInfoNew)
    {
        return toAjax(inspectionTaskInfoNewService.updateInspectionTaskInfoNew(inspectionTaskInfoNew));
    }

    /**
     * 删除巡检记录新
     */
    @PreAuthorize("@ss.hasPermi('inspection:taskinfonew:remove')")
    @Log(title = "巡检记录新", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(inspectionTaskInfoNewService.deleteInspectionTaskInfoNewByIds(ids));
    }
}

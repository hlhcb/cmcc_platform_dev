package com.cmcc.web.controller.system;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import com.cmcc.common.core.domain.entity.SysRole;
import com.cmcc.common.core.domain.entity.SysUser;
import com.cmcc.common.core.domain.model.LoginUser;
import com.cmcc.common.utils.StringUtils;
import com.cmcc.common.utils.bean.BeanUtils;
import com.cmcc.system.domain.*;
import com.cmcc.system.service.BelongingPlaceInfoCacheService;
import com.cmcc.system.service.ISysPostService;
import com.cmcc.system.service.ITenantInteviewRecordService;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.cmcc.common.annotation.Log;
import com.cmcc.common.core.controller.BaseController;
import com.cmcc.common.core.domain.AjaxResult;
import com.cmcc.common.enums.BusinessType;
import com.cmcc.system.service.IInterviewInfoService;
import com.cmcc.common.utils.poi.ExcelUtil;
import com.cmcc.common.core.page.TableDataInfo;

/**
 * 访问记录Controller
 * 
 * @author terry
 * @date 2024-02-24
 */
@RestController
@RequestMapping("/officer/inteviewinfo")
public class InterviewInfoController extends BaseController
{
    @Autowired
    private IInterviewInfoService interviewInfoService;

    @Autowired
    private ITenantInteviewRecordService iTenantInteviewRecordService;

    @Autowired
    private BelongingPlaceInfoCacheService belongingPlaceInfoCacheService;

    @Autowired
    private ISysPostService postService;

    /**
     * app查询当月数据
     */
    @GetMapping("/countinterview")
    public InterviewCountResult countinterview()
    {
        InterviewCountResult result = new InterviewCountResult();

        Map<String, Object> queryMap = new HashMap<>();
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        String format = today.format(formatter);
        queryMap.put("currMouth", format);
        LoginUser loginUser = getLoginUser();
        if (loginUser == null)
        {
            return result;
        }
        queryMap.put("officerId", loginUser.getUserId());
        List<InterviewCountResult> interviewCountResults = interviewInfoService.selectCountInterview(queryMap);
        if (interviewCountResults == null || interviewCountResults.size() == 0)
        {
            return result;
        }
        InterviewCountResult result1 = interviewCountResults.get(0);
        result1.setInterviewGoal(50);
        return result1;
    }


    private InterviewRankRes getPostRank(int postId, int userId, List<InterviewRankResult> totalList)
    {
        InterviewRankRes res = new InterviewRankRes();
        List<InterviewRankResult> rankList = new ArrayList<>();

        if (totalList == null || totalList.size()==0)
        {
            res.setRankList(rankList);
            return res;
        }
        List<InterviewRankResult> collect = totalList.stream().filter(a -> postId == (a.getPostId()==null?0:a.getPostId().intValue())).collect(Collectors.toList());
        if (collect == null || collect.size() == 0)
        {
            res.setRankList(rankList);
            return res;
        }

        int j = 1;
        int jlastRank = 1;
        int jlastCount = 0;
        for (InterviewRankResult info1 : collect)
        {
            InterviewRankResult info = SerializationUtils.clone(info1);
            if (info.getInterviewCount().intValue() == jlastCount)
            {
                info.setRank(jlastRank);

            }
            else {
                info.setRank(j);
                jlastRank = j;
            }

            if (info.getOfficerId().intValue() == userId)
            {
                res.setRank(info.getRank());
                res.setCount(info.getInterviewCount());
            }
            jlastCount = info.getInterviewCount().intValue();

            if (j <= 10)
            {
                rankList.add(info);
//                break;
            }
            j++;
        }
        res.setRankList(rankList);
        return res;
    }

//    public static void main(String[] args) {
//
//        Integer interviewInfo = new Integer(3);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        if (1 == interviewInfo.intValue())
//        {
//            LocalDate today = LocalDate.now();
//            LocalDateTime startOfToday = LocalDateTime.of(today, LocalTime.MIN);
//            LocalDateTime endOfToday = LocalDateTime.of(today, LocalTime.MAX);
//            System.out.println("开始时间："+startOfToday.format(formatter));
//            System.out.println("结束时间："+endOfToday.format(formatter));
//        }
//        // 本周
//        else if (2 == interviewInfo.intValue())
//        {
//            LocalDate thisMonday = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
//            LocalDate nextSunday = LocalDate.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
//            LocalDateTime startOfToday = LocalDateTime.of(thisMonday, LocalTime.MIN);
//            LocalDateTime endOfToday = LocalDateTime.of(nextSunday, LocalTime.MAX);
//            System.out.println("开始时间："+startOfToday.format(formatter));
//            System.out.println("结束时间："+endOfToday.format(formatter));
//        }
//        // 本月
//        else if (3 == interviewInfo.intValue())
//        {
//            LocalDate firstDayOfThisMonth = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
//            LocalDate lastDayOfThisMonth = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
//            LocalDateTime startOfToday = LocalDateTime.of(firstDayOfThisMonth, LocalTime.MIN);
//            LocalDateTime endOfToday = LocalDateTime.of(lastDayOfThisMonth, LocalTime.MAX);
//            System.out.println("开始时间："+startOfToday.format(formatter));
//            System.out.println("结束时间："+endOfToday.format(formatter));
//        }
//    }
    /**
     * app排行榜
     */
    @PostMapping("/appranklist")
    public AjaxResult appranklist(@RequestBody InterviewInfo interviewInfo)
    {
        InterviewInfo queryInfo = new InterviewInfo();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if (interviewInfo.getQueryTimeFlag() == null )
        {
            if (interviewInfo.getCompareBeginTime() != null )
            {
                queryInfo.setCompareBeginTime(interviewInfo.getCompareBeginTime()+" 00:00:00");
            }
            if (interviewInfo.getCompareEndTime() != null)
            {
                queryInfo.setCompareEndTime(interviewInfo.getCompareEndTime()+" 23:59:59");
            }
        }
        // 今日
        else if (1 == interviewInfo.getQueryTimeFlag().intValue())
        {
            LocalDate today = LocalDate.now();
            LocalDateTime startOfToday = LocalDateTime.of(today, LocalTime.MIN);
            LocalDateTime endOfToday = LocalDateTime.of(today, LocalTime.MAX);
            queryInfo.setCompareBeginTime(startOfToday.format(formatter));
            queryInfo.setCompareEndTime(endOfToday.format(formatter));
        }
        // 本周
        else if (2 == interviewInfo.getQueryTimeFlag().intValue())
        {
            LocalDate thisMonday = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
            LocalDate nextSunday = LocalDate.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
            LocalDateTime startOfToday = LocalDateTime.of(thisMonday, LocalTime.MIN);
            LocalDateTime endOfToday = LocalDateTime.of(nextSunday, LocalTime.MAX);
            queryInfo.setCompareBeginTime(startOfToday.format(formatter));
            queryInfo.setCompareEndTime(endOfToday.format(formatter));
        }
        // 本月
        else if (3 == interviewInfo.getQueryTimeFlag().intValue())
        {
            LocalDate firstDayOfThisMonth = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
            LocalDate lastDayOfThisMonth = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
            LocalDateTime startOfToday = LocalDateTime.of(firstDayOfThisMonth, LocalTime.MIN);
            LocalDateTime endOfToday = LocalDateTime.of(lastDayOfThisMonth, LocalTime.MAX);
            queryInfo.setCompareBeginTime(startOfToday.format(formatter));
            queryInfo.setCompareEndTime(endOfToday.format(formatter));
        }
        else {
            if (interviewInfo.getCompareBeginTime() != null )
            {
                queryInfo.setCompareBeginTime(interviewInfo.getCompareBeginTime()+" 00:00:00");
            }
            if (interviewInfo.getCompareEndTime() != null)
            {
                queryInfo.setCompareEndTime(interviewInfo.getCompareEndTime()+" 23:59:59");
            }
        }

        LoginUser loginUser = getLoginUser();
//
        List<InterviewRankRes> returnObj = new ArrayList<>();
        if (loginUser == null)
        {
            return AjaxResult.success(returnObj);
        }
//        returnObj.setAvater(loginUser.getUser().getAvatar());
//        returnObj.setNickName(loginUser.getUser().getNickName());
        List<InterviewRankResult> interviewRankResults = interviewInfoService.selectRankInterview(queryInfo);
        if (interviewRankResults == null)
        {
            return AjaxResult.success(returnObj);
        }

        int postId = 0;
        List<Long> postIds = postService.selectPostListByUserId(loginUser.getUserId());
        if (postIds != null && postIds.size() > 0)
        {
            postId = postIds.get(0).intValue();
        }
//
        // 遍历职位并计算
        for (PostEnum item : PostEnum.values())
        {
            InterviewRankRes postRank = this.getPostRank(item.getPostId(), loginUser.getUserId().intValue(), interviewRankResults);
            postRank.setAvater(loginUser.getUser().getAvatar());
            postRank.setRankName(item.getPostName());
            postRank.setNickName(loginUser.getUser().getNickName());
            postRank.setShowFlag(item.getPostId().intValue() == postId);
            returnObj.add(postRank);
        }
//

        // 部门排名
        List<InterviewRankResult> collect = interviewRankResults.stream().filter(a -> loginUser.getDeptId().intValue() == a.getDeptId().intValue()).collect(Collectors.toList());
        InterviewRankRes res = new InterviewRankRes();
        res.setRankName("部门");
        res.setShowFlag(true);
        res.setNickName(loginUser.getUser().getNickName());
        res.setAvater(loginUser.getUser().getAvatar());

        if (collect == null || collect.size() == 0)
        {
            returnObj.add(res);
            return  AjaxResult.success(returnObj);
        }

        List<InterviewRankResult> result = new ArrayList<>();
        int i = 1;
        int lastRank = 1;
        int lastCount = 0;
        for (InterviewRankResult info : collect)
        {
            if (info.getInterviewCount().intValue() == lastCount)
            {
                info.setRank(lastRank);
                if (info.getOfficerId().intValue() == loginUser.getUserId().intValue())
                {
                    res.setRank(lastRank);
                    res.setCount(info.getInterviewCount());
                }
            }
            else {
                info.setRank(i);
                if (info.getOfficerId().intValue() == loginUser.getUserId().intValue())
                {
                    res.setRank(i);
                    res.setCount(info.getInterviewCount());
                }
                lastRank = i;
            }
            result.add(info);
            lastCount = info.getInterviewCount().intValue();
            i++;
//            if (i > 20)
//            {
//                break;
//            }
        }

        res.setRankList(result);
        returnObj.add(res);
        return AjaxResult.success(returnObj);
    }

    /**
     * app查询列表
     */
    @PostMapping("/applist")
    public TableDataInfo applist(@RequestBody InterviewInfo interviewInfo)
    {
        startPage();
        InterviewInfo queryInfo = new InterviewInfo();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if (interviewInfo.getQueryTimeFlag() == null )
        {
            if (interviewInfo.getCompareBeginTime() != null )
            {
                queryInfo.setCompareBeginTime(interviewInfo.getCompareBeginTime()+" 00:00:00");
            }
            if (interviewInfo.getCompareEndTime() != null)
            {
                queryInfo.setCompareEndTime(interviewInfo.getCompareEndTime()+" 23:59:59");
            }
        }
        // 今日
        else if (1 == interviewInfo.getQueryTimeFlag().intValue())
        {
            LocalDate today = LocalDate.now();
            LocalDateTime startOfToday = LocalDateTime.of(today, LocalTime.MIN);
            LocalDateTime endOfToday = LocalDateTime.of(today, LocalTime.MAX);
            queryInfo.setCompareBeginTime(startOfToday.format(formatter));
            queryInfo.setCompareEndTime(endOfToday.format(formatter));
        }
        // 本周
        else if (2 == interviewInfo.getQueryTimeFlag().intValue())
        {
            LocalDate thisMonday = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
            LocalDate nextSunday = LocalDate.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
            LocalDateTime startOfToday = LocalDateTime.of(thisMonday, LocalTime.MIN);
            LocalDateTime endOfToday = LocalDateTime.of(nextSunday, LocalTime.MAX);
            queryInfo.setCompareBeginTime(startOfToday.format(formatter));
            queryInfo.setCompareEndTime(endOfToday.format(formatter));
        }
        // 本月
        else if (3 == interviewInfo.getQueryTimeFlag().intValue())
        {
            LocalDate firstDayOfThisMonth = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
            LocalDate lastDayOfThisMonth = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
            LocalDateTime startOfToday = LocalDateTime.of(firstDayOfThisMonth, LocalTime.MIN);
            LocalDateTime endOfToday = LocalDateTime.of(lastDayOfThisMonth, LocalTime.MAX);
            queryInfo.setCompareBeginTime(startOfToday.format(formatter));
            queryInfo.setCompareEndTime(endOfToday.format(formatter));
        }
        else {
            if (interviewInfo.getCompareBeginTime() != null )
            {
                queryInfo.setCompareBeginTime(interviewInfo.getCompareBeginTime()+" 00:00:00");
            }
            if (interviewInfo.getCompareEndTime() != null)
            {
                queryInfo.setCompareEndTime(interviewInfo.getCompareEndTime()+" 23:59:59");
            }
        }


        if(StringUtils.isNotBlank(interviewInfo.getInterviewName()))
        {
            queryInfo.setInterviewName(interviewInfo.getInterviewName());
        }
        if (interviewInfo.getQueryAll() == null || !interviewInfo.getQueryAll()) {
            LoginUser loginUser = getLoginUser();
            queryInfo.setOfficerId(loginUser.getUserId());
        }
        List<InterviewInfo> list = interviewInfoService.selectInterviewInfoList(queryInfo);
        return getDataTable(list);
    }

    @PostMapping("/appupdateinfo")
    public AjaxResult appupdateinfo(@RequestBody SubmitInteviewInfo submitInteviewInfo)
    {
        LoginUser loginUser = getLoginUser();
//        loginUser.getUser().getDept().getParentId()
        if (loginUser == null)
        {
            return new AjaxResult(500, "用户未登录");
        }
        SubmitInteviewInfoBase baseFormData = submitInteviewInfo.getBaseFormData();
        if (baseFormData == null || StringUtils.isBlank(baseFormData.getPhone()))
        {
            return new AjaxResult(500, "提交信息失败");
        }

        InterviewInfo list = interviewInfoService.selectInterviewInfoById(baseFormData.getId());


        InterviewInfo interviewInfo = new InterviewInfo();
        if (list == null)
        {
            return new AjaxResult(500, "记录不存在");
        }

        interviewInfo.setOfficerId(loginUser.getUserId());
        interviewInfo.setId(baseFormData.getId());
//        if (loginUser.getUser().getDept() != null) {
//            if (loginUser.getUser().getDept().getParentId() != null && 100 == loginUser.getUser().getDept().getParentId().intValue())
//            {
//                interviewInfo.setDeptParentId(loginUser.getDeptId());
//            } else {
//                interviewInfo.setDeptParentId(loginUser.getUser().getDept().getParentId());
//            }
//        }
        interviewInfo.setOfficerName(loginUser.getUser().getNickName());
        interviewInfo.setMapAddress(baseFormData.getMapAddress());
        interviewInfo.setLatitude(baseFormData.getLatitude());
        interviewInfo.setLongitude(baseFormData.getLongitude());
        interviewInfo.setInterviewPhone(baseFormData.getPhone());
        interviewInfo.setInterviewName(baseFormData.getName());
        interviewInfo.setInterviewAddr(baseFormData.getAddress());
        interviewInfo.setLocalPoliceStation(baseFormData.getAffiliation1());
        interviewInfo.setBelongingPlace(submitInteviewInfo.getAffiliation2());
        interviewInfo.setContradictionFlag(baseFormData.getProblem());
        interviewInfo.setContradictionText(submitInteviewInfo.getProblemFromData());
        interviewInfo.setInterviewBeginTime(baseFormData.getTimebegin());
        interviewInfo.setInterviewEndTime(baseFormData.getTimeover());
        if (submitInteviewInfo.getUrls() != null && submitInteviewInfo.getUrls().size()>0)
        {
            interviewInfo.setattachmentOne(submitInteviewInfo.getUrls().get(0));
        }
        if (submitInteviewInfo.getUrls() != null && submitInteviewInfo.getUrls().size()>1)
        {
            interviewInfo.setAttachmentTwo(submitInteviewInfo.getUrls().get(1));
        }
        interviewInfo.setSatisfyFlag(baseFormData.getProblem1());
        interviewInfo.setSatisfyText(submitInteviewInfo.getProblemFromData1());
        interviewInfo.setInterviewText(baseFormData.getIntroduction());
        int tenantFlag = 0;
        if (baseFormData != null && baseFormData.getTenantflag()){
            tenantFlag = 1;
        }
        interviewInfo.setTenantFlag(tenantFlag);
        int ret = interviewInfoService.updateInterviewInfo(interviewInfo);



        TenantInteviewRecord tenantInteviewRecord = new TenantInteviewRecord();
        tenantInteviewRecord.setInteviewId(interviewInfo.getId());
        tenantInteviewRecord.setTenantName(submitInteviewInfo.getTenantFormData().getName());
        tenantInteviewRecord.setTenantWechat(submitInteviewInfo.getTenantFormData().getPhone());
        tenantInteviewRecord.setTenantTel(submitInteviewInfo.getTenantFormData().getNumber());

        iTenantInteviewRecordService.updateTenantInteviewRecord(tenantInteviewRecord);
        return toAjax(ret);
    }

    @GetMapping("/appcheckadmin")
    public AjaxResult appcheckadmin()
    {
        SysUser user = getLoginUser().getUser();
        if (user == null)
        {
            return AjaxResult.success(false);
        }
        List<SysRole> roles = user.getRoles();
        if (roles == null)
        {
            return AjaxResult.success(false);
        }
        for (SysRole a : roles)
        {
            if ("xjadmin".equals(a.getRoleKey()) || "admin".equals(a.getRoleKey()))
            {
                return AjaxResult.success(true);
            }
        }
        return AjaxResult.success(false);
    }

    /**
     * app查询详情
     */
    @GetMapping("/appinteviewinfo")
    public AjaxResult appinteviewinfo(@RequestParam long id)
    {
        SubmitInteviewInfo submitInteviewInfo = new SubmitInteviewInfo();
        InterviewInfo interviewInfo = interviewInfoService.selectInterviewInfoById(id);
        if (interviewInfo == null)
        {
            return AjaxResult.error(500,"没有查询到记录");
        }
        SubmitInteviewInfoBase submitInteviewInfoBase = new SubmitInteviewInfoBase();
        TenantFormData tenantFormData = new TenantFormData();
        submitInteviewInfoBase.setId(id);
        submitInteviewInfoBase.setName(interviewInfo.getInterviewName());
        submitInteviewInfoBase.setPhone(interviewInfo.getInterviewPhone());
        submitInteviewInfoBase.setAffiliation1(interviewInfo.getLocalPoliceStation());
        submitInteviewInfoBase.setAffiliation2(interviewInfo.getBelongingPlace());
        submitInteviewInfoBase.setAddress(interviewInfo.getInterviewAddr());
        submitInteviewInfoBase.setMapAddress(interviewInfo.getMapAddress());
        submitInteviewInfoBase.setLatitude(interviewInfo.getLatitude());
        submitInteviewInfoBase.setLongitude(interviewInfo.getLongitude());
        submitInteviewInfoBase.setIntroduction(interviewInfo.getInterviewText());
        submitInteviewInfoBase.setProblem(interviewInfo.getContradictionFlag());
        submitInteviewInfoBase.setProblem1(interviewInfo.getSatisfyFlag());
        submitInteviewInfoBase.setTenantflag(interviewInfo.getTenantFlag().intValue()!=0);
        submitInteviewInfoBase.setTimebegin(interviewInfo.getInterviewBeginTime());
        submitInteviewInfoBase.setTimeover(interviewInfo.getInterviewEndTime());

        submitInteviewInfo.setAffiliation2(interviewInfo.getBelongingPlace());
        submitInteviewInfo.setProblemFromData(interviewInfo.getContradictionText());
        submitInteviewInfo.setProblemFromData1(interviewInfo.getSatisfyText());
        List<String> urls = new ArrayList<>();
        if (StringUtils.isNotBlank(interviewInfo.getattachmentOne()))
        {
            urls.add(interviewInfo.getattachmentOne());
            if (StringUtils.isNotBlank(interviewInfo.getAttachmentTwo()))
            {
                urls.add(interviewInfo.getAttachmentTwo());
            }
        }
        submitInteviewInfo.setUrls(urls);

        TenantInteviewRecord qurey = new TenantInteviewRecord();
        qurey.setInteviewId(id);
        List<TenantInteviewRecord> tenantInteviewRecords = iTenantInteviewRecordService.selectTenantInteviewRecordList(qurey);
       if (tenantInteviewRecords != null && tenantInteviewRecords.size() > 0)
       {
           TenantInteviewRecord record =  tenantInteviewRecords.get(0);
           if (StringUtils.isNotBlank(record.getTenantName()))
           {
               tenantFormData.setName(record.getTenantName());
               tenantFormData.setPhone(record.getTenantWechat());
               tenantFormData.setNumber(record.getTenantTel());
           }

       }

        submitInteviewInfo.setBaseFormData(submitInteviewInfoBase);
        submitInteviewInfo.setTenantFormData(tenantFormData);

        return AjaxResult.success(submitInteviewInfo);
    }

    /**
     * 查询访问记录列表
     */
    @PreAuthorize("@ss.hasPermi('officer:inteviewinfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(InterviewInfo interviewInfo)
    {
        startPage();
        SysUser user = getLoginUser().getUser();
        if (user == null)
        {
            return new TableDataInfo();
        }
        List<SysRole> roles = user.getRoles();
        for (SysRole a : roles)
        {
            if ("xjadmin".equals(a.getRoleKey()) || "admin".equals(a.getRoleKey()))
            {
//                interviewInfo.setDeptParentId(user.getDeptId());
            }
            else if ("areaadmin".equals(a.getRoleKey()))
            {
                interviewInfo.setDeptParentId(user.getDeptId());
            }
            else
            {
                String localPoliceStationName = belongingPlaceInfoCacheService.getLocalPoliceStationName(String.valueOf(user.getDeptId()));
                if (StringUtils.isNotBlank(localPoliceStationName))
                {
                    interviewInfo.setLocalPoliceStation(localPoliceStationName);
                }
            }
        }
        if (StringUtils.isNotBlank(interviewInfo.getLocalPoliceStationId()))
        {
            String localPoliceStationName = belongingPlaceInfoCacheService.getLocalPoliceStationName(interviewInfo.getLocalPoliceStationId());
            if (StringUtils.isNotBlank(localPoliceStationName))
            {
                interviewInfo.setLocalPoliceStation(localPoliceStationName);
            }
        }
        if (StringUtils.isNotBlank(interviewInfo.getBelongingPlaceId()))
        {
            String placeName = belongingPlaceInfoCacheService.getPlaceName(interviewInfo.getBelongingPlaceId());
            if (StringUtils.isNotBlank(placeName))
            {
                interviewInfo.setBelongingPlace(placeName);
            }
        }

//        if (StringUtils.isNotBlank(interviewInfo.getCompareBeginTime()))
//        {
//            String compareBeginTime = interviewInfo.getCompareBeginTime();
//            if (compareBeginTime.length() == 9)
//            {
//                interviewInfo.setCompareBeginTime(compareBeginTime + " 00:00:00");
//            }
//        }
//
//        if (StringUtils.isNotBlank(interviewInfo.getCompareEndTime()))
//        {
//            String compareEndTime = interviewInfo.getCompareEndTime();
//            if (compareEndTime.length() == 9)
//            {
//                interviewInfo.setCompareEndTime(compareEndTime + " 23:59:59");
//            }
//        }
        List<InterviewInfo> list = interviewInfoService.selectInterviewInfoList(interviewInfo);
        return getDataTable(list);
    }

    @GetMapping("/appcountinfo")
    public AjaxResult appgetcountinfo()
    {
        // 获取用户总数
        LoginUser loginUser = getLoginUser();
        if (loginUser == null)
        {
            return new AjaxResult(500, "用户未登录");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Map<String,Object> queryInfo = new HashMap<>();

        queryInfo.put("officerId", loginUser.getUserId());
        //
        LocalDate today = LocalDate.now();
        LocalDateTime startOfToday = LocalDateTime.of(today, LocalTime.MIN);
        LocalDateTime endOfToday = LocalDateTime.of(today, LocalTime.MAX);
        queryInfo.put("todayBeginTime",startOfToday.format(formatter));
        queryInfo.put("todayEndTime",endOfToday.format(formatter));
        // 本周
        LocalDate thisMonday = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate nextSunday =today.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        LocalDateTime startOfweek = LocalDateTime.of(thisMonday, LocalTime.MIN);
        LocalDateTime endOfweek = LocalDateTime.of(nextSunday, LocalTime.MAX);
        queryInfo.put("weekBeginTime",startOfweek.format(formatter));
        queryInfo.put("weekEndTime",endOfweek.format(formatter));
        // 本月
        LocalDate firstDayOfThisMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDayOfThisMonth = today.with(TemporalAdjusters.lastDayOfMonth());
        LocalDateTime startOfMonth = LocalDateTime.of(firstDayOfThisMonth, LocalTime.MIN);
        LocalDateTime endOfMonth = LocalDateTime.of(lastDayOfThisMonth, LocalTime.MAX);
        queryInfo.put("monthBeginTime",startOfMonth.format(formatter));
        queryInfo.put("monthEndTime",endOfMonth.format(formatter));
        AppCountInfo appCountInfo = interviewInfoService.selectAppCountInfo(queryInfo);
        return AjaxResult.success(appCountInfo);
    }

    @PostMapping("/submitinteview")
    public AjaxResult submitinteview(@RequestBody SubmitInteviewInfo submitInteviewInfo){

        LoginUser loginUser = getLoginUser();
        if (loginUser == null || loginUser.getUser() == null)
        {
            return new AjaxResult(500, "用户未登录");
        }
        SubmitInteviewInfoBase baseFormData = submitInteviewInfo.getBaseFormData();
        if (baseFormData == null || StringUtils.isBlank(baseFormData.getPhone()))
        {
            return new AjaxResult(500, "提交信息失败");
        }

        InterviewInfo queryInfo = new InterviewInfo();
        queryInfo.setInterviewPhone(baseFormData.getPhone());
        List<InterviewInfo> list = interviewInfoService.selectInterviewInfoList(queryInfo);


        InterviewInfo interviewInfo = new InterviewInfo();
        if (list != null && list.size()>0)
        {
//            return new AjaxResult(500, "手机号已存在");
            interviewInfo.setRepeatFlag(list.size()+1);
            InterviewInfo updateInfo = new InterviewInfo();
            updateInfo.setInterviewPhone(baseFormData.getPhone());
            updateInfo.setRepeatFlag(list.size()+1);
            interviewInfoService.updateInterviewInfoRepeatFlag(updateInfo);
        }
        else {
            interviewInfo.setRepeatFlag(1);
        }
        if (loginUser.getUser().getDept() != null) {
            if (loginUser.getUser().getDept().getParentId() != null && loginUser.getUser().getDept().getParentId().intValue() == 100 )
            {
                interviewInfo.setDeptParentId(loginUser.getDeptId());
            }else {
                interviewInfo.setDeptParentId(loginUser.getUser().getDept().getParentId());
            }
        }
        interviewInfo.setOfficerId(loginUser.getUserId());
        interviewInfo.setOfficerName(loginUser.getUser().getNickName());
        interviewInfo.setMapAddress(baseFormData.getMapAddress());
        interviewInfo.setLatitude(baseFormData.getLatitude());
        interviewInfo.setLongitude(baseFormData.getLongitude());
        interviewInfo.setInterviewPhone(baseFormData.getPhone());
        interviewInfo.setInterviewName(baseFormData.getName());
        interviewInfo.setInterviewAddr(baseFormData.getAddress());
        interviewInfo.setLocalPoliceStation(baseFormData.getAffiliation1());
        interviewInfo.setBelongingPlace(submitInteviewInfo.getAffiliation2());
//        interviewInfo.setBelongingPlace(baseFormData.getAffiliation2());
        interviewInfo.setContradictionFlag(baseFormData.getProblem());
        interviewInfo.setContradictionText(submitInteviewInfo.getProblemFromData());
        interviewInfo.setInterviewBeginTime(baseFormData.getTimebegin());
        interviewInfo.setInterviewEndTime(baseFormData.getTimeover());
        if (submitInteviewInfo.getUrls() != null && submitInteviewInfo.getUrls().size()>0)
        {
            interviewInfo.setattachmentOne(submitInteviewInfo.getUrls().get(0));
        }
        if (submitInteviewInfo.getUrls() != null && submitInteviewInfo.getUrls().size()>1)
        {
            interviewInfo.setAttachmentTwo(submitInteviewInfo.getUrls().get(1));
        }
        interviewInfo.setSatisfyFlag(baseFormData.getProblem1());
        interviewInfo.setSatisfyText(submitInteviewInfo.getProblemFromData1());
        interviewInfo.setInterviewText(baseFormData.getIntroduction());
        int tenantFlag = 0;
        if (baseFormData != null && baseFormData.getTenantflag()){
            tenantFlag = 1;
        }
        interviewInfo.setTenantFlag(tenantFlag);
        int ret = interviewInfoService.insertInterviewInfo(interviewInfo);

        TenantInteviewRecord tenantInteviewRecord = new TenantInteviewRecord();
        tenantInteviewRecord.setInteviewId(interviewInfo.getId());
        tenantInteviewRecord.setTenantName(submitInteviewInfo.getTenantFormData().getName());
        tenantInteviewRecord.setTenantWechat(submitInteviewInfo.getTenantFormData().getPhone());
        tenantInteviewRecord.setTenantTel(submitInteviewInfo.getTenantFormData().getNumber());

        iTenantInteviewRecordService.insertTenantInteviewRecord(tenantInteviewRecord);
        return toAjax(ret);
    }

    @GetMapping("/totalinteviewlist")
    public TableDataInfo totalinteviewlist()
    {
        LoginUser loginUser = getLoginUser();
        InterviewInfo interviewInfo = new InterviewInfo();
        interviewInfo.setOfficerId(loginUser.getUserId());
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateNowStr = sdf.format(d)+" 00:00:00";
        interviewInfo.setCompareBeginTime(dateNowStr);
        List<InterviewInfo> list = interviewInfoService.selectInterviewInfoList(interviewInfo);

        List<TodayInteview> today = new ArrayList<>();
        if (list != null && list.size() > 0) {
            int i = 0;
            for (InterviewInfo a : list)
            {
                if (i > 1) {
                    break;
                }
                i++;
                TodayInteview todayInteview = new TodayInteview();
                todayInteview.setId(a.getId());
                todayInteview.setAddress(a.getInterviewAddr());
                todayInteview.setName(a.getInterviewName());
                if (a.getInterviewBeginTime() != null) {
                    String s = new SimpleDateFormat("HH:mm").format(a.getInterviewBeginTime()).toString();
                    todayInteview.setDate(s);
                    if (a.getInterviewEndTime() != null)
                    {
                        Instant instant = a.getInterviewBeginTime().toInstant();
                        LocalDateTime beginDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

                        Instant instant1 = a.getInterviewEndTime().toInstant();
                        LocalDateTime endDateTime = LocalDateTime.ofInstant(instant1, ZoneId.systemDefault());

                        Duration duration = Duration.between(beginDateTime, endDateTime);
                        long minutes = duration.toMinutes();
                        String format = String.format("%dmin", minutes);
                        todayInteview.setTime(format);
                    }
                }
                List<String> urlList = new ArrayList<>();
                if (StringUtils.isNotBlank(a.getattachmentOne())){
                    urlList.add(a.getattachmentOne());
                }
                if (StringUtils.isNotBlank(a.getAttachmentTwo())){
                    urlList.add(a.getAttachmentTwo());
                }
                todayInteview.setUrl(urlList);
                today.add(todayInteview);
            }
        }
        return getDataTable(today);
    }

    /**
     * 导出访问记录列表
     */
    @PreAuthorize("@ss.hasPermi('officer:inteviewinfo:export')")
    @Log(title = "访问记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, InterviewInfo interviewInfo)
    {
        SysUser user = getLoginUser().getUser();
        if (user == null)
        {
            return;
        }
        List<SysRole> roles = user.getRoles();
        for (SysRole a : roles)
        {
            if ("areaadmin".equals(a.getRoleKey()))
            {
                interviewInfo.setDeptParentId(user.getDeptId());
                break;
            }
        }
        List<InterviewInfo> list = interviewInfoService.selectInterviewInfoList(interviewInfo);
        ExcelUtil<InterviewInfo> util = new ExcelUtil<InterviewInfo>(InterviewInfo.class);
        util.exportExcel(response, list, "访问记录数据");
    }

    /*
    *  判断是否晚上18点以后
    */
    public static boolean isAfterNoon(Date date) {
        if (date == null) {
            return false;
        }

        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        Date now =null;
        Date endTime = null;
        try {
            now = df.parse(df.format(date));
            endTime = df.parse("18:00");
            Calendar nowdata = Calendar.getInstance();
            nowdata.setTime(now);

            Calendar end = Calendar.getInstance();
            end.setTime(endTime);

            if (nowdata.after(end)){
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }

       return false;
    }

    @PreAuthorize("@ss.hasPermi('officer:inteviewinfo:export')")
    @Log(title = "访问记录", businessType = BusinessType.EXPORT)
    @PostMapping("/exportExtend")
    public void exportExtend(HttpServletResponse response, InterviewInfo interviewInfo)
    {
        SysUser user = getLoginUser().getUser();
        if (user == null)
        {
            return;
        }
        List<SysRole> roles = user.getRoles();
        for (SysRole a : roles)
        {
            if ("areaadmin".equals(a.getRoleKey()))
            {
                interviewInfo.setDeptParentId(user.getDeptId());
                break;
            }
        }
        List<InterviewInfo> list = interviewInfoService.selectInterviewInfoList(interviewInfo);
        List<InterviewInfo> collect = list.stream().filter((InterviewInfo a) -> isAfterNoon(a.getInterviewBeginTime())).collect(Collectors.toList());
        ExcelUtil<InterviewInfo> util = new ExcelUtil<InterviewInfo>(InterviewInfo.class);
        util.exportExcel(response, collect, "访问记录数据");
    }

    @PreAuthorize("@ss.hasPermi('officer:inteviewinfo:export')")
    @Log(title = "访问记录", businessType = BusinessType.EXPORT)
    @PostMapping("/exportpro")
    public void exportpro(HttpServletResponse response, InterviewInfo interviewInfo)
    {

        SysUser user = getLoginUser().getUser();
        if (user == null)
        {
            return;
        }
        List<SysRole> roles = user.getRoles();
        for (SysRole a : roles)
        {
            if ("areaadmin".equals(a.getRoleKey()))
            {
                interviewInfo.setDeptParentId(user.getDeptId());
                break;
            }
        }
        List<InterviewInfo> list = interviewInfoService.selectInterviewInfoList(interviewInfo);
        ExcelUtil<ExportInterviewInfo> util = new ExcelUtil<ExportInterviewInfo>(ExportInterviewInfo.class);
//        util.exportExcel(response, list, "访问记录数据");

        List<ExportInterviewInfo> exportList = new ArrayList<>();
        if (list != null && list.size() > 0)
        {
            for (InterviewInfo obj : list)
            {
                ExportInterviewInfo dest = new ExportInterviewInfo();
                BeanUtils.copyBeanProp( dest, obj );
                dest.setStreesName(StressEnum.getStressByLocation(dest.getLocalPoliceStation()));
                exportList.add(dest);
            }
        }

        util.exportExcel(response, exportList, "访问记录数据");

    }

    /**
     * 获取访问记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('officer:inteviewinfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(interviewInfoService.selectInterviewInfoById(id));
    }

    /**
     * 新增访问记录
     */
    @PreAuthorize("@ss.hasPermi('officer:inteviewinfo:add')")
    @Log(title = "访问记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody InterviewInfo interviewInfo)
    {
        return toAjax(interviewInfoService.insertInterviewInfo(interviewInfo));
    }

    /**
     * 修改访问记录
     */
    @PreAuthorize("@ss.hasPermi('officer:inteviewinfo:edit')")
    @Log(title = "访问记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody InterviewInfo interviewInfo)
    {
        return toAjax(interviewInfoService.updateInterviewInfo(interviewInfo));
    }

    /**
     * 删除访问记录
     */
    @PreAuthorize("@ss.hasPermi('officer:inteviewinfo:remove')")
    @Log(title = "访问记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(interviewInfoService.deleteInterviewInfoByIds(ids));
    }
}

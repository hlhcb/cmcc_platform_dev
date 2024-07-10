package com.cmcc.web.controller.system;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import javax.servlet.http.HttpServletResponse;

import com.cmcc.common.core.domain.model.LoginUser;
import com.cmcc.common.utils.StringUtils;
import com.cmcc.system.domain.*;
import com.cmcc.system.service.ICmccEnterpriseInfoService;
import com.cmcc.system.service.ICmccInterviewNetService;
import com.cmcc.system.service.ICmccInterviewOtherService;
import com.cmcc.system.service.impl.CmccEnterpriseInfoServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.cmcc.common.annotation.Log;
import com.cmcc.common.core.controller.BaseController;
import com.cmcc.common.core.domain.AjaxResult;
import com.cmcc.common.enums.BusinessType;
import com.cmcc.system.service.ICmccInterviewBaseService;
import com.cmcc.common.utils.poi.ExcelUtil;
import com.cmcc.common.core.page.TableDataInfo;

/**
 * 用户走访Controller
 * 
 * @author terry
 * @date 2024-05-17
 */
@RestController
@RequestMapping("/customer/interviewbase")
public class CmccInterviewBaseController extends BaseController
{
    @Autowired
    private ICmccInterviewBaseService cmccInterviewBaseService;

    @Autowired
    private ICmccInterviewNetService cmccInterviewNetService;

    @Autowired
    private ICmccInterviewOtherService cmccInterviewOtherService;

    @Autowired
    private ICmccEnterpriseInfoService cmccEnterpriseInfoService;


    @GetMapping("/getAppInfoNew")
    public AjaxResult getAppInfoNew(@RequestParam Long enterpriseId){

        Long aLong = cmccInterviewBaseService.selectLeastByEnterprise(enterpriseId);
        if (aLong == null) {
            CmccEnterpriseInfo cmccEnterpriseInfo = cmccEnterpriseInfoService.selectCmccEnterpriseInfoById(enterpriseId);
            if (cmccEnterpriseInfo == null) {
                return AjaxResult.success();
            }
            AppCmccInterviewInfo app = new AppCmccInterviewInfo();
            app.setAddrInfo(cmccEnterpriseInfo.getEnterpriseAddr());
            app.setCustomerTel(cmccEnterpriseInfo.getCustomerTel());
            app.setCustomerPosition(cmccEnterpriseInfo.getCustomerPosition());
            app.setCustomerName(cmccEnterpriseInfo.getCustomerName());
            return AjaxResult.success(app);
        }
       return this.getAppInfo(aLong);


    }

    @GetMapping("/getAppInfo")
    public AjaxResult getAppInfo(@RequestParam Long id){

        LoginUser loginUser = getLoginUser();
        CmccInterviewBase cmccInterviewBase = cmccInterviewBaseService.selectCmccInterviewBaseById(id);

        if (cmccInterviewBase == null) {
            return error("未找到查询结果");
        }

        if (loginUser == null){
            return error("未获取到登录信息");
        }

        if (loginUser.getDeptId() != null && loginUser.getDeptId().intValue() != cmccInterviewBase.getDeptId().intValue()){
            return error("不是你的走访记录");
        }
        AppCmccInterviewInfo appCmccInterviewInfo = new AppCmccInterviewInfo();
        appCmccInterviewInfo.setAddrInfo(cmccInterviewBase.getAddrInfo());
        appCmccInterviewInfo.setBusinessName(cmccInterviewBase.getBusinessName());
        appCmccInterviewInfo.setCustomerName(cmccInterviewBase.getCustomerName());
        appCmccInterviewInfo.setCustomerPosition(cmccInterviewBase.getCustomerPosition());
        appCmccInterviewInfo.setCustomerTel(cmccInterviewBase.getCustomerTel());
        appCmccInterviewInfo.setInformation(cmccInterviewBase.getInformation());
        appCmccInterviewInfo.setLatitude(cmccInterviewBase.getLatitude());
        appCmccInterviewInfo.setLongitude(cmccInterviewBase.getLongitude());
        appCmccInterviewInfo.setMapAddress(cmccInterviewBase.getMapAddress());
        appCmccInterviewInfo.setSceneType(cmccInterviewBase.getSceneType());
        appCmccInterviewInfo.setTimebegin(cmccInterviewBase.getTimeBegin());
        appCmccInterviewInfo.setTimeover(cmccInterviewBase.getTimeOver());
        appCmccInterviewInfo.setGroupId(cmccInterviewBase.getGroupId());
        appCmccInterviewInfo.setEnterpriseId(cmccInterviewBase.getEnterpriseId());
        appCmccInterviewInfo.setGroupName(cmccInterviewBase.getGroupName());
        appCmccInterviewInfo.setRemarks(cmccInterviewBase.getRemarks());
        List<String> urls = new ArrayList<>();
        if (StringUtils.isNotBlank(cmccInterviewBase.getAttachmentOne())){
            urls.add(cmccInterviewBase.getAttachmentOne());
        }
        if (StringUtils.isNotBlank(cmccInterviewBase.getAttachmentTwo())){
            urls.add(cmccInterviewBase.getAttachmentTwo());
        }
        appCmccInterviewInfo.setUrls(urls);

        if (cmccInterviewBase.getCmccInterviewNetList() != null && cmccInterviewBase.getCmccInterviewNetList().size() > 0) {
            List<CmccInterviewNet> cmccInterviewNetList = cmccInterviewBase.getCmccInterviewNetList();
            List<AppCmccInterviewInfoNet> netFormData = new ArrayList<>();
            for (CmccInterviewNet cmccInterviewNet : cmccInterviewNetList) {
                AppCmccInterviewInfoNet net = new AppCmccInterviewInfoNet();
                net.setEndTime(cmccInterviewNet.getEndTime());
                net.setIspName(cmccInterviewNet.getIspName());
                net.setNetPrice(cmccInterviewNet.getNetPrice());
                net.setNetSpeed(cmccInterviewNet.getNetSpeed());
                net.setNetType(cmccInterviewNet.getNetType());
                netFormData.add(net);
            }
            appCmccInterviewInfo.setNetFormData(netFormData);
        }

        CmccInterviewOther otherQuery = new CmccInterviewOther();
        otherQuery.setInterviewId(id);
        List<CmccInterviewOther> cmccInterviewOthers = cmccInterviewOtherService.selectCmccInterviewOtherList(otherQuery);
        if (cmccInterviewOthers != null && cmccInterviewOthers.size() > 0) {
            appCmccInterviewInfo.setProblem(1);
            List<AppCmccInterviewInfoOther> otherList = new ArrayList<>();
            for (CmccInterviewOther cmccOter : cmccInterviewOthers){
                AppCmccInterviewInfoOther other = new AppCmccInterviewInfoOther();
                other.setBusinessDetail(cmccOter.getBusinessDetail());
                other.setBusinessType(cmccOter.getBusinessType());
                otherList.add(other);
            }
            appCmccInterviewInfo.setNeedFormData(otherList);
        } else {
            appCmccInterviewInfo.setProblem(0);
        }
        return success(appCmccInterviewInfo);
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
        AppCountInfo appCountInfo = cmccInterviewBaseService.selectAppCountInfo(queryInfo);
        return AjaxResult.success(appCountInfo);
    }


    @PostMapping("/applist")
    public TableDataInfo applist(@RequestBody CmccInterviewBase cmccInterviewBase)
    {
        LoginUser loginUser = getLoginUser();
        if (loginUser == null){
            getDataTable(new ArrayList<>());
        }
        startPage();
        CmccInterviewBase queryInfo = new CmccInterviewBase();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if (cmccInterviewBase.getQueryTimeFlag() != null )
        {
            Map<String, Object> map = new HashMap<>();
            // 今日
            if (1 == cmccInterviewBase.getQueryTimeFlag().intValue())
            {
                LocalDate today = LocalDate.now();
                LocalDateTime startOfToday = LocalDateTime.of(today, LocalTime.MIN);
                LocalDateTime endOfToday = LocalDateTime.of(today, LocalTime.MAX);
                map.put("beginRecordDate",startOfToday.format(formatter));
                map.put("endRecordDate",endOfToday.format(formatter));
            }
            // 本周
            else if (2 == cmccInterviewBase.getQueryTimeFlag().intValue())
            {
                LocalDate thisMonday = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
                LocalDate nextSunday = LocalDate.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
                LocalDateTime startOfToday = LocalDateTime.of(thisMonday, LocalTime.MIN);
                LocalDateTime endOfToday = LocalDateTime.of(nextSunday, LocalTime.MAX);
                map.put("beginRecordDate",startOfToday.format(formatter));
                map.put("endRecordDate",endOfToday.format(formatter));
            }
            // 本月
            else if (3 == cmccInterviewBase.getQueryTimeFlag().intValue())
            {
                LocalDate firstDayOfThisMonth = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
                LocalDate lastDayOfThisMonth = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
                LocalDateTime startOfToday = LocalDateTime.of(firstDayOfThisMonth, LocalTime.MIN);
                LocalDateTime endOfToday = LocalDateTime.of(lastDayOfThisMonth, LocalTime.MAX);
                map.put("beginRecordDate",startOfToday.format(formatter));
                map.put("endRecordDate",endOfToday.format(formatter));
            }
            else {
//                return getDataTable(new ArrayList<>());
            }
            queryInfo.setParams(map);
        }

        if (cmccInterviewBase.getQueryAll() == null || !cmccInterviewBase.getQueryAll())
        {
            queryInfo.setUserId(loginUser.getUserId());
        }

        if (cmccInterviewBase.getEnterpriseId() != null) {
            queryInfo.setEnterpriseId(cmccInterviewBase.getEnterpriseId());
        }

        if (StringUtils.isNotBlank(cmccInterviewBase.getInterviewName())) {
            queryInfo.setInterviewName(cmccInterviewBase.getInterviewName());
        }
        List<CmccInterviewBase> cmccInterviewBases = cmccInterviewBaseService.selectCmccInterviewBaseList(queryInfo);
        return getDataTable(cmccInterviewBases);
    }

    @GetMapping("/appGetTodayList")
    public AjaxResult appGetTodayList()
    {
        LoginUser loginUser = getLoginUser();
        if (loginUser == null) {
            return  error("获取登录用户失败");
        }

        CmccInterviewBase query = new CmccInterviewBase();
        query.setUserId(loginUser.getUserId());
        Map<String, Object> params = new HashMap<>();
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        params.put("likeRecordDate",formattedDate);
        query.setParams(params);
        List<CmccInterviewBase> list = cmccInterviewBaseService.selectCmccInterviewBaseList(query);
        List<AppInterviewListVo> today = new ArrayList<>();
        if (list != null && list.size() > 0) {
            int i = 0;
            for (CmccInterviewBase a : list)
            {
                if (i > 1) {
                    break;
                }
                i++;
                AppInterviewListVo todayInteview = new AppInterviewListVo();
                todayInteview.setId(a.getId());
                todayInteview.setAddress(a.getAddrInfo());
                todayInteview.setName(a.getUserName());
                todayInteview.setTime(a.getUseTime().toString());
                String s = new SimpleDateFormat("HH:mm").format(a.getRecordDate()).toString();
                todayInteview.setDate(s);
                List<String> urlList = new ArrayList<>();
                if (StringUtils.isNotBlank(a.getAttachmentOne())){
                    urlList.add(a.getAttachmentOne());
                }
                if (StringUtils.isNotBlank(a.getAttachmentTwo())){
                    urlList.add(a.getAttachmentTwo());
                }
                todayInteview.setUrl(urlList);
                today.add(todayInteview);
            }
        }
        return success(today);
    }


    @PostMapping("/appAdd")
    public AjaxResult appAdd(@RequestBody AppCmccInterviewInfo info)
    {
        LoginUser loginUser = getLoginUser();
        if (loginUser == null) {
            return  error("获取登录用户失败");
        }
        if (info == null)
        {
            return  error("提交信息为空。");
        }
        CmccInterviewBase base = new CmccInterviewBase();
        base.setAddrInfo(info.getAddrInfo());
        if (loginUser.getUser() != null && loginUser.getUser().getDept() != null){
            base.setAreaName(loginUser.getUser().getDept().getDeptName());
        }
        if (info.getUrls() != null && info.getUrls().size() > 0){
            base.setAttachmentOne(info.getUrls().get(0));
            if (info.getUrls().size() > 1) {
                base.setAttachmentTwo(info.getUrls().get(1));
            }
        }
        base.setDeptId(loginUser.getDeptId());
        base.setUserId(loginUser.getUserId());
        base.setUserName(loginUser.getUser().getNickName());
        base.setSceneType(info.getSceneType());
        base.setBusinessName(info.getBusinessName());
        base.setCustomerName(info.getCustomerName());
        base.setCustomerTel(info.getCustomerTel());
        base.setCustomerPosition(info.getCustomerPosition());
        base.setInformation(info.getInformation());
        base.setRecordDate(new Date());
        base.setTimeBegin(info.getTimebegin());
        base.setTimeOver(info.getTimeover());
        base.setEnterpriseId(info.getEnterpriseId());
        base.setGroupId(info.getGroupId());
        base.setRemarks(info.getRemarks());
        if (info.getTimebegin() != null && info.getTimeover() != null) {
            Instant instant = info.getTimebegin().toInstant();
            LocalDateTime beginDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

            Instant instant1 = info.getTimeover().toInstant();
            LocalDateTime endDateTime = LocalDateTime.ofInstant(instant1, ZoneId.systemDefault());

            Duration duration = Duration.between(beginDateTime, endDateTime);
            long minutes = duration.toMinutes();
            base.setUseTime(minutes);
        }
        base.setMapAddress(info.getMapAddress());
        base.setLatitude(info.getLatitude());
        base.setLongitude(info.getLongitude());
        base.setGroupName(info.getGroupName());

        // todo: 缺少关联表
        base.setDirection("");
        base.setWorkNo(loginUser.getUsername());

        int ret = cmccInterviewBaseService.insertCmccInterviewBase(base);
        if (info.getNetFormData() != null && info.getNetFormData().size() > 0)
        {
            List<CmccInterviewNet> addNetList = new ArrayList<>();
            List<AppCmccInterviewInfoNet> netFormData = info.getNetFormData();
            for (AppCmccInterviewInfoNet net: netFormData)
            {
                CmccInterviewNet cmccInterviewNet = new CmccInterviewNet();
                cmccInterviewNet.setEndTime(net.getEndTime());
                cmccInterviewNet.setInterviewId(base.getId());
                cmccInterviewNet.setIspName(net.getIspName());
                cmccInterviewNet.setNetPrice(net.getNetPrice());
                cmccInterviewNet.setNetSpeed(net.getNetSpeed());
                cmccInterviewNet.setNetType(net.getNetType());
                ret = cmccInterviewNetService.insertCmccInterviewNet(cmccInterviewNet);
            }
        }

        if (info.getNeedFormData() != null && info.getNeedFormData().size() > 0){
            List<AppCmccInterviewInfoOther> needFormData = info.getNeedFormData();
            for (AppCmccInterviewInfoOther need : needFormData){
                CmccInterviewOther cmccInterviewOther = new CmccInterviewOther();
                cmccInterviewOther.setInterviewId(base.getId());
                cmccInterviewOther.setBusinessDetail(need.getBusinessDetail());
                cmccInterviewOther.setBusinessType(need.getBusinessType());
                cmccInterviewOtherService.insertCmccInterviewOther(cmccInterviewOther);
            }
        }
        if (base.getEnterpriseId() != null) {
            CmccEnterpriseInfo cmccEnterpriseInfo = new CmccEnterpriseInfo();
            cmccEnterpriseInfo.setId(base.getEnterpriseId());
            cmccEnterpriseInfo.setUpdateTime(new Date());
            cmccEnterpriseInfo.setUpdateUserId(loginUser.getUserId());
            cmccEnterpriseInfo.setUpdateUserName(loginUser.getUser().getNickName());
            cmccEnterpriseInfo.setCustomerName(base.getCustomerName());
            cmccEnterpriseInfo.setCustomerTel(base.getCustomerTel());
            cmccEnterpriseInfo.setCustomerPosition(base.getCustomerPosition());
            cmccEnterpriseInfoService.updateCmccEnterpriseInfo(cmccEnterpriseInfo);
        }
        return success(ret);
    }


    /**
     * 查询用户走访列表
     */
    @PreAuthorize("@ss.hasPermi('customer:interviewbase:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmccInterviewBase cmccInterviewBase)
    {
        startPage();
        List<CmccInterviewBase> list = cmccInterviewBaseService.selectCmccInterviewBaseList(cmccInterviewBase);
        return getDataTable(list);
    }

    /**
     * 导出用户走访列表
     */
    @PreAuthorize("@ss.hasPermi('customer:interviewbase:export')")
    @Log(title = "用户走访", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmccInterviewBase cmccInterviewBase)
    {
        List<CmccInterviewBase> list = cmccInterviewBaseService.selectCmccInterviewBaseList(cmccInterviewBase);
        ExcelUtil<CmccInterviewBase> util = new ExcelUtil<CmccInterviewBase>(CmccInterviewBase.class);
        util.exportExcel(response, list, "用户走访数据");
    }

    /**
     * 获取用户走访详细信息
     */
    @PreAuthorize("@ss.hasPermi('customer:interviewbase:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(cmccInterviewBaseService.selectCmccInterviewBaseById(id));
    }

    /**
     * 新增用户走访
     */
    @PreAuthorize("@ss.hasPermi('customer:interviewbase:add')")
    @Log(title = "用户走访", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmccInterviewBase cmccInterviewBase)
    {
        return toAjax(cmccInterviewBaseService.insertCmccInterviewBase(cmccInterviewBase));
    }

    /**
     * 修改用户走访
     */
    @PreAuthorize("@ss.hasPermi('customer:interviewbase:edit')")
    @Log(title = "用户走访", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmccInterviewBase cmccInterviewBase)
    {
        return toAjax(cmccInterviewBaseService.updateCmccInterviewBase(cmccInterviewBase));
    }

    /**
     * 删除用户走访
     */
    @PreAuthorize("@ss.hasPermi('customer:interviewbase:remove')")
    @Log(title = "用户走访", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(cmccInterviewBaseService.deleteCmccInterviewBaseByIds(ids));
    }
}

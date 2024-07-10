package com.cmcc.web.controller.system;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import com.cmcc.common.utils.StringUtils;
import com.cmcc.system.domain.*;
import com.cmcc.system.domain.vo.ContactDetail;
import com.cmcc.system.service.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.cmcc.common.annotation.Log;
import com.cmcc.common.core.controller.BaseController;
import com.cmcc.common.core.domain.AjaxResult;
import com.cmcc.common.enums.BusinessType;
import com.cmcc.common.utils.poi.ExcelUtil;
import com.cmcc.common.core.page.TableDataInfo;

/**
 * 返乡人员明细Controller
 * 
 * @author terry
 * @date 2023-12-24
 */
@RestController
@RequestMapping("/springmarketing/returndetail")
public class PeopleReturnDetailController extends BaseController
{
    @Autowired
    private IPeopleReturnDetailService peopleReturnDetailService;

    @Autowired
    private IContactPeopleCountService contactPeopleCountService;
    
    @Autowired
    private IBusinessProcessingCountService businessProcessingCountService;

    @Autowired
    private CenterGardCacheService centerGardCacheService;

    @Autowired
    private ICenterRankInfoService centerRankInfoService;



    /**
     * 查询返乡人员明细列表
     */
    @PreAuthorize("@ss.hasPermi('springmarketing:returndetail:list')")
    @GetMapping("/list")
    public TableDataInfo list(PeopleReturnDetail peopleReturnDetail)
    {
        startPage();
        List<PeopleReturnDetail> list = peopleReturnDetailService.selectPeopleReturnDetailList(peopleReturnDetail);
        return getDataTable(list);
    }

    @GetMapping("/getCountyHomeData")
    public CountyHomeData getCountyHomeData(@RequestParam(value = "marketingTpye", required = false) String marketingTpye)
    {
        CountyHomeData countyHomeData = new CountyHomeData();

        Date currentDate1 = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate1);
        calendar.add(Calendar.DATE,-1);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM");
        countyHomeData.setRecordDate(dateFormat.format(calendar.getTime()));

        Map<String, List<CountyBusinessRankVo>> businessMap = getBusinessMap(dateFormat1.format(calendar.getTime()));
        countyHomeData.setBusinessMap(businessMap);
        CountyBusinessTableVo countyBusinessTableVo = changeTable(businessMap);
        countyHomeData.setCountyBusinessTableVo(countyBusinessTableVo);


        List<CountyReturnVo> countyReturnVos = peopleReturnDetailService.selectCountyCountByDate(dateFormat1.format(calendar.getTime()));
        List<CountyContactVo> countyContactVos = contactPeopleCountService.selectCountyContactByDate(dateFormat1.format(calendar.getTime()));
        Map<String, CountyContactVo> countyContactVoMap = new HashMap<>();
        Map<String, CountyReturnVo> countyReturnVosMap = new HashMap<>();
        if (countyContactVos != null && countyContactVos.size() > 0)
        {
            countyContactVoMap = countyContactVos.stream().collect(Collectors.toMap(CountyContactVo::getCountyId, countyContactVo -> countyContactVo));
        }

        if (countyReturnVos != null && countyReturnVos.size() > 0 )
        {
            countyReturnVosMap = countyReturnVos.stream().collect(Collectors.toMap(CountyReturnVo::getCountyId, countyReturnVo -> countyReturnVo));
        }

        List<CountyDetailVo> countyDetailVoList = new ArrayList<>();
        Map<String, String> countys = CountyEnum.getIscIds();
        for (Map.Entry<String,String> entry:countys.entrySet() )
        {
            String countyId = entry.getKey();
            String countyName = entry.getValue();
            CountyDetailVo countyDetailVo = new CountyDetailVo();
            countyDetailVo.setCountyId(countyId);
            countyDetailVo.setSort(Integer.parseInt(countyId));
            int returnTotal =  0;
            CountyReturnVo countyReturnVo = countyReturnVosMap.get(countyId);
            if (countyReturnVo != null && countyReturnVo.getTotal() != null) {
                countyDetailVo.setReturnPeople(countyReturnVo.getTotal());
                returnTotal = countyReturnVo.getTotal();
            }
            else {
                countyDetailVo.setReturnPeople(0);
            }

            CountyContactVo countyContactVo = countyContactVoMap.get(countyId);
            if (countyContactVo != null)
            {
                countyDetailVo.setContactPeople(countyContactVo.getTotal());
                countyDetailVo.setConversionRate(div1(countyContactVo.getTotal(),returnTotal,2));
            }
            else {
                countyDetailVo.setContactPeople(0);
                countyDetailVo.setConversionRate(0d);
            }
            countyDetailVo.setCountyName(countyName);
            countyDetailVoList.add(countyDetailVo);
        }
        countyDetailVoList.sort(Comparator.comparing(CountyDetailVo::getSort));
        countyHomeData.setCountyList(countyDetailVoList);


        List<CenterRankListVo> rankList = getRankList(dateFormat1.format(calendar.getTime()),marketingTpye);
        countyHomeData.setCenterRankInfos(rankList);
        return countyHomeData;
    }

    private List<CenterRankListVo> getRankList(String currYear, String markType)
    {
        List<CenterRankListVo> list = new ArrayList<>();
        CenterRankInfo centerRankInfo = new CenterRankInfo();
        centerRankInfo.setMarketingTypeName(currYear);
        String s = BusinessTypeEnum.getIscIds().get(markType);
        if (StringUtils.isNotBlank(s))
        {
            centerRankInfo.setMarketingType(markType);
        }
        List<CenterRankInfo> centerRankInfos = centerRankInfoService.selectRankList(centerRankInfo);
        if (centerRankInfos == null || centerRankInfos.size() == 0)
        {
            return list;
        }

        int i = 0;
        int temprank = 1;
        CenterRankInfo temp = centerRankInfos.get(0);
        for (CenterRankInfo vo : centerRankInfos)
        {
            i++;
            CenterRankListVo centerRankListVo = new CenterRankListVo();
            centerRankListVo.setCenterId(vo.getCenterId());
            String tempStr = vo.getCenterName();
            tempStr = tempStr.replaceAll("南昌市","").replaceAll( vo.getCountyName(),"");
            centerRankListVo.setCenterName(tempStr);
            centerRankListVo.setCountyName(vo.getCountyName());
            centerRankListVo.setScore(vo.getScore());
            if (temp.getScore().doubleValue() == vo.getScore().doubleValue())
            {
                centerRankListVo.setRank(temprank);
            }
            else
            {
                temprank = i;
                centerRankListVo.setRank(temprank);
            }
            list.add(centerRankListVo);
        }
        return list;
    }


    private int[] getInitBusiness(List<CountyBusinessVo> list)
    {
        int[] rank = {1,1,1,1};
        if (list == null)
        {
            return  rank;
        }

        Map<String, List<CountyBusinessVo>> collect = list.stream().collect(Collectors.groupingBy(CountyBusinessVo::getMarketingType));
        for (Map.Entry<String, List<CountyBusinessVo>> entry : collect.entrySet())
        {
            Integer markType = Integer.valueOf(entry.getKey());
            if (markType>0 && markType < 5)
            {
                rank[markType-1] = entry.getValue().size()+1;
            }
        }

        return rank;
    }

    private List<CountyBusinessVo> orderBybusiness(List<CountyBusinessVo> list)
    {
        if (list == null)
        {
            return  list;
        }

        Map<String, List<CountyBusinessVo>> collect = list.stream().collect(Collectors.groupingBy(CountyBusinessVo::getMarketingType));

        for (Map.Entry<String, List<CountyBusinessVo>> entry : collect.entrySet())
        {
            List<CountyBusinessVo> value = entry.getValue();
            value.sort(Comparator.comparing(CountyBusinessVo::getTotal).reversed());
            int i = 0;
            CountyBusinessVo cur = value.get(0);
            int curRank = 1;
            for (CountyBusinessVo a :value)
            {
                ++i;
                if (a.getTotal().intValue() == cur.getTotal().intValue())
                {
                    a.setRank(curRank);
                }
                else {
                    a.setRank(i);
                    curRank = i;
                }
                cur = a;
            }
        }
        return list;
    }

    private CountyBusinessTableVo changeTable(Map<String, List<CountyBusinessRankVo>> map)
    {
        CountyBusinessTableVo countyBusinessTableVo = new CountyBusinessTableVo();
        List<String> countyNames = new ArrayList<>();
        List<CountyBusinessRankVo> temp = new ArrayList<>();
        List<CountyBusinessItemVo> table = new ArrayList<>();
        String groupNames[] = {"群雄逐鹿", "奋勇争先",  "砥砺奋进"};
        for (String groupMame : groupNames)
        {
            List<CountyBusinessRankVo> countyBusinessRankVos = map.get(groupMame);
            if (countyBusinessRankVos != null)
            {
                for (CountyBusinessRankVo a : countyBusinessRankVos)
                {
                    temp.add(a);
                }
            }
        }
        for (CountyBusinessRankVo vo : temp)
        {
            countyNames.add(vo.getCountyName());
        }

        for (BusinessTypeEnum typeEnum : BusinessTypeEnum.values())
        {
            CountyBusinessItemVo item = new CountyBusinessItemVo();
            item.setBusinessname(typeEnum.getBusinessName());
            int[] scoreList = getScoreList(typeEnum.getBusinessId(), temp,0);
            item.setScoreList(scoreList);
            int[] rankList = getScoreList(typeEnum.getBusinessId(), temp,1);
            item.setRankList(rankList);
            table.add(item);
        }

        countyBusinessTableVo.setBusinessCounty(countyNames);
        countyBusinessTableVo.setBusinessCountyScore(table);
        return countyBusinessTableVo;
    }


    private int[] getScoreList(String businessType, List<CountyBusinessRankVo> list, int type)
    {
        int scoreList[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        for (int i = 0; i < 12; i++)
        {
            CountyBusinessRankVo vo = list.get(i);
            if (vo != null)
            {

                int ii = Integer.valueOf(businessType)-1;
                if (type == 1)
                {
                    int[] scorearray = vo.getRankArray();
                    scoreList[i] = scorearray[ii];
                }
                else
                {
                    int[] scorearray = vo.getScorearray();
                    scoreList[i] = scorearray[ii];
                }
            }
        }
        return scoreList;
    }

    private Map<String, List<CountyBusinessRankVo>> getBusinessMap(String currYear)
    {
        Map<String, List<CountyBusinessRankVo>> map = new HashMap<>();
        List<CountyBusinessVo> countyBusinessVos = businessProcessingCountService.selectCountyBusinessByDate(currYear);
        countyBusinessVos = orderBybusiness(countyBusinessVos);
//        int[] defaultInt = getInitBusiness(countyBusinessVos);
        Map<String, List<CountyBusinessVo>> collect = null;
        //
        if (countyBusinessVos != null)
        {
            collect = countyBusinessVos.stream().collect(Collectors.groupingBy(CountyBusinessVo::getCountyId));
        }
        for (CountyEnum county : CountyEnum.values())
        {
            List<CountyBusinessRankVo> countyBusinessRankVos = map.get(county.getGroupName());
            if (countyBusinessRankVos == null )
            {
                countyBusinessRankVos = new ArrayList<>();

            }

            CountyBusinessRankVo obj =  null;
            for (CountyBusinessRankVo vo : countyBusinessRankVos)
            {
                if (county.getCountyId().equals(vo.getCountyId())) {
                    obj = vo;
                    break;
                }
            }

            if (obj == null)
            {
                obj = new CountyBusinessRankVo();
                obj.setCountyId(county.getCountyId());
                obj.setCountyName(county.getCountyName());
                if (collect == null)
                {
                    int[] bussArray = {0,0,0,0};
                    int[] rankArray = getInitBusiness(countyBusinessVos);
                    obj.setScorearray(bussArray);
                    obj.setRankArray(rankArray);
                }
                else
                {
                    List<CountyBusinessVo> countyBusinessVos1 = collect.get(county.getCountyId());
                    if (countyBusinessVos1 == null)
                    {
                        int[] bussArray = {0,0,0,0};
                        int[] rankArray = getInitBusiness(countyBusinessVos);
                        obj.setScorearray(bussArray);
                        obj.setRankArray(rankArray);
                    }
                    else
                     {
                         int[] bussArray = {0,0,0,0};
                         int[] rankArray = getInitBusiness(countyBusinessVos);
                         for (BusinessTypeEnum a : BusinessTypeEnum.values())
                         {
                             Integer integer = Integer.valueOf(a.getBusinessId());
                             if (integer > 0 & integer < 5)
                             {
                                 for (CountyBusinessVo voo : countyBusinessVos1)
                                 {
                                     if (a.getBusinessId().equals(voo.getMarketingType()))
                                     {
                                         bussArray[integer-1] =voo.getTotal();
                                         rankArray[integer-1] = voo.getRank();
                                     }
                                 }
                             }
                         }
                         obj.setScorearray(bussArray);
                         obj.setRankArray(rankArray);
                     }
                }
                countyBusinessRankVos.add(obj);
            }
            map.put(county.getGroupName(), countyBusinessRankVos);

        }
        return map;
    }

    @GetMapping("/getHomeData1")
    public PeoPleReturnHome getHomeData1() {
        PeoPleReturnHome peoPleReturnHome = new PeoPleReturnHome();

        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        int lastYear = currentYear - 1;
        List<TotalReturnPeople> totalReturnPeople = peopleReturnDetailService.selectTotalReturnPeopleByYear(String.valueOf(currentYear));
        List<TotalReturnPeople> lastReturnPeople = peopleReturnDetailService.selectTotalReturnPeopleByYear(String.valueOf(lastYear));
        int[]   beforeYearData = getYearData(lastReturnPeople);
        int[]   currentYearData = getYearData(totalReturnPeople);

        List<ReturnRankRecord> returnRankRecords = peopleReturnDetailService.selectReturnRankRecord(String.valueOf(currentYear));

        peoPleReturnHome.setBeforeYearData(beforeYearData);
        peoPleReturnHome.setCurrentYearData(currentYearData);
        peoPleReturnHome.setReturnRankList(returnRankRecords);


        List<ReturnTypeDeatail> returnTypeDeatails = peopleReturnDetailService.selectReturnTypeDeatail(String.valueOf(currentYear));
        peoPleReturnHome.setReturnTypeList(returnTypeDeatails);
        Date currentDate1 = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate1);
        calendar.add(Calendar.DATE,-1);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM");
        peoPleReturnHome.setRecordDate(dateFormat.format(calendar.getTime()));


        int dayContactTotal = peopleReturnDetailService.selectPeopleCountByDate(dateFormat.format(calendar.getTime()));
        int monthContactTotal = peopleReturnDetailService.selectPeopleCountByDate(dateFormat1.format(calendar.getTime()));


        List<BusinessCountResult> totalCountResults = businessProcessingCountService.selectBusinessCountByDate(dateFormat.format(calendar.getTime()));
        List<BusinessCountResult> MonthCountResults = businessProcessingCountService.selectBusinessCountByDate(dateFormat1.format(calendar.getTime()));
        int[] dayBusiness =getBusinessData(totalCountResults);
        int[] mouthBusiness = getBusinessData(MonthCountResults);
        peoPleReturnHome.setDayBusiness(dayBusiness);
        peoPleReturnHome.setMouthBusiness(mouthBusiness);


        List<MapFlightVo> mapFlightVos= peopleReturnDetailService.selectMapFlightByYear(String.valueOf(currentYear));
        List<List<MapFlightVo>> mapFlightData = getMapFlightData(mapFlightVos);
        peoPleReturnHome.setsHData(mapFlightData);

        int dayContacti = contactPeopleCountService.selectPeopleCountByDate(dateFormat.format(calendar.getTime()));
        int mouthContacti = contactPeopleCountService.selectPeopleCountByDate(dateFormat1.format(calendar.getTime()));
        ContactDetail dayContact = new ContactDetail();
        dayContact.setName("当日接触率");
        dayContact.setTotal(dayContactTotal);
        dayContact.setContact(dayContacti);
        dayContact.setContactPercentage(div(dayContacti,dayContactTotal,2));
        peoPleReturnHome.setDayContact(dayContact);

        ContactDetail mouthContact = new ContactDetail();
        mouthContact.setName("当月接触率");
        mouthContact.setTotal(monthContactTotal);
        mouthContact.setContact(mouthContacti);
        mouthContact.setContactPercentage(div(mouthContacti,monthContactTotal,2));
        peoPleReturnHome.setMouthContact(mouthContact);

        return peoPleReturnHome;
    }

    public double div(double v1,double v2, int scale) {
        if (scale < 0)
        {
            return 0D;
        }
        if (v2 == 0)
        {
            return 100D;
        }
        BigDecimal b1 = new BigDecimal(Double.valueOf(v1));
        BigDecimal b2 = new BigDecimal(Double.valueOf(v2));

        double v = b1.multiply(new BigDecimal(100)).divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
        return v;
    }


    public double div1(double v1,double v2, int scale) {
        if (scale < 0)
        {
            return 0D;
        }
        if (v2 == 0)
        {
            return 100D;
        }
        BigDecimal b1 = new BigDecimal(Double.valueOf(v1));
        BigDecimal b2 = new BigDecimal(Double.valueOf(v2));

        double v = b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
        return v;
    }

    private int[] getBusinessData(List<BusinessCountResult> list)
    {
        int[] returnData = {0, 0, 0, 0};
        for (BusinessCountResult t: list)
        {
            int item = Integer.valueOf(t.getMarketingType());
            if (item>0 && item < 5) {
                returnData[item-1] =t.getTotal();
            }
        }
        return returnData;
    }


    private List<List<MapFlightVo>> getMapFlightData(List<MapFlightVo> mapFlightVos)
    {
        List<List<MapFlightVo>> list = new ArrayList<>();
        List<MapFlightVo> ncList = new ArrayList<>();
        MapFlightVo currNc = new MapFlightVo();
        currNc.setName("南昌");
        ncList.add(currNc);

        MapFlightVo currNc1= new MapFlightVo();
        currNc1.setName("南昌");
        currNc1.setValue(100d);
        ncList.add(currNc1);
        list.add(ncList);

        for (MapFlightVo vo: mapFlightVos)
        {
            List<MapFlightVo> curList = new ArrayList<>();
            MapFlightVo curr = new MapFlightVo();
            String  cityName = vo.getName();
            vo.setName(cityName.substring(0,cityName.length()-1));
            curr.setName("南昌");
            curList.add(curr);
            curList.add(vo);

            list.add(curList);
        }
        return list;
    }

    private int[] getYearData( List<TotalReturnPeople> list)
    {
        int[] returnData = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        for (TotalReturnPeople a : list) {
            int item = Integer.valueOf(a.getCurrMonth());
            if (item>0 && item < 13) {
                returnData[item-1] =a.getTotalReturnNumber();
            }
        }
        return returnData;
    }

    /**
     * 查询首页数据
     */
    @GetMapping("/getHomeData")
    public PeoPleReturnHome getHomeData()
    {
        PeoPleReturnHome peoPleReturnHome = new PeoPleReturnHome();
        int[]   beforeYearData = {120, 132, 101, 134, 90, 230, 210, 101, 134, 90, 230, 210};
        int[]   currentYearData = {220, 182, 191, 234, 290, 330, 310, 191, 234, 290, 330, 310};
        peoPleReturnHome.setBeforeYearData(beforeYearData);
        peoPleReturnHome.setCurrentYearData(currentYearData);

//        String[] totalRankName = {"北京", "上海", "天津", "重庆", "江苏", "云南"};
//        int[] totalRankPeople = {367,300,256,208,173,123};
//
//        peoPleReturnHome.setTotalRankName(totalRankName);
//        peoPleReturnHome.setTotalRankPeople(totalRankPeople);

        List<ReturnRankRecord> returnRankRecords = new ArrayList<>();
        ReturnRankRecord rank1 = new ReturnRankRecord(1,"北京", 367);
        ReturnRankRecord rank2 = new ReturnRankRecord(1,"上海", 300);
        ReturnRankRecord rank3 = new ReturnRankRecord(1,"天津", 256);
        ReturnRankRecord rank4 = new ReturnRankRecord(1,"重庆", 208);
        ReturnRankRecord rank5 = new ReturnRankRecord(1,"江苏", 173);
        ReturnRankRecord rank6 = new ReturnRankRecord(1,"云南", 100);
        returnRankRecords.add(rank1);
        returnRankRecords.add(rank2);
        returnRankRecords.add(rank3);
        returnRankRecords.add(rank4);
        returnRankRecords.add(rank5);
        returnRankRecords.add(rank6);
        peoPleReturnHome.setReturnRankList(returnRankRecords);

        List<ReturnTypeDeatail> returnTypeList = new ArrayList<>();
        returnTypeList.add(new ReturnTypeDeatail("省内返乡", 330));
        returnTypeList.add(new ReturnTypeDeatail("外省返乡", 440));
        peoPleReturnHome.setReturnTypeList(returnTypeList);

        ContactDetail dayContact = new ContactDetail();
        dayContact.setName("当日接触率");
        dayContact.setTotal(100);
        dayContact.setContact(55);
        dayContact.setContactPercentage(55d);
        peoPleReturnHome.setDayContact(dayContact);

        ContactDetail mouthContact = new ContactDetail();
        mouthContact.setName("当月接触率");
        mouthContact.setTotal(2000);
        mouthContact.setContact(1000);
        mouthContact.setContactPercentage(50d);
        peoPleReturnHome.setMouthContact(mouthContact);

        int[]   dayBusiness = {100, 50, 120, 198};
        int[]   mouthBusiness = {1700, 500, 1200, 1980};
        peoPleReturnHome.setDayBusiness(dayBusiness);
        peoPleReturnHome.setMouthBusiness(mouthBusiness);


        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DATE,-1);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        peoPleReturnHome.setRecordDate(dateFormat.format(calendar.getTime()));

        return peoPleReturnHome;
    }

    /**
     * 导出返乡人员明细列表
     */
    @PreAuthorize("@ss.hasPermi('springmarketing:returndetail:export')")
    @Log(title = "返乡人员明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PeopleReturnDetail peopleReturnDetail)
    {
        List<PeopleReturnDetail> list = peopleReturnDetailService.selectPeopleReturnDetailList(peopleReturnDetail);
        ExcelUtil<PeopleReturnDetail> util = new ExcelUtil<PeopleReturnDetail>(PeopleReturnDetail.class);
        util.exportExcel(response, list, "返乡人员明细数据");
    }

    /**
     * 获取返乡人员明细详细信息
     */
    @PreAuthorize("@ss.hasPermi('springmarketing:returndetail:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(peopleReturnDetailService.selectPeopleReturnDetailById(id));
    }

    /**
     * 新增返乡人员明细
     */
    @PreAuthorize("@ss.hasPermi('springmarketing:returndetail:add')")
    @Log(title = "返乡人员明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PeopleReturnDetail peopleReturnDetail)
    {
        return toAjax(peopleReturnDetailService.insertPeopleReturnDetail(peopleReturnDetail));
    }

    /**
     * 修改返乡人员明细
     */
    @PreAuthorize("@ss.hasPermi('springmarketing:returndetail:edit')")
    @Log(title = "返乡人员明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PeopleReturnDetail peopleReturnDetail)
    {
        return toAjax(peopleReturnDetailService.updatePeopleReturnDetail(peopleReturnDetail));
    }

    /**
     * 删除返乡人员明细
     */
    @PreAuthorize("@ss.hasPermi('springmarketing:returndetail:remove')")
    @Log(title = "返乡人员明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(peopleReturnDetailService.deletePeopleReturnDetailByIds(ids));
    }
}

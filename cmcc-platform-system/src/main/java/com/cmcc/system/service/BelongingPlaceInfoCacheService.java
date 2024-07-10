package com.cmcc.system.service;

import com.cmcc.system.domain.BelongPlaceCode;
import com.cmcc.system.domain.BelongingPlaceInfo;
import com.cmcc.system.domain.CenterAreaCode;
import com.cmcc.system.domain.MarketingGardInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author:terry
 * @create: 2024-02-24 16:52
 * @Description: belonging_place_info缓存service
 */
@Component
public class BelongingPlaceInfoCacheService {
    private static Logger logger = LoggerFactory.getLogger(BelongingPlaceInfoCacheService.class);

    @Autowired
    private IBelongingPlaceInfoService iBelongingPlaceInfoService;

    private static List<BelongingPlaceInfo> belongingPlaceList  = new ArrayList<>();

    @PostConstruct
    public void initGardList()
    {
        belongingPlaceList = iBelongingPlaceInfoService.selectBelongingPlaceInfoList(new BelongingPlaceInfo());
    }

    public String getLocalPoliceStationId(String localPoliceStationName)
    {
        for (BelongingPlaceInfo  a :belongingPlaceList)
        {
            if (a.getBelongingPlaceName().equals(localPoliceStationName))
            {
                return a.getLocalPoliceStationId();
            }
        }
        return "";
    }

    public String getLocalPoliceStationName(String localPoliceStationId)
    {
        for (BelongingPlaceInfo  a :belongingPlaceList)
        {
            if (a.getLocalPoliceStationId().equals(localPoliceStationId))
            {
                return a.getLocalPoliceStationName();
            }
        }
        return "";
    }

    public String getPlaceId(String placeName)
    {
        for (BelongingPlaceInfo  a :belongingPlaceList)
        {
            if (a.getBelongingPlaceName().equals(placeName))
            {
                return a.getBelongingPlaceId();
            }
        }
        return "";
    }


    public String getPlaceName(String placeId)
    {
        for (BelongingPlaceInfo  a :belongingPlaceList)
        {
            if (a.getBelongingPlaceId().equals(placeId))
            {
                return a.getBelongingPlaceName();
            }
        }
        return "";
    }

    public List<BelongPlaceCode> getStationListPro(String currentCode)
    {
        List<BelongPlaceCode> list = new ArrayList<>();
        Map<String, BelongingPlaceInfo> clearMap = new HashMap<>();
        for ( BelongingPlaceInfo info : belongingPlaceList)
        {
            BelongingPlaceInfo belongingPlaceInfo = clearMap.get(info.getLocalPoliceStationId());
            if (belongingPlaceInfo == null && currentCode.equals(info.getLocalPoliceStationId()))
            {
                clearMap.put(info.getLocalPoliceStationId(), info);
                BelongPlaceCode center = new BelongPlaceCode();

//                center.setParentCode(info.getSysCountyCode());
                center.setCode(info.getLocalPoliceStationId());
                center.setName(info.getLocalPoliceStationName());
                list.add(center);
            }
        }
        return list;
    }

    public List<BelongPlaceCode> getStationList()
    {
        List<BelongPlaceCode> list = new ArrayList<>();
        Map<String, BelongingPlaceInfo> clearMap = new HashMap<>();
        for ( BelongingPlaceInfo info : belongingPlaceList)
        {
            BelongingPlaceInfo belongingPlaceInfo = clearMap.get(info.getLocalPoliceStationId());
            if (belongingPlaceInfo == null)
            {
                clearMap.put(info.getLocalPoliceStationId(), info);
                BelongPlaceCode center = new BelongPlaceCode();

//                center.setParentCode(info.getSysCountyCode());
                center.setCode(info.getLocalPoliceStationId());
                center.setName(info.getLocalPoliceStationName());
                list.add(center);
            }
        }
        return list;
    }

    public List<BelongPlaceCode> getStationListByParent(int parentId)
    {
        List<BelongPlaceCode> list = new ArrayList<>();
        Map<String, BelongingPlaceInfo> clearMap = new HashMap<>();
        for ( BelongingPlaceInfo info : belongingPlaceList)
        {
            if (info.getParentDeptId()!=null && info.getParentDeptId().intValue() == parentId){
            BelongingPlaceInfo belongingPlaceInfo = clearMap.get(info.getLocalPoliceStationId());
            if (belongingPlaceInfo == null)
            {
                clearMap.put(info.getLocalPoliceStationId(), info);
                BelongPlaceCode center = new BelongPlaceCode();

//                center.setParentCode(info.getSysCountyCode());
                center.setCode(info.getLocalPoliceStationId());
                center.setName(info.getLocalPoliceStationName());
                list.add(center);
            }
            }
        }
        return list;
    }

    public List<BelongPlaceCode> getBelongListByParentId(String stationId, int parentId)
    {
        List<BelongPlaceCode> list = new ArrayList<>();
        Map<String, List<BelongingPlaceInfo>> collect = belongingPlaceList.stream().collect(Collectors.groupingBy(BelongingPlaceInfo::getLocalPoliceStationId));
        List<BelongingPlaceInfo> belongingPlaceInfos = collect.get(stationId);
        if (belongingPlaceInfos == null)
        {
            return list;
        }
        for ( BelongingPlaceInfo info : belongingPlaceInfos)
        {
            if (info.getParentDeptId() != null && info.getParentDeptId().intValue() == parentId)
            {
                BelongPlaceCode center = new BelongPlaceCode();
                center.setParentCode(info.getLocalPoliceStationId());
                center.setCode(info.getBelongingPlaceId());
                center.setName(info.getBelongingPlaceName());
                list.add(center);
            }

        }
        return list;
    }

    public List<BelongPlaceCode> getBelongList(String stationId)
    {
        List<BelongPlaceCode> list = new ArrayList<>();
        Map<String, List<BelongingPlaceInfo>> collect = belongingPlaceList.stream().collect(Collectors.groupingBy(BelongingPlaceInfo::getLocalPoliceStationId));
        List<BelongingPlaceInfo> belongingPlaceInfos = collect.get(stationId);
        if (belongingPlaceInfos == null)
        {
            return list;
        }
        for ( BelongingPlaceInfo info : belongingPlaceInfos)
        {
            BelongPlaceCode center = new BelongPlaceCode();
            center.setParentCode(info.getLocalPoliceStationId());
            center.setCode(info.getBelongingPlaceId());
            center.setName(info.getBelongingPlaceName());
            list.add(center);
        }
        return list;
    }
}

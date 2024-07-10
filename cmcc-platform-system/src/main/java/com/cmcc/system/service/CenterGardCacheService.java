package com.cmcc.system.service;

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
 * @create: 2023-12-28 20:27
 * @Description: gard缓存service
 */
@Component
public class CenterGardCacheService {


    private static Logger logger = LoggerFactory.getLogger(CenterGardCacheService.class);

    @Autowired
    private IMarketingGardInfoService marketingGardInfoService;

    private static List<MarketingGardInfo> gardList  = new ArrayList<>();

    @PostConstruct
    public void initGardList()
    {
        gardList = marketingGardInfoService.selectMarketingGardInfoList(new MarketingGardInfo());
    }

    public String getCountyId(String countyName)
    {
        for (MarketingGardInfo  a :gardList)
        {
            if (a.getCountyName().equals(countyName))
            {
                return a.getSysCountyCode();
            }
        }
        return "";
    }

    public String getCountyName(String countyId)
    {
        for (MarketingGardInfo  a :gardList)
        {
            if (a.getSysCountyCode().equals(countyId))
            {
                return a.getCountyName();
            }
        }
        return "";
    }

    public String getCenterId(String centerName)
    {
        for (MarketingGardInfo  a :gardList)
        {
            if (a.getCenterName().equals(centerName))
            {
                return a.getCenterCode();
            }
        }
        return "";
    }

    public String getCenterName(String centerId)
    {
        for (MarketingGardInfo  a :gardList)
        {
            if (a.getCenterCode().equals(centerId))
            {
                return a.getCenterName();
            }
        }
        return "";
    }

    public String getTownId(String townName)
    {
        for (MarketingGardInfo  a :gardList)
        {
            if (a.getTownName().equals(townName))
            {
                return a.getTownCode();
            }
        }
        return "";
    }


    public String getTownName(String townId)
    {
        for (MarketingGardInfo  a :gardList)
        {
            if (a.getTownCode().equals(townId))
            {
                return a.getTownName();
            }
        }
        return "";
    }


    public List<CenterAreaCode> GetCenterList(String countyCode)
    {
        List<CenterAreaCode> list = new ArrayList<>();
        Map<String, List<MarketingGardInfo>> collect = gardList.stream().collect(Collectors.groupingBy(MarketingGardInfo::getSysCountyCode));
        List<MarketingGardInfo> marketingGardInfos = collect.get(countyCode);
        if (marketingGardInfos == null)
        {
            return list;
        }
        Map<String, MarketingGardInfo> clearMap = new HashMap<>();
        for ( MarketingGardInfo info : marketingGardInfos)
        {
            MarketingGardInfo marketingGardInfo = clearMap.get(info.getCenterCode());
            if (marketingGardInfo == null)
            {
                clearMap.put(info.getCenterCode(), info);
                CenterAreaCode center = new CenterAreaCode();

                center.setParentCode(info.getSysCountyCode());
                center.setCode(info.getCenterCode());
                center.setName(info.getCenterName());
                list.add(center);
            }
        }
        return list;
    }


    public List<CenterAreaCode> GetTownList(String centerCode)
    {
        List<CenterAreaCode> list = new ArrayList<>();
        Map<String, List<MarketingGardInfo>> collect = gardList.stream().collect(Collectors.groupingBy(MarketingGardInfo::getCenterCode));
        List<MarketingGardInfo> marketingGardInfos = collect.get(centerCode);
        if (marketingGardInfos == null)
        {
            return list;
        }
        for ( MarketingGardInfo info : marketingGardInfos)
        {
                CenterAreaCode center = new CenterAreaCode();
                center.setParentCode(info.getCenterCode());
                center.setCode(info.getTownCode());
                center.setName(info.getTownName());
                list.add(center);
        }
        return list;
    }
}

package com.cmcc.system.service;

import com.cmcc.system.domain.BelongingPlaceInfo;
import com.cmcc.system.domain.EnterpriseUserInfo;
import com.cmcc.system.domain.UnionEnterpriseConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author:terry
 * @create: 2024-04-18 03:08
 * @Description: enterprise_user_info缓存service
 */
@Component
public class EnterpriseUserInfoCacheService {

    private static Logger logger = LoggerFactory.getLogger(EnterpriseUserInfoCacheService.class);

    @Autowired
    private IEnterpriseUserInfoService iEnterpriseUserInfoService;

    private static List<EnterpriseUserInfo> enterpriseUserInfoList  = new ArrayList<>();

    @PostConstruct
    public void initEnterpriseUserInfoList()
    {
        enterpriseUserInfoList = iEnterpriseUserInfoService.selectEnterpriseUserInfoList(new EnterpriseUserInfo());
    }

    public String getPhoneByUserId(long userId)
    {
        for (EnterpriseUserInfo info : enterpriseUserInfoList)
        {
            if (info.getUserId() != null && info.getUserId().longValue() == userId)
            {
                return info.getUserTel();
            }
        }
        return "";
    }

}

package com.cmcc.system.service;

import com.cmcc.system.domain.AppUnionConfig;
import com.cmcc.system.domain.AppUnionConfigTwo;
import com.cmcc.system.domain.EnterpriseUserInfo;
import com.cmcc.system.domain.UnionEnterpriseConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:terry
 * @create: 2024-04-18 03:06
 * @Description: union_enterprise_config缓存service
 */
@Component
public class UnionEnterpriseConfigCacheService {

    private static Logger logger = LoggerFactory.getLogger(UnionEnterpriseConfigCacheService.class);

    @Autowired
    private IUnionEnterpriseConfigService iUnionEnterpriseConfigService;

    private static List<UnionEnterpriseConfig> unionEnterpriseConfigList  = new ArrayList<>();

    @PostConstruct
    public void initUnionEnterpriseConfigList()
    {
        unionEnterpriseConfigList = iUnionEnterpriseConfigService.selectUnionEnterpriseConfigList(new UnionEnterpriseConfig());
    }
    public boolean isUnionAdmin(long userId)
    {
        for (UnionEnterpriseConfig config : unionEnterpriseConfigList)
        {
            if (config.getUnionUserId().longValue() == userId)
            {
                return true;
            }
        }
        return false;
    }

    public String getPhoneByUserId(long userId)
    {
        for (UnionEnterpriseConfig config : unionEnterpriseConfigList)
        {
            if (config.getUnionUserId() != null && config.getUnionUserId().longValue() == userId)
            {
                return config.getUnionUserTel();
            }
        }
        return "";
    }

    public List<Long> getAllDeptId(long userId)
    {
        List<Long> list = new ArrayList<>();
        for (UnionEnterpriseConfig config : unionEnterpriseConfigList)
        {
            if (config.getUnionUserId() != null && config.getUnionUserId().longValue() == userId)
            {
                list.add(config.getDeptId());
            }
        }
        return list;
    }

    public List<AppUnionConfig> getAllUnion()
    {
        List<AppUnionConfig> list = new ArrayList<>();
        Map<Long, AppUnionConfig> map = new HashMap<>();
        for (UnionEnterpriseConfig config : unionEnterpriseConfigList)
        {
            if (map.get(config.getUnionId()) != null) {
                continue;
            }
            AppUnionConfig appUnionConfig = new AppUnionConfig();
            appUnionConfig.setArea1id(config.getUnionId());
            appUnionConfig.setArea1(config.getUnionName());
            map.put(config.getUnionId(), appUnionConfig);
            list.add(appUnionConfig);
        }
        return list;
    }

    public Long getAppUnionUserIdByDeptId(long deptId)
    {
        for (UnionEnterpriseConfig config : unionEnterpriseConfigList)
        {
            if (config.getDeptId() != null && config.getDeptId().longValue() == deptId)
            {

                return config.getUnionUserId();
            }
        }
        return null;
    }

    public AppUnionConfig getAppUnionConfigByDeptId(long deptId)
    {
        for (UnionEnterpriseConfig config : unionEnterpriseConfigList)
        {
            if (config.getDeptId() != null && config.getDeptId().longValue() == deptId)
            {
                AppUnionConfig appUnionConfig = new AppUnionConfig();
                appUnionConfig.setArea1id(config.getUnionId());
                appUnionConfig.setArea1(config.getUnionName());
                return appUnionConfig;
            }
        }
        return null;
    }

    public AppUnionConfig getAppUnionConfigByUserId(long userId)
    {
        for (UnionEnterpriseConfig config : unionEnterpriseConfigList)
        {
            if (config.getUnionUserId() != null && config.getUnionUserId().longValue() == userId)
            {
                AppUnionConfig appUnionConfig = new AppUnionConfig();
                appUnionConfig.setArea1id(config.getUnionId());
                appUnionConfig.setArea1(config.getUnionName());
                return appUnionConfig;
            }
        }
        return null;
    }

    public List<AppUnionConfigTwo> getAppUnionConfig2ByUserId(long unionId)
    {

        List<AppUnionConfigTwo> configList = new ArrayList<>();
        for (UnionEnterpriseConfig config : unionEnterpriseConfigList)
        {
            if (config.getUnionId() != null && config.getUnionId().longValue() == unionId)
            {
                AppUnionConfigTwo appUnionConfig = new AppUnionConfigTwo();
                appUnionConfig.setArea2(config.getDeptName());
                appUnionConfig.setArea2id(config.getDeptId());
                configList.add(appUnionConfig);
            }
        }
        return configList;
    }
}

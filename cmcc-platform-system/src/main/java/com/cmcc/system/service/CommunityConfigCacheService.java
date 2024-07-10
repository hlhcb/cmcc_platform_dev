package com.cmcc.system.service;

import com.cmcc.system.domain.CommunityConfig;
import com.cmcc.system.domain.CommunityConfigVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author:terry
 * @create: 2024-05-11 14:56
 * @Description: community_config缓存service
 */
@Component
public class CommunityConfigCacheService {
    private static Logger logger = LoggerFactory.getLogger(CommunityConfigCacheService.class);


    @Autowired
    private ICommunityConfigService iCommunityConfigService;

    private static List<CommunityConfig> communityConfigsList = new ArrayList<>();

    @PostConstruct
    public  void initCommunityConfigList()
    {
        communityConfigsList = iCommunityConfigService.selectCommunityConfigList(new CommunityConfig());
    }

    public List<CommunityConfigVo> getCommunityByPlace(String belongingPlace){
        List<CommunityConfigVo> list = new ArrayList<>();
        int i = 0;
        for (CommunityConfig vo : communityConfigsList)
        {
            if(belongingPlace.equals(vo.getBelongingPlace()))
            {
                CommunityConfigVo config = new CommunityConfigVo();
                config.setText(vo.getCommunity());
                config.setValue(i);
                list.add(config);
                i++;
            }
        }
        return list;
    }

    public Long getDeptIdByCommunity(String community)
    {
        for (CommunityConfig vo : communityConfigsList)
        {
            if(community.equals(vo.getCommunity())){
                return vo.getDeptId();
            }
        }
        return null;
    }

    public List<CommunityConfigVo> getBelongingPlaceList(){
        List<CommunityConfigVo> list = new ArrayList<>();
        List<String> collect = communityConfigsList.stream().map(CommunityConfig::getBelongingPlace).collect(Collectors.toList());
        if (collect == null || collect.size() < 1)
        {
            return list;
        }
        List<String> collect1 = collect.stream().distinct().collect(Collectors.toList());
        if (collect1 != null && collect1.size() > 0) {
            int i = 0;
            for (String text : collect1)
            {
                CommunityConfigVo vo = new CommunityConfigVo();
                vo.setText(text);
                vo.setValue(i);
                list.add(vo);
                i++;
            }
        }
        return list;
    }
}

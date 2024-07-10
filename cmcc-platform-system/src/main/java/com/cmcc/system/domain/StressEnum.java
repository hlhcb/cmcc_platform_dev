package com.cmcc.system.domain;

import com.cmcc.common.utils.StringUtils;

public enum StressEnum {

    高新派出所("高新派出所","艾溪湖管理处"),
    艾溪湖派出所("艾溪湖派出所","艾溪湖管理处"),
    瑶湖派出所("瑶湖派出所","昌东镇"),
    麻丘派出所("麻丘派出所","麻丘镇"),
    昌东派出所("昌东派出所","昌东镇"),
    光伏产业园派出所("光伏产业园派出所","昌东镇"),
    航空城派出所("航空城派出所","昌东镇"),
    鲤鱼洲派出所("鲤鱼洲派出所","鲤鱼洲管理处");
    private String localPoliceStation;

    private String stressName;

    private StressEnum(String localPoliceStation, String stressName)
    {
        this.localPoliceStation = localPoliceStation;
        this.stressName = stressName;
    }

    public String getLocalPoliceStation() {
        return localPoliceStation;
    }

    public void setLocalPoliceStation(String localPoliceStation) {
        this.localPoliceStation = localPoliceStation;
    }

    public String getStressName() {
        return stressName;
    }

    public void setStressName(String stressName) {
        this.stressName = stressName;
    }

    public static String getStressByLocation(String value)
    {
        if (StringUtils.isEmpty(value))
        {
            return "";
        }
        for (StressEnum item : StressEnum.values())
        {
            if (value.equals(item.getLocalPoliceStation()))
            {
                return item.getStressName();
            }
        }
        return "";
    }
}

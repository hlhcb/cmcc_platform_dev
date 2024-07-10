package com.cmcc.system.domain;

import com.cmcc.common.utils.StringUtils;

public enum AreaTypeEnum {

    东湖区(2004L, "东湖区"),
    西湖区(2005L, "西湖区"),
    青山湖区(2006L, "青山湖区"),
    青云谱区(2007L, "青云谱区"),
    昌北区(2008L, "昌北区"),
    湾里区(2009L,"湾里区"),
    高新区(2010L,"高新区"),
    红谷滩区(2011L,"红谷滩区"),
    南昌县(2012L,"南昌县"),
    新建区(2013L,"新建区"),
    进贤县(2014L,"进贤县"),
    安义县(2015L,"安义县");

    private Long areaId;

    private String areaName;

    private AreaTypeEnum(Long areaId, String areaName)
    {
        this.areaId = areaId;
        this.areaName = areaName;
    }

    public  boolean checkAreaId(long deptId){
        for (AreaTypeEnum value : AreaTypeEnum.values()) {
            if (value.getAreaId().longValue() == deptId) {
                return true;
            }
        }
        return false;
    }

    public static Long getValueByName(String name)
    {
        if (StringUtils.isEmpty(name))
        {
            return 0l;
        }
        for (AreaTypeEnum item : AreaTypeEnum.values())
        {
            if (name.equals(item.name()))
            {
                return item.getAreaId();
            }
        }
        return 0l;
    }

    public static String getNameByValue(Long value)
    {
        if (value == null)
        {
            return "";
        }
        for (AreaTypeEnum item : AreaTypeEnum.values())
        {
            if (value.equals(item.getAreaId()))
            {
                return item.name();
            }
        }
        return "";
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}

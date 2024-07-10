package com.cmcc.system.domain;

import com.cmcc.common.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author:terry
 * @create: 2023-12-28 06:56
 * @Description: 区县枚举
 */
public enum CountyEnum {

    东湖区("1", "东湖区","群雄逐鹿"),
    西湖区("2", "西湖区","群雄逐鹿"),
    青山湖区("3", "青山湖区","奋勇争先"),
    青云谱区("4", "青云谱区","奋勇争先"),
    昌北区("5", "昌北区","砥砺奋进"),
    红谷滩区("6", "红谷滩区","群雄逐鹿"),
    高新区("7", "高新区","奋勇争先"),
    湾里区("8", "湾里区","奋勇争先"),
    南昌县("9", "南昌县","奋勇争先"),
    新建区("10", "新建区","群雄逐鹿"),
    进贤县("11", "进贤县","砥砺奋进"),
    安义县("12", "安义县","奋勇争先");

    private CountyEnum(String countyId, String countyName, String groupName)
    {
        this.countyId = countyId;
        this.countyName = countyName;
        this.groupName = groupName;
    }

    private String countyName;
    private String countyId;

    private String groupName;

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getCountyId() {
        return countyId;
    }

    public void setCountyId(String countyId) {
        this.countyId = countyId;
    }

    public static String getValueByName(String name)
    {
        if (StringUtils.isEmpty(name))
        {
            return "";
        }
        for (CountyEnum item : CountyEnum.values())
        {
            if (name.equals(item.name()))
            {
                return item.getCountyId();
            }
        }
        return "";
    }

    public static String getNameByValue(String value)
    {
        if (StringUtils.isEmpty(value))
        {
            return "";
        }
        for (CountyEnum item : CountyEnum.values())
        {
            if (value.equals(item.getCountyId()))
            {
                return item.name();
            }
        }
        return "";
    }

    public static String ofText(String text)
    {
        for (CountyEnum codeEnum : CountyEnum.values())
        {
            if (codeEnum.getCountyName().equals(text))
            {
                return codeEnum.getCountyId();
            }
        }

        return "";
    }

    public static Map<String,String> getIscIds()
    {
        Map<String,String> iscIds = new HashMap<>();
        for (CountyEnum codeEnum : CountyEnum.values())
        {
            iscIds.put(codeEnum.getCountyId(),codeEnum.getCountyName());
        }
        return iscIds;
    }

    public static String ofValue(String value)
    {
        for (CountyEnum codeEnum : CountyEnum.values())
        {
            if (codeEnum.getCountyId().equals(value))
            {
                return codeEnum.getCountyName();
            }
        }
        return "";
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}

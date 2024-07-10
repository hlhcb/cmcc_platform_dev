package com.cmcc.system.domain;

import com.cmcc.common.utils.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:terry
 * @create: 2023-12-28 09:38
 * @Description: 业务类型
 */
public enum BusinessTypeEnum {

    等效("1", "等效"),
    等效带宽("2", "等效带宽"),
    G合约("3", "5G合约"),
    CHN产品("4", "CHN产品");

    private String businessId;

    private String businessName;

    private BusinessTypeEnum(String businessId, String businessName)
    {
        this.businessId = businessId;
        this.businessName = businessName;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public static String getValueByName(String name)
    {
        if (StringUtils.isEmpty(name))
        {
            return "";
        }
        for (BusinessTypeEnum item : BusinessTypeEnum.values())
        {
            if (name.equals(item.name()))
            {
                return item.getBusinessId();
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
        for (BusinessTypeEnum item : BusinessTypeEnum.values())
        {
            if (value.equals(item.getBusinessId()))
            {
                return item.name();
            }
        }
        return "";
    }

    public static List<String> getBusinessNameList()
    {
        List<String> list = new ArrayList<>();
        for (BusinessTypeEnum codeEnum : BusinessTypeEnum.values())
        {
            list.add(codeEnum.getBusinessName());
        }
        return list;
    }

    public static String ofText(String text)
    {
        for (BusinessTypeEnum codeEnum : BusinessTypeEnum.values())
        {
            if (codeEnum.getBusinessName().equals(text))
            {
                return codeEnum.getBusinessId();
            }
        }

        return "";
    }

    public static Map<String,String> getIscIds()
    {
        Map<String,String> iscIds = new HashMap<>();
        for (BusinessTypeEnum codeEnum : BusinessTypeEnum.values())
        {
            iscIds.put(codeEnum.getBusinessId(),codeEnum.getBusinessName());
        }
        return iscIds;
    }

    public static String ofValue(String value)
    {
        for (BusinessTypeEnum codeEnum : BusinessTypeEnum.values())
        {
            if (codeEnum.getBusinessId().equals(value))
            {
                return codeEnum.getBusinessName();
            }
        }
        return "";
    }
}

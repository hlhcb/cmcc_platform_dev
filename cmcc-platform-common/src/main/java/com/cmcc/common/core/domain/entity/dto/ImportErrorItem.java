package com.cmcc.common.core.domain.entity.dto;

import java.util.List;

/**
 * @author RenHang
 * @date 2024-07-05 14:34
 * @description:error item
 */
public class ImportErrorItem {
    private Integer rowNum;
    private List<String> errorItemList;

    public Integer getRowNum() {
        return rowNum;
    }

    public void setRowNum(Integer rowNum) {
        this.rowNum = rowNum;
    }

    public List<String> getErrorItemList() {
        return errorItemList;
    }

    public void setErrorItemList(List<String> errorItemList) {
        this.errorItemList = errorItemList;
    }
}

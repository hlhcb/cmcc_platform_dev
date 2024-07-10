package com.cmcc.system.service;

import java.util.List;
import com.cmcc.system.domain.RentalHousingInfo;

/**
 * 出租房信息Service接口
 * 
 * @author terry
 * @date 2024-05-11
 */
public interface IRentalHousingInfoService 
{
    /**
     * 查询出租房信息
     * 
     * @param id 出租房信息主键
     * @return 出租房信息
     */
    public RentalHousingInfo selectRentalHousingInfoById(Long id);

    /**
     * 查询出租房信息列表
     * 
     * @param rentalHousingInfo 出租房信息
     * @return 出租房信息集合
     */
    public List<RentalHousingInfo> selectRentalHousingInfoList(RentalHousingInfo rentalHousingInfo);

    /**
     * 新增出租房信息
     * 
     * @param rentalHousingInfo 出租房信息
     * @return 结果
     */
    public int insertRentalHousingInfo(RentalHousingInfo rentalHousingInfo);

    /**
     * 修改出租房信息
     * 
     * @param rentalHousingInfo 出租房信息
     * @return 结果
     */
    public int updateRentalHousingInfo(RentalHousingInfo rentalHousingInfo);

    /**
     * 批量删除出租房信息
     * 
     * @param ids 需要删除的出租房信息主键集合
     * @return 结果
     */
    public int deleteRentalHousingInfoByIds(Long[] ids);

    /**
     * 删除出租房信息信息
     * 
     * @param id 出租房信息主键
     * @return 结果
     */
    public int deleteRentalHousingInfoById(Long id);
}

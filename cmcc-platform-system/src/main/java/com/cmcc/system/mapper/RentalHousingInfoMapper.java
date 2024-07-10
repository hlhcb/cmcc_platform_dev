package com.cmcc.system.mapper;

import java.util.List;
import com.cmcc.system.domain.RentalHousingInfo;
import com.cmcc.system.domain.TenantUserInfo;

/**
 * 出租房信息Mapper接口
 * 
 * @author terry
 * @date 2024-05-11
 */
public interface RentalHousingInfoMapper 
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
     * 删除出租房信息
     * 
     * @param id 出租房信息主键
     * @return 结果
     */
    public int deleteRentalHousingInfoById(Long id);

    /**
     * 批量删除出租房信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteRentalHousingInfoByIds(Long[] ids);

    /**
     * 批量删除租客信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTenantUserInfoByRentalHousingIds(Long[] ids);
    
    /**
     * 批量新增租客信息
     * 
     * @param tenantUserInfoList 租客信息列表
     * @return 结果
     */
    public int batchTenantUserInfo(List<TenantUserInfo> tenantUserInfoList);
    

    /**
     * 通过出租房信息主键删除租客信息信息
     * 
     * @param id 出租房信息ID
     * @return 结果
     */
    public int deleteTenantUserInfoByRentalHousingId(Long id);
}

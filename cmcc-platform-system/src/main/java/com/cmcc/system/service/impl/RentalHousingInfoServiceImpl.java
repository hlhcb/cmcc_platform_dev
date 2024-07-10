package com.cmcc.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.cmcc.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.cmcc.system.domain.TenantUserInfo;
import com.cmcc.system.mapper.RentalHousingInfoMapper;
import com.cmcc.system.domain.RentalHousingInfo;
import com.cmcc.system.service.IRentalHousingInfoService;

/**
 * 出租房信息Service业务层处理
 * 
 * @author terry
 * @date 2024-05-11
 */
@Service
public class RentalHousingInfoServiceImpl implements IRentalHousingInfoService 
{
    @Autowired
    private RentalHousingInfoMapper rentalHousingInfoMapper;

    /**
     * 查询出租房信息
     * 
     * @param id 出租房信息主键
     * @return 出租房信息
     */
    @Override
    public RentalHousingInfo selectRentalHousingInfoById(Long id)
    {
        return rentalHousingInfoMapper.selectRentalHousingInfoById(id);
    }

    /**
     * 查询出租房信息列表
     * 
     * @param rentalHousingInfo 出租房信息
     * @return 出租房信息
     */
    @Override
    public List<RentalHousingInfo> selectRentalHousingInfoList(RentalHousingInfo rentalHousingInfo)
    {
        return rentalHousingInfoMapper.selectRentalHousingInfoList(rentalHousingInfo);
    }

    /**
     * 新增出租房信息
     * 
     * @param rentalHousingInfo 出租房信息
     * @return 结果
     */
    @Transactional
    @Override
    public int insertRentalHousingInfo(RentalHousingInfo rentalHousingInfo)
    {
        int rows = rentalHousingInfoMapper.insertRentalHousingInfo(rentalHousingInfo);
        insertTenantUserInfo(rentalHousingInfo);
        return rows;
    }

    /**
     * 修改出租房信息
     * 
     * @param rentalHousingInfo 出租房信息
     * @return 结果
     */
    @Transactional
    @Override
    public int updateRentalHousingInfo(RentalHousingInfo rentalHousingInfo)
    {
        rentalHousingInfoMapper.deleteTenantUserInfoByRentalHousingId(rentalHousingInfo.getId());
        insertTenantUserInfo(rentalHousingInfo);
        return rentalHousingInfoMapper.updateRentalHousingInfo(rentalHousingInfo);
    }

    /**
     * 批量删除出租房信息
     * 
     * @param ids 需要删除的出租房信息主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteRentalHousingInfoByIds(Long[] ids)
    {
        rentalHousingInfoMapper.deleteTenantUserInfoByRentalHousingIds(ids);
        return rentalHousingInfoMapper.deleteRentalHousingInfoByIds(ids);
    }

    /**
     * 删除出租房信息信息
     * 
     * @param id 出租房信息主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteRentalHousingInfoById(Long id)
    {
        rentalHousingInfoMapper.deleteTenantUserInfoByRentalHousingId(id);
        return rentalHousingInfoMapper.deleteRentalHousingInfoById(id);
    }

    /**
     * 新增租客信息信息
     * 
     * @param rentalHousingInfo 出租房信息对象
     */
    public void insertTenantUserInfo(RentalHousingInfo rentalHousingInfo)
    {
        List<TenantUserInfo> tenantUserInfoList = rentalHousingInfo.getTenantUserInfoList();
        Long id = rentalHousingInfo.getId();
        if (StringUtils.isNotNull(tenantUserInfoList))
        {
            List<TenantUserInfo> list = new ArrayList<TenantUserInfo>();
            for (TenantUserInfo tenantUserInfo : tenantUserInfoList)
            {
                tenantUserInfo.setRentalHousingId(id);
                list.add(tenantUserInfo);
            }
            if (list.size() > 0)
            {
                rentalHousingInfoMapper.batchTenantUserInfo(list);
            }
        }
    }
}

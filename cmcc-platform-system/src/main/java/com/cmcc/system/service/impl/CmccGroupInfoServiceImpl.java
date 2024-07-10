package com.cmcc.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cmcc.system.mapper.CmccGroupInfoMapper;
import com.cmcc.system.domain.CmccGroupInfo;
import com.cmcc.system.service.ICmccGroupInfoService;

/**
 * 楼宇信息维护Service业务层处理
 * 
 * @author terry
 * @date 2024-06-20
 */
@Service
public class CmccGroupInfoServiceImpl implements ICmccGroupInfoService 
{
    @Autowired
    private CmccGroupInfoMapper cmccGroupInfoMapper;

    /**
     * 查询楼宇信息维护
     * 
     * @param id 楼宇信息维护主键
     * @return 楼宇信息维护
     */
    @Override
    public CmccGroupInfo selectCmccGroupInfoById(Long id)
    {
        return cmccGroupInfoMapper.selectCmccGroupInfoById(id);
    }

    /**
     * 查询楼宇信息维护列表
     * 
     * @param cmccGroupInfo 楼宇信息维护
     * @return 楼宇信息维护
     */
    @Override
    public List<CmccGroupInfo> selectCmccGroupInfoList(CmccGroupInfo cmccGroupInfo)
    {
        return cmccGroupInfoMapper.selectCmccGroupInfoList(cmccGroupInfo);
    }

    /**
     * 新增楼宇信息维护
     * 
     * @param cmccGroupInfo 楼宇信息维护
     * @return 结果
     */
    @Override
    public int insertCmccGroupInfo(CmccGroupInfo cmccGroupInfo)
    {
        return cmccGroupInfoMapper.insertCmccGroupInfo(cmccGroupInfo);
    }

    /**
     * 修改楼宇信息维护
     * 
     * @param cmccGroupInfo 楼宇信息维护
     * @return 结果
     */
    @Override
    public int updateCmccGroupInfo(CmccGroupInfo cmccGroupInfo)
    {
        return cmccGroupInfoMapper.updateCmccGroupInfo(cmccGroupInfo);
    }

    /**
     * 批量删除楼宇信息维护
     * 
     * @param ids 需要删除的楼宇信息维护主键
     * @return 结果
     */
    @Override
    public int deleteCmccGroupInfoByIds(Long[] ids)
    {
        return cmccGroupInfoMapper.deleteCmccGroupInfoByIds(ids);
    }

    /**
     * 删除楼宇信息维护信息
     * 
     * @param id 楼宇信息维护主键
     * @return 结果
     */
    @Override
    public int deleteCmccGroupInfoById(Long id)
    {
        return cmccGroupInfoMapper.deleteCmccGroupInfoById(id);
    }
}

package com.cmcc.system.mapper;

import java.util.List;
import com.cmcc.system.domain.CmccGroupInfo;

/**
 * 楼宇信息维护Mapper接口
 * 
 * @author terry
 * @date 2024-06-20
 */
public interface CmccGroupInfoMapper 
{
    /**
     * 查询楼宇信息维护
     * 
     * @param id 楼宇信息维护主键
     * @return 楼宇信息维护
     */
    public CmccGroupInfo selectCmccGroupInfoById(Long id);

    /**
     * 查询楼宇信息维护列表
     * 
     * @param cmccGroupInfo 楼宇信息维护
     * @return 楼宇信息维护集合
     */
    public List<CmccGroupInfo> selectCmccGroupInfoList(CmccGroupInfo cmccGroupInfo);

    /**
     * 新增楼宇信息维护
     * 
     * @param cmccGroupInfo 楼宇信息维护
     * @return 结果
     */
    public int insertCmccGroupInfo(CmccGroupInfo cmccGroupInfo);

    /**
     * 修改楼宇信息维护
     * 
     * @param cmccGroupInfo 楼宇信息维护
     * @return 结果
     */
    public int updateCmccGroupInfo(CmccGroupInfo cmccGroupInfo);

    /**
     * 删除楼宇信息维护
     * 
     * @param id 楼宇信息维护主键
     * @return 结果
     */
    public int deleteCmccGroupInfoById(Long id);

    /**
     * 批量删除楼宇信息维护
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCmccGroupInfoByIds(Long[] ids);
}

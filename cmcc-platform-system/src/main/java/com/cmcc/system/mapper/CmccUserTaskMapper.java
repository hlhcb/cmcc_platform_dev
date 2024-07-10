package com.cmcc.system.mapper;

import java.util.List;
import java.util.Map;

import com.cmcc.system.domain.AppEnterpriseTask;
import com.cmcc.system.domain.AppGroupTaskList;
import com.cmcc.system.domain.CmccUserTask;

/**
 * 用户任务Mapper接口
 * 
 * @author terry
 * @date 2024-06-20
 */
public interface CmccUserTaskMapper 
{

    public List<AppEnterpriseTask> selectCmccEnterpriseTsk(Map<String, Object> map);


    public List<AppGroupTaskList> selectCmccUserTask(Map<String, Object> map);
    /**
     * 查询用户任务
     * 
     * @param id 用户任务主键
     * @return 用户任务
     */
    public CmccUserTask selectCmccUserTaskById(Long id);

    /**
     * 查询用户任务列表
     * 
     * @param cmccUserTask 用户任务
     * @return 用户任务集合
     */
    public List<CmccUserTask> selectCmccUserTaskList(CmccUserTask cmccUserTask);

    /**
     * 新增用户任务
     * 
     * @param cmccUserTask 用户任务
     * @return 结果
     */
    public int insertCmccUserTask(CmccUserTask cmccUserTask);

    /**
     * 修改用户任务
     * 
     * @param cmccUserTask 用户任务
     * @return 结果
     */
    public int updateCmccUserTask(CmccUserTask cmccUserTask);

    /**
     * 删除用户任务
     * 
     * @param id 用户任务主键
     * @return 结果
     */
    public int deleteCmccUserTaskById(Long id);

    /**
     * 批量删除用户任务
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCmccUserTaskByIds(Long[] ids);

    /**
     * 批量插入用户任务
     *
     * @param successList 需要插入的任务集合
     * @return 结果
     */

    int insertBatchUserTask(List<CmccUserTask> successList);
}

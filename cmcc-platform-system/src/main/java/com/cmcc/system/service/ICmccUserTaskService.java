package com.cmcc.system.service;

import java.util.List;
import java.util.Map;

import com.cmcc.common.core.domain.entity.dto.ImportErrorItem;
import com.cmcc.common.core.domain.model.LoginUser;
import com.cmcc.system.domain.AppEnterpriseTask;
import com.cmcc.system.domain.AppGroupTaskList;
import com.cmcc.system.domain.CmccUserTask;
import org.springframework.web.multipart.MultipartFile;

/**
 * 用户任务Service接口
 * 
 * @author terry
 * @date 2024-06-20
 */
public interface ICmccUserTaskService 
{

    public List<AppGroupTaskList> selectCmccUserTask(Map<String, Object> map);

    public List<AppEnterpriseTask> selectCmccEnterpriseTsk(Map<String, Object> map);


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
     * 批量删除用户任务
     * 
     * @param ids 需要删除的用户任务主键集合
     * @return 结果
     */
    public int deleteCmccUserTaskByIds(Long[] ids);

    /**
     * 删除用户任务信息
     * 
     * @param id 用户任务主键
     * @return 结果
     */
    public int deleteCmccUserTaskById(Long id);

    /**
     * create time: 2024/7/5 14:37
     * @param file excel文件
     * @param loginUser 用户信息
     * @return java.util.List<com.cmcc.common.core.domain.entity.dto.ImportErrorItem>
     * description: 批量导入用户任务
     */

    List<ImportErrorItem> importUserTask(MultipartFile file, LoginUser loginUser);

}

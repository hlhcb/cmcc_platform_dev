package com.cmcc.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cmcc.common.constant.Constants;
import com.cmcc.common.core.domain.entity.SysUser;
import com.cmcc.common.core.domain.entity.dto.ImportErrorItem;
import com.cmcc.common.core.domain.model.LoginUser;
import com.cmcc.common.exception.ServiceException;
import com.cmcc.common.utils.DateUtils;
import com.cmcc.common.utils.ExcelUtils;
import com.cmcc.common.utils.StringUtils;
import com.cmcc.system.domain.*;
import com.cmcc.system.mapper.CmccGroupInfoMapper;
import com.cmcc.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cmcc.system.mapper.CmccUserTaskMapper;
import com.cmcc.system.service.ICmccUserTaskService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 用户任务Service业务层处理
 * 
 * @author terry
 * @date 2024-06-20
 */
@Service
public class CmccUserTaskServiceImpl implements ICmccUserTaskService 
{
    @Autowired
    private CmccUserTaskMapper cmccUserTaskMapper;

    @Autowired
    private CmccGroupInfoMapper cmccGroupInfoMapper;
    
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public List<AppGroupTaskList> selectCmccUserTask(Map<String, Object> map){
        return cmccUserTaskMapper.selectCmccUserTask(map);
    }

    @Override
    public List<AppEnterpriseTask> selectCmccEnterpriseTsk(Map<String, Object> map){
        return cmccUserTaskMapper.selectCmccEnterpriseTsk(map);
    }
    /**
     * 查询用户任务
     * 
     * @param id 用户任务主键
     * @return 用户任务
     */
    @Override
    public CmccUserTask selectCmccUserTaskById(Long id)
    {
        return cmccUserTaskMapper.selectCmccUserTaskById(id);
    }

    /**
     * 查询用户任务列表
     * 
     * @param cmccUserTask 用户任务
     * @return 用户任务
     */
    @Override
    public List<CmccUserTask> selectCmccUserTaskList(CmccUserTask cmccUserTask)
    {
        return cmccUserTaskMapper.selectCmccUserTaskList(cmccUserTask);
    }

    /**
     * 新增用户任务
     * 
     * @param cmccUserTask 用户任务
     * @return 结果
     */
    @Override
    public int insertCmccUserTask(CmccUserTask cmccUserTask)
    {
        cmccUserTask.setCreateTime(DateUtils.getNowDate());
        return cmccUserTaskMapper.insertCmccUserTask(cmccUserTask);
    }

    /**
     * 修改用户任务
     * 
     * @param cmccUserTask 用户任务
     * @return 结果
     */
    @Override
    public int updateCmccUserTask(CmccUserTask cmccUserTask)
    {
        return cmccUserTaskMapper.updateCmccUserTask(cmccUserTask);
    }

    /**
     * 批量删除用户任务
     * 
     * @param ids 需要删除的用户任务主键
     * @return 结果
     */
    @Override
    public int deleteCmccUserTaskByIds(Long[] ids)
    {
        return cmccUserTaskMapper.deleteCmccUserTaskByIds(ids);
    }

    /**
     * 删除用户任务信息
     * 
     * @param id 用户任务主键
     * @return 结果
     */
    @Override
    public int deleteCmccUserTaskById(Long id)
    {
        return cmccUserTaskMapper.deleteCmccUserTaskById(id);
    }

    /**
     * create time: 2024/7/5 14:41
     * @param file excel文件
     * @param loginUser 登录用户
     * @return java.util.List<com.cmcc.common.core.domain.entity.dto.ImportErrorItem>
     * description: 批量导入
     */
    @Override
    public List<ImportErrorItem> importUserTask(MultipartFile file, LoginUser loginUser) {
        //批量导入
        List<List<String>> dataList = ExcelUtils.readExcel(file, Constants.EXCEL_TITLE_USER_TASK, 0);

        //错误数据行
        List<ImportErrorItem> errorList = new ArrayList<>();

        //正确数据行
        List<CmccUserTask> successList = new ArrayList<>();

        int dataRowNum = 1;
        for (List<String> row : dataList) {
            if (errorList.size() > Constants.LENGTH_50) {
                throw new ServiceException("错误数据超过" + Constants.LENGTH_50 + "行，请认真检查数据后再导入");
            }
            dataRowNum++;
            List<String> errorItemList = new ArrayList<>();
            int index = 0;
            //区县名称
            String deptName = row.get(index++);
            //客户经理
            String customerManagerName = row.get(index++);
            //集团名称
            String groupName = row.get(index++);

            String startTime = row.get(index++);

            String endTime = row.get(index++);

            if (StringUtils.isEmpty(deptName)||StringUtils.isEmpty(customerManagerName)||StringUtils.isEmpty(groupName)){
                errorItemList.add("基本信息不完整");

                ImportErrorItem errorItem = new ImportErrorItem();
                errorItem.setRowNum(dataRowNum);
                errorItem.setErrorItemList(errorItemList);
                errorList.add(errorItem);
                continue;
            }
            Long deptId = AreaTypeEnum.getValueByName(deptName);
            if( 0L == deptId){
                errorItemList.add("区县名称不存在");

                ImportErrorItem errorItem = new ImportErrorItem();
                errorItem.setRowNum(dataRowNum);
                errorItem.setErrorItemList(errorItemList);
                errorList.add(errorItem);
                continue;
            }
            CmccGroupInfo cmccGroupInfo = new CmccGroupInfo();
            cmccGroupInfo.setGroupName(groupName);
            cmccGroupInfo.setDeptId(deptId);
            cmccGroupInfo.setDeptName(deptName);
            List<CmccGroupInfo> cmccGroupInfos = cmccGroupInfoMapper.selectCmccGroupInfoList(cmccGroupInfo);
            if (cmccGroupInfos.isEmpty()){
                errorItemList.add("集团名称不匹配");
            }
            if (StringUtils.isEmpty(startTime)||StringUtils.isEmpty(endTime)||!startTime.matches("^-?\\d+$")||!endTime.matches("^-?\\d+$"))
            {
                errorItemList.add("时间格式错误");
                ImportErrorItem errorItem = new ImportErrorItem();
                errorItem.setRowNum(dataRowNum);
                errorItem.setErrorItemList(errorItemList);
                errorList.add(errorItem);
                continue;
            }
            startTime = StringUtils.convertExcelDateToDate(Integer.parseInt(startTime));
            endTime = StringUtils.convertExcelDateToDate(Integer.parseInt(endTime));
            if (!StringUtils.isValidDateTime(startTime) || !StringUtils.isValidDateTime(endTime)){
                errorItemList.add("时间格式错误");
            }else {
                if (startTime.compareTo(endTime) > 0){
                    errorItemList.add("开始时间不能大于结束时间");
                }else {
                    if (DateUtils.getNowDate().before(DateUtils.parseDate(startTime))||DateUtils.getNowDate().after(DateUtils.parseDate(endTime))){
                        errorItemList.add("时间不在当前时间范围内");
                    }
                }
            }

            SysUser sysUser = new SysUser();
            List<SysUser> sysUsers = sysUserMapper.selectUserList(sysUser);
            Long userId= 0L;
            boolean flag = false;
            for (SysUser user : sysUsers){
                if (user.getNickName().equals(customerManagerName)) {
                    flag = true;
                    userId = user.getUserId();
                    break;
                }
            }
            if (!flag){
                errorItemList.add("客户经理不存在");
            }
            if (!errorItemList.isEmpty() || !errorList.isEmpty()){
                ImportErrorItem errorItem = new ImportErrorItem();
                errorItem.setRowNum(dataRowNum);
                errorItem.setErrorItemList(errorItemList);
                errorList.add(errorItem);
                continue;
            }
            CmccUserTask cmccUserTask = new CmccUserTask();


            cmccUserTask.setDeptId(deptId);
            cmccUserTask.setDeptName(deptName);

            cmccUserTask.setUserId(userId);
            cmccUserTask.setUserName(customerManagerName);

            cmccUserTask.setGroupId(cmccGroupInfos.get(0).getId());
            cmccUserTask.setGroupName(cmccGroupInfos.get(0).getGroupName());

            cmccUserTask.setTaskStatus("0");

            cmccUserTask.setGroupType(cmccGroupInfos.get(0).getGroupType());

            cmccUserTask.setCreateUserId(loginUser.getUser().getUserId());
            cmccUserTask.setCreateUserName(loginUser.getUser().getNickName());

            cmccUserTask.setCreateTime(DateUtils.getNowDate());
            cmccUserTask.setTaskBeginTime(StringUtils.parseDate(startTime));
            cmccUserTask.setTaskEndTime(StringUtils.parseDate(endTime));

            cmccUserTask.setTaskType(3);
            successList.add(cmccUserTask);
        }
        if (successList.isEmpty()) {
            return errorList;
        }
        cmccUserTaskMapper.insertBatchUserTask(successList);
        return errorList;
    }
}

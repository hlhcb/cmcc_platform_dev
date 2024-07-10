package com.cmcc.system.service.impl;

import java.util.List;

import com.cmcc.common.utils.uuid.UUID;
import com.cmcc.system.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cmcc.system.mapper.PeopleReturnDetailMapper;
import com.cmcc.system.service.IPeopleReturnDetailService;

/**
 * 返乡人员明细Service业务层处理
 * 
 * @author terry
 * @date 2023-12-24
 */
@Service
public class PeopleReturnDetailServiceImpl implements IPeopleReturnDetailService 
{
    @Autowired
    private PeopleReturnDetailMapper peopleReturnDetailMapper;

    /**
     * 查询返乡人员明细
     * 
     * @param id 返乡人员明细主键
     * @return 返乡人员明细
     */
    @Override
    public PeopleReturnDetail selectPeopleReturnDetailById(String id)
    {
        return peopleReturnDetailMapper.selectPeopleReturnDetailById(id);
    }

    /**
     * 查询返乡人员明细列表
     * 
     * @param peopleReturnDetail 返乡人员明细
     * @return 返乡人员明细
     */
    @Override
    public List<PeopleReturnDetail> selectPeopleReturnDetailList(PeopleReturnDetail peopleReturnDetail)
    {
        return peopleReturnDetailMapper.selectPeopleReturnDetailList(peopleReturnDetail);
    }


    @Override
    public List<TotalReturnPeople> selectTotalReturnPeopleByYear(String currYear){

        return peopleReturnDetailMapper.selectTotalReturnPeopleByYear(currYear);
    }

    @Override
    public List<ReturnRankRecord> selectReturnRankRecord(String currYear){

        List<ReturnRankRecord> returnRankRecords = peopleReturnDetailMapper.selectReturnRankRecord(currYear);
        int i = 0;
        for (ReturnRankRecord obj: returnRankRecords)
        {
            i++;
            obj.setRank(i);
        }
        return returnRankRecords;
    }

    @Override
    public List<ReturnTypeDeatail> selectReturnTypeDeatail(String currYear){
        return peopleReturnDetailMapper.selectReturnTypeDeatail(currYear);
    }

    @Override
    public int selectPeopleCountByDate(String currYear)
    {
        return peopleReturnDetailMapper.selectPeopleCountByDate(currYear);
    }
    /**
     * 新增返乡人员明细
     * 
     * @param peopleReturnDetail 返乡人员明细
     * @return 结果
     */
    @Override
    public int insertPeopleReturnDetail(PeopleReturnDetail peopleReturnDetail)
    {

        peopleReturnDetail.setId(UUID.randomUUID().toString());
        return peopleReturnDetailMapper.insertPeopleReturnDetail(peopleReturnDetail);
    }

    /**
     * 修改返乡人员明细
     * 
     * @param peopleReturnDetail 返乡人员明细
     * @return 结果
     */
    @Override
    public int updatePeopleReturnDetail(PeopleReturnDetail peopleReturnDetail)
    {
        return peopleReturnDetailMapper.updatePeopleReturnDetail(peopleReturnDetail);
    }

    /**
     * 批量删除返乡人员明细
     * 
     * @param ids 需要删除的返乡人员明细主键
     * @return 结果
     */
    @Override
    public int deletePeopleReturnDetailByIds(String[] ids)
    {
        return peopleReturnDetailMapper.deletePeopleReturnDetailByIds(ids);
    }

    /**
     * 删除返乡人员明细信息
     * 
     * @param id 返乡人员明细主键
     * @return 结果
     */
    @Override
    public int deletePeopleReturnDetailById(String id)
    {
        return peopleReturnDetailMapper.deletePeopleReturnDetailById(id);
    }

    @Override
    public List<MapFlightVo> selectMapFlightByYear(String currYear)
    {
        return peopleReturnDetailMapper.selectMapFlightByYear(currYear);
    }

    @Override
    public List<CountyReturnVo> selectCountyCountByDate(String currYear)
    {
        return peopleReturnDetailMapper.selectCountyCountByDate(currYear);
    }
}

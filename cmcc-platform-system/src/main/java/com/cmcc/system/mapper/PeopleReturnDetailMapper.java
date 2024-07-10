package com.cmcc.system.mapper;

import java.util.List;

import com.cmcc.system.domain.*;

/**
 * 返乡人员明细Mapper接口
 * 
 * @author terry
 * @date 2023-12-24
 */
public interface PeopleReturnDetailMapper 
{
    /**
     * 查询返乡人员明细
     * 
     * @param id 返乡人员明细主键
     * @return 返乡人员明细
     */
    public PeopleReturnDetail selectPeopleReturnDetailById(String id);

    /**
     * 查询返乡人员明细列表
     * 
     * @param peopleReturnDetail 返乡人员明细
     * @return 返乡人员明细集合
     */
    public List<PeopleReturnDetail> selectPeopleReturnDetailList(PeopleReturnDetail peopleReturnDetail);

    public List<TotalReturnPeople>selectTotalReturnPeopleByYear(String currYear);

    public List<ReturnRankRecord>selectReturnRankRecord(String currYear);

    public List<ReturnTypeDeatail>selectReturnTypeDeatail(String currYear);

    public int selectPeopleCountByDate(String currYear);


    public List<MapFlightVo> selectMapFlightByYear(String currYear);

    public List<CountyReturnVo> selectCountyCountByDate(String currYear);


    /**
     * 新增返乡人员明细
     * 
     * @param peopleReturnDetail 返乡人员明细
     * @return 结果
     */
    public int insertPeopleReturnDetail(PeopleReturnDetail peopleReturnDetail);

    /**
     * 修改返乡人员明细
     * 
     * @param peopleReturnDetail 返乡人员明细
     * @return 结果
     */
    public int updatePeopleReturnDetail(PeopleReturnDetail peopleReturnDetail);

    /**
     * 删除返乡人员明细
     * 
     * @param id 返乡人员明细主键
     * @return 结果
     */
    public int deletePeopleReturnDetailById(String id);

    /**
     * 批量删除返乡人员明细
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePeopleReturnDetailByIds(String[] ids);
}

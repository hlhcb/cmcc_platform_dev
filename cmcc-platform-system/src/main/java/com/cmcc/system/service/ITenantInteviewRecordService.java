package com.cmcc.system.service;

import java.util.List;
import com.cmcc.system.domain.TenantInteviewRecord;

/**
 * 房东访问Service接口
 * 
 * @author terry
 * @date 2024-02-24
 */
public interface ITenantInteviewRecordService 
{
    /**
     * 查询房东访问
     * 
     * @param id 房东访问主键
     * @return 房东访问
     */
    public TenantInteviewRecord selectTenantInteviewRecordById(Long id);

    /**
     * 查询房东访问列表
     * 
     * @param tenantInteviewRecord 房东访问
     * @return 房东访问集合
     */
    public List<TenantInteviewRecord> selectTenantInteviewRecordList(TenantInteviewRecord tenantInteviewRecord);

    /**
     * 新增房东访问
     * 
     * @param tenantInteviewRecord 房东访问
     * @return 结果
     */
    public int insertTenantInteviewRecord(TenantInteviewRecord tenantInteviewRecord);

    /**
     * 修改房东访问
     * 
     * @param tenantInteviewRecord 房东访问
     * @return 结果
     */
    public int updateTenantInteviewRecord(TenantInteviewRecord tenantInteviewRecord);

    /**
     * 批量删除房东访问
     * 
     * @param ids 需要删除的房东访问主键集合
     * @return 结果
     */
    public int deleteTenantInteviewRecordByIds(Long[] ids);

    /**
     * 删除房东访问信息
     * 
     * @param id 房东访问主键
     * @return 结果
     */
    public int deleteTenantInteviewRecordById(Long id);
}

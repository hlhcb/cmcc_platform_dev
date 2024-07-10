package com.cmcc.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cmcc.system.mapper.TenantInteviewRecordMapper;
import com.cmcc.system.domain.TenantInteviewRecord;
import com.cmcc.system.service.ITenantInteviewRecordService;

/**
 * 房东访问Service业务层处理
 * 
 * @author terry
 * @date 2024-02-24
 */
@Service
public class TenantInteviewRecordServiceImpl implements ITenantInteviewRecordService 
{
    @Autowired
    private TenantInteviewRecordMapper tenantInteviewRecordMapper;

    /**
     * 查询房东访问
     * 
     * @param id 房东访问主键
     * @return 房东访问
     */
    @Override
    public TenantInteviewRecord selectTenantInteviewRecordById(Long id)
    {
        return tenantInteviewRecordMapper.selectTenantInteviewRecordById(id);
    }

    /**
     * 查询房东访问列表
     * 
     * @param tenantInteviewRecord 房东访问
     * @return 房东访问
     */
    @Override
    public List<TenantInteviewRecord> selectTenantInteviewRecordList(TenantInteviewRecord tenantInteviewRecord)
    {
        return tenantInteviewRecordMapper.selectTenantInteviewRecordList(tenantInteviewRecord);
    }

    /**
     * 新增房东访问
     * 
     * @param tenantInteviewRecord 房东访问
     * @return 结果
     */
    @Override
    public int insertTenantInteviewRecord(TenantInteviewRecord tenantInteviewRecord)
    {
        return tenantInteviewRecordMapper.insertTenantInteviewRecord(tenantInteviewRecord);
    }

    /**
     * 修改房东访问
     * 
     * @param tenantInteviewRecord 房东访问
     * @return 结果
     */
    @Override
    public int updateTenantInteviewRecord(TenantInteviewRecord tenantInteviewRecord)
    {
        return tenantInteviewRecordMapper.updateTenantInteviewRecord(tenantInteviewRecord);
    }

    /**
     * 批量删除房东访问
     * 
     * @param ids 需要删除的房东访问主键
     * @return 结果
     */
    @Override
    public int deleteTenantInteviewRecordByIds(Long[] ids)
    {
        return tenantInteviewRecordMapper.deleteTenantInteviewRecordByIds(ids);
    }

    /**
     * 删除房东访问信息
     * 
     * @param id 房东访问主键
     * @return 结果
     */
    @Override
    public int deleteTenantInteviewRecordById(Long id)
    {
        return tenantInteviewRecordMapper.deleteTenantInteviewRecordById(id);
    }
}

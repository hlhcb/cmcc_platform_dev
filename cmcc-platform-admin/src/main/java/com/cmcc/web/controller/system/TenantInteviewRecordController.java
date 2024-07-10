package com.cmcc.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cmcc.common.annotation.Log;
import com.cmcc.common.core.controller.BaseController;
import com.cmcc.common.core.domain.AjaxResult;
import com.cmcc.common.enums.BusinessType;
import com.cmcc.system.domain.TenantInteviewRecord;
import com.cmcc.system.service.ITenantInteviewRecordService;
import com.cmcc.common.utils.poi.ExcelUtil;
import com.cmcc.common.core.page.TableDataInfo;

/**
 * 房东访问Controller
 * 
 * @author terry
 * @date 2024-02-24
 */
@RestController
@RequestMapping("/officer/inteviewrecord")
public class TenantInteviewRecordController extends BaseController
{
    @Autowired
    private ITenantInteviewRecordService tenantInteviewRecordService;

    /**
     * 查询房东访问列表
     */
    @PreAuthorize("@ss.hasPermi('officer:inteviewrecord:list')")
    @GetMapping("/list")
    public TableDataInfo list(TenantInteviewRecord tenantInteviewRecord)
    {
        startPage();
        List<TenantInteviewRecord> list = tenantInteviewRecordService.selectTenantInteviewRecordList(tenantInteviewRecord);
        return getDataTable(list);
    }

    /**
     * 导出房东访问列表
     */
    @PreAuthorize("@ss.hasPermi('officer:inteviewrecord:export')")
    @Log(title = "房东访问", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TenantInteviewRecord tenantInteviewRecord)
    {
        List<TenantInteviewRecord> list = tenantInteviewRecordService.selectTenantInteviewRecordList(tenantInteviewRecord);
        ExcelUtil<TenantInteviewRecord> util = new ExcelUtil<TenantInteviewRecord>(TenantInteviewRecord.class);
        util.exportExcel(response, list, "房东访问数据");
    }

    /**
     * 获取房东访问详细信息
     */
    @PreAuthorize("@ss.hasPermi('officer:inteviewrecord:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tenantInteviewRecordService.selectTenantInteviewRecordById(id));
    }

    /**
     * 新增房东访问
     */
    @PreAuthorize("@ss.hasPermi('officer:inteviewrecord:add')")
    @Log(title = "房东访问", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TenantInteviewRecord tenantInteviewRecord)
    {
        return toAjax(tenantInteviewRecordService.insertTenantInteviewRecord(tenantInteviewRecord));
    }

    /**
     * 修改房东访问
     */
    @PreAuthorize("@ss.hasPermi('officer:inteviewrecord:edit')")
    @Log(title = "房东访问", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TenantInteviewRecord tenantInteviewRecord)
    {
        return toAjax(tenantInteviewRecordService.updateTenantInteviewRecord(tenantInteviewRecord));
    }

    /**
     * 删除房东访问
     */
    @PreAuthorize("@ss.hasPermi('officer:inteviewrecord:remove')")
    @Log(title = "房东访问", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tenantInteviewRecordService.deleteTenantInteviewRecordByIds(ids));
    }
}

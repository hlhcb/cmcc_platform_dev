<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="80px">
      <el-form-item label="区县" prop="deptId">
        <el-select v-model="queryParams.deptId" filterable placeholder="请选择区县"   clearable @change="selctArea($event)">
          <el-option
              v-for="dict in areaList"
              :key="dict.value"
              :label="dict.text"
              :value="dict.value"
            ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="楼宇/园区" prop="groupId">
        <el-select v-model="queryParams.groupId" filterable placeholder="请选择楼宇或者园区"   clearable>
          <el-option
            v-for="dict in groupList"
            :key="dict.id"
            :label="dict.groupName"
            :value="dict.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="企业名称" prop="enterpriseName">
        <el-input
          v-model="queryParams.enterpriseName"
          placeholder="请输入企业名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="客户名称" prop="customerName">
        <el-input
          v-model="queryParams.customerName"
          placeholder="请输入客户名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="客户电话" prop="customerTel">
        <el-input
          v-model="queryParams.customerTel"
          placeholder="请输入客户电话"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="企业状态" prop="enterpriseStatus">
        <el-select v-model="queryParams.enterpriseStatus" placeholder="请选择企业状态" clearable>
          <el-option
            v-for="dict in dict.type.enterprise_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['customer:cmccenterpriseinfo:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['customer:cmccenterpriseinfo:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['customer:cmccenterpriseinfo:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['customer:cmccenterpriseinfo:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>
    <el-table v-loading="loading" :data="cmccenterpriseinfoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="区县" align="center" prop="deptName" />
      <el-table-column label="楼宇/园区" align="center" prop="groupName" />
      <el-table-column label="企业名称" align="center" prop="enterpriseName" />
      <el-table-column label="企业地址" align="center" prop="enterpriseAddr" />
      <el-table-column label="客户名称" align="center" prop="customerName" />
      <el-table-column label="客户电话" align="center" prop="customerTel" />
      <el-table-column label="客户职位" align="center" prop="customerPosition" />
      <!-- <el-table-column label="法人姓名" align="center" prop="corporation" /> -->
      <el-table-column label="企业状态" align="center" prop="enterpriseStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.enterprise_type" :value="scope.row.enterpriseStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['customer:cmccenterpriseinfo:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['customer:cmccenterpriseinfo:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改企业信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="区县" prop="deptIdObj">
          <el-select v-model="form.deptIdObj" filterable placeholder="请选择区县"  @change="selectAddArea($event)">
            <el-option
              v-for="dict in areaList"
              :key="dict.value"
              :label="dict.text"
              :value="{value:dict.value,label:dict.text}"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="楼宇/园区" prop="groupId">
          <el-select v-model="form.groupId" filterable placeholder="请选择楼宇/园区"  @change="selectGroupInfo($event)">
            <el-option
              v-for="dict in addgroupList"
              :key="dict.id"
              :label="dict.groupName"
              :value="dict.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="企业名称" prop="enterpriseName">
          <el-input v-model="form.enterpriseName" placeholder="请输入企业名称" />
        </el-form-item>
        <el-form-item label="企业地址" prop="enterpriseAddr">
          <el-input v-model="form.enterpriseAddr" placeholder="请输入企业地址" />
        </el-form-item>
        <el-form-item label="客户名称" prop="customerName">
          <el-input v-model="form.customerName" placeholder="请输入客户名称" />
        </el-form-item>
        <el-form-item label="客户电话" prop="customerTel">
          <el-input v-model="form.customerTel" placeholder="请输入客户电话" />
        </el-form-item>
        <el-form-item label="客户职位" prop="customerPosition">
          <el-input v-model="form.customerPosition" placeholder="请输入客户职位" />
        </el-form-item>
        <!-- <el-form-item label="法人姓名" prop="corporation">
          <el-input v-model="form.corporation" placeholder="请输入法人姓名" />
        </el-form-item> -->
        <el-form-item label="企业状态" prop="enterpriseStatus">
          <el-select v-model="form.enterpriseStatus" placeholder="请选择企业状态">
            <el-option
              v-for="dict in dict.type.enterprise_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listCmccenterpriseinfo, getCmccenterpriseinfo, delCmccenterpriseinfo, addCmccenterpriseinfo, updateCmccenterpriseinfo } from "@/api/customer/cmccenterpriseinfo";
import {getAreaList,getCmccGroupList} from "@/api/customer/cmccgroupinfo";
export default {
  name: "Cmccenterpriseinfo",
  dicts: ['enterprise_type'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 企业信息表格数据
      cmccenterpriseinfoList: [],
      // 区县字典字段
      areaList: [],

      groupList: [],
      addgroupList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        groupId: null,
        enterpriseName: null,
        enterpriseAddr: null,
        customerName: null,
        customerTel: null,
        customerPosition: null,
        createUserId: null,
        createUserName: null,
        updateUserId: null,
        updateUserName: null,
        corporation: null,
        enterpriseStatus: null,
        deptId: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  
  created() {
    
    this.getarealist();
    this.getList();
  },
  methods: {
    selectAddArea (value) {
      console.log("selectAddArea"+JSON.stringify(value))
      this.form.groupId = null
      this.form.groupName = null
      this.form.groupIdObj = null
      this.form.deptName = value.label
      this.form.deptId = value.value
      if (value.value !== undefined && value.value != null && value.value != "") {
        this.getaddCmccGroup(value.value);
      } else {
        this.addgroupList = [];
      }
    },
    selctArea(value) {
      console.log("selctArea"+JSON.stringify(value))
      this.queryParams.groupId = null
      this.queryParams.groupName = null
      
      if (value !== undefined && value != null && value != "") {
        this.getCmccGroup(value);
      } else {
        this.groupList = [];
      }
    },
    getarealist() {
      getAreaList().then(response => {
        this.areaList = response.data;
      });
    },
    selectGroupInfo(value) {
        console.log("enter selectGroupInfo" + JSON.stringify(value))
        console.log("enter selectGroupInfo" + JSON.stringify(this.addgroupList))
        for (var i=0;i<this.addgroupList.length;i++) { 
           if (value == this.addgroupList[i].id) {
            this.form.groupName = this.addgroupList[i].groupName
           }
        }
    },
    getCmccGroup(deptId){
      getCmccGroupList(deptId).then(response => {
        this.groupList = response.data
      })
    },
    getaddCmccGroup(deptId){
      getCmccGroupList(deptId).then(response => {
        this.addgroupList = response.data
      })
    },
    /** 查询企业信息列表 */
    getList() {
      this.loading = true;
      listCmccenterpriseinfo(this.queryParams).then(response => {
        this.cmccenterpriseinfoList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        groupId: null,
        enterpriseName: null,
        enterpriseAddr: null,
        customerName: null,
        customerTel: null,
        customerPosition: null,
        createUserId: null,
        createUserName: null,
        createTime: null,
        updateUserId: null,
        updateUserName: null,
        updateTime: null,
        corporation: null,
        enterpriseStatus: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加企业信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getCmccenterpriseinfo(id).then(response => {
        this.form = response.data;
        this.getaddCmccGroup(response.data.deptId)
        this.form.groupIdObj = {value:response.data.groupId,label:response.data.groupName}
        this.form.deptIdObj = {value:String(response.data.deptId),label:response.data.deptName}
        // console.log("enter getCmccenterpriseinfo："+JSON.stringify(this.form))
        // console.log("enter getCmccenterpriseinfo："+JSON.stringify(this.areaList))
        this.open = true;
        this.title = "修改企业信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateCmccenterpriseinfo(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCmccenterpriseinfo(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除企业信息编号为"' + ids + '"的数据项？').then(function() {
        return delCmccenterpriseinfo(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('customer/cmccenterpriseinfo/export', {
        ...this.queryParams
      }, `cmccenterpriseinfo_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>

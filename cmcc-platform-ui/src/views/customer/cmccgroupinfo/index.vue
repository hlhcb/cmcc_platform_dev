<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <!-- <el-form-item label="部门id" prop="deptId">
        <el-input
          v-model="queryParams.deptId"
          placeholder="请输入部门id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item label="区县名称" prop="deptId">
        <el-select v-model="queryParams.deptId" placeholder="请选择区县" filterable clearable>
            <el-option
              v-for="dict in areaList"
              :key="dict.value"
              :label="dict.text"
              :value="dict.value"
            ></el-option>
          </el-select>
      </el-form-item>
      <el-form-item label="类型" prop="groupType">
        <el-select v-model="queryParams.groupType" placeholder="请选择类型" clearable>
          <el-option
            v-for="dict in dict.type.scene_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="名称" prop="groupName">
        <el-input
          v-model="queryParams.groupName"
          placeholder="请输入名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['customer:cmccgroupinfo:add']"
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
          v-hasPermi="['customer:cmccgroupinfo:edit']"
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
          v-hasPermi="['customer:cmccgroupinfo:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['customer:cmccgroupinfo:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="cmccgroupinfoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="主键id" align="center" prop="id" /> -->
      
      <!-- <el-table-column label="部门id" align="center" prop="deptId" /> -->
      <el-table-column label="区县名称" align="center" prop="deptName" />
      <el-table-column label="类型" align="center" prop="groupType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.scene_type" :value="scope.row.groupType"/>
        </template>
      </el-table-column>
      <el-table-column label="名称" align="center" prop="groupName" />
      <el-table-column label="详细地址" align="center" prop="groupAddr" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['customer:cmccgroupinfo:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['customer:cmccgroupinfo:remove']"
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

    <!-- 添加或修改楼宇信息维护对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <!-- <el-form-item label="部门id" prop="deptId">
          <el-input v-model="form.deptId" placeholder="请输入部门id" />
        </el-form-item> -->
        <el-form-item label="区县名称" prop="deptIdObj">
            <el-select v-model="form.deptName" placeholder="请选择区县" @change="selectArea($event)" filterable>
            <el-option
              v-for="dict in areaList"
              :key="dict.value"
              :label="dict.text"
              :value="{label:dict.text,value:dict.value}"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="类型" prop="groupType">
          <el-select v-model="form.groupType" placeholder="请选择类型">
            <el-option
              v-for="dict in dict.type.scene_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="名称" prop="groupName">
          <el-input v-model="form.groupName" placeholder="请输入名称" />
        </el-form-item>
        <el-form-item label="详细地址" prop="groupAddr">
          <el-input v-model="form.groupAddr" placeholder="请输入详细地址" />
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
import { listCmccgroupinfo, getCmccgroupinfo, delCmccgroupinfo, addCmccgroupinfo, updateCmccgroupinfo,getAreaList } from "@/api/customer/cmccgroupinfo";

export default {
  name: "Cmccgroupinfo",
  dicts: ['scene_type'],
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
      // 楼宇信息维护表格数据
      cmccgroupinfoList: [],
      // 区县字典字段
      areaList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        groupName: null,
        deptId: null,
        deptName: null,
        groupType: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
    this.getarealist();
  },
  methods: {
    /** 查询楼宇信息维护列表 */
    getList() {
      this.loading = true;
      listCmccgroupinfo(this.queryParams).then(response => {
       
        // for (var i=0; i < response.rows; i++){
        //   response.rows[i].deptIdStr = String(response.rows[i].deptId)
        // }
        this.cmccgroupinfoList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    getarealist() {
      getAreaList().then(response => {
        this.areaList = response.data;
      });
    },
    selectArea(value) {
      this.form.deptId = parseInt(value.value)
      this.form.deptName = value.label
      console.log("enter selectArea"+JSON.stringify(value))
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
        groupName: null,
        deptId: null,
        // deptIdStr: null,
        deptName: null,
        groupType: null,
        groupAddr: null
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
      this.title = "添加楼宇信息维护";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getCmccgroupinfo(id).then(response => {
        this.form = response.data;
        console.log(JSON.stringify(this.form))
        this.open = true;
        this.title = "修改楼宇信息维护";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateCmccgroupinfo(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCmccgroupinfo(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除楼宇信息维护编号为"' + ids + '"的数据项？').then(function() {
        return delCmccgroupinfo(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('customer/cmccgroupinfo/export', {
        ...this.queryParams
      }, `cmccgroupinfo_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>

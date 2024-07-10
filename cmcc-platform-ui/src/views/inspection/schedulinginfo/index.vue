<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="任务日期" prop="taskDate">
        <el-date-picker clearable
          v-model="queryParams.taskDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择任务日期">
        </el-date-picker>
      </el-form-item>
      <!-- <el-form-item label="值班人员id" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入值班人员id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item label="值班人员姓名" prop="userName">
        <el-input
          v-model="queryParams.userName"
          placeholder="请输入值班人员姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!-- <el-form-item label="部门id" prop="deptId">
        <el-input
          v-model="queryParams.deptId"
          placeholder="请输入部门id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item label="企业名称" prop="deptName">
        <el-input
          v-model="queryParams.deptName"
          placeholder="请输入企业名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!-- <el-form-item label="联防长id" prop="unionUserId">
        <el-input
          v-model="queryParams.unionUserId"
          placeholder="请输入联防长id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item label="联防长名称" prop="unionUserName">
        <el-input
          v-model="queryParams.unionUserName"
          placeholder="请输入联防长名称"
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
          v-hasPermi="['inspection:schedulinginfo:add']"
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
          v-hasPermi="['inspection:schedulinginfo:edit']"
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
          v-hasPermi="['inspection:schedulinginfo:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['inspection:schedulinginfo:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="schedulinginfoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键id" align="center" prop="id" />
      <el-table-column label="任务日期" align="center" prop="taskDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.taskDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="值班人员id" align="center" prop="userId" />
      <el-table-column label="值班人员姓名" align="center" prop="userName" />
      <el-table-column label="部门id" align="center" prop="deptId" />
      <el-table-column label="部门名称" align="center" prop="deptName" />
      <el-table-column label="任务状态" align="center" prop="taskStatus" />
      <el-table-column label="联防长id" align="center" prop="unionUserId" />
      <el-table-column label="联防长名称" align="center" prop="unionUserName" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['inspection:schedulinginfo:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['inspection:schedulinginfo:remove']"
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

    <!-- 添加或修改值班配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="任务日期" prop="taskDate">
          <el-date-picker clearable
            v-model="form.taskDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择任务日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="值班人员id" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入值班人员id" />
        </el-form-item>
        <el-form-item label="值班人员姓名" prop="userName">
          <el-input v-model="form.userName" placeholder="请输入值班人员姓名" />
        </el-form-item>
        <el-form-item label="部门id" prop="deptId">
          <el-input v-model="form.deptId" placeholder="请输入部门id" />
        </el-form-item>
        <el-form-item label="部门名称" prop="deptName">
          <el-input v-model="form.deptName" placeholder="请输入部门名称" />
        </el-form-item>
        <el-form-item label="联防长id" prop="unionUserId">
          <el-input v-model="form.unionUserId" placeholder="请输入联防长id" />
        </el-form-item>
        <el-form-item label="联防长名称" prop="unionUserName">
          <el-input v-model="form.unionUserName" placeholder="请输入联防长名称" />
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
import { listSchedulinginfo, getSchedulinginfo, delSchedulinginfo, addSchedulinginfo, updateSchedulinginfo } from "@/api/inspection/schedulinginfo";

export default {
  name: "Schedulinginfo",
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
      // 值班配置表格数据
      schedulinginfoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        taskDate: null,
        userId: null,
        userName: null,
        deptId: null,
        deptName: null,
        taskStatus: null,
        unionUserId: null,
        unionUserName: null
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
  },
  methods: {
    /** 查询值班配置列表 */
    getList() {
      this.loading = true;
      listSchedulinginfo(this.queryParams).then(response => {
        this.schedulinginfoList = response.rows;
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
        taskDate: null,
        userId: null,
        userName: null,
        deptId: null,
        deptName: null,
        taskStatus: null,
        unionUserId: null,
        unionUserName: null
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
      this.title = "添加值班配置";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getSchedulinginfo(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改值班配置";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateSchedulinginfo(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSchedulinginfo(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除值班配置编号为"' + ids + '"的数据项？').then(function() {
        return delSchedulinginfo(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('inspection/schedulinginfo/export', {
        ...this.queryParams
      }, `schedulinginfo_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>

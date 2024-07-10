<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="联防区id" prop="unionId">
        <el-input
          v-model="queryParams.unionId"
          placeholder="请输入联防区id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联防区名称" prop="unionName">
        <el-input
          v-model="queryParams.unionName"
          placeholder="请输入联防区名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="部门id" prop="deptId">
        <el-input
          v-model="queryParams.deptId"
          placeholder="请输入部门id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="部门名称" prop="deptName">
        <el-input
          v-model="queryParams.deptName"
          placeholder="请输入部门名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="任务类型" prop="taskType">
        <el-select v-model="queryParams.taskType" placeholder="请选择任务类型" clearable>
          <el-option
            v-for="dict in dict.type.work_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="走访人员id" prop="taskUserId">
        <el-input
          v-model="queryParams.taskUserId"
          placeholder="请输入走访人员id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="走访人员名称" prop="taskUserName">
        <el-input
          v-model="queryParams.taskUserName"
          placeholder="请输入走访人员名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="走访地址" prop="taskAddress">
        <el-input
          v-model="queryParams.taskAddress"
          placeholder="请输入走访地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否隐患" prop="troubleFlag">
        <el-select v-model="queryParams.troubleFlag" placeholder="请选择是否隐患" clearable>
          <el-option
            v-for="dict in dict.type.tenant_flag"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="记录时间" prop="recordTime">
        <el-date-picker clearable
          v-model="queryParams.recordTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择记录时间">
        </el-date-picker>
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
          v-hasPermi="['inspection:taskinfo:add']"
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
          v-hasPermi="['inspection:taskinfo:edit']"
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
          v-hasPermi="['inspection:taskinfo:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['inspection:taskinfo:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="taskinfoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键id" align="center" prop="id" />
      <el-table-column label="联防区id" align="center" prop="unionId" />
      <el-table-column label="联防区名称" align="center" prop="unionName" />
      <el-table-column label="部门id" align="center" prop="deptId" />
      <el-table-column label="部门名称" align="center" prop="deptName" />
      <el-table-column label="任务类型" align="center" prop="taskType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.work_type" :value="scope.row.taskType"/>
        </template>
      </el-table-column>
      <el-table-column label="走访人员id" align="center" prop="taskUserId" />
      <el-table-column label="走访人员名称" align="center" prop="taskUserName" />
      <el-table-column label="走访地址" align="center" prop="taskAddress" />
      <el-table-column label="附件地址1" align="center" prop="attachmentOne" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.attachmentOne" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="附件地址2" align="center" prop="attachmentTwo" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.attachmentTwo" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="是否隐患" align="center" prop="troubleFlag">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.tenant_flag" :value="scope.row.troubleFlag"/>
        </template>
      </el-table-column>
      <el-table-column label="走访情况" align="center" prop="recordInfo" />
      <el-table-column label="隐患信息" align="center" prop="troubleInfo" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="记录时间" align="center" prop="recordTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.recordTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['inspection:taskinfo:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['inspection:taskinfo:remove']"
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

    <!-- 添加或修改巡检记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="联防区id" prop="unionId">
          <el-input v-model="form.unionId" placeholder="请输入联防区id" />
        </el-form-item>
        <el-form-item label="联防区名称" prop="unionName">
          <el-input v-model="form.unionName" placeholder="请输入联防区名称" />
        </el-form-item>
        <el-form-item label="部门id" prop="deptId">
          <el-input v-model="form.deptId" placeholder="请输入部门id" />
        </el-form-item>
        <el-form-item label="部门名称" prop="deptName">
          <el-input v-model="form.deptName" placeholder="请输入部门名称" />
        </el-form-item>
        <el-form-item label="任务类型" prop="taskType">
          <el-select v-model="form.taskType" placeholder="请选择任务类型">
            <el-option
              v-for="dict in dict.type.work_type"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="走访人员id" prop="taskUserId">
          <el-input v-model="form.taskUserId" placeholder="请输入走访人员id" />
        </el-form-item>
        <el-form-item label="走访人员名称" prop="taskUserName">
          <el-input v-model="form.taskUserName" placeholder="请输入走访人员名称" />
        </el-form-item>
        <el-form-item label="走访地址" prop="taskAddress">
          <el-input v-model="form.taskAddress" placeholder="请输入走访地址" />
        </el-form-item>
        <el-form-item label="附件地址1" prop="attachmentOne">
          <image-upload v-model="form.attachmentOne"/>
        </el-form-item>
        <el-form-item label="附件地址2" prop="attachmentTwo">
          <image-upload v-model="form.attachmentTwo"/>
        </el-form-item>
        <el-form-item label="是否隐患" prop="troubleFlag">
          <el-select v-model="form.troubleFlag" placeholder="请选择是否隐患">
            <el-option
              v-for="dict in dict.type.tenant_flag"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="走访情况" prop="recordInfo">
          <el-input v-model="form.recordInfo" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="隐患信息" prop="troubleInfo">
          <el-input v-model="form.troubleInfo" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
        </el-form-item>
        <el-form-item label="记录时间" prop="recordTime">
          <el-date-picker clearable
            v-model="form.recordTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择记录时间">
          </el-date-picker>
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
import { listTaskinfo, getTaskinfo, delTaskinfo, addTaskinfo, updateTaskinfo } from "@/api/inspection/taskinfo";

export default {
  name: "Taskinfo",
  dicts: ['tenant_flag', 'work_type'],
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
      // 巡检记录表格数据
      taskinfoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        unionId: null,
        unionName: null,
        deptId: null,
        deptName: null,
        taskType: null,
        taskUserId: null,
        taskUserName: null,
        taskAddress: null,
        attachmentOne: null,
        attachmentTwo: null,
        troubleFlag: null,
        recordInfo: null,
        troubleInfo: null,
        recordTime: null
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
    /** 查询巡检记录列表 */
    getList() {
      this.loading = true;
      listTaskinfo(this.queryParams).then(response => {
        this.taskinfoList = response.rows;
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
        unionId: null,
        unionName: null,
        deptId: null,
        deptName: null,
        taskType: null,
        taskUserId: null,
        taskUserName: null,
        taskAddress: null,
        attachmentOne: null,
        attachmentTwo: null,
        troubleFlag: null,
        recordInfo: null,
        troubleInfo: null,
        remark: null,
        recordTime: null
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
      this.title = "添加巡检记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getTaskinfo(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改巡检记录";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateTaskinfo(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTaskinfo(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除巡检记录编号为"' + ids + '"的数据项？').then(function() {
        return delTaskinfo(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('inspection/taskinfo/export', {
        ...this.queryParams
      }, `taskinfo_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>

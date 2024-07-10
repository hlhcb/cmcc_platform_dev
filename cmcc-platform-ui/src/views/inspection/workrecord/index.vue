<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="工单编号" prop="workId">
        <el-input
          v-model="queryParams.workId"
          placeholder="请输入工单编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="工单类型" prop="workType">
        <el-select v-model="queryParams.workType" placeholder="请选择工单类型" clearable>
          <el-option
            v-for="dict in dict.type.work_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="企业名称" prop="deptName">
        <el-input
          v-model="queryParams.deptName"
          placeholder="请输入企业名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="工单状态" prop="workStatus">
        <el-select v-model="queryParams.workStatus" placeholder="请选择工单状态" clearable>
          <el-option
            v-for="dict in dict.type.work_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <!-- <el-form-item label="联防id" prop="unionId">
        <el-input
          v-model="queryParams.unionId"
          placeholder="请输入联防id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item label="联防区名称" prop="unionName">
        <el-input
          v-model="queryParams.unionName"
          placeholder="请输入联防区名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!-- <el-form-item label="走访记录id" prop="taskId">
        <el-input
          v-model="queryParams.taskId"
          placeholder="请输入走访记录id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <!-- <el-form-item label="走访人id" prop="taskUserId">
        <el-input
          v-model="queryParams.taskUserId"
          placeholder="请输入走访人id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item label="上报人姓名" prop="taskUserName">
        <el-input
          v-model="queryParams.taskUserName"
          placeholder="请输入上报人姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!-- <el-form-item label="处理人id" prop="accpetUserId">
        <el-input
          v-model="queryParams.accpetUserId"
          placeholder="请输入处理人id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item label="接收人姓名" prop="accpetUserName">
        <el-input
          v-model="queryParams.accpetUserName"
          placeholder="请输入接收人姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!-- <el-form-item label="接收时间" prop="accpetTime">
        <el-date-picker clearable
          v-model="queryParams.accpetTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择接收时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="处理时间" prop="dealTime">
        <el-date-picker clearable
          v-model="queryParams.dealTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择处理时间">
        </el-date-picker>
      </el-form-item> -->
      <!-- <el-form-item label="地址" prop="address">
        <el-input
          v-model="queryParams.address"
          placeholder="请输入地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['inspection:workrecord:add']"
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
          v-hasPermi="['inspection:workrecord:edit']"
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
          v-hasPermi="['inspection:workrecord:remove']"
        >删除</el-button>
      </el-col> -->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['inspection:workrecord:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="workrecordList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="主键id" align="center" prop="id" /> -->
      <el-table-column label="工单编号" align="center" prop="workId" />
      <el-table-column label="工单类型" align="center" prop="workType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.work_type" :value="scope.row.workType"/>
        </template>
      </el-table-column>
      <!-- <el-table-column label="部门id" align="center" prop="deptId" /> -->
      <el-table-column label="企业名称" align="center" prop="unionDeptName" />
      <el-table-column label="工单状态" align="center" prop="workStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.work_status" :value="scope.row.workStatus"/>
        </template>
      </el-table-column>
      <!-- <el-table-column label="联防id" align="center" prop="unionId" /> -->
      <el-table-column label="联防区名称" align="center" prop="unionName" />
      <!-- <el-table-column label="走访记录id" align="center" prop="taskId" /> -->
      <!-- <el-table-column label="走访人id" align="center" prop="taskUserId" /> -->
      <el-table-column label="上报人姓名" align="center" prop="taskUserName" />
      <!-- <el-table-column label="处理人id" align="center" prop="accpetUserId" /> -->
      <el-table-column label="接收人姓名" align="center" prop="accpetUserName" />
      <el-table-column label="处理人姓名" align="center" prop="changeDeptName" />
      <el-table-column label="隐患信息" align="center" prop="troubleInfo" />
      <el-table-column label="附件1" align="center" prop="attachmentOne" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.attachmentOne" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="附件2" align="center" prop="attachmentTwo" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.attachmentTwo" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="接收时间" align="center" prop="accpetTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.accpetTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="处理结果" align="center" prop="resultInfo" />
      <el-table-column label="处理结果拍照1" align="center" prop="returnAttachmentOne" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.returnAttachmentOne" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="处理结果拍照2" align="center" prop="returnAttachmentTwo" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.returnAttachmentTwo" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="处理时间" align="center" prop="dealTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.dealTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="地址" align="center" prop="address" />
      <!-- <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['inspection:workrecord:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['inspection:workrecord:remove']"
          >删除</el-button>
        </template>
      </el-table-column> -->
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改工单记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="工单类型" prop="workType">
          <el-select v-model="form.workType" placeholder="请选择工单类型">
            <el-option
              v-for="dict in dict.type.work_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="部门id" prop="deptId">
          <el-input v-model="form.deptId" placeholder="请输入部门id" />
        </el-form-item>
        <el-form-item label="部门名称" prop="deptName">
          <el-input v-model="form.deptName" placeholder="请输入部门名称" />
        </el-form-item>
        <el-form-item label="工单状态" prop="workStatus">
          <el-select v-model="form.workStatus" placeholder="请选择工单状态">
            <el-option
              v-for="dict in dict.type.work_status"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="联防id" prop="unionId">
          <el-input v-model="form.unionId" placeholder="请输入联防id" />
        </el-form-item>
        <el-form-item label="联防区名称" prop="unionName">
          <el-input v-model="form.unionName" placeholder="请输入联防区名称" />
        </el-form-item>
        <el-form-item label="走访记录id" prop="taskId">
          <el-input v-model="form.taskId" placeholder="请输入走访记录id" />
        </el-form-item>
        <el-form-item label="走访人id" prop="taskUserId">
          <el-input v-model="form.taskUserId" placeholder="请输入走访人id" />
        </el-form-item>
        <el-form-item label="走访人姓名" prop="taskUserName">
          <el-input v-model="form.taskUserName" placeholder="请输入走访人姓名" />
        </el-form-item>
        <el-form-item label="处理人id" prop="accpetUserId">
          <el-input v-model="form.accpetUserId" placeholder="请输入处理人id" />
        </el-form-item>
        <el-form-item label="处理人姓名" prop="accpetUserName">
          <el-input v-model="form.accpetUserName" placeholder="请输入处理人姓名" />
        </el-form-item>
        <el-form-item label="隐患信息" prop="troubleInfo">
          <el-input v-model="form.troubleInfo" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="附件1" prop="attachmentOne">
          <image-upload v-model="form.attachmentOne"/>
        </el-form-item>
        <el-form-item label="附件2" prop="attachmentTwo">
          <image-upload v-model="form.attachmentTwo"/>
        </el-form-item>
        <el-form-item label="接收时间" prop="accpetTime">
          <el-date-picker clearable
            v-model="form.accpetTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择接收时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="处理结果" prop="resultInfo">
          <el-input v-model="form.resultInfo" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="处理结果拍照1" prop="returnAttachmentOne">
          <image-upload v-model="form.returnAttachmentOne"/>
        </el-form-item>
        <el-form-item label="处理结果拍照2" prop="returnAttachmentTwo">
          <image-upload v-model="form.returnAttachmentTwo"/>
        </el-form-item>
        <el-form-item label="处理时间" prop="dealTime">
          <el-date-picker clearable
            v-model="form.dealTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择处理时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入地址" />
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
import { listWorkrecord, getWorkrecord, delWorkrecord, addWorkrecord, updateWorkrecord } from "@/api/inspection/workrecord";

export default {
  name: "Workrecord",
  dicts: ['work_status', 'work_type'],
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
      // 工单记录表格数据
      workrecordList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        workId: null,
        workType: null,
        deptId: null,
        deptName: null,
        workStatus: null,
        unionId: null,
        unionName: null,
        taskId: null,
        taskUserId: null,
        taskUserName: null,
        accpetUserId: null,
        accpetUserName: null,
        troubleInfo: null,
        attachmentOne: null,
        attachmentTwo: null,
        accpetTime: null,
        resultInfo: null,
        returnAttachmentOne: null,
        returnAttachmentTwo: null,
        dealTime: null,
        address: null
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
    /** 查询工单记录列表 */
    getList() {
      this.loading = true;
      listWorkrecord(this.queryParams).then(response => {
        this.workrecordList = response.rows;
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
        workId: null,
        workType: null,
        deptId: null,
        deptName: null,
        workStatus: null,
        unionId: null,
        unionName: null,
        taskId: null,
        taskUserId: null,
        taskUserName: null,
        accpetUserId: null,
        accpetUserName: null,
        troubleInfo: null,
        attachmentOne: null,
        attachmentTwo: null,
        accpetTime: null,
        resultInfo: null,
        returnAttachmentOne: null,
        returnAttachmentTwo: null,
        dealTime: null,
        address: null
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
      this.title = "添加工单记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getWorkrecord(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改工单记录";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateWorkrecord(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addWorkrecord(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除工单记录编号为"' + ids + '"的数据项？').then(function() {
        return delWorkrecord(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('inspection/workrecord/export', {
        ...this.queryParams
      }, `workrecord_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>

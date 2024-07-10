<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="系统区县编码" prop="sysCountyCode">
        <el-input
          v-model="queryParams.sysCountyCode"
          placeholder="请输入系统区县编码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="区县编码" prop="countyCode">
        <el-input
          v-model="queryParams.countyCode"
          placeholder="请输入区县编码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="区县名称" prop="countyName">
        <el-input
          v-model="queryParams.countyName"
          placeholder="请输入区县名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="网格编码" prop="centerCode">
        <el-input
          v-model="queryParams.centerCode"
          placeholder="请输入网格编码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="网格名称" prop="centerName">
        <el-input
          v-model="queryParams.centerName"
          placeholder="请输入网格名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="微格编码" prop="townCode">
        <el-input
          v-model="queryParams.townCode"
          placeholder="请输入微格编码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="微格名称" prop="townName">
        <el-input
          v-model="queryParams.townName"
          placeholder="请输入微格名称"
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
          v-hasPermi="['springmarketing:gardinfo:add']"
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
          v-hasPermi="['springmarketing:gardinfo:edit']"
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
          v-hasPermi="['springmarketing:gardinfo:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['springmarketing:gardinfo:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="gardinfoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键id" align="center" prop="id" />
      <el-table-column label="系统区县编码" align="center" prop="sysCountyCode" />
      <el-table-column label="区县编码" align="center" prop="countyCode" />
      <el-table-column label="区县名称" align="center" prop="countyName" />
      <el-table-column label="网格编码" align="center" prop="centerCode" />
      <el-table-column label="网格名称" align="center" prop="centerName" />
      <el-table-column label="微格编码" align="center" prop="townCode" />
      <el-table-column label="微格名称" align="center" prop="townName" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['springmarketing:gardinfo:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['springmarketing:gardinfo:remove']"
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

    <!-- 添加或修改网格信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="系统区县编码" prop="sysCountyCode">
          <el-input v-model="form.sysCountyCode" placeholder="请输入系统区县编码" />
        </el-form-item>
        <el-form-item label="区县编码" prop="countyCode">
          <el-input v-model="form.countyCode" placeholder="请输入区县编码" />
        </el-form-item>
        <el-form-item label="区县名称" prop="countyName">
          <el-input v-model="form.countyName" placeholder="请输入区县名称" />
        </el-form-item>
        <el-form-item label="网格编码" prop="centerCode">
          <el-input v-model="form.centerCode" placeholder="请输入网格编码" />
        </el-form-item>
        <el-form-item label="网格名称" prop="centerName">
          <el-input v-model="form.centerName" placeholder="请输入网格名称" />
        </el-form-item>
        <el-form-item label="微格编码" prop="townCode">
          <el-input v-model="form.townCode" placeholder="请输入微格编码" />
        </el-form-item>
        <el-form-item label="微格名称" prop="townName">
          <el-input v-model="form.townName" placeholder="请输入微格名称" />
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
import { listGardinfo, getGardinfo, delGardinfo, addGardinfo, updateGardinfo } from "@/api/springmarketing/gardinfo";

export default {
  name: "Gardinfo",
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
      // 网格信息表格数据
      gardinfoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        sysCountyCode: null,
        countyCode: null,
        countyName: null,
        centerCode: null,
        centerName: null,
        townCode: null,
        townName: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        sysCountyCode: [
          { required: true, message: "系统区县编码不能为空", trigger: "blur" }
        ],
        countyCode: [
          { required: true, message: "区县编码不能为空", trigger: "blur" }
        ],
        countyName: [
          { required: true, message: "区县名称不能为空", trigger: "blur" }
        ],
        centerCode: [
          { required: true, message: "网格编码不能为空", trigger: "blur" }
        ],
        centerName: [
          { required: true, message: "网格名称不能为空", trigger: "blur" }
        ],
        townCode: [
          { required: true, message: "微格编码不能为空", trigger: "blur" }
        ],
        townName: [
          { required: true, message: "微格名称不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询网格信息列表 */
    getList() {
      this.loading = true;
      listGardinfo(this.queryParams).then(response => {
        this.gardinfoList = response.rows;
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
        sysCountyCode: null,
        countyCode: null,
        countyName: null,
        centerCode: null,
        centerName: null,
        townCode: null,
        townName: null
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
      this.title = "添加网格信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getGardinfo(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改网格信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateGardinfo(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addGardinfo(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除网格信息编号为"' + ids + '"的数据项？').then(function() {
        return delGardinfo(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('springmarketing/gardinfo/export', {
        ...this.queryParams
      }, `gardinfo_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>

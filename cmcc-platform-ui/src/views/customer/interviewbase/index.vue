<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="单位名称" prop="areaName">
        <el-input
          v-model="queryParams.areaName"
          placeholder="请输入单位名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!-- <el-form-item label="局向" prop="direction">
        <el-input
          v-model="queryParams.direction"
          placeholder="请输入局向"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="工号" prop="workNo">
        <el-input
          v-model="queryParams.workNo"
          placeholder="请输入工号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item label="走访人" prop="userName">
        <el-input
          v-model="queryParams.userName"
          placeholder="请输入走访人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="场景选择" prop="sceneType">
        <el-select v-model="queryParams.sceneType" placeholder="请选择场景选择" clearable>
          <el-option
            v-for="dict in dict.type.scene_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="单位名称" prop="businessName">
        <el-input
          v-model="queryParams.businessName"
          placeholder="请输入单位名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="客户姓名" prop="customerName">
        <el-input
          v-model="queryParams.customerName"
          placeholder="请输入客户姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!-- <el-form-item label="走访具体事项" prop="information">
        <el-input
          v-model="queryParams.information"
          placeholder="请输入走访具体事项"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item label="走访时间">
        <el-date-picker
          v-model="daterangeRecordDate"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <!-- <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['customer:interviewbase:add']"
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
          v-hasPermi="['customer:interviewbase:edit']"
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
          v-hasPermi="['customer:interviewbase:remove']"
        >删除</el-button>
      </el-col> -->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['customer:interviewbase:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="interviewbaseList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="主键id" align="center" prop="id" /> -->
      <el-table-column label="单位名称" align="center" prop="areaName" />
      <!-- <el-table-column label="局向" align="center" prop="direction" />
      <el-table-column label="工号" align="center" prop="workNo" /> -->
      <el-table-column label="走访人" align="center" prop="userName" />
      <el-table-column label="场景选择" align="center" prop="sceneType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.scene_type" :value="scope.row.sceneType"/>
        </template>
      </el-table-column>
      <el-table-column label="单位名称" align="center" prop="businessName" />
      <el-table-column label="详细地址" align="center" prop="addrInfo" />
      <el-table-column label="客户姓名" align="center" prop="customerName" />
      <el-table-column label="客户手机号" align="center" prop="customerTel" />
      <el-table-column label="客户职位" align="center" prop="customerPosition" />
      <el-table-column label="走访具体事项" align="center" prop="information" />
      <el-table-column label="走访时间" align="center" prop="recordDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.recordDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="耗时（分钟）" align="center" prop="useTime" />
      <el-table-column label="走访开始时间" align="center" prop="timeBegin" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.timeBegin, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="走访结束时间" align="center" prop="timeOver" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.timeOver, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="定位具体地址" align="center" prop="mapAddress" />
      <el-table-column label="经度" align="center" prop="latitude" />
      <el-table-column label="维度" align="center" prop="longitude" />
      <!-- <el-table-column label="附件1" align="center" prop="attachmentOne" /> -->
      <el-table-column label="附件1" align="center" prop="attachmentOne" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.attachmentOne" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <!-- <el-table-column label="附件2" align="center" prop="attachmentTwo" /> -->
      <el-table-column label="附件2" align="center" prop="attachmentTwo" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.attachmentTwo" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <!-- <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['customer:interviewbase:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['customer:interviewbase:remove']"
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

    <!-- 添加或修改用户走访对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="场景选择" prop="sceneType">
          <el-select v-model="form.sceneType" placeholder="请选择场景选择">
            <el-option
              v-for="dict in dict.type.scene_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="单位名称" prop="businessName">
          <el-input v-model="form.businessName" placeholder="请输入单位名称" />
        </el-form-item>
        <el-form-item label="详细地址" prop="addrInfo">
          <el-input v-model="form.addrInfo" placeholder="请输入详细地址" />
        </el-form-item>
        <el-form-item label="客户姓名" prop="customerName">
          <el-input v-model="form.customerName" placeholder="请输入客户姓名" />
        </el-form-item>
        <el-form-item label="客户手机号" prop="customerTel">
          <el-input v-model="form.customerTel" placeholder="请输入客户手机号" />
        </el-form-item>
        <el-form-item label="客户职位" prop="customerPosition">
          <el-input v-model="form.customerPosition" placeholder="请输入客户职位" />
        </el-form-item>
        <el-form-item label="走访具体事项" prop="information">
          <el-input v-model="form.information" placeholder="请输入走访具体事项" />
        </el-form-item>
        <el-form-item label="走访时间" prop="recordDate">
          <el-date-picker clearable
            v-model="form.recordDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择走访时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="走访开始时间" prop="timeBegin">
          <el-date-picker clearable
            v-model="form.timeBegin"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择走访开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="走访结束时间" prop="timeOver">
          <el-date-picker clearable
            v-model="form.timeOver"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择走访结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="附件1" prop="attachmentOne">
          <el-input v-model="form.attachmentOne" placeholder="请输入附件1" />
        </el-form-item>
        <el-form-item label="附件2" prop="attachmentTwo">
          <el-input v-model="form.attachmentTwo" placeholder="请输入附件2" />
        </el-form-item>
        <el-divider content-position="center">客户走访线路信息信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddCmccInterviewNet">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteCmccInterviewNet">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="cmccInterviewNetList" :row-class-name="rowCmccInterviewNetIndex" @selection-change="handleCmccInterviewNetSelectionChange" ref="cmccInterviewNet">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="类型" prop="netType" width="150">
            <template slot-scope="scope">
              <el-select v-model="scope.row.netType" placeholder="请选择类型">
                <el-option label="请选择字典生成" value="" />
              </el-select>
            </template>
          </el-table-column>
          <el-table-column label="网络运营商" prop="ispName" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.ispName" placeholder="请输入网络运营商" />
            </template>
          </el-table-column>
          <el-table-column label="带宽" prop="netSpeed" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.netSpeed" placeholder="请输入带宽" />
            </template>
          </el-table-column>
          <el-table-column label="月单价" prop="netPrice" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.netPrice" placeholder="请输入月单价" />
            </template>
          </el-table-column>
          <el-table-column label="到期时间" prop="endTime" width="240">
            <template slot-scope="scope">
              <el-date-picker clearable v-model="scope.row.endTime" type="date" value-format="yyyy-MM-dd" placeholder="请选择到期时间" />
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listInterviewbase, getInterviewbase, delInterviewbase, addInterviewbase, updateInterviewbase } from "@/api/customer/interviewbase";

export default {
  name: "Interviewbase",
  dicts: ['scene_type'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedCmccInterviewNet: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 用户走访表格数据
      interviewbaseList: [],
      // 客户走访线路信息表格数据
      cmccInterviewNetList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 到期时间时间范围
      daterangeRecordDate: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        areaName: null,
        direction: null,
        workNo: null,
        userName: null,
        sceneType: null,
        businessName: null,
        customerName: null,
        information: null,
        recordDate: null,
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
    /** 查询用户走访列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeRecordDate && '' != this.daterangeRecordDate) {
        this.queryParams.params["beginRecordDate"] = this.daterangeRecordDate[0];
        this.queryParams.params["endRecordDate"] = this.daterangeRecordDate[1];
      }
      listInterviewbase(this.queryParams).then(response => {
        this.interviewbaseList = response.rows;
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
        areaName: null,
        direction: null,
        workNo: null,
        userName: null,
        sceneType: null,
        businessName: null,
        addrInfo: null,
        customerName: null,
        customerTel: null,
        customerPosition: null,
        information: null,
        recordDate: null,
        useTime: null,
        userId: null,
        deptId: null,
        timeBegin: null,
        timeOver: null,
        mapAddress: null,
        latitude: null,
        longitude: null,
        attachmentOne: null,
        attachmentTwo: null
      };
      this.cmccInterviewNetList = [];
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.daterangeRecordDate = [];
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
      this.title = "添加用户走访";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getInterviewbase(id).then(response => {
        this.form = response.data;
        this.cmccInterviewNetList = response.data.cmccInterviewNetList;
        this.open = true;
        this.title = "修改用户走访";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.cmccInterviewNetList = this.cmccInterviewNetList;
          if (this.form.id != null) {
            updateInterviewbase(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addInterviewbase(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除用户走访编号为"' + ids + '"的数据项？').then(function() {
        return delInterviewbase(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
	/** 客户走访线路信息序号 */
    rowCmccInterviewNetIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 客户走访线路信息添加按钮操作 */
    handleAddCmccInterviewNet() {
      let obj = {};
      obj.netType = "";
      obj.ispName = "";
      obj.netSpeed = "";
      obj.netPrice = "";
      obj.endTime = "";
      this.cmccInterviewNetList.push(obj);
    },
    /** 客户走访线路信息删除按钮操作 */
    handleDeleteCmccInterviewNet() {
      if (this.checkedCmccInterviewNet.length == 0) {
        this.$modal.msgError("请先选择要删除的客户走访线路信息数据");
      } else {
        const cmccInterviewNetList = this.cmccInterviewNetList;
        const checkedCmccInterviewNet = this.checkedCmccInterviewNet;
        this.cmccInterviewNetList = cmccInterviewNetList.filter(function(item) {
          return checkedCmccInterviewNet.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleCmccInterviewNetSelectionChange(selection) {
      this.checkedCmccInterviewNet = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('customer/interviewbase/export', {
        ...this.queryParams
      }, `interviewbase_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>

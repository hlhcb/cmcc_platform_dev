<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="归属地" prop="belongingPlace">
        <el-input
          v-model="queryParams.belongingPlace"
          placeholder="请输入归属地"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="小区" prop="community">
        <el-input
          v-model="queryParams.community"
          placeholder="请输入小区"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!-- <el-form-item label="详细地址" prop="addressInfo">
        <el-input
          v-model="queryParams.addressInfo"
          placeholder="请输入详细地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item label="房主姓名" prop="hostName">
        <el-input
          v-model="queryParams.hostName"
          placeholder="请输入房主姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="同住人姓名" prop="cohabitant">
        <el-input
          v-model="queryParams.cohabitant"
          placeholder="请输入同住人姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!-- <el-form-item label="房主身份证号" prop="hostCardId">
        <el-input
          v-model="queryParams.hostCardId"
          placeholder="请输入房主身份证号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="房主电话" prop="hostTel">
        <el-input
          v-model="queryParams.hostTel"
          placeholder="请输入房主电话"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="租赁起始日期">
        <el-date-picker
          v-model="daterangeRentalBeginDate"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="租赁截至日期">
        <el-date-picker
          v-model="daterangeRentalEndDate"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item> -->
      <!-- <el-form-item label="租客姓名" prop="tenantName">
        <el-input
          v-model="queryParams.tenantName"
          placeholder="请输入租客姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="租客省份证号" prop="tenantCardId">
        <el-input
          v-model="queryParams.tenantCardId"
          placeholder="请输入租客省份证号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="租客手机号" prop="tenantTel">
        <el-input
          v-model="queryParams.tenantTel"
          placeholder="请输入租客手机号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item label="记录时间">
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
      <!-- <el-form-item label="派出所名称" prop="localPoliceStation">
        <el-input
          v-model="queryParams.localPoliceStation"
          placeholder="请输入派出所名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="派出所id" prop="deptId">
        <el-input
          v-model="queryParams.deptId"
          placeholder="请输入派出所id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item label="上报类型" prop="recordType">
        <el-select v-model="queryParams.recordType" placeholder="请选择上报类型" clearable>
          <el-option
            v-for="dict in dict.type.report_type"
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
          v-hasPermi="['consumer:rentalinfo:add']"
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
          v-hasPermi="['consumer:rentalinfo:edit']"
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
          v-hasPermi="['consumer:rentalinfo:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['consumer:rentalinfo:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="rentalinfoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="主键id" align="center" prop="id" /> -->
      <el-table-column label="归属地" align="center" prop="belongingPlace" />
      <el-table-column label="小区" align="center" prop="community" />
      <el-table-column label="详细地址" align="center" prop="addressInfo" />
      <el-table-column label="房主姓名" align="center" prop="hostName" />
      <el-table-column label="房主身份证号" align="center" prop="hostCardId" />
      <el-table-column label="房主电话" align="center" prop="hostTel" />
      <el-table-column label="租赁起始日期" align="center" prop="rentalBeginDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.rentalBeginDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="租赁截至日期" align="center" prop="rentalEndDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.rentalEndDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="租客姓名" align="center" prop="tenantName" />
      <el-table-column label="租客省份证号" align="center" prop="tenantCardId" />
      <el-table-column label="租客手机号" align="center" prop="tenantTel" />
      <el-table-column label="记录时间" align="center" prop="recordDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.recordDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <!-- <el-table-column label="派出所名称" align="center" prop="localPoliceStation" />
      <el-table-column label="派出所id" align="center" prop="deptId" /> -->
      <el-table-column label="上报类型" align="center" prop="recordType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.report_type" :value="scope.row.recordType"/>
        </template>
      </el-table-column>
      <el-table-column label="租赁合同1" align="center" prop="attachmentOne" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.attachmentOne" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="租赁合同2" align="center" prop="attachmentTwo" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.attachmentTwo" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['consumer:rentalinfo:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['consumer:rentalinfo:remove']"
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

    <!-- 添加或修改出租房信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="上报类型" prop="recordType">
          <el-select v-model="form.recordType" placeholder="请选择上报类型">
            <el-option
              v-for="dict in dict.type.report_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="归属地" prop="belongingPlace">
          <el-input v-model="form.belongingPlace" placeholder="请输入归属地" />
        </el-form-item>
        <el-form-item label="小区" prop="community">
          <el-input v-model="form.community" placeholder="请输入小区" />
        </el-form-item>
        <el-form-item label="详细地址" prop="addressInfo">
          <el-input v-model="form.addressInfo" placeholder="请输入详细地址" />
        </el-form-item>
        <el-form-item label="房主姓名" prop="hostName">
          <el-input v-model="form.hostName" placeholder="请输入房主姓名" />
        </el-form-item>
        <el-form-item label="房主身份证号" prop="hostCardId">
          <el-input v-model="form.hostCardId" placeholder="请输入房主身份证号" />
        </el-form-item>
        <el-form-item label="房主电话" prop="hostTel">
          <el-input v-model="form.hostTel" placeholder="请输入房主电话" />
        </el-form-item>
        <el-form-item label="租赁起始日期" prop="rentalBeginDate">
          <el-date-picker clearable
            v-model="form.rentalBeginDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择租赁起始日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="租赁截至日期" prop="rentalEndDate">
          <el-date-picker clearable
            v-model="form.rentalEndDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择租赁截至日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="租客姓名" prop="tenantName">
          <el-input v-model="form.tenantName" placeholder="请输入租客姓名" />
        </el-form-item>
        <el-form-item label="租客省份证号" prop="tenantCardId">
          <el-input v-model="form.tenantCardId" placeholder="请输入租客省份证号" />
        </el-form-item>
        <el-form-item label="租客手机号" prop="tenantTel">
          <el-input v-model="form.tenantTel" placeholder="请输入租客手机号" />
        </el-form-item>
        <!-- <el-form-item label="记录时间" prop="recordDate">
          <el-date-picker clearable
            v-model="form.recordDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择记录时间">
          </el-date-picker>
        </el-form-item> -->
        <!-- <el-form-item label="派出所名称" prop="localPoliceStation">
          <el-input v-model="form.localPoliceStation" placeholder="请输入派出所名称" />
        </el-form-item>
        <el-form-item label="派出所id" prop="deptId">
          <el-input v-model="form.deptId" placeholder="请输入派出所id" />
        </el-form-item> -->
        <el-form-item label="租赁合同1" prop="attachmentOne">
          <image-upload v-model="form.attachmentOne"/>
        </el-form-item>
        <el-form-item label="租赁合同2" prop="attachmentTwo">
          <image-upload v-model="form.attachmentTwo"/>
        </el-form-item>
        <el-divider content-position="center">租客信息信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddTenantUserInfo">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteTenantUserInfo">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="tenantUserInfoList" :row-class-name="rowTenantUserInfoIndex" @selection-change="handleTenantUserInfoSelectionChange" ref="tenantUserInfo">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="租客姓名" prop="tenantName" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.tenantName" placeholder="请输入租客姓名" />
            </template>
          </el-table-column>
          <el-table-column label="租客身份证" prop="tenantCardId" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.tenantCardId" placeholder="请输入租客身份证" />
            </template>
          </el-table-column>
          <el-table-column label="租客电话" prop="tenantTel" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.tenantTel" placeholder="请输入租客电话" />
            </template>
          </el-table-column>
          <el-table-column label="排序" prop="orderId" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.orderId" placeholder="请输入排序" />
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
import { listRentalinfo, getRentalinfo, delRentalinfo, addRentalinfo, updateRentalinfo } from "@/api/consumer/rentalinfo";

export default {
  name: "Rentalinfo",
  dicts: ['report_type'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedTenantUserInfo: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 出租房信息表格数据
      rentalinfoList: [],
      // 租客信息表格数据
      tenantUserInfoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 排序id时间范围
      daterangeRentalBeginDate: [],
      // 排序id时间范围
      daterangeRentalEndDate: [],
      // 排序id时间范围
      daterangeRecordDate: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        belongingPlace: null,
        community: null,
        addressInfo: null,
        hostName: null,
        hostCardId: null,
        hostTel: null,
        rentalBeginDate: null,
        rentalEndDate: null,
        tenantName: null,
        tenantCardId: null,
        tenantTel: null,
        recordDate: null,
        localPoliceStation: null,
        deptId: null,
        recordType: null,
        attachmentOne: null,
        attachmentTwo: null,
        cohabitant: null,
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
    /** 查询出租房信息列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeRentalBeginDate && '' != this.daterangeRentalBeginDate) {
        this.queryParams.params["beginRentalBeginDate"] = this.daterangeRentalBeginDate[0];
        this.queryParams.params["endRentalBeginDate"] = this.daterangeRentalBeginDate[1];
      }
      if (null != this.daterangeRentalEndDate && '' != this.daterangeRentalEndDate) {
        this.queryParams.params["beginRentalEndDate"] = this.daterangeRentalEndDate[0];
        this.queryParams.params["endRentalEndDate"] = this.daterangeRentalEndDate[1];
      }
      if (null != this.daterangeRecordDate && '' != this.daterangeRecordDate) {
        this.queryParams.params["beginRecordDate"] = this.daterangeRecordDate[0];
        this.queryParams.params["endRecordDate"] = this.daterangeRecordDate[1];
      }
      listRentalinfo(this.queryParams).then(response => {
        this.rentalinfoList = response.rows;
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
        belongingPlace: null,
        community: null,
        addressInfo: null,
        hostName: null,
        hostCardId: null,
        hostTel: null,
        rentalBeginDate: null,
        rentalEndDate: null,
        tenantName: null,
        tenantCardId: null,
        tenantTel: null,
        recordDate: null,
        localPoliceStation: null,
        deptId: null,
        recordType: null,
        attachmentOne: null,
        attachmentTwo: null
      };
      this.tenantUserInfoList = [];
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.daterangeRentalBeginDate = [];
      this.daterangeRentalEndDate = [];
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
      this.title = "添加出租房信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getRentalinfo(id).then(response => {
        this.form = response.data;
        this.tenantUserInfoList = response.data.tenantUserInfoList;
        this.open = true;
        this.title = "修改出租房信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.tenantUserInfoList = this.tenantUserInfoList;
          if (this.form.id != null) {
            updateRentalinfo(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addRentalinfo(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除出租房信息编号为"' + ids + '"的数据项？').then(function() {
        return delRentalinfo(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
	/** 租客信息序号 */
    rowTenantUserInfoIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 租客信息添加按钮操作 */
    handleAddTenantUserInfo() {
      let obj = {};
      obj.tenantName = "";
      obj.tenantCardId = "";
      obj.tenantTel = "";
      obj.orderId = "";
      this.tenantUserInfoList.push(obj);
    },
    /** 租客信息删除按钮操作 */
    handleDeleteTenantUserInfo() {
      if (this.checkedTenantUserInfo.length == 0) {
        this.$modal.msgError("请先选择要删除的租客信息数据");
      } else {
        const tenantUserInfoList = this.tenantUserInfoList;
        const checkedTenantUserInfo = this.checkedTenantUserInfo;
        this.tenantUserInfoList = tenantUserInfoList.filter(function(item) {
          return checkedTenantUserInfo.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleTenantUserInfoSelectionChange(selection) {
      this.checkedTenantUserInfo = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('consumer/rentalinfo/export', {
        ...this.queryParams
      }, `rentalinfo_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>

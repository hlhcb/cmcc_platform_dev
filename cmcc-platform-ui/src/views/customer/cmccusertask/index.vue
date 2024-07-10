<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="80px">
      <el-form-item label="区县名称" prop="deptId">
        <el-select v-model="queryParams.deptId" placeholder="请选择区县" filterable clearable @change="selctArea($event)">
          <el-option
            v-for="dict in areaList"
            :key="dict.value"
            :label="dict.text"
            :value="dict.value"
        ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="楼宇/园区" prop="groupId">
        <el-select v-model="queryParams.groupId" filterable placeholder="请选择楼宇/园区"   clearable>
          <el-option
            v-for="dict in groupList"
            :key="dict.id"
            :label="dict.groupName"
            :value="dict.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="客户经理" prop="userName">
        <el-input
          v-model="queryParams.userName"
          placeholder="请输入客户经理名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="集团类型" prop="groupType">
        <el-select v-model="queryParams.groupType" placeholder="请选择集团类型" clearable>
          <el-option
            v-for="dict in dict.type.scene_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <!-- <el-form-item label="任务状态" prop="taskStatus">
        <el-select v-model="queryParams.taskStatus" placeholder="请选择任务状态" clearable>
          <el-option
            v-for="dict in dict.type.task_enum"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item> -->
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
          v-hasPermi="['customer:cmccusertask:add']"
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
          v-hasPermi="['customer:cmccusertask:edit']"
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
          v-hasPermi="['customer:cmccusertask:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="info"
          plain
          icon="el-icon-upload2"
          size="mini"
          @click="handleImport"
          v-hasPermi="['customer:cmccusertask:import']"
        >导入</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['customer:cmccusertask:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="cmccusertaskList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="主键id" align="center" prop="id" />
      <el-table-column label="区县id" align="center" prop="deptId" /> -->
      <el-table-column label="区县名称" align="center" prop="deptName" />
      <!-- <el-table-column label="用户id" align="center" prop="userId" /> -->
      <el-table-column label="客户经理名称" align="center" prop="userName" />
      <!-- <el-table-column label="集团id" align="center" prop="groupId" /> -->
      <el-table-column label="集团名称" align="center" prop="groupName" />
      <el-table-column label="任务开始时间" align="center" prop="taskBeginTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.taskBeginTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="任务结束时间" align="center" prop="taskEndTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.taskEndTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="集团类型" align="center" prop="groupType">


        <template slot-scope="scope">
          <dict-tag :options="dict.type.scene_type" :value="scope.row.groupType"/>
        </template>
      </el-table-column>
      <!-- <el-table-column label="任务状态" align="center" prop="taskStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.task_enum" :value="scope.row.taskStatus"/>
        </template>
      </el-table-column> -->
      <!-- <el-table-column label="创建人id" align="center" prop="createUserId" />
      <el-table-column label="创建人" align="center" prop="createUserName" /> -->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['customer:cmccusertask:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['customer:cmccusertask:remove']"
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

    <!-- 添加或修改用户任务对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <!-- <el-form-item label="区县id" prop="deptId">
          <el-input v-model="form.deptId" placeholder="请输入区县id" />
        </el-form-item> -->
        <el-form-item label="区县" prop="deptIdObj">
          <!-- <el-input v-model="form.deptName" placeholder="请输入区县名称" /> -->
          <el-select v-model="form.deptIdObj" filterable placeholder="请选择区县"  @change="selectAddArea($event)">
            <el-option
              v-for="dict in areaList"
              :key="dict.value"
              :label="dict.text"
              :value="{value:dict.value,label:dict.text}"
            ></el-option>
          </el-select>
        </el-form-item>
        <!-- <el-form-item label="用户id" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入用户id" />
        </el-form-item> -->

        <!-- <el-form-item label="集团id" prop="groupId">
          <el-input v-model="form.groupId" placeholder="请输入集团id" />
        </el-form-item> -->
        <el-form-item label="楼宇/名称" prop="groupId">
          <!-- <el-input v-model="form.groupName" placeholder="请输入楼宇/名称" /> -->
          <el-select v-model="form.groupId" filterable placeholder="请选择楼宇/园区"  @change="selectGroupInfo($event)">
            <el-option
              v-for="dict in addgroupList"
              :key="dict.id"
              :label="dict.groupName"
              :value="dict.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="客户经理" prop="userName">
          <!-- <el-input v-model="form.userName" placeholder="请选择客户经理" /> -->
          <userSelect ref="empSelect" v-model="form.userName" @change="taskUserChange"/>
        </el-form-item>
        <el-form-item label="走访时效" prop="taskType">
          <el-radio-group v-model="form.taskType">
            <el-radio
              v-for="dict in taskEnum"
              :key="dict.value"
              :label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>

        <!-- <el-form-item label="集团类型" prop="groupType">
          <el-select v-model="form.groupType" placeholder="请选择集团类型">
            <el-option
              v-for="dict in dict.type.scene_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="创建人id" prop="createUserId">
          <el-input v-model="form.createUserId" placeholder="请输入创建人id" />
        </el-form-item>
        <el-form-item label="创建人" prop="createUserName">
          <el-input v-model="form.createUserName" placeholder="请输入创建人" />
        </el-form-item> -->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <!-- 用户导入对话框 -->
    <el-dialog :title="upload.title" :visible.sync="upload.open" width="400px" append-to-body>
      <el-upload
        ref="upload"
        :limit="1"
        accept=".xlsx, .xls"
        :headers="upload.headers"
        :file-list="fileList"
        :action="upload.url"
        :disabled="upload.isUploading"
        :on-progress="handleFileUploadProgress"
        :on-success="handleFileSuccess"
        :on-error="test"
        :auto-upload="false"
        drag
      >
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
        <div class="el-upload__tip text-center" slot="tip">
          <span>仅允许导入xls、xlsx格式文件。</span>
          <el-link type="primary" :underline="false" style="font-size:12px;vertical-align: baseline;" @click="importTemplate">下载模板</el-link>
        </div>
      </el-upload>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFileForm">确 定</el-button>
        <el-button @click="upload.open = false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listCmccusertask, getCmccusertask, delCmccusertask, addCmccusertask, updateCmccusertask } from "@/api/customer/cmccusertask";
import { getAreaList , getCmccGroupList} from "@/api/customer/cmccgroupinfo";
import userSelect from "./userSelect.vue"
import { getToken } from "@/utils/auth";
export default {
  name: "Cmccusertask",
  components: {userSelect},
  dicts: ['scene_type', 'task_enum'],
  data() {
    return {
      fileList : [],
      taskEnum : [{value:0, label:"本周"},{value:1, label:"一天"},{value:2, label:"三天"}],
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
      // 用户任务表格数据
      cmccusertaskList: [],

      groupList: [],

      addgroupList: [],
      // 区县字典字段
      areaList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      upload: {
        // 是否显示弹出层（用户导入）
        open: false,
        // 弹出层标题（用户导入）
        title: "",
        // 是否禁用上传
        isUploading: false,
        // 设置上传的请求头部
        headers: { Authorization: "Bearer " + getToken() },
        // 上传的地址
        url: process.env.VUE_APP_BASE_API + "/customer/cmccusertask/addBatch"
      },
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        deptId: null,
        deptName: null,
        userId: null,
        userName: null,
        groupId: null,
        groupName: null,
        taskStatus: null,
        groupType: null,
        createUserId: null,
        createUserName: null,
      },
      // 表单参数
      form: {
        taskType: 0
      },
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
    test(err,file,fileList) {
      console.log('上传文件失败',err,file,fileList)
    },
    taskUserChange(val, rows){

      console.log(JSON.stringify(rows))
      console.log(this.form.userName)
      if (rows === undefined) {
        this.form.userId = undefined
        this.form.userName = undefined
      } else {
        this.form.userId = rows.userId
        this.form.userName = rows.nickName
      }
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
    getaddCmccGroup(deptId){
      getCmccGroupList(deptId).then(response => {
        this.addgroupList = response.data
      })
    },
    selectAddArea (value) {
      // console.log("selectAddArea"+JSON.stringify(value))
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
    getCmccGroup(deptId){
      getCmccGroupList(deptId).then(response => {
        this.groupList = response.data
      })
    },
    getarealist() {
      getAreaList().then(response => {
        this.areaList = response.data;
      });
    },
    /** 查询用户任务列表 */
    getList() {
      this.loading = true;
      listCmccusertask(this.queryParams).then(response => {
        this.cmccusertaskList = response.rows;
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
        taskType: 0,
        deptId: null,
        deptName: null,
        userId: null,
        userName: null,
        groupId: null,
        groupName: null,
        taskStatus: null,
        groupType: null,
        createUserId: null,
        createUserName: null,
        createTime: null
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
      this.title = "添加用户任务";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getCmccusertask(id).then(response => {
        this.form = response.data;
        this.getaddCmccGroup(response.data.deptId)
        this.form.groupIdObj = {value:response.data.groupId,label:response.data.groupName}
        this.form.deptIdObj = {value:String(response.data.deptId),label:response.data.deptName}
        this.open = true;
        this.title = "修改用户任务";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateCmccusertask(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCmccusertask(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除用户任务编号为"' + ids + '"的数据项？').then(function() {
        return delCmccusertask(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('customer/cmccusertask/export', {
        ...this.queryParams
      }, `cmccusertask_${new Date().getTime()}.xlsx`)
    },
    /** 导入按钮操作 */
    handleImport() {
      console.log("Import button clicked");
      this.upload.title = "用户导入";
      this.upload.open = true;
    },
    /** 下载模板操作 */
    importTemplate() {
      this.download('/customer/cmccusertask/importTemplate', {
      }, `user_task_template_${new Date().getTime()}.xlsx`)
    },
    // 文件上传中处理
    handleFileUploadProgress(event, file, fileList) {

      this.upload.isUploading = true;
      console.log(event,file,fileList)
    },
    // 文件上传成功处理
    handleFileSuccess(response, file, fileList) {
  this.upload.open = false;
  this.upload.isUploading = false;
  this.$refs.upload.clearFiles();

  let errorDetails = "";
  response.data.forEach(item => {
    errorDetails += `<div>第 ${item.rowNum} 行: ${item.errorItemList.join(", ")}</div>`;
  });

  this.$alert(`<div style='overflow: auto;overflow-x: hidden;max-height: 70vh;padding: 10px 20px 0;'>${errorDetails}</div>`, "导入结果", { dangerouslyUseHTMLString: true });

  this.getList();
  console.log(response, file, fileList);
},
    // 提交上传文件
    submitFileForm() {
      this.$refs.upload.submit();
    }
  }
};
</script>

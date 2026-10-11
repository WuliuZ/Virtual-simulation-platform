<template>
  <el-container style="height: 100%">
    <el-aside style="width: auto"> </el-aside>
    <el-container>
      <el-main>
        <!-- 查询栏 -->
        <div>
          <el-form ref="searchForm" :inline="true" :model="searchForm">
            <el-form-item label="实训实验名称">
              <el-input
                v-model="searchForm.name"
                placeholder="名称"
              ></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="getData" icon="el-icon-search"
                >查询</el-button
              >
            </el-form-item>
          </el-form>
          <el-row>
            <el-button
              @click="addFormVisible = true"
              type="primary"
              size="medium"
              icon="el-icon-edit"
            >
              添加</el-button
            >
            <!-- <el-button
                class="user-btn"
                size="medium"
                icon="el-icon-plus"
                type="primary"
                @click="exp"
              >
                批量导出</el-button
              > -->
            <!-- <el-button type="danger" size="medium" @click="deleteByIds"
              >- 批量删除</el-button
            > -->
            <!-- <el-upload
                action="http://localhost:8080/api/user/upload"
                :show-file-list="false"
                :on-success="successUpload"
                style="display: inline-block"
              >
                <el-button class="user-btn" type="primary">批量导⼊</el-button>
              </el-upload> -->
          </el-row>

          <!-- 新增设备 -->
          <el-dialog
            title="新增实训实验"
            :visible.sync="addFormVisible"
            width="30%"
            :before-close="addClose"
          >
            <el-form
              ref="addForm"
              :model="addForm"
              :rules="rules"
              label-width="auto"
              size="mini"
            >
              <el-form-item label="实训实验名称" prop="name">
                <el-input v-model="addForm.name"></el-input>
              </el-form-item>
              <el-form-item label="课程学时" prop="classHours">
                <el-input v-model="addForm.classHours"></el-input>
              </el-form-item>
              <el-form-item label="专业" prop="major">
                <el-input v-model="addForm.major"></el-input>
              </el-form-item>
              <el-form-item label="实验类型" prop="experimentType">
                <el-input v-model="addForm.experimentType"></el-input>
              </el-form-item>
              <el-form-item label="简介" prop="intro">
                <el-input v-model="addForm.intro"></el-input>
              </el-form-item>
              <el-form-item label="实验室" prop="laboratory_id">
                <el-select v-model="addForm.laboratory_id" placeholder="请选择类别">
                <el-option
                  v-for="item in laboratoryList"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                />
              </el-select>
              </el-form-item>

              <el-form-item>
                <el-button type="primary" @click="add('addForm')">提交</el-button>
                <el-button @click="addFormVisible = false">取消</el-button>
              </el-form-item>
            </el-form>
          </el-dialog>
        </div>
        <!-- 表格 -->
        <br />
        <el-table
          :data="tableData"
          border
          @selection-change="handleSelectionChange"
        >
          <el-table-column type="selection" fixed="left" width="55">
          </el-table-column>
          <el-table-column prop="name" label="实训课程名称" width="150">
          </el-table-column>
          <el-table-column prop="classHours" label="课程学时" width="120">
          </el-table-column>
          <el-table-column prop="major" label="专业" width="120">
          </el-table-column>
          <el-table-column prop="experimentType" label="实验类型" width="120">
          </el-table-column>
          <el-table-column prop="intro" label="简介" width="120">
          </el-table-column>
          <el-table-column prop="laboratory_name" label="实验室" width="120">
          </el-table-column>
          <el-table-column prop="update_time" label="更新时间" width="200">
          </el-table-column>
          
          <el-table-column
            fixed="right"
            label="操作"
            align="center"
            width="135"
          >
            <template slot-scope="scope">
              <el-button type="text" size="small" @click="edit(scope.row)"
                >编辑</el-button
              >
              <el-button
                type="text"
                size="small"
                @click="showDetails(scope.row)"
                >详情</el-button
              >

              <el-popconfirm
                title="是否确定删除该实训实验？"
                @confirm="del(scope.row.id)"
              >
                <el-button
                  type="text"
                  size="small"
                  style="margin-left: 10px"
                  slot="reference"
                  >删除</el-button
                >
              </el-popconfirm>
            </template>
          </el-table-column>
        </el-table>
        <!-- 编辑设备 -->
        <el-dialog title="编辑信息" :visible.sync="editFormVisible">
          <el-form
            ref="editForm"
            :model="editForm"
            label-width="auto"
            size="mini"
          >
            <el-form-item label="实训课程">
              <el-input v-model="editForm.name"></el-input>
            </el-form-item>
            
            <el-form-item label="专业">
              <el-input v-model="editForm.major"></el-input>
            </el-form-item>
            <el-form-item label="课程学时">
              <el-input v-model="editForm.classHours"></el-input>
            </el-form-item>

            <el-form-item label="实验类型">
              <el-input v-model="editForm.experimentType"></el-input>
            </el-form-item>
            <el-form-item label="简介">
              <el-input v-model="editForm.intro"></el-input>
            </el-form-item>
            <el-form-item label="实验室">
              <el-select v-model="editForm.laboratory_id" placeholder="请选择类别">
                <el-option
                  v-for="item in laboratoryList"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                />
              </el-select>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="confirmEdit()">确 定</el-button>
            <el-button @click="editFormVisible = false">取 消</el-button>
          </div>
        </el-dialog>
        <!-- 详情 -->
        <el-dialog :visible.sync="detailsVisible" title="用户详情">
          <el-descriptions title="用户信息" :column="2">
            <el-descriptions-item label="实训课程名称">{{
              selectedUser.name
            }}</el-descriptions-item>
            <el-descriptions-item label="专业">{{
              selectedUser.major
            }}</el-descriptions-item>
            <el-descriptions-item label="课程学时">{{
              selectedUser.classHours
            }}</el-descriptions-item>
            <el-descriptions-item label="实验类型">{{
              selectedUser.experimentType
            }}</el-descriptions-item>
            <el-descriptions-item label="简介">{{
              selectedUser.intro
            }}</el-descriptions-item>
            <el-descriptions-item label="实验室">{{
              selectedUser.laboratory_name
            }}</el-descriptions-item>
            <el-descriptions-item label="更新时间">{{
              selectedUser.update_time
            }}</el-descriptions-item>

            <!-- 根据需要添加更多描述项 -->
          </el-descriptions>
          <span slot="footer">
            <el-button type="primary" @click="detailsVisible = false"
              >关闭</el-button
            >
          </span>
        </el-dialog>
        <br />
        <!-- 分页条 -->
        <!-- <div class="block">
            <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :background="background"
              :current-page="currentPage"
              :page-sizes="[5, 10, 15, 20]"
              :page-size="5"
              layout="total, sizes, prev, pager, next, jumper"
              :total="totalCount"
            >
            </el-pagination>
          </div> -->
      </el-main>
    </el-container>
  </el-container>
</template>
  
  
  <script>
import request from "../../utils/request";
import moment from "moment";

export default {
  data() {
    var checkname = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入实训实验名称"));
      } else {
        callback();
      }
    };
    return {
      
      detailsVisible: false, //详情对话框可见性
      selectedUser: {}, //当前选中的用户
      addFormVisible: false, //新增设备表单可见性
      editFormVisible: false, //编辑表单可见性
      multipleSelection: [], //选中行的数据存储在该数组
      currentPage: 1, // 当前页码
      pageSize: 10, // 每页显示的记录数
      total: 0, // 总记录数
      //搜索框
      searchForm: {
        name: "",
      },
      laboratoryList:[],
      tableData: [], //表格数据
      //编辑表单
      editForm: {
        name: "",
        major: "",
        classHours: "",
        certificationMapping: "",
        experimentType: "",
        competitionMapping: "",
        courseSpecific: "",
        intro: "",
        laboratory_id: "",
        relatedTrainingResources: "",
      },
      // 新增表单
      addForm: {
        name: "",
        major: "",
        classHours: "",
        certificationMapping: "",
        experimentType: "",
        competitionMapping: "",
        courseSpecific: "",
        intro: "",
        laboratory_id: "",
        relatedTrainingResources: "",
      },
      rules:{
        name: [{ required: true, message: "请输入实训实验名称", trigger: "blur",validator:checkname , }],
          // major: [{ required: true, message: "请输入专业", trigger: "blur" }],
        classHours: [{ required: true, message: "请输入课程学时", trigger: "blur" }],
      //   certificationMapping: [{ required: true, message: "请输入证书", trigger: "blur" }],
      //   experimentType: [{ required: true, message: "请输入实验类型", trigger: "blur" }],
      //   competitionMapping: [{ required: true, message: "请输入竞赛", trigger: "blur" }],
      //   courseSpecific: [{ required: true, message: "请输入课程特色", trigger: "blur" }],
      //   create_time: [{ required: true, message: "请输入创建时间", trigger: "blur" }],
      //   update_time: [{ required: true, message: "请输入更新时间", trigger: "blur" }],
      //   intro: [{ required: true, message: "请输入课程简介", trigger: "blur" }],
      //   laboratory_id: [{ required: true, message: "请输入实验室", trigger: "blur" }],
      }
    };
  },
  methods: {
    showDetails(user) {
      this.selectedUser = user;
      console.log(this.selectedUser);
      this.detailsVisible = true;
    },
    // 时间转换
    formatUpdateTime(dateString) {
      return moment(dateString).format("YYYY-MM-DD HH:mm:ss");
    },
    // 获取数据
    getData() {
      //发送请求访问后端接口
      let params = {
        name: this.searchForm.name,
      };
      console.log(params);
      request
        .get("/training/selectTraining", { params })
        .then((res) => {
          console.log(res);
          if (res.code == 0) {
            console.log(res.data);

            let tempData = res.data;
            tempData.forEach((item) => {
              item.update_time = this.formatUpdateTime(item.update_time);
              switch (item.type) {
                case 1:
                  item.type = "学生";
                  break;
                case 2:
                  item.type = "教师";
                  break;
                case 3:
                  item.type = "实验室管理员";
                  break;
                case 4:
                  item.type = "后台管理员";
                  break;
                case 5:
                  item.type = "超管";
                  break;
                //default情况不需要处理，因为1和2已经被过滤掉了
              }
            });
            this.tableData = tempData;
          } else {
            alert("查询失败");
          }
        })
        .catch((error) => {
          // 处理请求出错的情况
          this.$message({
            showClose: true,
            message: "请求出错",
            type: "error",
          });
          console.error("Error:", error);
        });
    },
    //添加数据表单的×
    addClose() {
      this.addFormVisible = false;
    },
    edit(row) {
      this.editFormVisible = true;
      let tempData = row;
      // //转换数据
      // if (tempData.laboratory_id == 1) {
      //   tempData.laboratory_id = "华迪";
      // } else if (tempData.laboratory_id == 2) {
      //   tempData.laboratory_id = "软通";
      // } else if (tempData.laboratory_id == 3) {
      //   tempData.laboratory_id = "学链";
      // }
      // if (tempData.status == 1) {
      //   tempData.status = "正常";
      // } else if (tempData.status == 2) {
      //   tempData.status = "损坏";
      // }

      this.editForm = tempData;
    },
    stringToLocalDateTime(dateTimeString) {
      // 解析字符串为 Date 对象
      const dateTime = new Date(dateTimeString);

      // 将 Date 对象转换为 LocalDateTime 对象（JavaScript 方式）
      const localDateTime = new Date(dateTime.getTime());

      return localDateTime;
    },
    confirmEdit() {
      let params = this.editForm;
      delete params.create_time;
      delete params.update_time;
      params.update_time = this.stringToLocalDateTime(params.update_time);
      console.log(params);
      request.post("/training/updateTraining", params).then((res) => {
        if (res.code == 0) {
          this.$message.success("修改成功！");
          this.getData();
        } else {
          this.$message.error("修改失败，设备已存在！");
          this.getData();
        }
      });
      this.editFormVisible = false;
    },

    //删除按钮的方法
    del(id) {
      request.delete("/training/deleteTrainingById/" + id).then((res) => {
        if (res.code == 0) {
          this.$message.success("删除成功！");
          this.getData();
        } else {
          this.$message.error("删除失败！");
          this.getData();
        }
      });
    },
    // 批量删除方法
    deleteByIds() {
      this.$confirm("此操作将删除该数据, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          let arr = [];
          //循环遍历获取选中数据的id并放⼊数组中
          this.multipleSelection.forEach((item) => {
            arr.push(item.id);
          });
          console.log(arr);
          request.get("/user/del/" + arr).then((res) => {
            if (res.code == 0) {
              this.$message.success("删除成功");
              this.getData();
            } else {
              this.$message.error("删除失败");
            }
          });
        })
        .catch(() => {
          //用户点击取消按钮
          this.$message.info("已取消删除");
        });
      //发送请求访问后端批量删除接⼝
    },
    //添加设备
    add(addForm) {
      this.$refs[addForm].validate((valid) => {
        if(valid){
      let parent = this.addForm;
      //转换几个字段

      console.log(parent);
      this.addFormVisible = true;
      request.post("/training/insertTraining", parent).then((result) => {
        if (result.code == 0) {
          this.$message({
            showClose: true,
            message: "添加成功",
            type: "success",
            onClose: () => {
              this.addFormVisible = false;
              this.resetForm();
              this.getData();
            },
          });
        } else {
          this.$message({
            showClose: true,
            message: "设备已存在",
            type: "error",
            onClose: () => {
              this.addFormVisible = false;
              this.resetForm();
            },
          });
        }
      });
    }else {
        this.$message({
              showClose: true,
              message: "请检查输入信息",
              type: "error",
            });
          return false;
        }
      });
    },
    //多选框记录
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    handleSizeChange(val) {
      // 重新设置每页显示的条数
      this.pageSize = val;
      this.page();
    },
    handleCurrentChange(val) {
      // 重新设置当前页码
      this.currentPage = val;
      this.page();
    },
    resetForm() {
      // 清除表单元素
      this.addForm = {};
      this.editForm = {};
    },
    exp() {
      console.log(this.multipleSelection);
      let arr = [];
      // 循环遍历获取选中数据的 id、name 和 number 并放入数组中
      this.multipleSelection.forEach((item) => {
        arr.push(item.id);
      });
      console.log(arr);
      // 发送 POST 请求
      request.get("/export/exportToExcel/" + arr);
    },
    findAllLaboratory() {
      request.get("/laboratory/selectLaborytory").then((result) => {
        console.log("roles:", result);

        this.laboratoryList = result.data;
      });
    },
  },
  mounted() {
    this.getData();
    this.findAllLaboratory();
  },
  computed: {},
};
</script>
  <style>
.el-header {
  background-color: #b3c0d1;
  color: #333;
  line-height: 60px;
}

.el-aside {
  color: #333;
}
.clink {
  color: #525354;
  text-decoration: none;
}
</style>
<template>
  <el-container style="height: 100%">
    <el-aside style="width: auto"> </el-aside>
    <el-container>
      <el-main>
        <!-- 查询栏 -->
        <div>
          <el-form ref="searchForm" :inline="true" :model="searchForm">
            <el-form-item label="姓名">
              <el-input v-model="searchForm.name" placeholder="姓名"></el-input>
            </el-form-item>
            <el-form-item label="电话">
              <el-input v-model="searchForm.phone" placeholder="电话"></el-input>
            </el-form-item>
            <el-form-item label="角色类型">
              <el-select v-model="searchForm.role_id" placeholder="请选择类别">
                <el-option
                  v-for="item in rolesList"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                />
              </el-select>
            </el-form-item>
            <!-- <el-form-item label="角色类型">
              <el-input
                v-model="searchForm.role_id"
                placeholder="类型"
              ></el-input>
            </el-form-item> -->
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

            <el-button type="danger" size="medium" @click="deleteByIds"
              >- 批量删除</el-button
            >
          </el-row>

          <!-- 新增设备 -->
          <el-dialog
            title="新增用户"
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
              <el-form-item label="姓名" prop="name">
                <el-input v-model="addForm.name"></el-input>
              </el-form-item>
              <el-form-item label="用户类型" prop="role_id">
                <el-select v-model="addForm.role_id" placeholder="请选择类别">
                  <el-option
                    v-for="item in rolesList"
                    :key="item.id"
                    :label="item.name"
                    :value="item.id"
                  />
                </el-select>
              </el-form-item>
              <el-form-item label="用户名" prop="username">
                <el-input v-model="addForm.username"></el-input>
              </el-form-item>

              <el-form-item label="密码" prop="password">
                <el-input v-model="addForm.password"></el-input>
              </el-form-item>

              <el-form-item label="邮箱" prop="email">
                <el-input v-model="addForm.email"></el-input>
              </el-form-item>
              <el-form-item label="电话" prop="phone">
                <el-input v-model="addForm.phone"></el-input>
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
          <el-table-column prop="name" label="姓名" width="150">
          </el-table-column>
          <el-table-column prop="role_name" label="角色类型" width="120">
          </el-table-column>
          <el-table-column prop="username" label="用户名" width="120">
          </el-table-column>
          <el-table-column prop="password" label="密码" width="120">
          </el-table-column>
          <el-table-column prop="email" label="邮箱" width="120">
          </el-table-column>
          <el-table-column prop="phone" label="电话" width="120">
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
                title="是否确定删除该用户？"
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
            <el-form-item label="姓名">
              <el-input v-model="editForm.name"></el-input>
            </el-form-item>
            <el-form-item label="用户类型">
              <el-select v-model="editForm.role_id" placeholder="请选择类别">
                <el-option
                  v-for="item in rolesList"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                />
              </el-select>
            </el-form-item>
            <el-form-item label="用户名">
              <el-input v-model="editForm.username"></el-input>
            </el-form-item>
            <el-form-item label="密码">
              <el-input v-model="editForm.password"></el-input>
            </el-form-item>

            <el-form-item label="邮箱">
              <el-input v-model="editForm.email"></el-input>
            </el-form-item>
            <el-form-item label="电话">
              <el-input v-model="editForm.phone"></el-input>
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
            <el-descriptions-item label="姓名">{{
              selectedUser.name
            }}</el-descriptions-item>
            <el-descriptions-item label="用户类型">{{
              selectedUser.role_name
            }}</el-descriptions-item>
            <el-descriptions-item label="用户名">{{
              selectedUser.username
            }}</el-descriptions-item>
            <el-descriptions-item label="密码">{{
              selectedUser.password
            }}</el-descriptions-item>
            <el-descriptions-item label="邮箱">{{
              selectedUser.email
            }}</el-descriptions-item>
            <el-descriptions-item label="电话">{{
              selectedUser.phone
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
import { mapState } from "vuex";

export default {
  data() {
    var checkusername = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入用户名"));
      } else {
        callback();
      }
    };
    return {
      user: localStorage.getItem("user")
        ? JSON.parse(localStorage.getItem("user"))
        : {},
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
        phone:"",
        role_id: "",
      },
      rolesList: [], //角色数据
      tableData: [], //表格数据
      //编辑表单
      editForm: {
        name: "", //姓名
        role_id: "", //角色名称
        username: "", //用户名
        password: "", //密码
        email: "", //邮箱
        phone: "", //电话
      },
      // 新增表单
      addForm: {
        name: "", //姓名
        username: "", //用户名
        password: "", //密码
        email: "", //邮箱
        phone: "", //电话
        role_id: "", //角色名称
      },
      rules:{
        username: [{ required: true, message: "请输入用户名", trigger: "blur",validator:checkusername , }],
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
  // 发送请求访问后端接口
  let params = {
    name: this.searchForm.name,
    phone: this.searchForm.phone,
    role_id: this.searchForm.role_id,
  };
  console.log(params);
  request
    .get("/user/selectAll", { params })
    .then((res) => {
      console.log(res);
      if (res.code == 0) {
        console.log(res.data);
        let tempData = res.data;
        // 过滤 role_id 为 3、4 或 5 的用户
        if(this.user.role_id === 4){
          tempData = tempData.filter((item) => item.role_id !== 3 && item.role_id !== 4 && item.role_id !== 5);
        }
        if (this.user.role_id === 5) {
          tempData = tempData.filter((item) => item.role_id === 3 || item.role_id === 4);
        }
        
        // 格式化更新时间
        tempData.forEach((item) => {
          item.update_time = this.formatUpdateTime(item.update_time);
        });
        // 更新表格数据
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
      console.log(params);
      delete params.create_time;
      delete params.update_time;
      // params.update_time = this.stringToLocalDateTime(params.update_time);

      request.post("/user/update", params).then((res) => {
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
      request.delete("/user/delete/" + id).then((res) => {
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
      this.$confirm("此操作将删除选中数据, 是否继续?", "提示", {
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
      request.post("/user/register", parent).then((result) => {
        if (result.code == 0) {
          this.$message({
            showClose: true,
            message: "添加成功",
            type: "success",
            duration: 1500, // 设置提示框显示时间
            onClose: () => {
              this.addFormVisible = false;
              this.resetForm();
              this.getData();
            },
          });
        } else {
          this.$message.error(result.msg);
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
    findAllRoles() {
    this.loading = true; // 设置一个loading状态，用于显示加载动画
    request.get("/role/selectRole").then((result) => {
      this.loading = false; // 请求完成后关闭loading
      this.rolesList = result.data; // 更新角色列表
      if (this.user.role_id === 4) {
        this.rolesList = this.rolesList.filter(
          (item) => item.id != 3 && item.id !=4 && item.id !=5
        );
      }else if (this.user.role_id === 5) {
      this.rolesList = this.rolesList.filter(
        (item) => item.id === 3 || item.id === 4);
    }
    });
  },
  },
  mounted() {
    this.getData();
    //当页面加载完成后，发送异步请求，获取数据
    this.findAllRoles();
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
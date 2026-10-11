<template>
  <el-container style="height: 100%">
    <el-aside style="width: auto"> </el-aside>
    <el-container>
      <el-main>
        <!-- 查询栏 -->
        <div>
          <el-form ref="searchForm" :inline="true" :model="searchForm">
            <el-form-item label="角色名称">
              <el-input v-model="searchForm.name" placeholder="名称"></el-input>
            </el-form-item>

            <el-form-item>
              <el-button type="primary" @click="getData" icon="el-icon-search"
                >查询</el-button
              >
            </el-form-item>
          </el-form>
          <el-row class="button-row">
            <el-button
              @click="addFormVisible = true"
              type="primary"
              icon="el-icon-edit"
            >
              添加</el-button
            >
          </el-row>

          <!-- 新增设备 -->
          <el-dialog
            title="新增角色"
            :visible.sync="addFormVisible"
            width="30%"
            :before-close="addClose"
          >
            <el-form
              ref="addForm"
              :rules="rules"
              :model="addForm"
              label-width="auto"
              size="mini"
            >
              <el-form-item label="角色名称" prop="name">
                <el-input v-model="addForm.name"></el-input>
              </el-form-item>
              <el-form-item label="角色描述" prop="role_describe">
                <el-input v-model="addForm.role_describe"></el-input>
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
        <el-table :data="tableData" border>
          <el-table-column prop="name" label="角色名称" width="100">
          </el-table-column>
          <el-table-column prop="role_describe" label="角色描述" width="240">
          </el-table-column>
          <el-table-column prop="create_time" label="创建时间" width="180">
          </el-table-column>
          <el-table-column prop="update_time" label="更新时间" width="180">
          </el-table-column>

          <el-table-column label="操作" align="center" width="200px">
            <template slot-scope="scope">
              <el-button type="text" size="small" @click="edit(scope.row)"
                >编辑</el-button
              >

              <el-popconfirm
                title="是否确定删除该角色？"
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
        <!-- 编辑角色 -->
        <el-dialog title="编辑信息" :visible.sync="editFormVisible">
          <el-form
            ref="editForm"
            :model="editForm"
            label-width="auto"
            size="mini"
          >
            <el-form-item label="角色名称">
              <el-input v-model="editForm.name"></el-input>
            </el-form-item>
            <el-form-item label="描述">
              <el-input v-model="editForm.role_describe"></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="confirmEdit()">确 定</el-button>
            <el-button @click="editFormVisible = false">取 消</el-button>
          </div>
        </el-dialog>

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
      if (value == "") {
        callback(new Error("请输入角色名"));
      } else {
        callback();
      }
    };
    return {
      addFormVisible: false, //新增设备表单可见性
      editFormVisible: false, //编辑表单可见性
      tableData: [], //表格数据
      searchForm: {
        name: "",
      },
      //编辑表单
      editForm: {
        name: "", //角色名称
        role_describe: "", //用户描述
      },
      // 新增表单
      addForm: {
        name: "", //角色名称
        role_describe: "", //用户描述
      },
      rules: {
        name: [
          {
            required: true,
            message: "请输入角色名",
            trigger: "blur",
            validator: checkname,
          },
        ],
        // number: [{ required: true, message: "请输入课程编号", trigger: "blur" }],
        // experiment: [{ required: true, message: "请输入实验", trigger: "blur" }],
        // intro: [{ required: true, message: "请输入简介", trigger: "blur" }],
        // pte_id: [{ required: true, message: "请输入实训实验", trigger: "blur" }],
      },
    };
  },
  methods: {
    // 时间转换
    formatUpdateTime(dateString) {
      return moment(dateString).format("YYYY-MM-DD HH:mm:ss");
    },
    // 获取数据
    getData() {
      let params = {
        name: this.searchForm.name,
      };
      //发送请求访问后端接口

      request
        .get("/role/selectRole", { params })
        .then((res) => {
          console.log(res);
          if (res.code == 0) {
            console.log(res.data);

            let tempData = res.data.filter(
              (item) => item.type !== 1 && item.type !== 2
            );
            tempData.forEach((item) => {
              item.update_time = this.formatUpdateTime(item.update_time);
              item.create_time = this.formatUpdateTime(item.create_time);
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
      // params.create_time = this.stringToLocalDateTime(params.create_time);
      request.post("/role/updateRole", params).then((res) => {
        if (res.code == 0) {
          this.$message.success("修改成功！");
          this.getData();
        } else {
          this.$message.error("修改失败！");
          this.getData();
        }
      });
      this.editFormVisible = false;
    },

    //删除按钮的方法
    del(id) {
      console.log(id);
      request.delete("/role/deleteRoleById/" + id).then((res) => {
        if (res.code == 0) {
          this.$message.success("删除成功！");
          this.getData();
        } else {
          this.$message.error("删除失败！");
          this.getData();
        }
      });
    },
    //添加
    add(addForm) {
      this.$refs[addForm].validate((valid) => {
        if(valid){
      let parent = this.addForm;
      console.log(parent);
      this.addFormVisible = true;
      request.post("/role/insertRole", parent).then((result) => {
        console.log(result);
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
            message: "请检查输入信息",
            type: "error",
          });
          return false;
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


    resetForm() {
      // 清除表单元素
      this.addForm = {};
      this.editForm = {};
    },
  },
  mounted() {
    this.getData();
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
.button-row {
  display: flex;
  /* justify-content: flex-end; 按钮将位于行的末尾 */
}
</style>
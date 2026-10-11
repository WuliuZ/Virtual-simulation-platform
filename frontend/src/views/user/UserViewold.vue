<template>
  <div>
    <el-input style="width: 120px; margin-right: 10px" v-model="name" placeholder="请输入姓名" clearable @keyup.enter.native="selectAll()"></el-input>
    <el-input style="width: 120px; margin-right: 10px" v-model="phone" placeholder="请输入电话" clearable @keyup.enter.native="selectAll()"></el-input>

    <el-button type="primary" @click="selectAll()">搜索</el-button>
    <el-table
      :data="tableData"
      style="width: 100%"
      :row-class-name="tableRowClassName"
    >
      <el-table-column prop="id" label="序号" width="50"> </el-table-column>
      <el-table-column prop="name" label="用户名" width="120"></el-table-column>
      <el-table-column prop="username" label="账号" width="120"></el-table-column>
      <el-table-column prop="email" label="邮箱" width="120"> </el-table-column>
      <el-table-column prop="phone" label="电话" width="120"> </el-table-column>
      <el-table-column prop="intro" label="简介" width="120"> </el-table-column>
      <el-table-column prop="type" label="用户类型" width="120"></el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button style="margin-right:10px" type="primary" @click="edit(scope.row)">编辑</el-button>
        
          <el-popconfirm title="是否确定删除该用户？" @confirm="del(scope.row.id)">
            <el-button slot="reference" type="danger">删除</el-button>
          </el-popconfirm>

        </template>
      </el-table-column>
    </el-table>

    <el-dialog title="用户信息" :visible.sync="dialogFormVisible">
      <el-form :model="editForm">
        <el-form-item label="用户名" :label-width="formLabelWidth">
          <el-input v-model="editForm.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="账号" :label-width="formLabelWidth">
          <el-input v-model="editForm.username" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" :label-width="formLabelWidth">
          <el-input v-model="editForm.email" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="电话" :label-width="formLabelWidth">
          <el-input v-model="editForm.phone" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="简介" :label-width="formLabelWidth">
          <el-input v-model="editForm.intro" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="用户类型" :label-width="formLabelWidth">
          <el-select v-model="editForm.type" placeholder="请选择用户类型">
            <el-option label="学生" value="1"></el-option>
            <el-option label="教师" value="2"></el-option>
            <el-option label="管理员" value="3"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="confirmEdit()">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import request from "../../utils/request";

export default {
  data() {
    return {
      tableData: [],
      phone: "",
      name: "",
      dialogFormVisible: false,
      editForm: {
        name: "",
        username: "",
        password: "",
        email: "",
        phone: "",
        intro: "",
        type: "学生",
      },
      formLabelWidth: "100px",
    };
  },
  methods: {
    //确定方法
    confirmEdit() {
      request.post("/user/update", this.editForm).then((res) => {
        if (res.code == 0) {
          this.$message.success("修改成功！");
          this.selectAll;
        } else {
        }
      });
      this.dialogFormVisible = false;
    },

    //编辑按钮的方法
    edit(row) {
      this.dialogFormVisible = true;
      this.editForm = row;
    },

    //删除按钮的方法
    del(id) {
      request.delete("/user/delete/" + id).then((res) => {
        if (res.code == 0) {
          this.$message.success("删除成功！");
          this.selectAll();
        } else {
        }
      });
    },
    //按条件查询
    selectAll() {
      //发送请求访问后端接口
      let params = {
        name: this.name,
        phone: this.phone,
      };

      request.get("/user/selectAll", { params }).then((res) => {
        if (res.code == 0) {
          console.log(res.data);
          this.tableData = res.data;
        } else {
          alert("查询失败");
        }
      });
    },
  },
  mounted() {
    //在加载页面时就会自动执行里面的代码
    this.selectAll();
  },
  tableRowClassName({ row, rowIndex }) {
      if (rowIndex === 1) {
        return "warning-row";
      } else if (rowIndex === 3) {
        return "success-row";
      }
      return "";
    },

};
</script>

<style>
.el-form-item__label {
  color: black !important; /* 使用 !important 以确保样式覆盖框架默认样式 */
}
</style>
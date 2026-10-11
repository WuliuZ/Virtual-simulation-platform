<template>
  <div class="home">
    <div class="bg-img"></div>

    <el-row type="flex" class="row-bg" justify="end">
      <el-col :span="16">
        <div></div>
      </el-col>
      <el-col :span="8">
        <div class="register-div">
          <el-form
            :model="ruleForm"
            status-icon
            :rules="rules"
            ref="ruleForm"
            label-width="80px"
            size="mini"
            class="register-form custom-label"
          >
          
            <el-button type="text" style=" color: black;" @click="goLogin">

              <i class="el-icon-caret-left"></i> 
              返回登录
            </el-button>
            
            <el-form-item label="用户名" prop="name">
              <el-input type="name" v-model="ruleForm.name"></el-input>
            </el-form-item>
            <el-form-item label="账号" prop="username">
              <el-input
                type="username"
                v-model="ruleForm.username"
                placeholder="输入5-20个以字母开头、可带数字、“_”、“.”的字串"
              ></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password">
              <el-input
                type="password"
                v-model="ruleForm.password"
                autocomplete="off"
                placeholder="输入长度在4~20的字串"
              ></el-input>
            </el-form-item>
            <el-form-item label="确认密码" prop="checkPass">
              <el-input
                type="password"
                v-model="ruleForm.checkPass"
                autocomplete="off"
              ></el-input>
            </el-form-item>
            <el-form-item label="邮箱" prop="email">
              <el-input type="email" v-model="ruleForm.email"></el-input>
            </el-form-item>
            <el-form-item label="电话" prop="phone">
              <el-input type="phone" v-model="ruleForm.phone"></el-input>
            </el-form-item>
            <el-form-item label="用户类型" prop="role_id">
              <el-select v-model="ruleForm.role_id" placeholder="请选择用户类型">
                <el-option label="学生" value="1"></el-option>
                <el-option label="教师" value="2"></el-option>
              
              </el-select>
            </el-form-item>
            <el-form-item>
              <el-button
                :plain="true"
                type="primary"
                @click="register('ruleForm')"
                >提交</el-button
              >
              <el-button @click="resetForm('ruleForm')">重置</el-button>
            </el-form-item>
          </el-form>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
// @ is an alias to /src

import request from "../../utils/request";
export default {
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"));
      } else {
        if (this.ruleForm.checkPass !== "") {
          this.$refs.ruleForm.validateField("checkPass");
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"));
      } else if (value !== this.ruleForm.password) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    };
    var checkemail = (rule, value, callback) => {
      const reg = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
      let flag = reg.test(value);
      if (value === "") {
        callback(new Error("邮箱不能为空"));
      } else if (!flag) {
        callback(new Error("请输入正确的邮箱"));
      } else {
        callback();
      }
    };
    var checkphone = (rule, value, callback) => {
      const reg =
        /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
      let flag = reg.test(value);
      if (value === "") {
        callback(new Error("电话不能为空"));
      } else if (!flag) {
        callback(new Error("请输入正确的电话"));
      } else {
        callback();
      }
    };
    var checkusername = (rule, value, callback) => {
      //输入5-20个以字母开头、可带数字、“_”、“.”的字串
      const reg = /^[a-zA-Z]{1}([a-zA-Z0-9]|[._]){4,19}$/;
      let flag = reg.test(value);
      if (value === "") {
        callback(new Error("账号不能为空"));
      } else if (!flag) {
        callback(new Error("账号5-20个以字母开头、可带数字、“_”、“.”"));
      } else {
        callback();
      }
    };
    return {
      options: [{}],
      ruleForm: {
        password: "",
        checkPass: "",
        name: "",
        username: "",
        email: "",
        phone: "",
        role_id:""
      },
      rules: {
        password: [
          { min: 4, max: 20, message: "请输入正确的密码", trigger: "blur" },
          { validator: validatePass, trigger: "blur" },
        ],
        checkPass: [{ validator: validatePass2, trigger: "blur" }],
        email: [{ validator: checkemail, trigger: "blur" }],
        type: [{ required: true, message: "请选择", trigger: "blur" }],
        phone: [{ validator: checkphone, trigger: "blur" }],
        name: [
          { max: 20, message: "最大限制20个字符", trigger: "change" },
          { required: true, message: "用户名不能为空", trigger: "change" },
        ],
        username: [
          { required: true, message: "账号不能为空", trigger: "change" },
          { validator: checkusername, trigger: "blur" },
        ],
      },
    };
  },
  methods: {
    goLogin() {
      this.$router.push("/login");
    },
    register(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          console.log(this.ruleForm);

          request.post("/user/register", this.ruleForm).then((res) => {
            if (res.code == 0) {
              this.$message({
                showClose: true,
                message: "注册成功,跳转到登录页",
                type: 'success',
              });
              this.$router.push("/login");
            } else {
              // 如果注册失败，可以显示一个错误消息
              this.$message.error(res.msg);
            }
          });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
  },
};
</script>

<style>
.register-div {
  /* 设置表单距浏览器上边距 */
  margin-top: 25%;
}
/* 设置背景图片 */
.bg-img {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-image: url("../../assets/registerbg.png");
  background-repeat: no-repeat;
  background-size: cover;
  background-position: center;
  z-index: -10;
}

/* 设置表单背景色 */ 
.register-form {
  width: 80%;
  padding: 40px;
  /* 设置透明背景 */
  background-color: rgba(255, 255, 255, 0.3);
  border-radius: 20px;
}

.custom-label .el-form-item__label {
  /*设置标签文字颜色为白色 */
  color: #ffffff;
}
</style>

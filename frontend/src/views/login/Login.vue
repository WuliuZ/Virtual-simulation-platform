<template>
  <div class="home">
    <div class="bg-img"></div>

    <el-row type="flex" class="row-bg" justify="end">
      <el-col :span="4">
        <div></div>
      </el-col>
      <el-col :span="16">
        <div class="login-div">
          <el-form
            :model="ruleForm"
            status-icon
            :rules="rules"
            ref="ruleForm"
            label-width="80px"
            
            class="login-form"
          >
          <div style="text-align:center" class="login-label">
            <h1><pre>欢迎登录虚拟仿真实训教学管理
及资源共享云平台管理系统</pre></h1>
            </div>
          
            <el-form-item label="账号" prop="username" class="login-input">
              <el-input
                type="username"
                v-model="ruleForm.username"
              ></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password" class="login-input">
              <el-input
                type="password"
                v-model="ruleForm.password"
                autocomplete="off"               
              ></el-input>
            </el-form-item>
            <!-- <el-form-item label="用户类型" prop="role_id" class="login-input">
              <el-select v-model="ruleForm.role_id" placeholder="请选择用户类型">
                <el-option label="学生" value="1"></el-option>
                <el-option label="教师" value="2"></el-option>
                <el-option label="实验室管理员" value="3"></el-option>
                <el-option label="后台管理员" value="4"></el-option>
                <el-option label="超级管理员" value="5"></el-option>
                <el-option label="小张" value="6"></el-option>
              </el-select>
            </el-form-item> -->
            <el-form-item class="login-btn" label-width="0px">
              <el-button
                :plain="true"
                type="primary"
                @click="login('ruleForm')"
                >登录</el-button
              >
              <el-button @click="toRegister()">注册</el-button>
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


    /*验证密码规则*/
    var validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("密码不能为空"));
      } else {
        if (this.ruleForm.checkPass !== "") {
          this.$refs.ruleForm.validateField("checkPass");
        }
        callback();
      }
    };
    
    
    var checkusername = (rule, value, callback) => {
      //输入5-20个以字母开头、可带数字、“_”、“.”的字串
      const reg = /^[a-zA-Z]{1}([a-zA-Z0-9]|[._]){4,19}$/;
      let flag = reg.test(value);
      if (value === "") {
        callback(new Error("账号不能为空"));
      } 
       else {
        callback();
      }
    };
    return {
      options: [{}],
      ruleForm: {
        password: "",
        username: "",
        role_id: "",
      },
      rules: {
        password: [
          { required: true, message: "密码不能为空", trigger: "blur" },
          { validator: validatePass, trigger: "blur" },
        ],
       
        username: [
          { required: true, message: "账号不能为空", trigger: "change" },
          { validator: checkusername, trigger: "blur" },
        ],
        type:[
          { required: true, message: "请选择用户类型", trigger: "blur" }
        ],
      },
    };
  },
  methods: {
    login(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          console.log(this.ruleForm);

          request.post("/user/login", this.ruleForm).then((res) => {
            if (res.code == 0) {
              this.$message({
                showClose: true,
                message: "登录成功,跳转到后台首页",
                type: 'success',
              });
              //登录成功后保存到本地
              //登录成功，将用户保存到客户端
             
              localStorage.setItem("user",JSON.stringify(res.data))
              //跳转页面
              setTimeout(() => {
                this.$router.push("/");
              }, 2000)
              
            } else {
              // 如果失败，可以显示一个错误消息
              this.$message.error(res.msg);
            }
          });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    toRegister() {
      this.$router.push("/register");
    },
  },
};
</script>

<style>
.login-input label{
  color:#ffffff;
}
/* button{
  width:150px;
  
} */
.login-btn{
  text-align: center;

}
.login-div {
  /* 设置表单距浏览器上边距 */
  margin-top: 17%;
  width: 770px;
}
/* .custom-select{  
  padding-right: 50px;  
} */
/* 设置背景图片 */
.bg-img {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-image: url("../../assets/loginbg.png");
  background-size: cover;
  background-position: center;
  z-index: -10;
}

/* 设置表单背景色 */ 
.login-form {
  width: 60%;
  padding: 20px;
  /* 设置透明背景 */
  background-color: rgba(255, 255, 255, 0.3);
  border-radius: 20px;
}

.login-label{
  /*设置标签文字颜色为白色 */
  color: #ffffff;
}
</style>

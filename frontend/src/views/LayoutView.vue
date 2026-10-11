<template>
  <div class="home">
    <el-container>
      <el-header class="header" style="background-color: #2f4256">
        <img
          src="@/assets/go-8.svg"
          style="
            width: 55px;
            height: 55px;
            position: absolute;
            left: 10px;
            top: 5px;
          "
        />
        <!--   font-size: 18px    字体大小 -->
        <span class="header-title">虚拟仿真设备管理系统</span>

        <el-dropdown
          style="
            color: white;
            font-size: 16px;
            float: right;
            height: 60px;
            line-height: 60px;
          "
          trigger="click"
        >
          <span class="el-dropdown-link">
           欢迎 {{ welcomeText }}
            {{ user.name }}<i class="el-icon-arrow-down el-icon--right"></i>
          </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>
              <div @click="loginout()">退出登录</div></el-dropdown-item
            >
          </el-dropdown-menu>
        </el-dropdown>
      </el-header>
      <el-container>
        <el-aside
          style="background-color: #2f4256; width: 200px; min-height: 91vh"
        >
          <el-menu
            :default-active="$route.path"
            router
            class="el-menu-vertical-demo"
            @open="handleOpen"
            @close="handleClose"
            background-color="#2F4256"
            text-color="#a1afbe"
            active-text-color="#ffd04b"
          >
            <el-menu-item index="/">
              <i class="el-icon-menu"></i>
              <span slot="title">系统首页</span>
            </el-menu-item>
            <el-submenu index="1" v-if="user.role_id==6 || user.role_id == 4 || user.role_id == 5">
              <template slot="title">
                <i class="el-icon-user-solid"></i>
                <span>用户</span>
              </template>
              <el-menu-item-group>
                <!-- 角色管理，展示所有管理员信息，包括后台、实验室、超级管理员 -->
                <el-menu-item index="/administrator" v-if="user.role_id==6 ||  user.role_id == 5" > 角色管理 </el-menu-item>
                <!--用户管理 展示所有用户信息 -->
                <el-menu-item index="/user">用户管理</el-menu-item>
              </el-menu-item-group>
            </el-submenu>
            <el-submenu index="2" v-if="user.role_id==6 || user.role_id == 1 || user.role_id == 2 || user.role_id == 4 ">
              <template slot="title">
                <i class="el-icon-document"></i>
                <span>课程</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="/course">课程管理</el-menu-item>
                <el-menu-item index="/training" v-if="user.role_id==2 || user.role_id==6">实训实验管理</el-menu-item>
              </el-menu-item-group>
            </el-submenu>
            <el-submenu index="3" v-if="user.role_id==6 || user.role_id == 3 ">
              <template slot="title">
                <i class="el-icon-suitcase"></i>
                <span>实验设备</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="/simulation">仿真设备管理</el-menu-item>
              </el-menu-item-group>
            </el-submenu>
            <el-submenu index="4" v-if="user.role_id==6 || user.role_id == 3 ">
              <template slot="title">
                <i class="el-icon-suitcase"></i>
                <span>实验室</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="/laboratory">实验室管理</el-menu-item>
              </el-menu-item-group>
            </el-submenu>
          </el-menu>
        </el-aside>

        <el-main>
          <router-view />
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
export default {
  data() {
    return {
      user: localStorage.getItem("user")
        ? JSON.parse(localStorage.getItem("user"))
        : {},
    };
  },
  methods: {
    loginout() {
      localStorage.removeItem("user");
      this.$message({
                showClose: true,
                message: "返回至登录页面",
                role_id: "success",
              });
              setTimeout(() => {
                this.$router.push("/login");
              }, 2000)
     
    },

    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
  },
  computed: {
    welcomeText() {
      const role_ids = ['学生', '教师', '实验室管理员', '后台管理员', '超管','开发者'];
      return role_ids[this.user.role_id-1]; // 注意：类型是从1开始计数的，所以要减1
    }
  }
};
</script>

<style lang="less">
.header-title {
  position: absolute;
  left: 80px;
  top: 1px;
  font-size: 20px;
  color: #d1d9de;
}
</style>

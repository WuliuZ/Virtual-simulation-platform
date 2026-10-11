import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import UserView from '../views/user/UserView.vue'
import LayoutView from '../views/LayoutView.vue'
import RegisterView from '../views/login/Register.vue'
import LoginView from '../views/login/Login.vue'
import SimulationView from "../views/experiment/SimulationView.vue"
import LaboratoryView from "../views/experiment/LaboratoryView.vue"
import CourseView from "../views/course/CourseView.vue"
import AdministratorView from '@/views/user/AdministratorView.vue'
import TrainingView from '@/views/course/TrainingView.vue';
Vue.use(VueRouter)

const routes = [
  
  {
    path: '/register',
    name: 'register',
    component: RegisterView
  },
  {
    path: '/login',
    name: 'login',
    component: LoginView
  },

  {
    path: '/',
    name: 'layout',
    component: LayoutView,
    children:[
      {
        path: '',
        name: 'home',
        component: HomeView
      },
      {
        path: '/simulation',
        name: 'simulation',
        component: SimulationView,
        meta: { title: '仿真设备' }
      },
      {
        path: '/laboratory',
        name: 'laboratory',
        component: LaboratoryView,
        meta: { title: '实验室' }
      },
      {
        path: '/course',
        name: 'course',
        component: CourseView,
        meta: { title: '课程信息' }
      },
      {
        path: '/training',
        name: 'training',
        component: TrainingView,
        meta: { title: '课程信息' }
      },
      {
        path: '/administrator',
        name: 'administrator',
        component: AdministratorView,
        meta: { title: '管理员信息' }
      },
      {
        path: 'user',
        name: 'user',
        component: UserView
      }
    ]
  },
  
]



const router = new VueRouter({
  routes
})

router.beforeEach((to, from, next) => {  
  // 如果目标路由是登录或注册页面，则直接放行  
  if (to.path === '/login' || to.path === '/register') {  
    next();  
  } else {  
    // 检查本地存储中是否存在用户信息  
    const admin = localStorage.getItem("user");  
    // 如果不存在且目标路由不是登录或注册页面，则重定向到登录页面  
    if (!admin) {  
      next("/login");  
    } else {  
      // 如果存在用户信息或目标路由是登录或注册之外的其他页面，则放行  
      next();  
    }  
  }  
});

export default router

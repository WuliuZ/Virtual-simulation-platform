import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui';//引入elementui
import 'element-ui/lib/theme-chalk/index.css';//引入elementui
import './assets/global.css'

Vue.use(ElementUI);//引入elementui

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')

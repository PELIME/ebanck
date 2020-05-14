import Vue from 'vue'
import App from './App.vue'
import router from './router'
import 'mint-ui/lib/style.css'
import MintUI from 'mint-ui'
Vue.config.productionTip = false
Vue.use(MintUI)
Vue.prototype.$toast = MintUI.Toast;
new Vue({
  router,
  $Toast:MintUI.Toast,
  render: h => h(App)
}).$mount('#app')

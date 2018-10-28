// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import axios from 'axios'
import Qs from 'qs'

Vue.config.productionTip = false
axios.defaults.baseURL = process.env.baseUrl
Vue.prototype.axios = axios;
Vue.prototype.qs = Qs;
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App},
  template: '<App/>'
})

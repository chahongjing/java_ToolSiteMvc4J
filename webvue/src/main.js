// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import axios from 'axios'
import Qs from 'qs'

Vue.prototype.serverHost = process.env.baseUrl;
axios.defaults.baseURL = Vue.prototype.serverHost
Vue.prototype.axios = axios;
Vue.prototype.qs = Qs;


axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8';
axios.defaults.paramsSerializer = function(params) {
    return $.param(params);
};
axios.defaults.transformResponse = [function (data) {
    return $.parseJSON(data);
}]
// 添加请求拦截器
axios.interceptors.request.use(function (config) {
    if (config.method === 'post') {
        config.data = $.param(config.data);
    }
    // 在发送请求之前做些什么
    return config;
}, function (error) {
    // 对请求错误做些什么
    return Promise.reject(error);
});

// 添加响应拦截器
axios.interceptors.response.use(function (response) {
    // 对响应数据做点什么
    return response;
}, function (error) {
    // 对响应错误做点什么
    console.error(error);
    return Promise.resolve({data: {status: window.Constant.AjaxStatus.ERROR, message: 'error reuqest!'}});
    //return Promise.reject(error);
});

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App},
  template: '<App/>'
})

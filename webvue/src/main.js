// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import axios from './common/axios'
import filters from './common/filters';
import store from './store/store';

Vue.prototype.serverHost = process.env.baseUrl;
Vue.prototype.axios = axios;
Object.keys(filters).forEach(k => Vue.filter(k, filters[k]));
/* eslint-disable no-new */
new Vue({
	el: '#app',
	store,
	router,
	components: { App},
	template: '<App/>'
})

// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from '@/App'
import router from '@/router'
import axios from './common/axios'
import filters from '@/common/filters';
import store from '@/store/store';
import directives from '@/common/directives';
import confirm from '@/common/confirm';
import toaster from '@/common/toaster';
import pagination from '@/components/common/pagination'
import dateTimePicker from '@/components/common/dateTimePicker';
import ulTree from '@/components/common/ulTree';

Object.keys(filters).forEach(k => Vue.filter(k, filters[k]));
Object.keys(directives).forEach(k => Vue.directive(k, directives[k]));
Vue.prototype.serverHost = process.env.baseUrl;
Vue.prototype.axios = axios;
Vue.prototype.$confirm = confirm;
Vue.prototype.$toaster = toaster;
Vue.component('pagination', pagination);
Vue.component('pagination', pagination);
Vue.component('pagination', pagination);
Vue.component('ultree', ulTree);
/* eslint-disable no-new */
new Vue({
	el: '#app',
	store,
	router,
	components: { App},
	template: '<App/>'
})

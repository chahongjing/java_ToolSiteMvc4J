// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from '@/App'
import router from '@/router'
import store from '@/store/store';
import filters from '@/common/filters';
import directives from '@/common/directives';
import axios from '@/common/axios'
import confirm from '@/common/confirm';
import toaster from '@/common/toaster';
import pagination from '@/components/common/pagination'
import tableListLoading from '@/components/common/tableListLoading'
import dateTimePicker from '@/components/common/dateTimePicker';
import ulTree from '@/components/common/ulTree';

Object.keys(filters).forEach(k => Vue.filter(k, filters[k]));
Object.keys(directives).forEach(k => Vue.directive(k, directives[k]));
Vue.prototype.serverHost = process.env.baseUrl;
Vue.prototype.axios = axios;
Vue.prototype.$confirm = confirm;
Vue.prototype.$toaster = toaster;
Vue.component('pagination', pagination);
Vue.component('tableListLoading', tableListLoading);
Vue.component('dateTimePicker', dateTimePicker);
Vue.component('ultree', ulTree);
/* eslint-disable no-new */
new Vue({
  el: '#app',
  store,
  router,
  components: {App},
  template: '<App/>',
  methods: {
    getUser() {
      return this.$store.state.user;
    },
    setUser(user) {
      this.$store.commit("USER_SIGNIN", user);
    },
    clearUser() {
      this.$store.commit("USER_SIGNOUT");
    },
    getLeftMenu() {
      return this.$store.state.leftMenu;
    },
    setLeftMenu(menuList) {
      this.$store.commit("SET_MENU", menuList);
    },
    clearMenu() {
      this.$store.commit("CLEAR_MENU");
    },
    getBreadcrumb() {
      return this.$store.state.breadcrumb;
    },
    setBreadcrumb(breadcurmbList) {
      this.$store.commit("SET_BREADCRUMB", breadcurmbList);
    },
    setBreadcrumbName(name) {
      var list = this.$store.state.breadcrumb;
      if(!list || list.length == 0) return;
      list[list.length - 1].name = name;
    },
    clearBreadrumb() {
      this.$store.commit("CLEAR_BREADCRUMB");
    },
    getPermissionList() {
      return this.$store.state.permissionList;
    },
    setPermissionList(permissionList) {
      this.$store.commit("SET_PERMISSIONLIST", permissionList);
    },
    clearPermissionList() {
      this.$store.commit("CLEAR_PERMISSIONLIST");
    },
    goBack: function () {
      var breadcrumb = this.getBreadcrumb();
      if (breadcrumb.length <= 1) {
        this.clearBreadrumb();
        this.$router.push({path: '/'});
        return;
      }
      var item = breadcrumb[breadcrumb.length - 2];
      if (item != null) {
        this.$router.push({path: item.path, query: item.query, params: item.params});
      } else {
        this.$router.push({path: '/'});
      }
    },
  }
})

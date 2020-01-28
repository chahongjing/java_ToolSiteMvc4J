// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue';
import App from '@/App';
import router from '@/router';
import store from '@/store/store';
import filters from '@/common/filters';
import directives from '@/common/directives';
import axios from '@/common/axios';
import confirm from '@/common/confirm';
import cstModal from '@/common/customModal';
import toaster from '@/common/toaster';
import pagination from '@/components/common/pagination';
import tableListLoading from '@/components/common/tableListLoading';
import dateTimePicker from '@/components/common/dateTimePicker';
import dateTimeRangePicker from '@/components/common/dateTimeRangePicker';
import videoPlayer from '@/components/common/videoPlayer';
import audioPlayer from '@/components/common/audioPlayer';
import mediaPlayer from '@/components/common/mediaPlayer';
import ulTree from '@/components/common/ulTree';
import select2 from '@/components/common/select2';
import 'jquery';

Object.keys(filters).forEach(k => Vue.filter(k, filters[k]));
Object.keys(directives).forEach(k => Vue.directive(k, directives[k]));
Vue.prototype.serverHost = process.env.baseUrl;
Vue.prototype.$axios = axios;
Vue.prototype.$confirm = confirm;
Vue.prototype.$cstModal = cstModal;
Vue.prototype.$toaster = toaster;
Vue.component('pagination', pagination);
Vue.component('tableListLoading', tableListLoading);
Vue.component('dateTimePicker', dateTimePicker);
Vue.component('dateTimeRangePicker', dateTimeRangePicker);
Vue.component('videoPlayer', videoPlayer);
Vue.component('audioPlayer', audioPlayer);
Vue.component('mediaPlayer', mediaPlayer);
Vue.component('ultree', ulTree);
Vue.component('select2', select2);
/* eslint-disable no-new */
new Vue({
  el: '#app',
  store,
  router,
  components: {App},
  template: '<App/>',
  data:function() {
    return {
      showLoadingBox: false
    };
  },
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
    getMenuList() {
      return this.$store.state.leftMenu.menuList;
    },
    setMenuList(menuList) {
      this.$store.commit("SET_MENULIST", menuList);
    },
    clearMenuList() {
      this.$store.commit("CLEAR_MENULIST");
    },
    getShowMenu() {
      return this.$store.state.leftMenu.showMenu;
    },
    setShowMenu(showMenu) {
      this.$store.commit("SET_SHOWMENU", showMenu);
    },
    getBreadcrumb() {
      return this.$store.state.breadcrumb.breadcurmbList;
    },
    setBreadcrumb(breadcurmbList) {
      this.$store.commit("SET_BREADCRUMB", breadcurmbList);
    },
    setBreadcrumbName(name) {
      var list = this.$store.state.breadcrumb.breadcrumb;
      if(!list || list.length == 0) return;
      list[list.length - 1].name = name;
    },
    clearBreadrumb() {
      this.$store.commit("CLEAR_BREADCRUMB");
    },
    getShowBreadcrumb() {
      return this.$store.state.breadcrumb.showBreadcrumb;
    },
    setShowBreadcrumb(showBreadcrumb) {
      this.$store.commit("SET_SHOWBREADCRUMB", showBreadcrumb);
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
    showLoading: function() {
      this.showLoadingBox = true;
    },
    hideLoading: function() {
      this.showLoadingBox = false;
    },
    goHome() {
      this.clearBreadrumb();
      var menuList = this.getMenuList() || [];
      for(var i = 0; i < menuList.length; i++) {
        for(var j = 0; j < menuList[i].children.length; j++) {
          menuList[i].children[j].isSelected = false;
          menuList[i].children[j].checked = false;
        }
      }
      this.setMenuList(menuList);
      this.$router.push({path: '/'});
    },
    goBack: function () {
      var breadcrumb = this.getBreadcrumb();
      if (breadcrumb.length <= 1) {
        this.goHome();
        return;
      }
      var item = breadcrumb[breadcrumb.length - 2];
      if (item != null) {
        this.$router.push({path: item.path, query: item.query, params: item.params});
      } else {
        this.goHome();
      }
    },
  }
})

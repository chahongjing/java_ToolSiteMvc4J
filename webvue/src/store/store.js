import Vue from 'vue';
import Vuex from 'vuex';
import user from './user';
import leftMenu from './leftMenu';
import breadcrumb from './breadcrumb';
import permissionList from './permissionList';

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    user, leftMenu, breadcrumb, permissionList
  }
});
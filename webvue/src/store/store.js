import Vue from 'vue'
import Vuex from 'vuex'
import user from './user'
import leftMenu from './leftMenu'
import bread from './bread'

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    user, leftMenu, bread
  }
});
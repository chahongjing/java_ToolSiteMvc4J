import Vue from 'vue';

export const SET_BREADCRUMB = 'SET_BREADCRUMB'; //登录成功
export const CLEAR_BREADCRUMB = 'CLEAR_BREADCRUMB'; //退出登录

export default {
    state: JSON.parse(sessionStorage.getItem('breadcrumb')) || [],
    mutations: {
        [SET_BREADCRUMB](state, breadcrumb) {
            sessionStorage.setItem('breadcrumb', JSON.stringify(breadcrumb))
            if(state == breadcrumb) return;
          state.length = 0;
          Array.prototype.push.apply(state, breadcrumb);
        },
        [CLEAR_BREADCRUMB](state) {
            sessionStorage.removeItem('breadcrumb');
            state.splice(0, state.length);
        }
    },
    actions: {
        [SET_BREADCRUMB]({commit}, breadcrumb) {
            commit(SET_BREADCRUMB, breadcrumb)
        },
        [CLEAR_BREADCRUMB]({commit}) {
            commit(CLEAR_BREADCRUMB)
        }
    }
}

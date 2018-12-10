import Vue from 'vue';

export const SET_MENU = 'SET_MENU'; //登录成功
export const CLEAR_MENU = 'CLEAR_MENU'; //退出登录

export default {
    state: JSON.parse(sessionStorage.getItem('leftMenu')) || [],
    mutations: {
        [SET_MENU](state, leftMenu) {
            sessionStorage.setItem('leftMenu', JSON.stringify(leftMenu))
          state.length = 0;
          Array.prototype.push.apply(state, leftMenu);
        },
        [CLEAR_MENU](state) {
            sessionStorage.removeItem('leftMenu')
            state.splice(0, state.length);
        }
    },
    actions: {
        [SET_MENU]({commit}, leftMenu) {
            commit(SET_MENU, leftMenu)
        },
        [CLEAR_MENU]({commit}) {
            commit(CLEAR_MENU)
        }
    }
}

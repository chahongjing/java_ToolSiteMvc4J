import Vue from 'vue';

export const SET_MENU = 'SET_MENU'; //登录成功
export const CLEAR_MENU = 'CLEAR_MENU'; //退出登录
export const SET_SHOWMENU = 'SET_SHOWMENU'; //登录成功

export default {
    state: JSON.parse(sessionStorage.getItem('leftMenu')) || {leftMenu:[],showLeftMenu:true},
    mutations: {
        [SET_MENU](state, leftMenu) {
            if(state.leftMenu == leftMenu) return;
          state.leftMenu.length = 0;
          Array.prototype.push.apply(state.leftMenu, leftMenu);
          sessionStorage.setItem('leftMenu', JSON.stringify(state))
        },
        [CLEAR_MENU](state) {
            //sessionStorage.removeItem('leftMenu')
            state.leftMenu.splice(0, state.length);
        },
        [SET_SHOWMENU](state, showLeftMenu) {
            state.showLeftMenu = showLeftMenu;
          sessionStorage.setItem('leftMenu', JSON.stringify(state))
        }
    },
    actions: {
        [SET_MENU]({commit}, leftMenu) {
            commit(SET_MENU, leftMenu)
        },
        [CLEAR_MENU]({commit}) {
            commit(CLEAR_MENU)
        },
        [SET_SHOWMENU]({commit}, showLeftMenu) {
            commit(SET_SHOWMENU, showLeftMenu)
        }
    }
}

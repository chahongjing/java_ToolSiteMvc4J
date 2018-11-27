import Vue from 'vue';

export const SET_BREAD = 'SET_BREAD'; //登录成功
export const CLEAR_BREAD = 'CLEAR_BREAD'; //退出登录

export default {
    state: JSON.parse(sessionStorage.getItem('bread')) || [],
    mutations: {
        [SET_BREAD](state, bread) {
            sessionStorage.setItem('bread', JSON.stringify(bread))
            Object.assign(state, bread)
        },
        [CLEAR_BREAD](state) {
            sessionStorage.removeItem('bread')
            Object.keys(state).forEach(k => Vue.delete(state, k))
        }
    },
    actions: {
        [SET_BREAD]({commit}, bread) {
            commit(SET_BREAD, bread)
        },
        [CLEAR_BREAD]({commit}) {
            commit(CLEAR_BREAD)
        }
    }
}
//store.commit("USER_SIGNIN",res.body.data);

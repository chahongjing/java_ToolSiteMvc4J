import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);

//注册全局组件
// Object.keys(components).forEach((key) => {
//     let name = key.replace(/(\w)/, (v) => v.toUpperCase()); //首字母大写
//     Vue.component(`v${name}`, components[key]);
// });

export default new Router({
  routes: [
    {
      path: '/',
      component: resolve => require(['../components/HelloWorld'], resolve)
    },
    {
      path: '/myPage',
      name: 'myPage',
      component: resolve => require(['../components/myPage'], resolve)
    }
  ]
})

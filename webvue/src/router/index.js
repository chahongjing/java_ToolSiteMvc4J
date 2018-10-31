import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);

//注册全局组件
// Object.keys(components).forEach((key) => {
//     let name = key.replace(/(\w)/, (v) => v.toUpperCase()); //首字母大写
//     Vue.component(`v${name}`, components[key]);
// });

var router = new Router({
  routes: [
    {
      path: '/',
      name: 'home',
      component: resolve => require(['../components/HelloWorld'], resolve)
    },
    {
      path: '/myPage',
      name: 'myPage',
      component: resolve => require(['../components/myPage'], resolve)
    },
    {
      path: '/configInfoList',
      name: 'configInfoList',
      component: resolve => require(['../components/sys/configInfoList'], resolve)
    },
    {
      path: '/addConfigInfo',
      name: 'addConfigInfo',
      component: resolve => require(['../components/sys/configInfoEdit'], resolve)
    }
  ]
});

router.beforeEach(function (to, from, next) {
    const nextRoute = [ 'account', 'order', 'course'];
    //跳转至上述3个页面
    if (nextRoute.indexOf(to.name) >= 0) {
        
    }
    //已登录的情况再去登录页，跳转至首页
    if (to.name === 'login') {
        //if (auth.IsLogin) {
            vueRouter.push({name: 'home'});
        //}
    }
    // 如果没有权限，则返回到没权限页面
    if(false) {
    	next({
	        path: '/login',
	        query: {redirect: to.fullPath}  // 将跳转的路由path作为参数，登录成功后跳转到该路由
	    });
    }
    next();
});
export default router;
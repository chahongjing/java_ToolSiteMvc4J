import Vue from 'vue'
import Router from 'vue-router'
import store from '../store/store';

Vue.use(Router);

var router = new Router({
  routes: [
  {
    path: '/',
    name: 'home',
    component: resolve => require(['../components/index'], resolve)
  },
  {
    path: '/login',
    name: 'login',
    component: resolve => require(['../components/user/login'], resolve)
  },
  {
    path: '/user',
    name: 'user',
    component: resolve => require(['../components/headerAndMenu'], resolve),
    children: [
      {
        path: 'userList',
        name: 'userList',
        component: resolve => require(['../components/user/userList'], resolve)
      },
      {
        path: 'userEdit',
        name: 'userEdit',
        component: resolve => require(['../components/user/userEdit'], resolve)
      }
    ]
  },
  {
    path: '/main',
    name: 'main',
    component: resolve => require(['../components/headerAndMenu'], resolve),
    children: [
      {
        path: '*',  //*号表示匹配任意内容
        title: '首页',
        redirect: '/',
        extra: {
          inMenu: false
        }
      }
    ]
  },
  {
    path: '/sys',
    name: 'sys',
    component: resolve => require(['../components/headerAndMenu'], resolve),
    children: [
    {
      path: 'menuList',
      name: 'menuList',
      component: resolve => require(['../components/sys/menuList'], resolve)
    },
    {
      path: 'menuEdit',
      name: 'menuEdit',
      component: resolve => require(['../components/sys/menuEdit'], resolve)
    },
    {
      path: 'configInfoList',
      name: 'configInfoList',
      component: resolve => require(['../components/sys/configInfoList'], resolve)
    },
    {
      path: 'configInfoEdit',
      name: 'configInfoEdit',
      component: resolve => require(['../components/sys/configInfoEdit'], resolve)
    },
    {
      path: '*',  //*号表示匹配任意内容
      title: '首页',
      redirect: '/',
      extra: {
        inMenu: false
      }
    }
    ]
  }
  ]
});

router.beforeEach(function (to, from, next) {
  var user = router.app.$store.state.user;
  if(!user.userId && to && to.path != '/login') {
    next({
     path: '/login',
     query: {redirect: to.fullPath}  // 将跳转的路由path作为参数，登录成功后跳转到该路由
    });
  }
    //跳转至上述3个页面
    // if (nextRoute.indexOf(to.name) >= 0) {

    // }
    //已登录的情况再去登录页，跳转至首页
    //if (to.name === 'login') {
        //if (auth.IsLogin) {
            //vueRouter.push({name: 'home'});
        //}
    //}
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
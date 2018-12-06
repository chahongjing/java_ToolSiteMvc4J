import Vue from 'vue'
import Router from 'vue-router'
import store from '@/store/store';

Vue.use(Router);

var router = new Router({
  routes: [
  {
    path: '/',
    component: resolve => require(['../components/headerAndMenu'], resolve),
    children:[{
      path: '/',
      name: '首页',
      component: resolve => require(['../components/index'], resolve)
    }]
  },
  {
    path: '/login',
    name: '用户登录',
    component: resolve => require(['../components/user/login'], resolve)
  },
  {
    path: '/user',
    name: '用户',
    component: resolve => require(['../components/headerAndMenu'], resolve),
    children: [
      {
        path: 'userList',
        name: '用户列表',
        component: resolve => require(['../components/user/userList'], resolve)
      },
      {
        path: 'userEdit',
        name: '用户详情',
        component: resolve => require(['../components/user/userEdit'], resolve)
      },
      {
        path: 'userRole',
        name: '用户角色',
        component: resolve => require(['../components/user/userRole'], resolve)
      }
    ]
  },
  {
    path: '/sys',
    name: '系统模块',
    component: resolve => require(['../components/headerAndMenu'], resolve),
    children: [
    {
      path: 'menuList',
      name: '菜单列表',
      component: resolve => require(['../components/sys/menuList'], resolve)
    },
    {
      path: 'menuEdit',
      name: '菜单详情',
      component: resolve => require(['../components/sys/menuEdit'], resolve)
    },
    {
      path: 'functionList',
      name: '功能列表',
      component: resolve => require(['../components/sys/functionList'], resolve)
    },
    {
      path: 'functionEdit',
      name: '功能详情',
      component: resolve => require(['../components/sys/functionEdit'], resolve)
    },
    {
      path: 'permissionList',
      name: '权限列表',
      component: resolve => require(['../components/sys/permissionList'], resolve)
    },
    {
      path: 'permissionEdit',
      name: '权限详情',
      component: resolve => require(['../components/sys/permissionEdit'], resolve)
    },
    {
      path: 'roleList',
      name: '角色列表',
      component: resolve => require(['../components/sys/roleList'], resolve)
    },
    {
      path: 'roleEdit',
      name: '角色详情',
      component: resolve => require(['../components/sys/roleEdit'], resolve)
    },
    {
      path: 'configInfoList',
      name: '配置列表',
      component: resolve => require(['../components/sys/configInfoList'], resolve)
    },
    {
      path: 'configInfoEdit',
      name: '配置详情',
      component: resolve => require(['../components/sys/configInfoEdit'], resolve)
    },
    {
      path: 'roleGrantPermission',
      name: '角色授权',
      component: resolve => require(['../components/sys/roleGrantPermission'], resolve)
    },
    {
      path: '*',  //*号表示匹配任意内容
      title: '首页',
      redirect: '/',
      meta: {
        inMenu: false
      }
    }
    ]
  },
  {
    path:'/test',
    component: resolve => require(['../components/headerAndMenu'], resolve),
    children: [
    {
        path: 'test',
        name: '测试',
        component: resolve => require(['../components/test'], resolve)
      }
    ]
  }
  ]
});

router.beforeEach(function (to, from, next) {
  var user = router.app.$store.state.user;
  // me.$store.commit("USER_SIGNIN", me.user);
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
    // 不跳转传false
    // next(false);
    // 处理面包屑
    if(to.path != from.path) {
      var breadcrumb = router.app.$store.state.breadcrumb;
      var i = 0;
      for(; i < breadcrumb.length; i++) {
        if(breadcrumb[i].path == to.path) {
          var current = breadcrumb[i];
          breadcrumb.splice(i, breadcrumb.length - i);
          break;
        }
      }
      if(to.path != '/' && to.path != '/login') {
        breadcrumb.push(to);
      }
    }

    next();
  });
export default router;

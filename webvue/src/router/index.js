import Vue from 'vue'
import Router from 'vue-router'
import store from '@/store/store';

Vue.use(Router);

var router = new Router({
  routes: [
  {
    path: '/',
    component: resolve => require(['../components/layout/headerAndMenu'], resolve),
    children:[{
      path: '/',
      name: 'home',
      component: resolve => require(['../components/index'], resolve),
      meta: {
          breadcurmbName: '首页',
      }
    }]
  },
  {
    path: '/login',
    name: 'login',
    component: resolve => require(['../components/user/login'], resolve),
      meta: {
          breadcurmbName: '用户登录',
      }
  },
  {
    path: '/user',
    name: 'user',
    component: resolve => require(['../components/layout/headerAndMenu'], resolve),
    children: [
      {
        path: 'userList',
        name: 'userList',
        component: resolve => require(['../components/user/userList'], resolve),
        meta:{
          breadcurmbName: '用户列表',
          'pageCode': 'userList_enter'
        }
      },
      {
        path: 'userEdit',
        name: 'userEdit',
        component: resolve => require(['../components/user/userEdit'], resolve),
        meta:{
          breadcurmbName: '用户详情',
          'pageCode': 'userEdit_enter'
        }
      },
      {
        path: 'userRole',
        name: 'userRole',
        component: resolve => require(['../components/user/userRole'], resolve),
        meta:{
          breadcurmbName: '用户角色',
          'pageCode': 'userRole_enter'
        }
      }
    ]
  },
  {
    path: '/admin',
    name: 'admin',
    component: resolve => require(['../components/layout/headerAndMenu'], resolve),
    children: [
    {
      path: 'menuList',
      name: 'menuList',
      component: resolve => require(['../components/admin/menuList'], resolve),
        meta:{
          breadcurmbName: '菜单列表',
          'pageCode': 'menuList_enter'
        }
    },
    {
      path: 'menuEdit',
      name: 'menuEdit',
      component: resolve => require(['../components/admin/menuEdit'], resolve),
        meta:{
          breadcurmbName: '菜单详情',
          'pageCode': 'menuEdit_enter'
        }
    },
    {
      path: 'functionList',
      name: 'functionList',
      component: resolve => require(['../components/admin/functionList'], resolve),
        meta:{
          breadcurmbName: '功能列表',
          'pageCode': 'functionList_enter'
        }
    },
    {
      path: 'functionEdit',
      name: 'functionEdit',
      component: resolve => require(['../components/admin/functionEdit'], resolve),
        meta:{
          breadcurmbName: '功能详情',
          'pageCode': 'functionEdit_enter'
        }
    },
    {
      path: 'permissionList',
      name: 'permissionList',
      component: resolve => require(['../components/admin/permissionList'], resolve),
        meta:{
          breadcurmbName: '权限列表',
          'pageCode': 'permissionList_enter'
        }
    },
    {
      path: 'permissionEdit',
      name: 'permissionEdit',
      component: resolve => require(['../components/admin/permissionEdit'], resolve),
        meta:{
          breadcurmbName: '权限详情',
          'pageCode': 'permissionEdit_enter'
        }
    },
    {
      path: 'roleList',
      name: 'roleList',
      component: resolve => require(['../components/admin/roleList'], resolve),
        meta:{
          breadcurmbName: '角色列表',
          'pageCode': 'roleList_enter'
        }
    },
    {
      path: 'roleEdit',
      name: 'roleEdit',
      component: resolve => require(['../components/admin/roleEdit'], resolve),
        meta:{
          breadcurmbName: '角色详情',
          'pageCode': 'roleEdit_enter'
        }
    },
    {
      path: 'configInfoList',
      name: 'configInfoList',
      component: resolve => require(['../components/admin/configInfoList'], resolve),
        meta:{
          breadcurmbName: '配置列表',
          'pageCode': 'configInfoList_enter'
        }
    },
    {
      path: 'configInfoEdit',
      name: 'configInfoEdit',
      component: resolve => require(['../components/admin/configInfoEdit'], resolve),
        meta:{
          breadcurmbName: '配置详情',
          'pageCode': 'configInfoEdit_enter'
        }
    },
    {
      path: 'roleGrantPermission',
      name: 'roleGrantPermission',
      component: resolve => require(['../components/admin/roleGrantPermission'], resolve),
        meta:{
          breadcurmbName: '角色授权',
          'pageCode': 'roleGrantPermission_enter'
        }
    },
    {
      path: '401',
      name: '401',
      component: resolve => require(['../components/sys/401'], resolve),
        meta:{
          breadcurmbName: '未授权'
        }
    },
    {
      path: '404',
      name: '404',
      component: resolve => require(['../components/sys/404'], resolve),
        meta:{
          breadcurmbName: '无法访问'
        }
    },
    {
      path: '*',
      name: '*',
      component: resolve => require(['../components/sys/404'], resolve),
        meta:{
          breadcurmbName: '无法访问'
        }
    }
    ]
  },
  {
    path:'/test',
    component: resolve => require(['../components/layout/headerAndMenu'], resolve),
    children: [
    {
        path: 'test',
        name: 'test',
        component: resolve => require(['../components/other/test'], resolve),
      meta: {
          breadcurmbName: '测试',
        inMenu: false
      }
      }
    ]
  }
  ]
});

router.beforeEach(function (to, from, next) {
  if (to.matched.length === 0) {
    next({name: '404'});
    return;
  }
  var user = router.app.$store.state.user;
  var permissionList = router.app.$store.state.permissionList;
  if(Vue.config.devtools) {
    console.log(to);
  }
  if(!user.userId && to && to.path != '/login') {
    next({
     path: '/login',
     query: {redirect: to.fullPath}  // 将跳转的路由path作为参数，登录成功后跳转到该路由
    });
  }
  // 判断有没有页面权限
  if(to.meta.pageCode) {
    if(!(permissionList && permissionList.some(item => item == to.meta.pageCode))) {
      // router.app.$toaster.error('没有权限！');
      // next(false);
      next({name: '401'});
      return;
    }
  }
  // 不跳转传false
  // next(false);
  // 处理面包屑
  if(to.path != from.path) {
    var breadcrumb = router.app.$store.state.breadcrumb;
    var breadcrumbIgnoreUrl = ['/', '/login'];
    var i = 0;
    for(; i < breadcrumb.length; i++) {
      if(breadcrumb[i].path == to.path) {
        var current = breadcrumb[i];
        breadcrumb.splice(i, breadcrumb.length - i);
        break;
      }
    }
    if(!breadcrumbIgnoreUrl.includes(to.path)) {
      breadcrumb.push({name: to.meta.breadcurmbName || to.name, path:to.path,
        query:to.query,params:to.params});
    }
    router.app.$store.commit("SET_BREADCRUMB", breadcrumb);
  }

  next();
});
export default router;

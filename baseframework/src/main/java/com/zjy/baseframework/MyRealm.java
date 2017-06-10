package com.zjy.baseframework;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static org.apache.shiro.web.filter.mgt.DefaultFilter.user;

/**
 * shiro认证
 *
 * @author chahongjing
 * @create 2016-10-27 23:04
 */
public class MyRealm extends AuthorizingRealm {

    protected static String currentKey = "currentUser";

    protected static Function<String, Object> myfun;
    /**
     * 为当前登录的Subject授予角色和权限
     *
     * @see 经测试:本例中该方法的调用时机为需授权资源被访问时
     * @see 经测试:并且每次访问需授权资源时都会执行该方法中的逻辑,这表明本例中默认并未启用AuthorizationCache
     * @see 个人感觉若使用了Spring3.1开始提供的ConcurrentMapCache支持,则可灵活决定是否启用AuthorizationCache
     * @see 比如说这里从数据库获取权限信息时,先去访问Spring3.1提供的缓存,而不使用Shior提供的AuthorizationCache
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取当前登录的用户名,等价于(String)principals.fromRealm(this.getName()).iterator().next()
//        String currentUsername = (String) super.getAvailablePrincipal(principals);
////      List<String> roleList = new ArrayList<String>();
////      List<String> permissionList = new ArrayList<String>();
////      //从数据库中获取当前登录用户的详细信息
////      User user = userService.getByUsername(currentUsername);
////      if(null != user){
////          //实体类User中包含有用户角色的实体类信息
////          if(null!=user.getRoles() && user.getRoles().size()>0){
////              //获取当前登录用户的角色
////              for(Role role : user.getRoles()){
////                  roleList.add(role.getName());
////                  //实体类Role中包含有角色权限的实体类信息
////                  if(null!=role.getPermissions() && role.getPermissions().size()>0){
////                      //获取权限
////                      for(Permission pmss : role.getPermissions()){
////                          if(!StringUtils.isEmpty(pmss.getPermission())){
////                              permissionList.add(pmss.getPermission());
////                          }
////                      }
////                  }
////              }
////          }
////      }else{
////          throw new AuthorizationException();
////      }
////      //为当前用户设置角色和权限
////      SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
////      simpleAuthorInfo.addRoles(roleList);
////      simpleAuthorInfo.addStringPermissions(permissionList);
//        SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
//        //实际中可能会像上面注释的那样从数据库取得
//        if (null != currentUsername && "mike".equals(currentUsername)) {
//            //添加一个角色,不是配置意义上的添加,而是证明该用户拥有admin角色
//            simpleAuthorInfo.addRole("admin");
//            //添加权限
//            simpleAuthorInfo.addStringPermission("admin:manage");
//            System.out.println("已为用户[mike]赋予了[admin]角色和[admin:manage]权限");
//            return simpleAuthorInfo;
//        }
//        //若该方法什么都不做直接返回null的话,就会导致任何用户访问/admin/listUser.jsp时都会自动跳转到unauthorizedUrl指定的地址
//        //详见applicationContext.xml中的<bean id="shiroFilter">的配置
//        return null;
        String primaryPrincipal = (String) principals.getPrimaryPrincipal();
        System.out.println("-----------*************************------------>" + primaryPrincipal);
        List<String> roles = new ArrayList<String>();
        List<String> permissions = new ArrayList<String>();

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

//        User user = null;//userService.findByUserName(primaryPrincipal);
//        if(user != null){
//            for (Role role : user.getRoles()) {
//                roles.add(role.getName());
//                for (Permission p : role.getPermissions()) {
//                    permissions.add(p.getPrivilege());
//                }
//            }
//        }else{
//            throw new AuthorizationException();
//        }
        //给当前用户设置角色
        info.addRoles(roles);
        //给当前用户设置权限
        info.addStringPermissions(permissions);

        return info;
    }


    /**
     * 验证当前登录的Subject
     *
     * @see 经测试:本例中该方法的调用时机为LoginController.login()方法中执行Subject.login()时
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        //User user = userService.getByUsername(token.getUsername());
        if (null != user) {
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(token.getUsername(), token.getPassword(), "");
            this.setSession(currentKey, myfun.apply(authcInfo.getPrincipals().asList().get(0).toString()));
            // this.setSession(currentKey, authcInfo);
            return authcInfo;
        } else {
            return null;
        }
    }


    /**
     * 将一些数据放到ShiroSession中,以便于其它地方使用
     *
     * @see 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到
     */
    private void setSession(Object key, Object value) {
        Subject currentUser = SecurityUtils.getSubject();
        if (null != currentUser) {
            Session session = currentUser.getSession();
            System.out.println("Session默认超时时间为[" + session.getTimeout() + "]毫秒");
            if (null != session) {
                session.setAttribute(key, value);
            }
        }
    }

    public Object getUser() {
        Subject currentUser = SecurityUtils.getSubject();
        if (null != currentUser) {
            Session session = currentUser.getSession();
            if (null != session) {
                Object obj = session.getAttribute(currentKey);
                if(obj != null) {
                    // AuthenticationInfo ai = (AuthenticationInfo)obj;
                    return obj;
                }
            }
        }
        return null;
    }
}

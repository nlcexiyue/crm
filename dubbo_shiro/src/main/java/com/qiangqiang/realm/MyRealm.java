package com.qiangqiang.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.Set;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/23
 * \* Time: 10:33
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class MyRealm extends AuthorizingRealm {

    //获取身份信息,可以在这里从数据库中获取用户的权限和角色信息
    //这个可以调用多次数据库
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //这个username用在后面权限和角色查询表的时候,根据用户名来查询
        String username = this.getAvailablePrincipal(principals).toString();

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //权限
        Set<String> s = new HashSet<>();
        s.add("user:print");
        s.add("user:query");
        s.add("user:delete");
        simpleAuthorizationInfo.setStringPermissions(s);
        //如果是超级管理员,拥有所有权限,这样写
        //simpleAuthorizationInfo.addStringPermission("*:*");
        //角色
        Set<String> r = new HashSet<>();
        r.add("role1");
        simpleAuthorizationInfo.setRoles(r);


        return simpleAuthorizationInfo;
    }

    //身份认证,只在登录的时候调用一次
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户名
        String username = token.getPrincipal().toString();
        System.out.println("用户名为:" + username);
        //获取密码
//        String password = token.getCredentials().toString();
        String password = new String((char[]) token.getCredentials());
        System.out.println("密码为" + password);
        if (!"zhangsan".equals(username)) {
            throw new UnknownAccountException();
        }
        if (!"123456".equals(password)) {
            throw new IncorrectCredentialsException();
        }

        //身份验证通过，返回一个身份信息
        //SimpleAuthenticationInfo(Object principal, Object credentials, String realmName)
        //principal这个对象传什么,后面的授权就会接收什么,这里可以将用户的权限角色都查询出来封装到map中,再传走
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username, password, this.getName());
        return simpleAuthenticationInfo;
    }
}
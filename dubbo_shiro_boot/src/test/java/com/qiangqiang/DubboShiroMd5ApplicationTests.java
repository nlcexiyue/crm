package com.qiangqiang;

import com.qiangqiang.realm.MyRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DubboShiroMd5ApplicationTests {

    @Autowired
    private DefaultSecurityManager defaultSecurityManager;

    @Autowired
    private MyRealm myRealm;

    @Autowired
    private HashedCredentialsMatcher hashedCredentialsMatcher;
    @Test
    void contextLoads() {
        //使用工厂模式来获取SecurityManager

        //这里也可以创建自定义的realm,与在shiro.ini文件中定义自定义realm的效果是一样的



        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();
        //如果还未认证
        if (!subject.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "123456");

            try {
                subject.login(token);
            } catch (UnknownAccountException uae) {
                System.out.println("没有该用户： " + token.getPrincipal());
            } catch (IncorrectCredentialsException ice) {
                System.out.println(token.getPrincipal() + " 的密码不正确!");
            }
        }
        if (subject.isAuthenticated()) {
            System.out.println("用户 " + subject.getPrincipal() + " 登录成功");
        }
        //是否有role1这个角色
        if (subject.hasRole("role1")) {
            System.out.println("有角色role1");
        } else {
            System.out.println("没有角色role1");
        }
        //是否有方法的执行权限
        if (subject.isPermitted("user:print")) {
            System.out.println("可以执行方法");
        } else {
            System.out.println("不能执行方法");
        }


    }

}

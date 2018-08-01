package org.tc.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;
import org.tc.shiro.realm.CustomRealm;

/**
 * @author toughChow
 * @date 2018/7/25 0025
 */
public class CustomRealmTest {


    @Test
    public void testAuthentication() {
        CustomRealm customRealm = new CustomRealm();

        // 1 create SecurityManager env
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(customRealm);

        // 2 main body submit the request
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("Mark","123456");
        subject.login(token);

        Assert.assertTrue(subject.isAuthenticated());

        subject.checkRole("user");

        subject.checkPermission("user:add");
    }

}

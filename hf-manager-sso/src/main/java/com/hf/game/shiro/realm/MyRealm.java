package com.hf.game.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;

/**
 * Created by 123 on 2019-6-4.
 */
public class MyRealm extends CasRealm {
    /**
     * 获取权限验证
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("1234");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        HashSet<String> set = new HashSet<>();
//        set.add("admin");
//        info.setRoles(set);
        info.addRole("admin");
        info.addStringPermission("user:delete");
// setDefaultRoles("admin");

        return info;
    }



    /**
     * 获取身份验证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */

}

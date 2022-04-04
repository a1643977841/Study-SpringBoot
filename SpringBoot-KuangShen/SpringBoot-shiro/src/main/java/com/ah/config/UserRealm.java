package com.ah.config;

import com.ah.entity.User;
import com.ah.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义UserRealm
 *
 * @author LiuHao
 * @version V1.0
 * @date 2022/3/1800:25
 */
@Slf4j
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private IUserService userService;
    /**
     * 授权
     *
     * @param principalCollection nick
     * @return nick
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("principalCollection=========》{}", "授权进来了");
        // 获取当前用户
        Subject subject = SecurityUtils.getSubject();
        User user =  (User) subject.getPrincipal();
        // 添加权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermission(user.getPerms());
        return simpleAuthorizationInfo;
    }

    /**
     * 认证
     *
     * @param token 用户token
     * @return nick
     * @throws AuthenticationException nick
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("authenticationToken===================》{}", "开始认证");
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        User user = userService.queryUserByName(userToken.getUsername());
        if (user == null) {
            //  return null 会抛出 UnknownAccountException异常 用户不存在
            return null;
        }
        //  存入session
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute("userinfo",user);
        // 把用户信息存入user
        return new SimpleAuthenticationInfo(user, user.getPassword(), "");
    }
}

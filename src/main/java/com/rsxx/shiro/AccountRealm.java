package com.rsxx.shiro;


import cn.hutool.core.bean.BeanUtil;
import com.rsxx.entity.RsUser;
import com.rsxx.service.RsUserService;
import com.rsxx.util.JwtUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountRealm extends AuthorizingRealm  {
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    RsUserService rsUserService;
    //为了让realm支持jwt的凭证校验
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    //权限校验
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    //登录认证校验
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        JwtToken jwtToken = (JwtToken) token;
        //获取userId
        System.err.println(jwtToken);
        String userId = jwtUtils.getClaimByToken((String) jwtToken.getPrincipal()).getSubject();
        //获取用户内容
        RsUser rsUser = rsUserService.getById(Long.valueOf(userId));
        if(rsUser == null){
            throw new UnknownAccountException("账户不存在");
        }
        if(rsUser.getUState() == 2){
            throw new LockedAccountException("账户已冻结");
        }
        if(rsUser.getUState() == 0){
            throw new LockedAccountException("账户未激活");
        }

        AccountProfile profile = new AccountProfile();
        BeanUtil.copyProperties(rsUser,profile);//将user数据转移到profile
        //用户信息  密钥token 用户名字
        return new SimpleAuthenticationInfo(profile,jwtToken.getCredentials(),getName());
    }
}

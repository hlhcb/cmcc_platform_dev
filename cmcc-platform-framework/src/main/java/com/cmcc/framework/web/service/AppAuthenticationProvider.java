package com.cmcc.framework.web.service;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author:terry
 * @create: 2024-03-20 20:13
 * @Description: 小程序登录
 */
@Component
public class AppAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private AppUserDetailsServiceImpl userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 这个获取表单输入中返回的用户名，此处获取到的是手机号码
        String userName = authentication.getName();

        // 这里构建来判断用户是否存在和密码是否正确
        // 这里调用我们的自己写的AppUserDetailsServiceImpl获取用户的方法；
        UserDetails userInfo = userDetailsService.loadUserByUsername(userName);
        Collection<? extends GrantedAuthority> authorities = userInfo.getAuthorities();
        // 构建返回的用户登录成功的token
        return new UsernamePasswordAuthenticationToken(userInfo, userInfo.getPassword(), authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        //return authentication.equals(UsernamePasswordAuthenticationToken.class);
        // 这里直接改成 return true;表示是支持这个执行
        return true;
    }
}

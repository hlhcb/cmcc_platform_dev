package com.cmcc.framework.web.service;

import com.cmcc.common.constant.Constants;
import com.cmcc.common.core.domain.entity.SysUser;
import com.cmcc.common.core.domain.model.LoginUser;
import com.cmcc.common.exception.ServiceException;
import com.cmcc.common.exception.user.UserPasswordNotMatchException;
import com.cmcc.common.utils.MessageUtils;
import com.cmcc.framework.manager.AsyncManager;
import com.cmcc.framework.manager.factory.AsyncFactory;
import com.cmcc.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author:terry
 * @create: 2024-03-20 20:27
 * @Description: app登录
 */
@Component
public class AppSysLoginService {

    @Autowired
    private ISysUserService userService;

    @Autowired
    private TokenService tokenService;

    @Resource
    private AppAuthenticationProvider authenticationManager;

    public String login1(String tel)
    {
        SysUser sysUser = userService.selectUserByTel(tel);
        if (sysUser == null) {
            throw new ServiceException("用户不存在");
        }
        String username = tel;
        String password = "123456";
        //自定义用户名和密码

        // 用户验证
        Authentication authentication;
        try {
            // 原来其实就这么一句话：该方法会去调用UserDetailsServiceImpl.loadUserByUsername。指的是原来若依自定义的UserDetailsServiceImpl
            //此处会让人很迷惑，特别是对新手来说。其实就是调用了AppUserDetailsServiceImpl中的loadUserByUsername方法
            //而这个方法的是通过AppAuthenticationProvider中去发起的。所以这个authenticationManager  其实就是注入的AppAuthenticationProvider
            //这个地方一定要注意！！！！！
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
                throw new UserPasswordNotMatchException();
            } else {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, e.getMessage()));
                throw new ServiceException(e.getMessage());
            }
        }
        AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        //recordLoginInfo(dbUser.getId());
        // 生成token
        return tokenService.createToken(loginUser);
    }
}

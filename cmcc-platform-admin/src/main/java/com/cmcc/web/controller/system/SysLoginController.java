package com.cmcc.web.controller.system;

import java.util.List;
import java.util.Set;

import com.alibaba.fastjson2.JSONObject;
import com.cmcc.common.utils.http.HttpUtils;
import com.cmcc.framework.web.service.AppSysLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.cmcc.common.constant.Constants;
import com.cmcc.common.core.domain.AjaxResult;
import com.cmcc.common.core.domain.entity.SysMenu;
import com.cmcc.common.core.domain.entity.SysUser;
import com.cmcc.common.core.domain.model.LoginBody;
import com.cmcc.common.utils.SecurityUtils;
import com.cmcc.framework.web.service.SysLoginService;
import com.cmcc.framework.web.service.SysPermissionService;
import com.cmcc.system.service.ISysMenuService;

/**
 * 登录验证
 * 
 * @author terry
 */
@RestController
public class SysLoginController
{
    @Autowired
    private SysLoginService loginService;

    @Autowired
    private AppSysLoginService appSysLoginService;

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private SysPermissionService permissionService;

    /**
     * 登录方法
     * 
     * @param loginBody 登录信息
     * @return 结果
     */
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody)
    {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                loginBody.getUuid());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    @PostMapping("/login1")
    public AjaxResult login1(@RequestBody SysUser loginBody)
    {
        AjaxResult ajax = AjaxResult.success();
        String s = HttpUtils.sendPost("https://api.weixin.qq.com/cgi-bin/stable_token", "{\"appid\": \"wxee6cb15604bf9e1a\",\"secret\": \"2aca00cbf87e300fa128ee536f04e4d6\",\"grant_type\": \"client_credential\"}");
//        String s = HttpUtils.sendPost("https://api.weixin.qq.com/cgi-bin/stable_token", "{\"appid\": \"wxee6cb15604bf9e1a\",\"secret\": \"2aca00cbf87e300fa128ee536f04e4d6\",\"grant_type\": \"client_credential\"}");

        JSONObject jsonObject = JSONObject.parseObject(s);
        //获取session_key和openid
//        JSONObject sessionKey = jsonObject.getJSONObject("data");
        String accessToken = jsonObject.getString("access_token");

//        String requsetParam = "{\"code\": \""+loginBody.getPhonenumber()+"\", \"openid\":\""+accessToken+"\"}";
        String requsetParam = "{\"code\": \""+loginBody.getPhonenumber()+"\"}";
        String s1 = HttpUtils.sendPost("https://api.weixin.qq.com/wxa/business/getuserphonenumber?access_token=" + accessToken, requsetParam);
        JSONObject jsonObject1 = JSONObject.parseObject(s1);
        //获取session_key和openid
//        JSONObject sessionKey1 = jsonObject1.getJSONObject("data");
        JSONObject accessToken1 = jsonObject1.getJSONObject("phone_info");
        String tel = accessToken1.getString("phoneNumber");

        // 生成令牌
        String token = appSysLoginService.login1(tel);
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    /**
     * 获取用户信息
     * 
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public AjaxResult getInfo()
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", user);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        return ajax;
    }

    /**
     * 获取路由信息
     * 
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public AjaxResult getRouters()
    {
        Long userId = SecurityUtils.getUserId();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(userId);
        return AjaxResult.success(menuService.buildMenus(menus));
    }
}

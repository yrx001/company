package com.rsxx.controller;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rsxx.common.dto.LoginDto;
import com.rsxx.common.lang.Result;
import com.rsxx.entity.RsCompany;
import com.rsxx.entity.RsUser;
import com.rsxx.service.RsCompanyService;
import com.rsxx.service.RsUserService;
import com.rsxx.util.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * 账户控制类
 */
@RestController
public class AccountController {
    @Autowired
    private RsUserService rsUserService;

    @Autowired
    private RsCompanyService rsCompanyService;

    @Autowired
    JwtUtils jwtUtils;

    /**
     * 用户登录
     * @param loginDto
     * @param response
     * @return
     */
    @RequestMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response){
        System.err.println(loginDto);
        System.err.println(111);
        RsUser user = rsUserService.getOne(new QueryWrapper<RsUser>().eq("uNumber", loginDto.getUsername()));
        Assert.notNull(user,"用户名不存在");//断言拦截

        //判断账号密码是否错误 因为是md5加密所以这里md5判断
        if(!user.getUPassword().equals(SecureUtil.md5(loginDto.getPassword()))){
            //密码不同则抛出异常
            return Result.fail("密码不正确");
        }
        String jwt = jwtUtils.generateToken(user.getId());

        //查询该用户所在的公司
        RsCompany company = rsCompanyService.getOne(new QueryWrapper<RsCompany>().eq("cid", user.getCid()));

        //将token 放在我们的header里面
        response.setHeader("Authorization",jwt);
        response.setHeader("Access-control-Expose-Headers","Authorization");
        return Result.succ(MapUtil.builder()
                .put("id",user.getId())
                .put("username",user.getUname())
                .put("uNumber",user.getUNumber())
                .put("uCompany",company.getCompanyName()).map()

        );
    }

    //需要认证权限才能退出登录
    @RequiresAuthentication
    @RequestMapping("/logout")
    public Result logout() {
        //退出登录
        SecurityUtils.getSubject().logout();
        return Result.succ("注销成功");
    }

}

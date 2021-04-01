package com.rsxx.controller;


import cn.hutool.core.lang.Assert;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rsxx.common.lang.Result;
import com.rsxx.entity.RsUser;
import com.rsxx.service.RsUserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author anonymous
 * @since 2021-03-10
 */
@RestController
@RequestMapping("/user")
public class RsUserController {
    @Autowired
    private RsUserService rsUserService;

    //登录拦截注解
//    @RequiresAuthentication
    @GetMapping("/index")
    public  Object index(){
        return Result.succ(200,"欢迎登陆","0");
    }

    /**
     * 注册新用户
     *
     * @param rsUser
     * @return
     */
    @RequiresAuthentication
    @RequestMapping("/add")
    public Object addUser(RsUser rsUser){
        rsUser.setUPassword(SecureUtil.md5(rsUser.getUPassword()));
        //判断账号是否存在
        RsUser Num= rsUserService.getOne(new QueryWrapper<RsUser>().eq("uNumber",rsUser.getUNumber()));
        Assert.isNull(Num,"账号重复");
        int result=rsUserService.addUser(rsUser);
        if (result>0){
            return  Result.succ(200,"添加成功",rsUser.getUNumber());
        }else{
            return  Result.fail(400,"添加失败，请重试",null);
        }
    }

    /**
     * 分页查找用户信息（全部）
     * @param currentPage
     * @return
     */

    @GetMapping("/sel")
    public Result selUser(@RequestParam(defaultValue = "1")Integer currentPage){
        Page page=new Page(currentPage,5);
        IPage pageData=rsUserService.page(page,new QueryWrapper<RsUser>().orderByDesc("id"));
        return Result.succ(pageData);
    }

    /**
     * 根据用户账号查找用户信息
     * @param uNumber
     * @return RsUser
     */
    @RequiresAuthentication
    @GetMapping("/selOne")
    public Result selUserById(String uNumber){
        return Result.succ(rsUserService.getOne(new QueryWrapper<RsUser>().eq("uNumber",uNumber)));
    }

    /**
     * 根据账号修改密码
     * @param uNumber
     * @param uPassword
     * @return
     */
    @RequiresAuthentication
    @GetMapping("/updatePassword")
    public Result UpdatePassWord(String uNumber,String uPassword){
        RsUser rsUser=rsUserService.getOne(new QueryWrapper<RsUser>().eq("uNumber",uNumber));
        uPassword=SecureUtil.md5(uPassword);
        rsUser.setUPassword(uPassword);
        System.err.println(rsUserService.update(rsUser,new UpdateWrapper<RsUser>().eq("uNumber",uNumber)));
        return Result.succ(rsUserService.update(rsUser,new UpdateWrapper<RsUser>().eq("uNumber",uNumber)));
    }

}

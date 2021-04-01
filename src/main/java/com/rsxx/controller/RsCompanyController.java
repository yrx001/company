package com.rsxx.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rsxx.common.lang.Result;
import com.rsxx.entity.RsUser;
import com.rsxx.service.RsCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author anonymous
 * @since 2021-03-10
 */
@RestController
@RequestMapping("/company")
public class RsCompanyController {
    @Autowired
    RsCompanyService rsCompanyService;

    @GetMapping("/selCompany")
    public Result selUser(){
        System.out.println(rsCompanyService.list());
        return Result.succ( rsCompanyService.list());

    }

}

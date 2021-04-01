package com.rsxx.service;

import com.rsxx.entity.RsUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author anonymous
 * @since 2021-03-10
 */
public interface RsUserService extends IService<RsUser> {
    //添加新用户
    public int addUser(RsUser rsUser);
}

package com.rsxx.service.impl;

import com.rsxx.entity.RsUser;
import com.rsxx.mapper.RsUserMapper;
import com.rsxx.service.RsUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author anonymous
 * @since 2021-03-10
 */
@Service
public class RsUserServiceImpl extends ServiceImpl<RsUserMapper, RsUser> implements RsUserService {

    @Resource
    private RsUserMapper rsUserMapper;

    @Override
    public int addUser(RsUser rsUser) {
        return rsUserMapper.addUser(rsUser);
    }
}

package com.rsxx.mapper;

import com.rsxx.entity.RsUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.awt.*;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author anonymous
 * @since 2021-03-10
 */
public interface RsUserMapper extends BaseMapper<RsUser> {
    //增加新用户
    public int addUser(RsUser rsUser);


}

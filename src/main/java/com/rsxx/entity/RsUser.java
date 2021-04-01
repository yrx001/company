package com.rsxx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author anonymous
 * @since 2021-03-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RsUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户表id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    private String uname;

    /**
     * 用户账号
     */
    @TableField("uNumber")
    private String uNumber;

    /**
     * 用户密码
     */
    @TableField("uPassword")
    private String uPassword;

    /**
     * 用户状态（0：待激活，1：激活，2：失效。）
     */
    @TableField("uState")
    private Integer uState;

    /**
     * 企业id
     */
    private Integer cid;


}

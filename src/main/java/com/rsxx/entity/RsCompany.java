package com.rsxx.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author anonymous
 * @since 2021-03-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("rs_company")
public class RsCompany implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 企业id
     */
    private Integer cid;

    /**
     * 企业名称
     */
    private String companyName;


}

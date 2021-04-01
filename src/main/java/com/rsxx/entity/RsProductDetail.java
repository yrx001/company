package com.rsxx.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
 * @since 2021-03-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RsProductDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 产品详细id
     */
    private Integer productPid;

    /**
     * 产品类型（关联产品类型表）
     */
    private String productTid;

    /**
     * 产品条形码
     */
    private String productPbarcode;

    /**
     * 产品名称
     */
    private String productPname;

    /**
     * 产品型号
     */
    private String productPspec;

    /**
     * 产品进价
     */
    private BigDecimal productBuy;

    /**
     * 产品单位
     */
    private String productPunit;

    /**
     * 产品唯一标识
     */
    private String productRowid;

    /**
     * 产品数量
     */
    private Integer productNum;

    /**
     * 产品售价
     */
    private BigDecimal productSell;

    /**
     * 生产厂家
     */
    private Integer productCid;

    /**
     * 生产日期
     */
    private LocalDateTime productionDate;

    /**
     * 有效期
     */
    private String guaranteePeriod;

    /**
     * 创建日期
     */
    private LocalDateTime createTime;

    /**
     * 产品备注
     */
    private String productRemark;


}

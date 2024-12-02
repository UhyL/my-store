package com.nju.mystore.po;

import com.nju.mystore.enums.CouponEnum;
import com.nju.mystore.enums.CouponStatusEnum;
import com.nju.mystore.vo.CouponVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Coupon {

    /**
     * 优惠券id
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "coupon_id")
    private Integer couponId;

    /**
     * 优惠券所属优惠券组的id
     */
    @Basic
    @Column(name = "coupon_group_id")
    private Integer couponGroupId;

    /**
     * 优惠券的种类 满减券、蓝鲸券
     */

    @Basic
    @Column(name = "coupon_category")
    @Enumerated(EnumType.STRING)
    private CouponEnum couponCategory;

    /**
     * 优惠券范围
     */
    @Basic
    @Column(name = "coupon_space")
    @Enumerated(EnumType.STRING)
    private CouponEnum couponSpace;

    /**
     * 满减券的满减金额
     */

    @Basic
    @Column(name = "full")
    private Double full;

    /**
     * 满减券的优惠金额
     */
    @Basic
    @Column(name = "reduction")
    private Double reduction;

    /**
     * 优惠券所属商店
     */
    @Basic
    @Column(name = "coupon_store_id")
    private Integer couponStoreId;

    /**
     * 优惠券创建时间
     */
    @Basic
    @Column(name = "coupon_create_time")
    private Date couponCreateTime;

    /**
     * 优惠券过期时间
     */
    @Basic
    @Column(name = "coupon_expire_time")
    private Date couponExpireTime;


    /**
     * 优惠券的状态
     */
    @Basic
    @Column(name = "coupon_status")
    private CouponStatusEnum couponStatus;


    public CouponVO toVO() {
        CouponVO couponVO = new CouponVO();
        couponVO.setCouponId(this.couponId);
        couponVO.setCouponCategory(this.couponCategory);
        couponVO.setCouponGroupId(this.couponGroupId);
        couponVO.setCouponSpace(this.couponSpace);
        couponVO.setFull(this.full);
        couponVO.setReduction(this.reduction);
        couponVO.setCouponStoreId(this.couponStoreId);
        couponVO.setCouponCreateTime(this.couponCreateTime);
        couponVO.setCouponExpireTime(this.couponExpireTime);
        couponVO.setCouponStatus(this.couponStatus);
        return couponVO;
    }
}

package com.nju.mystore.po;


import com.nju.mystore.enums.CouponEnum;
import com.nju.mystore.vo.CouponGroupVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class CouponGroup {
    /**
     * 优惠券组id
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "coupon_group_id")
    private Integer couponGroupId;

    /**
     * 优惠券组优惠券使用范围
     */
    @Basic
    @Column(name = "coupon_group_space")
    @Enumerated(EnumType.STRING)
    private CouponEnum couponGroupSpace;

    /**
     * 优惠券组所属商店id
     */
    @Basic
    @Column(name = "coupon_group_store_id")
    private Integer couponGroupStoreId;

    /**
     * 优惠券组创建时间
     */
    @Basic
    @Column(name = "coupon_group_create_time")
    private Date couponGroupCreateTime;

    /**
     * 优惠券组剩余数量
     */
    @Basic
    @Column(name = "coupon_group_remains")
    private Integer couponGroupRemains;

    /**
     * 优惠券组领取数量
     */
    @Basic
    @Column(name = "coupon_group_receive_num")
    private Integer couponGroupReceiveNum;


    /**
     * 优惠券的种类 满减券、蓝鲸券
     */

    @Basic
    @Column(name = "coupon_group_category" )
    @Enumerated(EnumType.STRING)
    private CouponEnum couponGroupCategory;

    /**
     * 满减券的满减金额
     */

    @Basic
    @Column(name ="full")
    private Double full;

    /**
     * 满减券的优惠金额
     */
    @Basic
    @Column(name ="reduction")
    private Double reduction;




    public CouponGroupVO toVO() {
        CouponGroupVO couponGroupVO = new CouponGroupVO();
        couponGroupVO.setCouponGroupId(this.couponGroupId);
        couponGroupVO.setCouponGroupSpace(this.couponGroupSpace);
        couponGroupVO.setCouponGroupStoreId(this.couponGroupStoreId);
        couponGroupVO.setCouponGroupCreateTime(this.couponGroupCreateTime);
        couponGroupVO.setCouponGroupRemains(this.couponGroupRemains);
        couponGroupVO.setCouponGroupReceiveNum(this.couponGroupReceiveNum);
        couponGroupVO.setCouponGroupCategory(this.couponGroupCategory);
        couponGroupVO.setFull(this.full);
        couponGroupVO.setReduction(this.reduction);
        return couponGroupVO;
    }
}

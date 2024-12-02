package com.nju.mystore.vo;


import com.nju.mystore.enums.CouponEnum;
import com.nju.mystore.po.CouponGroup;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class CouponGroupVO {
    private Integer couponGroupId;

    private CouponEnum couponGroupSpace;

    private Integer couponGroupStoreId;

    private Date couponGroupCreateTime;

    private Integer couponGroupRemains;

    private Integer couponGroupReceiveNum;

    private CouponEnum couponGroupCategory;

    private Double full;

    private Double reduction;

    public CouponGroup toPO() {
        CouponGroup couponGroup = new CouponGroup();
        couponGroup.setCouponGroupId(this.couponGroupId);
        couponGroup.setCouponGroupSpace(this.couponGroupSpace);
        couponGroup.setCouponGroupStoreId(this.couponGroupStoreId);
        couponGroup.setCouponGroupCreateTime(this.couponGroupCreateTime);
        couponGroup.setCouponGroupRemains(this.couponGroupRemains);
        couponGroup.setCouponGroupReceiveNum(this.couponGroupReceiveNum);
        couponGroup.setCouponGroupCategory(this.couponGroupCategory);
        couponGroup.setFull(this.full);
        couponGroup.setReduction(this.reduction);
        return couponGroup;
    }
}

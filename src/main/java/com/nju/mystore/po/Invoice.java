package com.nju.mystore.po;


import com.nju.mystore.enums.GetProductsEnum;
import com.nju.mystore.enums.InvoiceStatusEnum;
import com.nju.mystore.vo.InvoiceVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Invoice {

    /**
     * 订单id
     */

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "invoice_id")
    private Integer invoiceId;

    /**
     * 订单货物id
     */
    @Basic
    @Column(name = "invoice_product_id")
    private Integer invoiceProductId;

    /**
     * 订单货物数量
     */
    @Basic
    @Column(name = "invoice_product_num")
    private Integer invoiceProductNum;

    /**
     * 创建订单用户id
     */

    @Basic
    @Column(name = "invoice_user_id")
    private Integer invoiceUserId;

    /**
     * 订单所属商店id
     */
    @Basic
    @Column(name = "invoice_store_id")
    private Integer invoiceStoreId;


    /**
     * 订单总价格
     */
    @Basic
    @Column(name = "invoice_price")
    private Double invoicePrice;

    /**
     * 订单实际价格
     */
    @Basic
    @Column(name = "invoice_real_price")
    private Double invoiceRealPrice;

    /**
     * 订单状态
     * 未支付 / 已支付 未发货 / 已发货 未送达 / 已送达
     */
    @Basic
    @Column(name = "invoice_status")
    private InvoiceStatusEnum invoiceStatus;


    /**
     * 订单收货地址
     */

    @Basic
    @Column(name = "invoice_address")
    private String invoiceAddress;

    /**
     * 订单收货电话
     */

    @Basic
    @Column(name = "invoice_phone")
    private String invoicePhone;

    /**
     * 订单收货人姓名
     */

    @Basic
    @Column(name = "invoice_name")
    private String invoiceName;


    /**
     * 订单创建时间
     */

    @Basic
    @Column(name = "invoice_time")
    private Date invoiceTime;


    /**
     * 订单支付时间
     */
    @Basic
    @Column(name = "invoice_pay_time")
    private Date invoicePayTime;


    /**
     * 订单提货方式
     */
    @Basic
    @Column(name = "invoice_get_products_way")
    private GetProductsEnum getProducts;

    /**
     * 订单评分
     */

    @Basic
    @Column(name = "invoice_score")
    private Integer invoiceScore;

    /**
     * 订单评论时间
     */
    @Basic
    @Column(name = "invoice_comment_time")
    private Date invoiceCommentTime;

    /**
     * 订单评论
     */

    @Basic
    @Column(name = "invoice_remark")
    private String invoiceRemark;

    @ElementCollection
    @CollectionTable(name = "invoice_coupon", joinColumns = @JoinColumn(name = "invoice_id"))
    private List<Integer> coupons;


    public InvoiceVO toVO() {
        InvoiceVO invoiceVO = new InvoiceVO();
        invoiceVO.setInvoiceId(this.invoiceId);
        invoiceVO.setInvoiceProductId(this.invoiceProductId);
        invoiceVO.setInvoiceProductNum(this.invoiceProductNum);
        invoiceVO.setInvoiceUserId(this.invoiceUserId);
        invoiceVO.setInvoiceStoreId(this.invoiceStoreId);
        invoiceVO.setInvoicePrice(this.invoicePrice);
        invoiceVO.setInvoiceRealPrice(this.invoiceRealPrice);
        invoiceVO.setInvoiceStatus(this.invoiceStatus);
        invoiceVO.setInvoiceAddress(this.invoiceAddress);
        invoiceVO.setInvoicePhone(this.invoicePhone);
        invoiceVO.setInvoiceName(this.invoiceName);
        invoiceVO.setInvoiceTime(this.invoiceTime);
        invoiceVO.setInvoiceProductId(this.invoiceProductId);
        invoiceVO.setInvoicePayTime(this.invoicePayTime);
        invoiceVO.setGetProducts(this.getProducts);
        invoiceVO.setInvoiceCommentTime(this.invoiceCommentTime);
        invoiceVO.setInvoiceRemark(this.invoiceRemark);
        invoiceVO.setInvoiceScore(this.invoiceScore);
        invoiceVO.setCoupons(this.coupons);
        return invoiceVO;
    }
}

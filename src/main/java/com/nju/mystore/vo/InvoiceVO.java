package com.nju.mystore.vo;


import com.nju.mystore.enums.GetProductsEnum;
import com.nju.mystore.enums.InvoiceStatusEnum;
import com.nju.mystore.po.Invoice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceVO {

    private Integer invoiceId;

    private Integer invoiceProductId;

    private Integer invoiceProductNum;

    private Integer invoiceUserId;

    private Integer invoiceStoreId;

    private InvoiceStatusEnum invoiceStatus;

    private Double invoicePrice;

    private Double invoiceRealPrice;

    private String invoiceAddress;

    private String invoicePhone;

    private String invoiceName;

    private Date invoiceTime;

    private Date invoicePayTime;

    private Integer invoiceScore;

    private GetProductsEnum getProducts;

    private Date invoiceCommentTime;

    private String invoiceRemark;

    private List<Integer> coupons;

    public Invoice toPO() {
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(this.invoiceId);
        invoice.setInvoiceProductId(this.invoiceProductId);
        invoice.setInvoiceProductNum(this.invoiceProductNum);
        invoice.setInvoiceUserId(this.invoiceUserId);
        invoice.setInvoiceStoreId(this.invoiceStoreId);
        invoice.setInvoiceStatus(this.invoiceStatus);
        invoice.setInvoiceRealPrice(this.invoiceRealPrice);
        invoice.setInvoicePrice(this.invoicePrice);
        invoice.setInvoiceAddress(this.invoiceAddress);
        invoice.setInvoicePhone(this.invoicePhone);
        invoice.setInvoiceName(this.invoiceName);
        invoice.setInvoiceTime(this.invoiceTime);
        invoice.setInvoicePayTime(this.invoicePayTime);
        invoice.setGetProducts(this.getProducts);
        invoice.setInvoiceCommentTime(this.invoiceCommentTime);
        invoice.setInvoiceRemark(this.invoiceRemark);
        invoice.setInvoiceScore(this.invoiceScore);
        invoice.setCoupons(this.coupons);
        return invoice;
    }
}

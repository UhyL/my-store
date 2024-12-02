package com.nju.mystore.repository;


import com.nju.mystore.enums.InvoiceStatusEnum;
import com.nju.mystore.po.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

    Invoice findByInvoiceId(Integer invoiceId);

    List<Invoice> findAllByInvoiceUserId(Integer invoiceUserId);

    List<Invoice> findAllByInvoiceStoreId(Integer invoiceStoreId);

    List<Invoice> findAllByInvoiceProductId(Integer invoiceProductId);

    Invoice findByInvoiceName(String invoiceName);

    @Modifying
    @Transactional
    @Query("UPDATE Invoice invoice SET invoice.invoiceStatus = :status WHERE invoice.invoiceId = :invoice_id")
    void updateInvoiceStatus(@Param("invoice_id") Integer invoiceId, @Param("status") InvoiceStatusEnum status);


    @Modifying
    @Transactional
    @Query("UPDATE Invoice invoice SET invoice.invoicePayTime = :pay_time WHERE invoice.invoiceId = :invoice_id")
    void updateInvoicePayTime(@Param("invoice_id") Integer invoiceId, @Param("pay_time") Date date);

    @Modifying
    @Transactional
    @Query("UPDATE Invoice invoice SET invoice.invoiceRemark = :remark, invoice.invoiceScore = :score, invoice.invoiceCommentTime = :time WHERE invoice.invoiceId = :invoice_id")
    void updateInvoiceRemarkAndScore(@Param("invoice_id") Integer invoiceId, @Param("remark") String remark, @Param("score") Integer score, @Param("time") Date time);

    @Modifying
    @Transactional
    @Query("UPDATE Invoice invoice SET invoice.invoiceRealPrice = :invoiceRealPrice WHERE invoice.invoiceId = :invoice_id")
    void updateInvoiceRealPrice(@Param("invoice_id") Integer invoiceId, @Param("invoiceRealPrice") Double invoiceRealPrice);

    @Modifying
    @Transactional
    @Query("DELETE FROM Invoice invoice WHERE invoice.invoiceId = :invoice_id")
    void deleteInvoice(@Param("invoice_id") Integer invoiceId);

}

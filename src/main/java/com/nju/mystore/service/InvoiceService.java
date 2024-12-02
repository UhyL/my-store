package com.nju.mystore.service;

import com.nju.mystore.vo.InvoiceVO;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

public interface InvoiceService {

    Integer createInvoice(InvoiceVO invoiceVO);

    InvoiceVO getInvoiceById(Integer invoiceId);

    List<InvoiceVO> getAllByInvoiceUserId(Integer userId);

    List<InvoiceVO> getAllByInvoiceStoreId(Integer storeId);

    Double getRealPrice(Integer invoiceId, Integer couponId);

    String payInvoice(Integer invoiceId);

    Boolean sendInvoice(Integer invoiceId);

    Boolean getInvoice(Integer invoiceId);

    Boolean addComment(Integer invoiceId, Integer score, String comment);

    List<InvoiceVO> getAllInvoices();

    Boolean cancelInvoice(Integer invoiceId);

    String exportInvoices(Integer storeId);

    String exportAllInvoices();

    @Scheduled(cron = "* * * * * * ")
    void updateInvoiceDate();

}

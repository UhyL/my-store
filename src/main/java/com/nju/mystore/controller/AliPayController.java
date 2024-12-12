//package com.nju.mystore.controller;
//
//import com.nju.mystore.Log.Log;
//import com.nju.mystore.enums.CouponStatusEnum;
//import com.nju.mystore.enums.InvoiceStatusEnum;
//import com.nju.mystore.po.Invoice;
//import com.nju.mystore.repository.CouponRepository;
//import com.nju.mystore.repository.InvoiceRepository;
//import com.nju.mystore.repository.ProductRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/ali")
//public class AliPayController {
//
//    @Autowired
//    InvoiceRepository invoiceRepository;
//
//    @Autowired
//    ProductRepository productRepository;
//    @Autowired
//    private CouponRepository couponRepository;
//
//    @PostMapping("/notify")
//    public void notify(HttpServletRequest httpServletRequest) {
//        if (httpServletRequest.getParameter("trade_status").equals("TRADE_SUCCESS")) {
//            System.out.println("=========支付宝异步回调========");
//            Map<String, String> params = new HashMap<>();
//            Map<String, String[]> requestParams = httpServletRequest.getParameterMap();
//            for (String name : requestParams.keySet()) {
//                params.put(name, httpServletRequest.getParameter(name));
//            }
//            Log.record_log("notify"+" "+"TRADE_SUCCESS");
//        } else if (httpServletRequest.getParameter("trade_status").equals("TRADE_FINISHED")) {
//            Invoice invoice = invoiceRepository.findByInvoiceId(Integer.parseInt(httpServletRequest.getParameter("out_trade_no")));
//            invoiceRepository.updateInvoicePayTime(invoice.getInvoiceId(), null);
//            invoiceRepository.updateInvoiceRealPrice(invoice.getInvoiceId(), null);
//            invoiceRepository.updateInvoiceStatus(invoice.getInvoiceId(), InvoiceStatusEnum.UNPAID);
//            productRepository.updateProductBySales(invoice.getInvoiceProductId(), invoice.getInvoiceProductNum() + productRepository.findByProductId(invoice.getInvoiceProductId()).getProductSales());
//            List<Integer> coupons = invoice.getCoupons();
//            for (Integer couponId : coupons) {
//                couponRepository.updateCouponStatus(couponId, CouponStatusEnum.UNUSED);
//            }
//            Log.record_log("notify"+" "+"TRADE_FINISHED");
//        }
//    }
//
//}

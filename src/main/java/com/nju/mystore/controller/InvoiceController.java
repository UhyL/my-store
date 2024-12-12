//package com.nju.mystore.controller;
//
//
//import com.nju.mystore.Log.Log;
//import com.nju.mystore.enums.RoleEnum;
//import com.nju.mystore.service.InvoiceService;
//import com.nju.mystore.util.AccessUtil;
//import com.nju.mystore.vo.InvoiceVO;
//import com.nju.mystore.vo.ResultVO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/invoices")
//public class InvoiceController {
//
//    @Autowired
//    InvoiceService invoiceService;
//
//
//    @PostMapping("/createInvoice")
//    public ResultVO<Integer> createInvoice(@RequestBody InvoiceVO invoiceVO) {
//        Log.record_log("createInvoice" + " " + "invoiceName+" + invoiceVO.getInvoiceName());
//        return ResultVO.buildSuccess(invoiceService.createInvoice(invoiceVO));
//    }
//
//    @GetMapping("/getInvoice/{invoiceId}")
//    public ResultVO<InvoiceVO> getInvoiceById(@PathVariable("invoiceId") Integer invoiceId) {
//        Log.record_log("getInvoiceById" + " " + "invoiceId" + invoiceId);
//        return ResultVO.buildSuccess(invoiceService.getInvoiceById(invoiceId));
//    }
//
//
//    @PostMapping("/payInvoice")
//    public ResultVO<String> payInvoice(@RequestParam("invoiceId") Integer invoiceId) {
//        Log.record_log("payInvoice" + " "
//                + "invoiceId=" + invoiceId);
//        return ResultVO.buildSuccess(invoiceService.payInvoice(invoiceId));
//    }
//
//    @AccessUtil(roles = {RoleEnum.CEO, RoleEnum.MANAGER})
//    @GetMapping("/getAllInvoices")
//    public ResultVO<List<InvoiceVO>> getAllInvoices() {
//        Log.record_log("getAllInvoices");
//        return ResultVO.buildSuccess(invoiceService.getAllInvoices());
//    }
//
//
//    @PostMapping("/sendInvoice")
//    public ResultVO<Boolean> sendInvoice(@RequestParam("invoiceId") Integer invoiceId) {
//        Log.record_log("sendInvoice" + " " + "invoiceId=" + invoiceId);
//        return ResultVO.buildSuccess(invoiceService.sendInvoice(invoiceId));
//    }
//
//    @PostMapping("/getInvoice")
//    public ResultVO<Boolean> getInvoice(@RequestParam("invoiceId") Integer invoiceId) {
//        Log.record_log("getInvoice" + " " + "invoiceId=" + invoiceId);
//        return ResultVO.buildSuccess(invoiceService.getInvoice(invoiceId));
//    }
//
//    @PostMapping("/commentInvoice")
//    public ResultVO<Boolean> commentInvoice(@RequestParam("invoiceId") Integer invoiceId,
//                                            @RequestParam("score") Integer score,
//                                            @RequestParam("comment") String comment) {
//        Log.record_log("commentInvoice" + " "
//                + "invoiceId=" + invoiceId + " "
//                + "score=" + score + " "
//                + "comment=" + comment);
//        return ResultVO.buildSuccess(invoiceService.addComment(invoiceId, score, comment));
//    }
//
//    @PostMapping("/cancelInvoice")
//    public ResultVO<Boolean> cancelInvoice(@RequestParam("invoiceId") Integer invoiceId) {
//        Log.record_log("cancelInvoice" + " " + "invoiceId=" + invoiceId);
//        return ResultVO.buildSuccess(invoiceService.cancelInvoice(invoiceId));
//    }
//
//
//
//    @GetMapping("getRealPrice/{invoiceId}/{couponId}")
//    public ResultVO<Double> getRealPrice(@PathVariable("invoiceId") Integer invoiceId, @PathVariable("couponId") Integer couponId) {
//        Log.record_log("getRealPrice" + " "
//                + "invoiceId=" + invoiceId + " "
//                + "couponId=" + couponId);
//        return ResultVO.buildSuccess(invoiceService.getRealPrice(invoiceId, couponId));
//    }
//
//
//    @AccessUtil(roles = {RoleEnum.CEO, RoleEnum.MANAGER})
//    @GetMapping("/exportAllInvoices")
//    public ResultVO<String> exportAllInvoices() {
//        Log.record_log("exportAllInvoices");
//        return ResultVO.buildSuccess(invoiceService.exportAllInvoices());
//    }
//
//    @AccessUtil(roles = {RoleEnum.CEO, RoleEnum.MANAGER, RoleEnum.STAFF})
//    @GetMapping("/exportInvoices/{storeId}")
//    public ResultVO<String> exportInvoices(@PathVariable("storeId") Integer storeId) {
//        Log.record_log("exportInvoices" + " " + "storeId=" + storeId);
//        return ResultVO.buildSuccess(invoiceService.exportInvoices(storeId));
//    }
//
//}

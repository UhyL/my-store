package com.nju.mystore.controller;

import com.nju.mystore.Log.Log;
import com.nju.mystore.enums.RoleEnum;
import com.nju.mystore.service.InvoiceService;
import com.nju.mystore.service.ProductService;
import com.nju.mystore.service.StoreService;
import com.nju.mystore.util.AccessUtil;
import com.nju.mystore.vo.InvoiceVO;
import com.nju.mystore.vo.ProductVO;
import com.nju.mystore.vo.ResultVO;
import com.nju.mystore.vo.StoreVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stores")
public class StoreController {
    @Autowired
    StoreService storeService;

    @Autowired
    ProductService productService;

    @Autowired
    InvoiceService invoiceService;

    @AccessUtil(roles = {RoleEnum.STAFF,RoleEnum.MANAGER})
    @PostMapping("/createStore")
    public ResultVO<Integer> createStore(@RequestBody StoreVO storeVO) {
        Log.record_log("createStore"+" "+"storeName="+storeVO.getStoreName());
        return ResultVO.buildSuccess(storeService.createStore(storeVO));
    }


    @GetMapping("/getStore/{storeId}")
    public ResultVO<StoreVO> getStoreById(@PathVariable("storeId") Integer storeId) {
        Log.record_log("getStoreById"+" "+"storeId="+storeId);
        return ResultVO.buildSuccess(storeService.getStoreById(storeId));
    }

    @GetMapping("/getAllStores")
    public ResultVO<List<StoreVO>> getAllStores() {
        Log.record_log("getAllStores");
        return ResultVO.buildSuccess(storeService.getAllStores());
    }

    @GetMapping("/getAllProducts/{storeId}")
    public ResultVO<List<ProductVO>> getAllProducts(@PathVariable("storeId") Integer storeId) {
        Log.record_log("getAllProducts"+" "+"storeId="+storeId);
        return ResultVO.buildSuccess(productService.getAllProducts(storeId));
    }

    @AccessUtil(roles = {RoleEnum.STAFF,RoleEnum.MANAGER})
    @GetMapping("/getAllInvoices/{storeId}")
    public ResultVO<List<InvoiceVO>> getAllInvoices(@PathVariable("storeId") Integer storeId) {
        Log.record_log("getAllInvoices"+" "+"storeId="+storeId);
        return ResultVO.buildSuccess(invoiceService.getAllByInvoiceStoreId(storeId));
    }

    @GetMapping("/getStoreAddress/{storeId}")
    public ResultVO<String> getStoreAddress(@PathVariable("storeId") Integer storeId) {
        Log.record_log("getStoreAddress"+" "+"storeId="+storeId);
        return ResultVO.buildSuccess(storeService.getStoreAddress(storeId));
    }

}

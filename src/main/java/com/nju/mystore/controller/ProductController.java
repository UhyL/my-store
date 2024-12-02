package com.nju.mystore.controller;


import com.nju.mystore.Log.Log;
import com.nju.mystore.enums.ProductEnum;
import com.nju.mystore.enums.RoleEnum;
import com.nju.mystore.service.ProductService;
import com.nju.mystore.util.AccessUtil;
import com.nju.mystore.vo.CommentVO;
import com.nju.mystore.vo.ProductVO;
import com.nju.mystore.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @AccessUtil(roles = {RoleEnum.STAFF, RoleEnum.MANAGER})
    @PostMapping("/createProduct")
    public ResultVO<Integer> createProduct(@RequestBody ProductVO productVO) {
        Log.record_log("createProduct" + " " + productVO.getProductName());
        return ResultVO.buildSuccess(productService.createProduct(productVO));
    }

    @GetMapping("/getProduct/{productId}")
    public ResultVO<ProductVO> getProductById(@PathVariable("productId") Integer productId) {
        Log.record_log("getProductById" + " " + productId);
        return ResultVO.buildSuccess(productService.getProductById(productId));
    }

    @AccessUtil(roles = {RoleEnum.STAFF, RoleEnum.MANAGER})
    @PostMapping("/updateProductSales")
    public synchronized ResultVO<Boolean> updateProductSales(@RequestParam("productId") Integer productId, @RequestParam("sales") Integer productSales) {
        ProductVO productVO = productService.getProductById(productId);
        Log.record_log("updateProductSales" + " " + "product_id=" + productId + " " + "sales=" + productSales);
        return ResultVO.buildSuccess(productService.updateProductSales(productId, productSales + productVO.getProductSales()));
    }

    @AccessUtil(roles = {RoleEnum.STAFF, RoleEnum.MANAGER})
    @PostMapping("/updateProductPicture")
    public ResultVO<Boolean> updateProductPicture(@RequestParam("productId") Integer productId, @RequestParam("picture") String picture) {
        Log.record_log("updateProductPicture" + " " + "productId=" + productId + " " + "picture=" + picture);
        return ResultVO.buildSuccess(productService.addMultiPicture(productId, picture));
    }

    @GetMapping("/getAllComments/{productId}")
    public ResultVO<List<CommentVO>> getAllComments(@PathVariable("productId") Integer productId) {
        Log.record_log("getAllComments" + " " + "productId=" + productId);
        return ResultVO.buildSuccess(productService.getAllComments(productId));
    }

    @GetMapping("/searchProduct")
    public ResultVO<List<ProductVO>> searchProduct(@RequestParam(value = "storeId", required = false) Integer storeId,
                                                   @RequestParam(value = "name", required = false) String name,
                                                   @RequestParam(value = "type", required = false) ProductEnum type,
                                                   @RequestParam(value = "minPrice", required = false) Double minPrice,
                                                   @RequestParam(value = "maxPrice", required = false) Double maxPrice) {
        Log.record_log("searchProduct" + " "
                + "storeId=" + storeId + " "
                + "name=" + name + " "
                + "type=" + type + " "
                + "minPrice=" + minPrice + " "
                + "maxPrice=" + maxPrice);
        return ResultVO.buildSuccess(productService.searchProduct(storeId, name, type, minPrice, maxPrice));
    }
}

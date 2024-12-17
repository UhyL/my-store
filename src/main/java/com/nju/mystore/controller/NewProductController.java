package com.nju.mystore.controller;

import com.nju.mystore.po.product.ProductAttribute;
import com.nju.mystore.service.NewProductService;
import com.nju.mystore.vo.ResultVO;
import com.nju.mystore.vo.product.CommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.nju.mystore.po.product.NewProduct;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/newProducts")
public class NewProductController {
    @Autowired
    NewProductService newProductService;

    @GetMapping("/getAttributesByCategory/{productCategory}")
    public ResultVO<List<ProductAttribute>> getAttributesByCategory( @PathVariable("productCategory") String productCategory) {
        return ResultVO.buildSuccess(newProductService.getAttributesByCategory(productCategory));
    }

    @GetMapping("/filterProducts/{productCategory}")
    public ResultVO<List<NewProduct>> filterProducts(@PathVariable("productCategory")String productCategory,
                                                     @RequestParam Map<String, String> filters) {
        return ResultVO.buildSuccess(newProductService.filterProducts(productCategory, filters));
    }

    @GetMapping("/getComments")
    public ResultVO<List<CommentVO>> getComments(@RequestParam ("productId") Integer productId) {
        return ResultVO.buildSuccess(newProductService.getCommentsByProductId(productId));
    }

    @PostMapping("/addComment")
    public ResultVO<Boolean> addComment(@RequestBody CommentVO commentVO) {
        return ResultVO.buildSuccess(newProductService.addComment(commentVO));
    }
}

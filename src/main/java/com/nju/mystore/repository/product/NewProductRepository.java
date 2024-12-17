package com.nju.mystore.repository.product;

import com.nju.mystore.enums.product.ProductCategory;
import com.nju.mystore.po.product.NewProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewProductRepository extends JpaRepository<NewProduct, Integer> {
    List<NewProduct> findByProductCategory(ProductCategory productCategory);
}

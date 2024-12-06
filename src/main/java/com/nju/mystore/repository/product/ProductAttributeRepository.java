package com.nju.mystore.repository.product;

import com.nju.mystore.po.product.ProductAttribute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductAttributeRepository extends JpaRepository<ProductAttribute, Integer> {
    List<ProductAttribute> findByProductCategory(String productCategory);
}

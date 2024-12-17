package com.nju.mystore.repository.product;

import com.nju.mystore.po.product.ProductOptionValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductOptionValueRepository extends JpaRepository<ProductOptionValue, Integer> {
    List<ProductOptionValue> findByProductId(Integer productId);

}

package com.nju.mystore.repository.product;

import com.nju.mystore.po.product.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductOptionRepository extends JpaRepository<ProductOption, Integer> {

    List<ProductOption> findByProductId(Integer productId);
}

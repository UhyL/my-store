package com.nju.mystore.repository.product;

import com.nju.mystore.po.product.NewProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewProductRepository extends JpaRepository<NewProduct, Integer> {
}

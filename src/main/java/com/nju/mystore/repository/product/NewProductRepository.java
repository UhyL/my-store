package com.nju.mystore.repository.product;

import com.nju.mystore.enums.product.ProductCategory;
import com.nju.mystore.po.product.NewProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewProductRepository extends JpaRepository<NewProduct, Integer> {
    List<NewProduct> findByProductCategory(ProductCategory productCategory);

    // 使用@Query来编写查询
    @Query("SELECT np FROM NewProduct np WHERE np.productPrice <> np.nowPrice")
    List<NewProduct> findProductsWithMismatchedPrices();
}

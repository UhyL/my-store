package com.nju.mystore.repository;

import com.nju.mystore.enums.ProductEnum;
import com.nju.mystore.po.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findByProductId(Integer productId);

    List<Product> findAllByProductStoreId(Integer productStoreId);

    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.productSales = :sales WHERE p.productId = :product_id")
    int updateProductBySales(@Param("product_id") Integer productId, @Param("sales") Integer productSales);

    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.productScore = :score, p.commentNum = :comment_num WHERE p.productId = :product_id")
    void updateProductByComment(@Param("product_id") Integer productId, @Param("score") Double productScore, @Param("comment_num") Integer commentNum);

    @Query("SELECT p FROM Product  p WHERE (:store_id = NULL  OR p.productStoreId = :store_id) AND (:name = ''  OR :name  = NULL OR p.productName LIKE concat('%', :name, '%' )) AND (:type = NULL  OR p.productCategory =:type) AND ((p.productPrice BETWEEN :min_price AND :max_price) OR (:min_price  = NULL AND :max_price = NULL ) OR (:min_price = NULL AND p.productPrice <= :max_price) OR (:max_price  = NULL  AND  p.productPrice >= :min_price))")
    List<Product> findProductByFactors(@Param("store_id") Integer storeId, @Param("name") String name, @Param("type") ProductEnum productEnum, @Param("min_price") Double minPrice, @Param("max_price") Double maxPrice);
}

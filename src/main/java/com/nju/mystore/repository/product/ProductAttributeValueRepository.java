package com.nju.mystore.repository.product;

import com.nju.mystore.po.product.NewProduct;
import com.nju.mystore.po.product.ProductAttributeValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductAttributeValueRepository extends JpaRepository<ProductAttributeValue, Integer> {

    @Query("SELECT pav.product FROM ProductAttributeValue pav " +
            "WHERE pav.productAttribute.productAttributeName = :attributeName AND pav.value = :value")
    List<NewProduct> findProductsByAttribute(@Param("attributeName") String attributeName,
                                             @Param("value") String value);
}

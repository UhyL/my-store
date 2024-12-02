package com.nju.mystore.repository;

import com.nju.mystore.po.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface StoreRepository extends JpaRepository<Store, Integer> {
    Store findByStoreName(String storeName);

    Store findByStoreId(Integer storeId);

    @Modifying
    @Transactional
    @Query("UPDATE Store s SET s.storeScore = :score, s.storeCommentNum = :comment_num WHERE s.storeId = :store_id")
    void updateStoreByComment(@Param("store_id") Integer storeId, @Param("score") Double score, @Param("comment_num") Integer commentNum);

}

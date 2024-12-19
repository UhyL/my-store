package com.nju.mystore.repository;

import com.nju.mystore.po.AddressInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface AddressInfoRepository extends JpaRepository<AddressInfo, Integer> {
    List<AddressInfo> findByUserId(Integer userId);


    @Modifying
    @Transactional
    @Query("DELETE FROM AddressInfo  addressInfo WHERE addressInfo.addressInfoId = :addressInfo_id")
    void deleteByAddressInfoId(@Param("addressInfo_id") Integer addressInfo_id);
}

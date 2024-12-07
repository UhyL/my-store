package com.nju.mystore.repository;

import com.nju.mystore.po.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;


public interface UserRepository extends JpaRepository<User, Integer> {
    User findByPhone(String phone);

    User findByPhoneAndPassword(String phone, String password);

    @Modifying
    @Transactional
    @Query("update User u SET u.address = :address WHERE u.id = :userId")
    void updateUserByAddress(@Param("userId") Integer userId, @Param("address") String address);

}

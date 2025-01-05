package com.balbasio.EticaretJavaCase.repository;

import com.balbasio.EticaretJavaCase.repository.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query("SELECT o.id FROM Order o WHERE o.userId=?1")
    List<Long> findAllOrderIdByUserId(Long userId);
}

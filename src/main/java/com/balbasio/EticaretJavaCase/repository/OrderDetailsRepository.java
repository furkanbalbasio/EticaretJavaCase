package com.balbasio.EticaretJavaCase.repository;

import com.balbasio.EticaretJavaCase.repository.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails,Long> {
}

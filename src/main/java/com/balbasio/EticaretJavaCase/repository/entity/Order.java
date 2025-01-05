package com.balbasio.EticaretJavaCase.repository.entity;

import com.balbasio.EticaretJavaCase.enums.EOrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long userId;
    LocalDate orderDate;
    String orderNo;
    EOrderStatus orderStatus;
    Long addressId;
    double totalPrice;

}

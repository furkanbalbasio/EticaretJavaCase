package com.balbasio.EticaretJavaCase.dto.response;

import com.balbasio.EticaretJavaCase.enums.EOrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetOrderResponseDto {
    String name;
    String surname;
    LocalDate orderDate;
    String orderNo;
    EOrderStatus orderStatus;
    String address;
    String addressType;
    double totalPrice;
}

package com.balbasio.EticaretJavaCase.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddOrderRequestDto {
    List<AddOrderDetailsRequestDto> orderDetailsList;
    Long addressId;
}

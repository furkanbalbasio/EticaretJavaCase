package com.balbasio.EticaretJavaCase.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddOrderDetailsRequestDto {
    Long productId;
    int quantity;
}

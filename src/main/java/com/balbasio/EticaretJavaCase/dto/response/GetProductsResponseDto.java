package com.balbasio.EticaretJavaCase.dto.response;

import com.balbasio.EticaretJavaCase.repository.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetProductsResponseDto {
    List<Product> productList;
}

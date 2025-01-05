package com.balbasio.EticaretJavaCase.dto.response;

import com.balbasio.EticaretJavaCase.enums.ECategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateProductResponseDto {
    String title;
    String description;
    ECategory category;
    double price;
    double rating;
    int stock;
}

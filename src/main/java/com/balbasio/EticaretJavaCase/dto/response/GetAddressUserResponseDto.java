package com.balbasio.EticaretJavaCase.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetAddressUserResponseDto {
    String address;
    String addressType;
}

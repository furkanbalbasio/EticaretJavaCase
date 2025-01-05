package com.balbasio.EticaretJavaCase.dto.response;

import com.balbasio.EticaretJavaCase.enums.EGender;
import com.balbasio.EticaretJavaCase.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileResponseDto {
    String name;
    String surname;
    String email;
    String phone;
    LocalDate birthDate;
    EGender gender;
    boolean isVerified;
}

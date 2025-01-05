package com.balbasio.EticaretJavaCase.dto.request;

import com.balbasio.EticaretJavaCase.enums.EGender;
import com.balbasio.EticaretJavaCase.repository.entity.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateUserProfileRequestDto {
    @NotNull
    String name;
    @NotNull
    String surname;
    @NotNull
    @Email
    String email;
    @NotNull
    String phone;
    @NotNull
    LocalDate birthdate;
    @NotNull
    EGender eGender;
}

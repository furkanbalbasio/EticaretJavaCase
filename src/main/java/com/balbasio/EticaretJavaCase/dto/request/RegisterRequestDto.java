package com.balbasio.EticaretJavaCase.dto.request;

import com.balbasio.EticaretJavaCase.enums.EGender;
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
public class RegisterRequestDto {
    @NotNull
    String name;
    @NotNull
    String surname;
    @Email
    String email;
    @NotNull
    String username;
    @NotBlank
    @Pattern(message = "Lütfen şifre kurallarına uyunuz",
            regexp = "^(?=.*[A-Za-z])(?=.*\\d).+$")
    String password;
    @NotNull
    String rePassword;
    String phone;
    LocalDate birthdate;
    EGender eGender;

}

package com.balbasio.EticaretJavaCase.repository.entity;

import com.balbasio.EticaretJavaCase.enums.EGender;
import com.balbasio.EticaretJavaCase.enums.ERole;
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
@Table(name = "tbl_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String surname;
    String email;
    String username;
    String password;
    String phone;
    Long addressId;
    LocalDate birthDate;
    ERole userRole;
    EGender gender;
    boolean isVerified;
}

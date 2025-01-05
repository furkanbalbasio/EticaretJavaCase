package com.balbasio.EticaretJavaCase.controller;

import com.balbasio.EticaretJavaCase.dto.request.*;
import com.balbasio.EticaretJavaCase.dto.response.*;
import com.balbasio.EticaretJavaCase.repository.entity.User;
import com.balbasio.EticaretJavaCase.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.balbasio.EticaretJavaCase.constant.RestApiUrls.*;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping(REGISTER)
    public ResponseEntity<BaseResponseDto<RegisterResponseDto>> register(@RequestBody @Valid RegisterRequestDto dto){
        userService.register(dto);
        return ResponseEntity.ok(BaseResponseDto.<RegisterResponseDto>builder()
                .responseCode(200)
                .data(RegisterResponseDto.builder()
                        .isRegister(true)
                        .message("Üyelik Başarı ile gerçekleşti")
                        .build())
                .build());
    }

    @PostMapping(LOGIN)
    public ResponseEntity<BaseResponseDto<LoginResponseDto>> login(@RequestBody @Valid LoginRequestDto dto){
        String token = userService.login(dto);
        return ResponseEntity.ok(BaseResponseDto.<LoginResponseDto>builder()
                .responseCode(200)
                .data(LoginResponseDto.builder()
                        .isLogin(true)
                        .token(token)
                        .build())
                .build());
    }
    @GetMapping(GETUSERPROFILE)
    public ResponseEntity<UserProfileResponseDto> getUserProfile(String token){
        return ResponseEntity.ok(userService.getUserProfileByToken(token));
    }
    @PutMapping(UPDATEUSERPROFILE)
    public ResponseEntity<Void> updateUserProfile(String token, @RequestBody @Valid UpdateUserProfileRequestDto dto){
        userService.updateUserProfile(token,dto);
        return ResponseEntity.ok().build();
    }
    @PostMapping(ADDADDRESS)
    public ResponseEntity<Void> addAddress(String token, @RequestBody @Valid AddAddressRequestDto dto){
        userService.addAddress(token, dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/admin/add-seller/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> addSeller(String token, @PathVariable Long userId){
        userService.addSellerUser(token, userId);
        return ResponseEntity.ok().build();
    }

//    @GetMapping(GETADDRESS)
//    public ResponseEntity<GetAddressUserResponseDto> getAddress(String token){
//      return ResponseEntity.ok(userService.getAddress(token));
//    }
}

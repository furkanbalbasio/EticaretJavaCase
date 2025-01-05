package com.balbasio.EticaretJavaCase.service;

import com.balbasio.EticaretJavaCase.dto.request.*;
import com.balbasio.EticaretJavaCase.dto.response.UserProfileResponseDto;
import com.balbasio.EticaretJavaCase.enums.ERole;
import com.balbasio.EticaretJavaCase.exception.ErrorType;
import com.balbasio.EticaretJavaCase.exception.EticaretJavaCaseException;
import com.balbasio.EticaretJavaCase.repository.AddressRepository;
import com.balbasio.EticaretJavaCase.repository.UserRepository;
import com.balbasio.EticaretJavaCase.repository.entity.Address;
import com.balbasio.EticaretJavaCase.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AddressRepository addressRepository;
    public String login(LoginRequestDto dto) {
        Optional<User> user = userRepository.findOptionalByUsernameAndPassword(dto.getUsername(),dto.getPassword());
        if(user.isEmpty()) throw new EticaretJavaCaseException(ErrorType.KULLANICI_ADI_VEYA_SIFRE_HATALI);
        Optional<String> jwtToken = jwtService.createToken(user.get().getId());
        if(jwtToken.isEmpty())
            throw new EticaretJavaCaseException(ErrorType.TOKEN_HATASI);
        return jwtToken.get();
    }

    public void register(RegisterRequestDto dto) {

        User user = User.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .name(dto.getName())
                .phone(dto.getPhone())
                .gender(dto.getEGender())
                .birthDate(dto.getBirthdate())
                .surname(dto.getSurname())
                .userRole(ERole.USER)
                .build();
        userRepository.save(user);

    }
    public UserProfileResponseDto getUserProfileByToken(String token) {
        Optional<User> user = tokenConvertUser(token);

        UserProfileResponseDto userProfileResponseDto = UserProfileResponseDto.builder()
                .name(user.get().getName())
                .phone(user.get().getPhone())
                .email(user.get().getEmail())
                .gender(user.get().getGender())
                .surname(user.get().getSurname())
                .birthDate(user.get().getBirthDate())
                .isVerified(user.get().isVerified())
                .build();
        return userProfileResponseDto;
    }

    public void updateUserProfile(String token, UpdateUserProfileRequestDto dto) {
        Optional<User> user = tokenConvertUser(token);

        user.get().setGender(dto.getEGender());
        user.get().setEmail(dto.getEmail());
        user.get().setBirthDate(dto.getBirthdate());
        user.get().setName(dto.getName());
        user.get().setSurname(dto.getSurname());
        user.get().setPhone(dto.getPhone());
        userRepository.save(user.get());
    }
    public Optional<User> tokenConvertUser(String token){
        Optional<Long> userId = jwtService.getIdByToken(token);
        if (userId.isEmpty()){
            throw new EticaretJavaCaseException(ErrorType.TOKEN_HATASI);
        }
        Optional<User> user = userRepository.findOptionalById(userId.get());
        return user;
    }
    public void addAddress(String token, AddAddressRequestDto dto) {
        Optional<User> user = tokenConvertUser(token);
        Address address = Address.builder()
                .address(dto.getAddress())
                .addressType(dto.getAddressType())
                .build();
        addressRepository.save(address);
        user.get().setAddressId(address.getId());
    }

    public void addSellerUser(String token, Long userId){
        Optional<User> user = tokenConvertUser(token);
        if (user.get().getUserRole() != ERole.ADMIN){
            throw new EticaretJavaCaseException(ErrorType.KULLANICI_YETKİ_HATASI);
        }
       Optional<User> sellerUser = userRepository.findById(userId);
        sellerUser.get().setUserRole(ERole.SELLER);
        userRepository.save(sellerUser.get());
    }
}

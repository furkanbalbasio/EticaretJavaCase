package com.balbasio.EticaretJavaCase.utility;

import com.balbasio.EticaretJavaCase.enums.ECategory;
import com.balbasio.EticaretJavaCase.enums.ERole;
import com.balbasio.EticaretJavaCase.repository.ProductRepository;
import com.balbasio.EticaretJavaCase.repository.UserRepository;
import com.balbasio.EticaretJavaCase.repository.entity.Product;
import com.balbasio.EticaretJavaCase.repository.entity.User;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class GenerateData {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @PostConstruct
    public void generateData() {
            User admin = User.builder()
                    .isVerified(true)
                    .username("admin")
                    .name("admin")
                    .surname("admin")
                    .email("admin@gmail.com")
                    .password("admin123.")
                    .userRole(ERole.ADMIN)
                    .build();
            userRepository.save(admin);

        Product product1 = Product.builder().title("Laptop").description("Dizüstü").stock(1000).price(15000.0).category(ECategory.ELEKTRONIK).build();
        Product product2 = Product.builder().title("Pantolon").description("Mavi renk").stock(1000).price(1250.0).category(ECategory.KIYAFET).build();
        Product product3 = Product.builder().title("Kazak").description("Kırmızı renk").stock(500).price(650.0).category(ECategory.KIYAFET).build();
        productRepository.saveAll(List.of(product1, product2,product3));

    }
}
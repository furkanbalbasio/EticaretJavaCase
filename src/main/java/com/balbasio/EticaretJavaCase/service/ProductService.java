package com.balbasio.EticaretJavaCase.service;

import com.balbasio.EticaretJavaCase.dto.request.AddProductRequestDto;
import com.balbasio.EticaretJavaCase.dto.response.AllProductResponseDto;
import com.balbasio.EticaretJavaCase.dto.response.GetProductResponseDto;
import com.balbasio.EticaretJavaCase.dto.response.ListProductByCategoryResponseDto;
import com.balbasio.EticaretJavaCase.dto.response.UpdateProductResponseDto;
import com.balbasio.EticaretJavaCase.enums.ECategory;
import com.balbasio.EticaretJavaCase.exception.ErrorType;
import com.balbasio.EticaretJavaCase.exception.EticaretJavaCaseException;
import com.balbasio.EticaretJavaCase.repository.ProductRepository;
import com.balbasio.EticaretJavaCase.repository.UserRepository;
import com.balbasio.EticaretJavaCase.repository.entity.Product;
import com.balbasio.EticaretJavaCase.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final CacheManager cacheManager;
    public AllProductResponseDto findAllProducts(int page, int size) {
        page -=1;
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productsPage =  productRepository.findAll(pageable);
        if (page >= productsPage.getTotalPages()) {
            return new AllProductResponseDto(
                    List.of(),
                    page + 1
            );
        }
        Objects.requireNonNull(cacheManager.getCache("productfindall")).clear();

        return new AllProductResponseDto(productsPage.getContent(),productsPage.getNumber()+1);
    }
    public Optional<User> tokenConvertUser(String token){
        Optional<Long> userId = jwtService.getIdByToken(token);
        if (userId.isEmpty()){
            throw new EticaretJavaCaseException(ErrorType.TOKEN_ERROR);
        }
        Optional<User> user = userRepository.findOptionalById(userId.get());
        return user;
    }
    public void addProduct(String token, AddProductRequestDto dto) {
       Optional<User> user = tokenConvertUser(token);
       Product product = Product.builder()
               .price(dto.getPrice())
               .stock(dto.getStock())
               .title(dto.getTitle())
               .category(dto.getCategory())
               .description(dto.getDescription())
               .rating(dto.getRating())
               .sellerId(user.get().getId())
               .status(true)
               .build();
       if (product.getStock() == 0){
           product.setStatus(false);
       }
       productRepository.save(product);

    }

    public GetProductResponseDto getProduct(Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        GetProductResponseDto getProductByIdDto = GetProductResponseDto.builder()
                .category(product.get().getCategory())
                .description(product.get().getDescription())
                .price(product.get().getPrice())
                .rating(product.get().getRating())
                .sellerId(product.get().getSellerId())
                .status(product.get().isStatus())
                .stock(product.get().getStock())
                .title(product.get().getTitle())
                .build();
        return getProductByIdDto;
    }

    public void updateProduct(String token, Long productId, UpdateProductResponseDto dto) {
       Optional<User> user = tokenConvertUser(token);
        productRepository.findById(productId);
        Optional<Product> product = productRepository.findById(productId);

        product = Optional.ofNullable(Product.builder()
                .stock(dto.getStock())
                .price(dto.getStock())
                .title(dto.getTitle())
                .category(dto.getCategory())
                .rating(dto.getRating())
                .description(dto.getDescription())
                .status(true)
                .build());
        if (dto.getStock() == 0){
            product.get().setStatus(false);
        }
        if (dto.getStock() < 0){
            throw new EticaretJavaCaseException(ErrorType.STOK_TUKENDI);
        }
        if (product.get().getSellerId() != user.get().getId()){
            throw new EticaretJavaCaseException(ErrorType.KULLANICI_YETKİ_HATASI);
        }

        productRepository.save(product.get());
     }

    public void deleteProduct(String token, Long productId) {
        Optional<User> user = tokenConvertUser(token);
        Optional<Product> product = productRepository.findById(productId);
        productRepository.delete(product.get());
        if (product.get().getSellerId() != user.get().getId()){
            throw new EticaretJavaCaseException(ErrorType.KULLANICI_YETKİ_HATASI);
        }
    }

    public ListProductByCategoryResponseDto listProductByCategory(ECategory category) {
        List<Product> productListByCategory = productRepository.findAllByCategory(category);
        ListProductByCategoryResponseDto dto = ListProductByCategoryResponseDto.builder()
                .productList(productListByCategory)
                .message("belirlenen kategoriye göre ürünler listelenmiştir.")
                .build();
        return dto;
    }

    public void updateProductStock(String token, Long productId, int stock) {
        Optional<User> user = tokenConvertUser(token);
        Optional<Product> product = productRepository.findById(productId);
        if (user.get().getId() != product.get().getSellerId()){
            throw new EticaretJavaCaseException(ErrorType.FARKLI_URUN_HATASI);
        }
        if (product.get().getSellerId() != user.get().getId()){
            throw new EticaretJavaCaseException(ErrorType.KULLANICI_YETKİ_HATASI);
        }
        product.get().setStock(stock);
        productRepository.save(product.get());
    }
}

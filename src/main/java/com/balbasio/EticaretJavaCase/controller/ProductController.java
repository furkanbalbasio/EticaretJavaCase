package com.balbasio.EticaretJavaCase.controller;

import com.balbasio.EticaretJavaCase.dto.request.AddProductRequestDto;
import com.balbasio.EticaretJavaCase.dto.response.*;
import com.balbasio.EticaretJavaCase.enums.ECategory;
import com.balbasio.EticaretJavaCase.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.balbasio.EticaretJavaCase.constant.RestApiUrls.*;

@RestController
@RequestMapping(PRODUCT)
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping(GETALLPRODUCT)
    public ResponseEntity<BaseResponseDto<AllProductResponseDto>> getAllProducts(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int size) {

        AllProductResponseDto response = productService.findAllProducts(page, size);
        return ResponseEntity.ok(BaseResponseDto.<AllProductResponseDto>builder()
                .data(response)
                .build());
    }


    @GetMapping("/get-product-by-id/{productId}")
    public ResponseEntity<GetProductResponseDto> getProduct(@PathVariable Long productId){
        return ResponseEntity.ok(productService.getProduct(productId));
    }

    @PostMapping(ADDPRODUCT)
    @PreAuthorize("hasAnyAuthority('SELLER','ADMIN')")
    public ResponseEntity<Void> addProduct(String token, @RequestBody AddProductRequestDto dto){
        productService.addProduct(token,dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update-product-by-id/{productId}")
    @PreAuthorize("hasAnyAuthority('SELLER','ADMIN')")
    public ResponseEntity<Void> updateProduct(String token, @PathVariable Long productId, @RequestBody @Valid UpdateProductResponseDto dto){
        productService.updateProduct(token, productId, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete-product-by-id/{productId}")
    @PreAuthorize("hasAnyAuthority('SELLER','ADMIN')")
    public ResponseEntity<Void> deleteProduct(String token, @PathVariable Long productId){
        productService.deleteProduct(token, productId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list-product-by-category/{category}")
    public ResponseEntity<ListProductByCategoryResponseDto> listProductByCategory(@PathVariable ECategory category){
        return ResponseEntity.ok(productService.listProductByCategory(category));
    }

    @PutMapping("/update-product-stock/{productId}")
    @PreAuthorize("hasAnyAuthority('SELLER','ADMIN')")
    public ResponseEntity<Void> updateProductStock(String token, @PathVariable Long productId, int stock){
        productService.updateProductStock(token, productId, stock);
        return ResponseEntity.ok().build();
    }
}

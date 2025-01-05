package com.balbasio.EticaretJavaCase.repository;

import com.balbasio.EticaretJavaCase.enums.ECategory;
import com.balbasio.EticaretJavaCase.repository.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findAllByCategory(ECategory category);
}

package com.balbasio.EticaretJavaCase.repository.entity;

import com.balbasio.EticaretJavaCase.enums.ECategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;
    String description;
    ECategory category;
    double price;
    double rating;
    int stock;
    Long sellerId;
    boolean status;
}

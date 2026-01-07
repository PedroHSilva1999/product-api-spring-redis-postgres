package com.products.ProductsAPI.dto;

import com.products.ProductsAPI.domain.entity.ProductModel;

import java.time.LocalDateTime;

public class ProductMapper {

    public static ProductModel toEntity(ProductRequestDTO dto){
        ProductModel productModel = new ProductModel();

        productModel.setName(dto.getName());
        productModel.setPrice(dto.getPrice());
        productModel.setQuantity(dto.getQuantity());

        productModel.setCreatedAt(LocalDateTime.now());
        return productModel;
    }

    public static ProductResponseDTO toDTO(ProductModel entity){
        return new ProductResponseDTO(
                entity.getId(),
                entity.getName(),
                entity.getQuantity(),
                entity.getPrice()
        );
    }
}

package com.products.ProductsAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ProductResponseDTO {

    private String id;

    private String name;

    private Integer quantity;

    private Double price;

}

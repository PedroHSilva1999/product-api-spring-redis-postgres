package com.products.ProductsAPI.repository;

import com.products.ProductsAPI.domain.entity.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductModel,String> {
}

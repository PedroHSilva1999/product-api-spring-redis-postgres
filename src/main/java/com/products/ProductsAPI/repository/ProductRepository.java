package com.products.ProductsAPI.repository;

import com.products.ProductsAPI.domain.entity.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductModel,String> {
    List<ProductModel> findByName(String name);
    List<ProductModel> findByIdAndName(String id,String name);
}

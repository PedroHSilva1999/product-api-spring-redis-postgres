package com.products.ProductsAPI.service;

import com.products.ProductsAPI.domain.entity.ProductModel;
import com.products.ProductsAPI.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public ProductModel save(ProductModel productModel){
        return productRepository.save(productModel);
    }
    public List<ProductModel> listAll(){
        return productRepository.findAll();
    }
}

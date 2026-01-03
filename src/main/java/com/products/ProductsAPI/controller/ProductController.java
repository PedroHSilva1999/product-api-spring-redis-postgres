package com.products.ProductsAPI.controller;

import com.products.ProductsAPI.domain.entity.ProductModel;
import com.products.ProductsAPI.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ProductModel save(@RequestBody ProductModel productModel){
        return productService.save(productModel);
    }

    @GetMapping
    public List<ProductModel> listAll(){
        return productService.listAll();
    }

}

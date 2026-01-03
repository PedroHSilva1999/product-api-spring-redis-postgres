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

    @GetMapping("/search")
    public List<ProductModel> search(@RequestParam(name = "id",required = false) String id,
            @RequestParam(name = "name",required = false) String name){

        return productService.search(id,name);
    }

    @PutMapping("{id}")
    public ProductModel update(@PathVariable String id,
                               @RequestBody ProductModel productModel){
        return productService.update(id, productModel);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        productService.delete(id);
    }

}

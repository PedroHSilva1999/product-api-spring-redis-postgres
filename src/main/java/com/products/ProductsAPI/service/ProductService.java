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

    public List<ProductModel> search(String id, String name){

        if(id != null && name != null){
            return productRepository.findByIdAndName(id,name );
        }
        if (id != null){
            return productRepository.findById(id).stream().toList();
        }
        if (name != null){
            return productRepository.findByName(name);
        }
        return null;



    }

    public ProductModel update(String id, ProductModel productModel){
        productModel.setId(id);
        return productRepository.save(productModel);
    }

    public void delete(String id){
        productRepository.delete(productRepository.getReferenceById(id));
    }

}

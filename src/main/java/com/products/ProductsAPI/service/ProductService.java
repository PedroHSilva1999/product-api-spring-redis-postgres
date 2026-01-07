package com.products.ProductsAPI.service;

import com.products.ProductsAPI.domain.entity.ProductModel;
import com.products.ProductsAPI.dto.ProductMapper;
import com.products.ProductsAPI.dto.ProductRequestDTO;
import com.products.ProductsAPI.dto.ProductResponseDTO;
import com.products.ProductsAPI.repository.ProductRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public ProductResponseDTO save(ProductRequestDTO productRequestDTO){
        ProductModel productModel = ProductMapper.toEntity(productRequestDTO);
        ProductModel saved = productRepository.save(productModel);
        return ProductMapper.toDTO(saved);

    }

    @Cacheable(value = "Allproducts")
    public List<ProductResponseDTO> listAll(){
        return productRepository.findAll().stream().map(ProductMapper::toDTO).toList();
    }

    @Cacheable(
            value = "products",
            key = "'id=' + #id + ':name=' + #name"
    )
    public List<ProductResponseDTO> search(String id, String name){

        if(id != null && name != null){
            return productRepository.findByIdAndName(id,name).stream().map(ProductMapper::toDTO).toList();
        }
        if (id != null){
            return productRepository.findById(id).stream().map(ProductMapper::toDTO).toList();
        }
        if (name != null){
            return productRepository.findByName(name).stream().map(ProductMapper::toDTO).toList();
        }
        return null;



    }

    public ProductResponseDTO update(String id, ProductModel productModel){
        productModel.setId(id);
        var saved = productRepository.save(productModel);
        return ProductMapper.toDTO(saved);
    }

    public void delete(String id){
        productRepository.delete(productRepository.getReferenceById(id));
    }

}

package com.ninos.service.impl;

import com.ninos.entity.Product;
import com.ninos.mapper.ProductMapper;
import com.ninos.model.ProductResponse;
import com.ninos.repository.ProductRepository;
import com.ninos.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;


    @Override
    public ProductResponse getProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("product with given id not exist"));
        return productMapper.productEntityToDto(product);
    }

    @Override
    public Page<ProductResponse> getAllProducts(Pageable pageable) {
        Page<Product> productPage = productRepository.findAll(pageable);
        return productPage.map(product -> productMapper.productEntityToDto(product));
    }


//    @Override
//    public List<ProductResponse> getAllProducts() {
//        List<Product> products = productRepository.findAll();
//        return products.stream().map(product -> productMapper.productEntityToDto(product))
//                .collect(Collectors.toList());
//    }





}

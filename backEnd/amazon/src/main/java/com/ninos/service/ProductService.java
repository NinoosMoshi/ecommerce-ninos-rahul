package com.ninos.service;

import com.ninos.model.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    ProductResponse getProductById(Long productId);

//    List<ProductResponse> getAllProducts();
    Page<ProductResponse> getAllProducts(Pageable pageable);

    List<ProductResponse> searchProductByName(String keyword);

    List<ProductResponse> searchProductsByBrand(Long brandId);

    List<ProductResponse> searchProductsByType(Long typeId);

    List<ProductResponse> searchProductByBrandAndType(Long brandId, Long typeId);

    List<ProductResponse> searchProductByBrandTypeAndName(Long brandId, Long typeId, String keyword);


}

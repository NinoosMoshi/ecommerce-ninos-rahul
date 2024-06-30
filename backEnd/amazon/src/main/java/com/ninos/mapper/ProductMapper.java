package com.ninos.mapper;

import com.ninos.entity.Product;
import com.ninos.model.ProductResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    public ProductResponse productEntityToDto(Product product){
        ProductResponse productResponse = new ProductResponse();
        productResponse.setProductBrand(product.getBrand().getName());
        productResponse.setProductType(product.getType().getName());
        BeanUtils.copyProperties(product,productResponse);
        return productResponse;
    }


    public Product productDtoToEntity(ProductResponse productResponse){
        Product product = new Product();
        BeanUtils.copyProperties(productResponse,product);
        return product;
    }


}

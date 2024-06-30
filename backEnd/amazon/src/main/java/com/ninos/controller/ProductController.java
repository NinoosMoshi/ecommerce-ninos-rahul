package com.ninos.controller;

import com.ninos.model.BrandResponse;
import com.ninos.model.ProductResponse;
import com.ninos.model.TypeResponse;
import com.ninos.service.BrandService;
import com.ninos.service.ProductService;
import com.ninos.service.TypeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    private final BrandService brandService;
    private final TypeService typeService;


    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") Long productId){
        ProductResponse productById = productService.getProductById(productId);
        return new ResponseEntity<>(productById, HttpStatus.OK);
    }


//    @GetMapping()
//    public ResponseEntity<List<ProductResponse>> getAllProducts(){
//        List<ProductResponse> allProducts = productService.getAllProducts();
//        return ResponseEntity.ok(allProducts);
//    }
    @GetMapping()
    public ResponseEntity<Page<ProductResponse>> getAllProducts(@PageableDefault(size = 10)Pageable pageable){
        Page<ProductResponse> productResponsePage = productService.getAllProducts(pageable);
        return ResponseEntity.ok(productResponsePage);
    }

    @GetMapping("/brands")
    public ResponseEntity<List<BrandResponse>> getAllBrands(){
        List<BrandResponse> allBrands = brandService.getAllBrands();
        return ResponseEntity.ok(allBrands);
    }


    @GetMapping("/types")
    public ResponseEntity<List<TypeResponse>> getAllTypes(){
        List<TypeResponse> allTypes = typeService.getAllTypes();
        return ResponseEntity.ok(allTypes);
    }




}

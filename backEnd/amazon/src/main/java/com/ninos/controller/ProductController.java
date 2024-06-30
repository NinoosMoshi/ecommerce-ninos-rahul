package com.ninos.controller;

import com.ninos.model.BrandResponse;
import com.ninos.model.ProductResponse;
import com.ninos.model.TypeResponse;
import com.ninos.service.BrandService;
import com.ninos.service.ProductService;
import com.ninos.service.TypeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    //  *** get all products without pageable ****
    //localhost:8080/api/products
//    @GetMapping()
//    public ResponseEntity<List<ProductResponse>> getAllProducts(){
//        List<ProductResponse> allProducts = productService.getAllProducts();
//        return ResponseEntity.ok(allProducts);
//    }

    //  *** get all products with pageable ****
    //localhost:8080/api/products?page=1&size=10
//    @GetMapping()
//    public ResponseEntity<Page<ProductResponse>> getAllProducts(@PageableDefault(size = 10)Pageable pageable){
//        Page<ProductResponse> productResponsePage = productService.getAllProducts(pageable);
//        return ResponseEntity.ok(productResponsePage);
//    }

    //  *** get all products with pageable and search keyword ****
    //localhost:8080/api/products
    //localhost:8080/api/products/search?keyword=gel
//    @GetMapping()
//    public ResponseEntity<Page<ProductResponse>> getAllProducts(
//            @PageableDefault(size = 10)Pageable pageable,
//            @RequestParam(name = "keyword", required = false) String keyword){
//
//        Page<ProductResponse> productResponsePage;
//        if(keyword != null && !keyword.isEmpty()){
//            List<ProductResponse> productResponses = productService.searchProductByName(keyword);
//            productResponsePage = new PageImpl<>(productResponses,pageable,productResponses.size());
//        }else {
//            productResponsePage = productService.getAllProducts(pageable);
//        }
//       return new ResponseEntity<>(productResponsePage,HttpStatus.OK);
//    }


    //  *** get all products with (pageable, search keyword and sort) ****
    // localhost:8080/api/products?sort=name&order=desc
    @GetMapping()
    public ResponseEntity<Page<ProductResponse>> getAllProducts(
            @PageableDefault(size = 10)Pageable pageable,
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "sort", defaultValue = "name") String sort,
            @RequestParam(name = "order", defaultValue = "asc") String order
            ){

        Page<ProductResponse> productResponsePage;
        if(keyword != null && !keyword.isEmpty()){
            List<ProductResponse> productResponses = productService.searchProductByName(keyword);
            productResponsePage = new PageImpl<>(productResponses,pageable,productResponses.size());
        }else {
            // there is no search criteria, then retrieve base on sort
            Sort.Direction direction = "asc".equalsIgnoreCase(order) ? Sort.Direction.ASC: Sort.Direction.DESC;
            Sort sorting = Sort.by(direction,sort);
            productResponsePage = productService.getAllProducts(PageRequest.of(pageable.getPageNumber(),
                                                                               pageable.getPageSize(),
                                                                               sorting));
        }
        return new ResponseEntity<>(productResponsePage,HttpStatus.OK);
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


    @GetMapping("/search")
    public ResponseEntity<List<ProductResponse>> searchProduct(@RequestParam("keyword") String keyword){
        List<ProductResponse> productResponses = productService.searchProductByName(keyword);
        return new ResponseEntity<>(productResponses,HttpStatus.OK);
    }




}

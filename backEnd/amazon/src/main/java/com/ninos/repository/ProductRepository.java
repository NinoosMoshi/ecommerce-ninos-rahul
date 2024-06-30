package com.ninos.repository;

import com.ninos.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

//    List<Product> findProductByName(String keyword);

    @Query("SELECT p FROM Product p WHERE p.name LIKE %:keyword%")
    List<Product> searchProductByName(String keyword);


}

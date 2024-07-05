package com.ninos.repository;

import com.ninos.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

//    List<Product> findProductByName(String keyword);

    @Query("SELECT p FROM Product p WHERE p.name LIKE %:keyword%")
    List<Product> searchProductByName(String keyword);

    @Query("SELECT p FROM Product p WHERE p.brand.id = :brandId")
    List<Product> searchByBrand(@Param("brandId") Long brandId);

    @Query("SELECT p FROM Product p WHERE p.type.id = :typeId")
    List<Product> searchByType(@Param("typeId") Long typeId);

    @Query("SELECT p FROM Product p WHERE p.brand.id=:brandId AND p.type.id=:typeId")
    List<Product> searchProductByBrandAndType(@Param("brandId") Long brandId, @Param("typeId") Long typeId);


    @Query("SELECT p FROM Product p WHERE p.brand.id=:brandId AND p.type.id=:typeId AND p.name LIKE %:keyword%")
    List<Product> searchProductByBrandTypeAndName(@Param("brandId") Long brandId,
                                                  @Param("typeId") Long typeId,
                                                  @Param("keyword") String keyword);

}

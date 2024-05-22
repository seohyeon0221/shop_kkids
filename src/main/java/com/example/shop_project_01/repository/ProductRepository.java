package com.example.shop_project_01.repository;

import com.example.shop_project_01.dto.ProductDto;
import com.example.shop_project_01.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByOrderByUploadDateDesc(Pageable pageable);

    List<Product> findAllByOrderByProductPriceAsc();
    List<Product> findAllByOrderByProductPriceDesc();
    
    List<Product> findAllByProductNameLike(String search);
}

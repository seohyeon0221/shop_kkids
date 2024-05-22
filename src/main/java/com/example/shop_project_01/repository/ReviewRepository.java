package com.example.shop_project_01.repository;

import com.example.shop_project_01.entity.Notice;
import com.example.shop_project_01.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findByProductProductId(Long productId);

    boolean existsByProductProductIdAndUserAccountUsername(Long productId, String username);
}

package com.example.shop_project_01.service;

import com.example.shop_project_01.dto.ReviewDto;
import com.example.shop_project_01.entity.Product;
import com.example.shop_project_01.entity.Review;
import com.example.shop_project_01.entity.UserAccount;
import com.example.shop_project_01.repository.ReviewRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    EntityManager em;
    @Autowired
    UserService userService;
    public void addReview(ReviewDto dto) {
        String username = userService.loginUsername();
        UserAccount userAccount = em.find(UserAccount.class,username);
        Product product = em.find(Product.class,dto.getProductId());

        String content = dto.getContent();

        Review review = new Review();
        review.setContent(content);
        review.setProduct(product);
        review.setUserAccount(userAccount);

        reviewRepository.save(review);
    }


    public boolean check(Long productId, String username) {
        return reviewRepository.existsByProductProductIdAndUserAccountUsername(productId, username);
    }

    public List<ReviewDto> getReviewsByProductId(Long productId) {
        return reviewRepository.findByProductProductId(productId).stream().map(x->ReviewDto.fromReviewEntity(x)).toList();
    }
}

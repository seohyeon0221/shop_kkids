package com.example.shop_project_01.dto;

import com.example.shop_project_01.entity.Notice;
import com.example.shop_project_01.entity.Product;
import com.example.shop_project_01.entity.Review;
import com.example.shop_project_01.entity.UserAccount;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Builder
public class ReviewDto {

    private Long reviewId;

    @NotEmpty(message = "리뷰 내용을 입력해주세요.")
    private String content;

    private LocalDate createdAt;
    private LocalDate updatedAt;

    private String username;
    private Long productId;


    public static ReviewDto fromReviewEntity(Review review){
        return new ReviewDto(
                review.getReviewId(),
                review.getContent(),
                review.getCreatedAt(),
                review.getUpdatedAt(),
                review.getUserAccount().getUsername(),
                review.getProduct().getProductId()
        );
    }

    public static Review fromReviewDto(ReviewDto dto){
        Review review = new Review();
        review.setReviewId(dto.getReviewId());
        review.setContent(dto.getContent());
        review.setCreatedAt(dto.getCreatedAt());
        review.setUpdatedAt(dto.getUpdatedAt());
        review.getUserAccount().setUsername(dto.getUsername());
        review.getProduct().setProductId(dto.getProductId());
        return review;
    }
}

package com.example.shop_project_01.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
//리뷰작성 엔티티
public class Review {

    //리뷰 번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long reviewId;

    private String content;

    @Column(name = "created_at",updatable = false)
    @CreatedDate
    private LocalDate createdAt;

    @Column(name = "updated_at",insertable = false)
    @LastModifiedDate
    private LocalDate updatedAt;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

//    @ManyToMany
//    @JoinColumn(name = "buyId",nullable = false)
//    private Buy buy;

    @ManyToOne
    @JoinColumn(name = "username", nullable = false)
    private UserAccount userAccount;
}

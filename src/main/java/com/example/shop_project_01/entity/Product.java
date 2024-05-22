package com.example.shop_project_01.entity;

import com.example.shop_project_01.constant.ProductSale;
import com.example.shop_project_01.constant.ProductStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
//상품정보
public class Product {
    
    //상품 번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;
    
    //상품 이름
    @Column(name = "product_name")
    private String productName;
    
    //상품 가격
    @Column(name = "product_price")
    private int productPrice;
    
    //상품 재고
    @Column(name = "product_stock")
    private int productStock;
    
    private String mainCategoryName;
    
    private String subCategoryName;
    //게시판
//    private String content;
    private String contentImgName;
    
    private String contentImgPath;
    
    private String imgName;
    
    private String imgPath;
    
    @Enumerated(EnumType.STRING)
    private ProductSale productSale;
    
    @CreatedDate
    @Column(name = "upload_date",updatable = false)
    private LocalDateTime uploadDate;
}

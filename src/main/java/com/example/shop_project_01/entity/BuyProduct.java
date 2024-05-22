package com.example.shop_project_01.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
// 소비자용
public class BuyProduct {
// 구매 번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "buy_product_id")
    private Long buyProductId;
    
    //구매 수량
    private int count;
    
    //구매가격
    @Column(name = "1ea_price")
    private int price;
    
    //상품 번호
//    @ManyToOne
//    @JoinColumn(name = "product_id")
    private Long productId;
    
    //구매번호
    //joinColumn - buy
    @ManyToOne
    @JoinColumn (name = "buy_id")
    private Buy buy;
    
    private int totalPrice;
    
}

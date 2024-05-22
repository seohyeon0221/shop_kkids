package com.example.shop_project_01.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
//장바구니 목록
public class CartProduct {
    
    //장바구니 아이템 번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_product_id")
    private Long cartProductId;
    
    // 구매수량
    private int count;

     //상품 번호
     @ManyToOne(fetch = FetchType.EAGER)
     @JoinColumn(name = "product_id")
     private Product product;

//    //구매당시의 상품 가격 ( 조인x _ 할인할때 가격을 알기위함 ) -> buy에만 필요
//    private int productPrice;

         //유저의 장바구니 번호
     @ManyToOne(fetch = FetchType.EAGER)
     @JoinColumn(name = "cart_id")
     private Cart cart;


}

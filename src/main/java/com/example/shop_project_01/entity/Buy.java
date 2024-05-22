package com.example.shop_project_01.entity;

import com.example.shop_project_01.constant.ProductStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
//주문 리스트
// 관리자용
public class Buy {
    
    // order ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "buy_id")
    private Long buyId;
    
    //구매 날짜
    @CreatedDate
    @Column(name = "buy_date",updatable = false)
    private LocalDateTime buyDate;
    
    //구매한 유저 아이디 ( joinColumn - UserAccount )
//    @ManyToOne
//    @JoinColumn(name = "username")
    private String username;
    
    //구매상태
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;
    
    @OneToMany(mappedBy = "buy")
    private List<BuyProduct> buyProducts = new ArrayList<>();
}

package com.example.shop_project_01.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
//유저당 배정된 장바구니
public class Cart {
    //장바구니 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long cartId;

    //배정된 유저아이디
    @OneToOne
    @JoinColumn(name = "username", referencedColumnName = "username", nullable = false)
    private UserAccount userAccount;
    
    @OneToMany(mappedBy = "cart",cascade = CascadeType.REMOVE)
    private List<CartProduct> cartProducts = new ArrayList<>();

}

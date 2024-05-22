package com.example.shop_project_01.dto;

import com.example.shop_project_01.entity.Cart;
import com.example.shop_project_01.entity.UserAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartDto {
    private Long cartId;

    //배정된 유저아이디
    private UserAccount userAccount;

    private String username;
    
    public CartDto(Long cartId, UserAccount userAccount) {
        this.cartId = cartId;
        this.userAccount = userAccount;
    }
}

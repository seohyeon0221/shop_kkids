package com.example.shop_project_01.repository;

import com.example.shop_project_01.dto.CartDto;
import com.example.shop_project_01.entity.Cart;
import com.example.shop_project_01.entity.Product;
import com.example.shop_project_01.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
// <<<<<<< sh2
//     Cart findByUserAccount(UserAccount userAccount);
// =======

    Cart findByUserAccount_Username(String username);

}

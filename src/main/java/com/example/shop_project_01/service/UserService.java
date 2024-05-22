package com.example.shop_project_01.service;

import com.example.shop_project_01.constant.UserRole;
import com.example.shop_project_01.dto.UserAccountDto;
import com.example.shop_project_01.entity.Cart;
import com.example.shop_project_01.entity.UserAccount;
import com.example.shop_project_01.repository.CartRepository;
import com.example.shop_project_01.repository.UserAccountRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {


       private final UserAccountRepository userAccountRepository;
       private final CartRepository cartRepository; // CartRepository 추가

       public UserService(UserAccountRepository userAccountRepository, CartRepository cartRepository) {
              this.userAccountRepository = userAccountRepository;
              this.cartRepository = cartRepository;
       }
       @Autowired
       PasswordEncoder passwordEncoder;

       @Autowired
       EntityManager em;
       
       @Transactional
       public void createUser(UserAccountDto dto) {
              UserAccount account = new UserAccount();
              account.setUsername(dto.getUsername());
              account.setPassword(passwordEncoder.encode(dto.getPassword()));
              account.setUserPhone(dto.getUserPhone());
              account.setUserAddress(dto.getUserAddress());
              account.setUserEmail(dto.getUserEmail());
              account.setName(dto.getName());
              if ("ADMIN".equals(dto.getUsername().toUpperCase())){
                     account.setUserRole(UserRole.ADMIN);
              }else {
                     account.setUserRole(UserRole.USER);
              }
              em.persist(account);
              Cart cart = new Cart();
              cart.setUserAccount(account);
              cartRepository.save(cart);
       }

       public String loginUsername(){
              String username = SecurityContextHolder.getContext().getAuthentication().getName();
       return username;
       }
       
       public void insertPoint(String username, int insertMoney) {
              UserAccount userAccount = em.find(UserAccount.class,username);
              int totalUserPoint = userAccount.getInsertPoint()+insertMoney;
              userAccount.setInsertPoint(totalUserPoint);
              em.persist(userAccount);
       }
}

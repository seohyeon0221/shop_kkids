package com.example.shop_project_01.entity;

import com.example.shop_project_01.constant.UserRole;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class UserAccount {
    @Id
    @Column(name = "username",length = 50)
    private String username;
    
    @Column(nullable = false)
    private String password;
    
    @Column(name = "name",length = 10)
    private String name;
    
    @Column(name = "user_phone",length = 30)
    private String userPhone;
    
    @Column(name = "user_address",length = 100)
    private String userAddress;
    
    @Column(name = "user_email",length = 50)
    private String userEmail;
    
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    
    @OneToOne(mappedBy = "userAccount", cascade = CascadeType.REMOVE)
    private Cart cart;
    
    @Column(name = "created_at",updatable = false)
    @CreatedDate
    private LocalDate createdAt;
    
    @Column(name = "updated_at",insertable = false)
    @LastModifiedDate
    private LocalDate updatedAt;
    
//    @OneToMany(mappedBy = "userAccount")
//    private List<Buy> buyList = new ArrayList<>();
    
    private int insertPoint;
}

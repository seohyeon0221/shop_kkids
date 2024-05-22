package com.example.shop_project_01.entity;

import jakarta.persistence.*;

@Entity
public class MainCategory {
       
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Long mainCategoryId;
       
       private String mainCategoryName;
}

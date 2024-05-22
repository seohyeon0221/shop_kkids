package com.example.shop_project_01.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
public class SubCategory {
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       @Column(name = "sub_category_id")
       private Long subCategoryId;
       
       //       @ManyToOne
//       @JoinColumn(name = "mainCategoryId")
//       private MainCategory mainCategory;
       private String mainCategory;
       
       private String subCategoryName;
}

package com.example.shop_project_01.constant;

public enum ProductSale {
       SOLD_OUT("SOLD_OUT"),
       FOR_SALE("FOR_SALE");
       
       private String value;
       
       ProductSale(String value) {
              this.value = value;
       }
}

package com.example.shop_project_01.constant;

public enum ProductStatus {

//    DEFAULT("BEFORE_PURCHASE"),
    DEPOSIT("PAY_MONEY"),
    DELIVER("DELIVERY_NOW"),
    FINISH("BUY_COMPLETE");

    private String value;
    ProductStatus(String value){
        this.value = value;
    }
}

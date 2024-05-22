package com.example.shop_project_01.dto;

import com.example.shop_project_01.constant.ProductStatus;
import com.example.shop_project_01.entity.BuyProduct;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyProductDto {
       //구매내역 번호 (상세)
       private Long buyProductId;
       
       //구매내역 번호 (상세) - 구매수량
       private int count;
       
       //구매내역 번호 (상세) - 구매가격 ( join 아님 )
       private int price;
       
       //구매내역 번호 (상세) - 상품번호
       private Long productId;
       
       private String productName;
//
       //구매확정
       private Long buyId;
       
       private int totalPrice;
       
       private ProductStatus productStatus;
       
       private LocalDateTime salesDate;
       
       private String date;
       
       private String username;
       
       private String statues;

       private boolean reviewExists;
       public BuyProductDto(int count, int price, Long productId) {
              this.count = count;
              this.price = price;
              this.productId = productId;
       }
       
       public BuyProductDto(int count, int price, Long productId, String productName) {
              this.count = count;
              this.price = price;
              this.productId = productId;
              this.productName = productName;
       }
       
       public static BuyProductDto buyProductDtoFromEntity (BuyProduct buyProduct){
              return new BuyProductDto(
                     buyProduct.getBuyProductId(),
                     buyProduct.getCount(),
                     buyProduct.getPrice(),
                     buyProduct.getProductId(),
                     buyProduct.getBuy().getBuyId(),
                     buyProduct.getTotalPrice(),
                     buyProduct.getBuy().getProductStatus(),
                     buyProduct.getBuy().getBuyDate(),
                     buyProduct.getBuy().getUsername()
              );
       }
       
       public BuyProductDto(Long buyProductId, int count, int price, Long productId, Long buyId, int totalPrice, ProductStatus productStatus, LocalDateTime salesDate, String username) {
              this.buyProductId = buyProductId;
              this.count = count;
              this.price = price;
              this.productId = productId;
              this.buyId = buyId;
              this.totalPrice = totalPrice;
              this.productStatus = productStatus;
              this.salesDate = salesDate;
              this.username = username;
       }
}

package com.example.shop_project_01.dto;

import com.example.shop_project_01.constant.ProductStatus;
import com.example.shop_project_01.entity.Buy;
import com.example.shop_project_01.entity.BuyProduct;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyDto {
       public BuyDto(LocalDateTime buyDate, String username, ProductStatus productStatus) {
              this.buyDate = buyDate;
              this.username = username;
              this.productStatus = productStatus;
       }
       
       public BuyDto(LocalDateTime buyDate, ProductStatus productStatus) {
              this.buyDate = buyDate;
              this.productStatus = productStatus;
       }
       
       //구매확정
       private Long buyId;
       //구매 날짜
       private LocalDateTime buyDate;
       //구매한 유저 아이디 ( joinColumn - UserAccount )
       private String username;
       //유저가 충전한 금액
//       private int insertPoint;
       
       //구매상태
       private ProductStatus productStatus;
       
       //구매내역 번호 (상세)
       private Long buyProductId;
       
       private String statues;
       
       private String date;
       public BuyDto(Long buyId, LocalDateTime buyDate, String username, ProductStatus productStatus) {
              this.buyId = buyId;
              this.buyDate = buyDate;
              this.username = username;
              this.productStatus = productStatus;
       }
       
       public static BuyDto buyDtoFromEntity (Buy buy){
              return new BuyDto(
                     buy.getBuyId(),
                     buy.getBuyDate(),
                     buy.getUsername(),
                     buy.getProductStatus()
              );
       }
}

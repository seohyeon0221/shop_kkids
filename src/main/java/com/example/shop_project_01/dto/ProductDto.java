package com.example.shop_project_01.dto;

import com.example.shop_project_01.constant.ProductSale;
import com.example.shop_project_01.entity.Product;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Builder
public class ProductDto {
    private Long productId;
    @NotEmpty(message = "상품이름 입력은 필수입니다")
    private String productName;
    private int productPrice;
    private int productStock;
//    @NotEmpty(message = "상품 설명 입력은 필수입니다")
    private String contentImgName;
    
    private String contentImgPath;
    @NotEmpty(message = "메인카테고리 입력은 필수입니다")
    private String mainCategory;
    @NotEmpty(message = "서브 카테고리 입력은 필수입니다")
    private String subCategory;

    private String imgName;
    
    private String imgPath;

    
    private LocalDateTime uploadDate;
    
    private ProductSale productSale;
    
    public static ProductDto fromProductEntity(Product product){
        return new ProductDto(
                product.getProductId(),
                product.getProductName(),
                product.getProductPrice(),
                product.getProductStock(),
                product.getContentImgName(),
                product.getContentImgPath(),
                product.getMainCategoryName(),
                product.getSubCategoryName(),
                product.getImgName(),
               product.getImgPath(),
               product.getUploadDate(),
               product.getProductSale()
        );
    }


    public static Product fromProductDto(ProductDto dto){
        Product product = new Product();
        product.setProductId(dto.getProductId());
        product.setProductName(dto.getProductName());
        product.setProductPrice(dto.getProductPrice());
        product.setProductStock(dto.getProductStock());
        product.setContentImgName(dto.getContentImgName());
        product.setContentImgPath(dto.getContentImgPath());
        product.setMainCategoryName(dto.getMainCategory());
        product.setSubCategoryName(dto.getSubCategory());
        product.setImgName(dto.getImgName());
        product.setImgPath(dto.getImgPath());
        product.setUploadDate(dto.getUploadDate());
        product.setProductSale(dto.getProductSale());
        return product;
    }
}

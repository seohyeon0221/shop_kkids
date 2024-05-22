package com.example.shop_project_01.dto;

import com.example.shop_project_01.constant.ProductSale;
import com.example.shop_project_01.entity.Cart;
import com.example.shop_project_01.entity.CartProduct;
import com.example.shop_project_01.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.parameters.P;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartProductDto {

    //장바구니 아이템 번호
    private Long cartProductId;

    // 구매수량
    private int count;
    
    //상품 번호
    private Long productId;

    //구매당시의 상품 가격 ( 조인x _ 할인할때 가격을 알기위함 ) -> 카트
    private int productPrice;

    //배정된 카트 아이디
    private Long cartId;

    private String productName;
    
    private int stock;
    
    private ProductSale productSale;
    public CartProductDto(int count, Long productId, int productPrice, Long cartId) {
        this.count = count;
        this.productId = productId;
        this.productPrice = productPrice;
        this.cartId = cartId;
    }

    public CartProductDto(int count, int productPrice, String productName) {
        this.count = count;
        this.productPrice = productPrice;
        this.productName = productName;
    }

    public static CartProduct fromCartProductDto(CartProductDto cartProductDto){
        CartProduct cartProduct = new CartProduct();
//        Cart cart = new Cart();
//        Product product = new Product();
        Long cartId = cartProductDto.getCartId();
        cartProduct.getProduct().setProductPrice(cartProductDto.getProductPrice());
        cartProduct.setCount(cartProductDto.getCount());
        cartProduct.getCart().setCartId(cartId);
        cartProduct.getProduct().setProductId(cartProductDto.getProductId());

        return cartProduct;
    }
}

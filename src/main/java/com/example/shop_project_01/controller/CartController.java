package com.example.shop_project_01.controller;

import com.example.shop_project_01.constant.ProductStatus;
import com.example.shop_project_01.dto.BuyDto;
import com.example.shop_project_01.dto.BuyProductDto;
import com.example.shop_project_01.dto.CartProductDto;
import com.example.shop_project_01.entity.BuyProduct;
import com.example.shop_project_01.entity.CartProduct;
import com.example.shop_project_01.service.CartAndBuyService;
import com.example.shop_project_01.service.UserService;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class CartController {
    @Autowired
    CartAndBuyService cartAndBuyService;
    
    @Autowired
    UserService userService;
    @GetMapping("/cart")
    public String showCart(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<CartProduct> cartProducts = cartAndBuyService.showMyCart(username);
        List<CartProductDto> cartProductDtos = new ArrayList<>();
        
            int listSize = cartProducts.size();
            for (CartProduct cartProduct : cartProducts){
               CartProductDto cart = new CartProductDto();
               cart.setCartProductId(cartProduct.getCartProductId());
               cart.setCount(cartProduct.getCount());
               cart.setProductName(cartProduct.getProduct().getProductName());
               cart.setProductPrice(cartProduct.getProduct().getProductPrice());
                cart.setStock(cartProduct.getProduct().getProductStock());
               cartProductDtos.add(cart);
            }
        model.addAttribute("size",listSize);
        model.addAttribute("myCart",cartProductDtos);

        return "cart/my_cart_all";
    }

    @GetMapping("/cart/delete/{cartProductId}")
    public String cartAllModifyDelete(
            @PathVariable("cartProductId")Long cartProductId) {
        System.out.println(cartProductId);
        cartAndBuyService.cartProductDelete(cartProductId);
        return "redirect:/cart";
    }

    @PostMapping("/cart/modify")
    public String cartAllModify(
            @RequestParam("cartProductId")Long cartProductId,
            @RequestParam("count")int count) {
        cartAndBuyService.cartProductModifyCount(count,cartProductId);
        return "redirect:/cart";
    }
    
    @GetMapping("/buy")
    public String cartBuyAll(Model model){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        int point = cartAndBuyService.userPointFindByUsername(username);
        List<CartProduct> cartProducts = cartAndBuyService.showMyCartSoldOut(username);
        List<CartProductDto> cartProductDtos = new ArrayList<>();
        
        int total  = 0;
        
        for (CartProduct cartProduct : cartProducts) {
            total = total + cartProduct.getProduct().getProductPrice() * cartProduct.getCount();
        }
        for (CartProduct cartProduct : cartProducts){
            CartProductDto cart = new CartProductDto();
            cart.setCartProductId(cartProduct.getCartProductId());
            cart.setCount(cartProduct.getCount());
            cart.setProductName(cartProduct.getProduct().getProductName());
            cart.setProductPrice(cartProduct.getProduct().getProductPrice());
            
            cartProductDtos.add(cart);
        }
        
        model.addAttribute("total", total);
        model.addAttribute("point",point);
        model.addAttribute("myCart",cartProductDtos);
        
        
        return "cart/buy_all";
    }
    
    
    @PostMapping("/buy_all")
    public String buyOne(
           @RequestParam("action")String action,
                         Model model
    ){
        String loginUsername = userService.loginUsername();
        
        if (action.equals("cancel")){
            return  "redirect:/cart";
            
        } else if (action.equals("buy")) {
            LocalDateTime buyDate = LocalDateTime.now();
            List<CartProduct> cartProducts = cartAndBuyService.showMyCartSoldOut(loginUsername);
            List<BuyProductDto> buyProductDtos = new ArrayList<>();
            BuyDto buyDto = new BuyDto(buyDate,ProductStatus.DEPOSIT);
            for (CartProduct cartProduct : cartProducts) {
                int nowPrice = cartProduct.getProduct().getProductPrice();
                int count = cartProduct.getCount();
                Long productId = cartProduct.getProduct().getProductId();
                BuyProductDto buyProductDto = new BuyProductDto(count,nowPrice
                       ,productId);
                buyProductDtos.add(buyProductDto);
                }
            cartAndBuyService.addBuyAll(buyDto,buyProductDtos);
            cartAndBuyService.deleteCartProduct(loginUsername);
            return "cart/buy_ok";
            
            
        } else if (action.equals("requestMoney")) {
            int userPoint = cartAndBuyService.userPointFindByUsername(loginUsername);
            model.addAttribute("userPoint",userPoint);
            model.addAttribute("username",loginUsername);
            return "/cart/insert_point";
        }
        
        return "redirect:/cart/buy";
    }
}

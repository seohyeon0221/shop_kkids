package com.example.shop_project_01.controller;

import com.example.shop_project_01.constant.ProductStatus;
import com.example.shop_project_01.dto.*;
import com.example.shop_project_01.entity.CartProduct;
import com.example.shop_project_01.service.CartAndBuyService;
import com.example.shop_project_01.service.CategoryService;
import com.example.shop_project_01.service.ReviewService;
import com.example.shop_project_01.service.UserService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@Slf4j
public class CartAndBuyController {
    
    @Autowired
    CategoryService categoryService;
    @Autowired
    CartAndBuyService cartAndBuyService;
    @Autowired
    UserService userService;
    @Autowired
    ReviewService reviewService;



    //리뷰 작성하기
    @GetMapping("/review_add")
    public String addNoticeView(Model model,@RequestParam("productId") Long productId) {

        model.addAttribute("productId",productId);
        model.addAttribute("reviewDto", new ReviewDto());
        return "myPage/new_review";
    }

    @PostMapping("/review_add")
    public String addNotice(@ModelAttribute("reviewDto") @Valid ReviewDto dto, BindingResult bindingResult,
                            @RequestParam("productId")Long productId
    ) {
        if (bindingResult.hasErrors()) {
            return "myPage/new_review";
        }

        reviewService.addReview(dto);
        return "redirect:/product_detail/" + productId;
    }



    @GetMapping("/product_detail/{productId}")
    public String product_detail(@PathVariable("productId") Long productId,
                                 Model model) {
        ProductDto product = categoryService.productViewOne(productId);
        String username = userService.loginUsername();

        //리뷰 목록 가져오기
        List<ReviewDto> reviews = reviewService.getReviewsByProductId(productId);
        Long count = reviews.stream().count();
        model.addAttribute("loginUsername", username);
        model.addAttribute("count",count);
        model.addAttribute("product", product);
        model.addAttribute("reviews", reviews);
        return "/product/product_detail";
    }
    
    @PostMapping("/product_detail/cart_and_buy")
    public String addCart(
            @RequestParam("price")int price,
            @RequestParam("count")int count,
            @RequestParam("productId")Long productId,
            @RequestParam("loginUsername")String loginUsername,
            @RequestParam("action")String action,
            Model model , RedirectAttributes redirectAttributes
            ) {
        Long userCartNum = cartAndBuyService.cartIdFindByUsername(loginUsername);

        if (action.equals("cart")) {
            if(userCartNum!=null) {
                CartProductDto cartProductDto = new CartProductDto(count, productId, price, userCartNum);
                cartAndBuyService.addCartProduct(cartProductDto);
            }
            redirectAttributes.addFlashAttribute("msg", "물건이 <a href=\"/cart\">장바구니</a>에 담겼습니다.!!");
            return "redirect:/product_detail/" + productId;
            
        } else if (action.equals("buy")) {
          //구매하기 버튼 눌렀을때 작동
            String productName = cartAndBuyService.productNameFindByProductId(productId);
            int userPoint = cartAndBuyService.userPointFindByUsername(loginUsername);
            
            BuyProductDto buyDto = new BuyProductDto(count,price,productId,productName);
            int total = price * count;
            
            System.out.println(productName);
            System.out.println(count);
            
            model.addAttribute("username",loginUsername);
            model.addAttribute("userPoint",userPoint);
            model.addAttribute("total",total);
            model.addAttribute("buyDto",buyDto);
            return "/cart/buy_one";
        } else {
            return "redirect:/product_detail/" + productId;
        }
    }
    
    @PostMapping("/buy_one")
    public String buyOne(@RequestParam("action")String action,
                         @RequestParam("productName")String productName,
                         @RequestParam("productId")Long productId,
                         @RequestParam("username")String loginUsername,
                         @RequestParam("count")int count,
                         @RequestParam("price")int nowPrice,
                         Model model
                         ){
        if (action.equals("cancel")){
            System.out.println(productName);
            System.out.println(productId);
            return  "redirect:/product_detail/" + productId;
            
        } else if (action.equals("buy")) {
            LocalDateTime buyDate = LocalDateTime.now();
            BuyDto buyDto = new BuyDto(buyDate,loginUsername,ProductStatus.DEPOSIT);
            BuyProductDto buyProductDto = new BuyProductDto(count,nowPrice,productId);
            cartAndBuyService.addBuyProductOne(buyDto,buyProductDto);
            
            return "cart/buy_ok";
        } else if (action.equals("requestMoney")) {
            int userPoint = cartAndBuyService.userPointFindByUsername(loginUsername);
            model.addAttribute("productId",productId);
            model.addAttribute("userPoint",userPoint);
            model.addAttribute("username",loginUsername);
            return "/cart/insert_point";
        }
        
        return "/product/main";
    }

    @GetMapping("/buyList")
    public String showBuyList(Model model) {
        String username = userService.loginUsername();
        List<BuyProductDto> dto = cartAndBuyService.showBuyList(username);

        for (BuyProductDto buyProductDto : dto){
            boolean reviewExists = reviewService.check(buyProductDto.getProductId(),username);
            buyProductDto.setReviewExists(reviewExists);
        }
            int count = dto.size();
            model.addAttribute("size", count);
            model.addAttribute("buyList", dto);

        return "myPage/buyList";
    }
}

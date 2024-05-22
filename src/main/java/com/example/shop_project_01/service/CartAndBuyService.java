package com.example.shop_project_01.service;

import com.example.shop_project_01.constant.ProductSale;
import com.example.shop_project_01.dto.BuyDto;
import com.example.shop_project_01.dto.BuyProductDto;
import com.example.shop_project_01.dto.CartProductDto;
import com.example.shop_project_01.dto.ProductDto;
import com.example.shop_project_01.entity.*;
import com.example.shop_project_01.repository.*;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartAndBuyService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    BuyProductRepository buyProductRepository;

    @Autowired
    UserAccountRepository userAccountRepository;
    @Autowired
    CartProductRepository cartProductRepository;
    
    @Autowired
    UserService userService;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    EntityManager em;

    //유저아이디로 유저의 카트아이디 가져오기
    public Long cartIdFindByUsername(String username) {

        Cart cart = cartRepository.findByUserAccount_Username(username);

        return cart.getCartId();
    }

    //장바구니에 담기
    public void addCartProduct(CartProductDto dto) {

        Cart cart = em.find(Cart.class, dto.getCartId());
        Product product = em.find(Product.class, dto.getProductId());
        List<CartProduct> cartProductList = cartProductRepository.findByCart_CartId(dto.getCartId());
//        List<CartProductDto> cartProductDtoList = new ArrayList<>();
        int ifAddCount = dto.getCount();
        boolean productChange = false;

        //카트에 중복된 물건이 있을경우 수량을 더하여 카트에 다시 저장
        for (CartProduct cartProduct : cartProductList) {
            if (cartProduct.getProduct().getProductId().equals(dto.getProductId())) {
                ifAddCount += cartProduct.getCount();
                cartProduct.setCount(ifAddCount);
                cartProductRepository.save(cartProduct);
                productChange = true;
                break;

            }
        }
        //카트에 중복된 물건이 없을경우 카트에 새로 저장
            if (!productChange) {
                CartProduct cartProduct = new CartProduct();
                ifAddCount = dto.getCount();
                cartProduct.setCount(ifAddCount);
                cartProduct.setCart(cart);
                cartProduct.setProduct(product);

                cartProductRepository.save(cartProduct);
            }
        }

    //유저의 전체 장바구니 가져오기
    public List<CartProduct> showMyCart(String username) {
        Cart cart = cartRepository.findByUserAccount_Username(username);
        List<CartProduct> cartProducts = cartProductRepository.findByCart_CartId(cart.getCartId());

        return cartProducts;
    }
    
    public List<CartProduct> showMyCartSoldOut(String username) {
        Cart cart = cartRepository.findByUserAccount_Username(username);
        List<CartProduct> cartProducts = cartProductRepository.findByCart_CartIdAndProduct_ProductSale(cart.getCartId(),ProductSale.FOR_SALE);
        
        return cartProducts;
    }
       
       
       public String productNameFindByProductId(Long productId) {
        String productName = productRepository.findById(productId).map(x->ProductDto.fromProductEntity(x)).get().getProductName();

        return productName;
       }
    
    public int userPointFindByUsername(String loginUsername) {
        int userPoint = userAccountRepository.findByUsername(loginUsername).getInsertPoint();
        return userPoint;
    }
    
    public void addBuyProductOne(BuyDto buyDto, BuyProductDto buyProductDto) {
        String username = userService.loginUsername();
        UserAccount userAccount = em.find(UserAccount.class,username);
        Product product = em.find(Product.class,buyProductDto.getProductId());
        BuyProduct buyProduct = new BuyProduct();
        int stock = product.getProductStock()-buyProductDto.getCount();
        product.setProductStock(stock);
        if (stock>0){
            product.setProductSale(ProductSale.FOR_SALE);
        }else {
            product.setProductSale(ProductSale.SOLD_OUT);
        }
        
        int totalPrice =  buyProductDto.getPrice()*buyProductDto.getCount();
        buyProduct.setCount(buyProductDto.getCount());
        buyProduct.setPrice(buyProductDto.getPrice());
        buyProduct.setProductId(buyProductDto.getProductId());
        buyProduct.setTotalPrice(totalPrice);
        Buy buy = new Buy();
        buy.setBuyDate(buyDto.getBuyDate());
        buy.setProductStatus(buyDto.getProductStatus());
        buy.setUsername(username);
        
        buyProduct.setBuy(buy);
        buy.getBuyProducts().add(buyProduct);
        
        em.persist(buy);
        em.persist(buyProduct);
        em.persist(product);
        userAccount.setInsertPoint(userAccount.getInsertPoint()-totalPrice);
        em.persist(userAccount);
    }
    
    public void addBuyAll(BuyDto buyDto, List<BuyProductDto> buyProductDto) {
        String username = userService.loginUsername();
        UserAccount userAccount = em.find(UserAccount.class,username);
        
        int total = 0;
        for (BuyProductDto buyPro : buyProductDto) {
            total = total + buyPro.getPrice() * buyPro.getCount();
        }
        Buy buy = new Buy();
        buy.setBuyDate(buyDto.getBuyDate());
        buy.setProductStatus(buyDto.getProductStatus());
        buy.setUsername(username);
        em.persist(buy);
        
        for (BuyProductDto buyPro : buyProductDto) {
            int totalPrice = buyPro.getPrice() * buyPro.getCount();
            Product product = em.find(Product.class,buyPro.getProductId());
            int stock = product.getProductStock()-buyPro.getCount();
            product.setProductStock(stock);
            if (stock>0){
                product.setProductSale(ProductSale.FOR_SALE);
            }else {
                product.setProductSale(ProductSale.SOLD_OUT);
            }
            em.persist(product);
            
            BuyProduct buyProduct = new BuyProduct();
            buyProduct.setCount(buyPro.getCount());
            buyProduct.setPrice(buyPro.getPrice());
            buyProduct.setProductId(buyPro.getProductId());
            buyProduct.setTotalPrice(totalPrice);
            buyProduct.setBuy(buy);
            
            em.persist(buyProduct);
            buy.getBuyProducts().add(buyProduct);
        }
        em.persist(buy);
        
        userAccount.setInsertPoint(userAccount.getInsertPoint()-total);
        em.persist(userAccount);
    }

    public void cartProductModifyCount(int changeCount,Long cartProductId) {
        CartProduct cartProduct = em.find(CartProduct.class,cartProductId);
        cartProduct.setCount(changeCount);

        em.persist(cartProduct);
    }

    public void cartProductDelete(Long cartProductId) {
        CartProduct cartProduct = em.find(CartProduct.class,cartProductId);

        em.remove(cartProduct);
    }
       
       public void deleteCartProduct(String loginUsername) {
//            Long cartId = cartIdFindByUsername(loginUsername);
            List<CartProduct> cartProducts = showMyCart(loginUsername);
            
            for (CartProduct cart : cartProducts){
                CartProduct cartProduct = em.find(CartProduct.class,cart.getCartProductId());
                
                em.remove(cartProduct);
            }
            
       }

       //사용자의 구매 list
       public List<BuyProductDto> showBuyList(String username) {
           List<BuyProductDto> dtos = buyProductRepository.findByBuy_Username(username).stream().map(x -> BuyProductDto.buyProductDtoFromEntity(x)).toList();
           List<BuyProductDto> dtoList = new ArrayList<>();
           String statues = null;

           for (BuyProductDto buyDto : dtos) {
               String productName = productNameFindByProductId(buyDto.getProductId());
               buyDto.setProductName(productName);
               buyDto.setDate(buyDto.getSalesDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd  HH : mm")));
               switch (buyDto.getProductStatus()) {
                   case FINISH:
                       statues = "배송완료";
                       buyDto.setStatues(statues);
                       break;

                   case DELIVER:
                       statues = "배송중";
                       buyDto.setStatues(statues);
                       break;

                   case DEPOSIT:
                       statues = "입금완료";
                       buyDto.setStatues(statues);
                       break;
               }
           }
           return dtos;

       }
}

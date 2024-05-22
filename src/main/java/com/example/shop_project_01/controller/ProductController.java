package com.example.shop_project_01.controller;

import com.example.shop_project_01.dto.ProductDto;
import com.example.shop_project_01.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {
    //상품보는거 연결된 컨트롤러
    @Autowired
    CategoryService categoryService;

    
    @PostMapping("/search")
    public String search(Model model, @RequestParam("search")String search){
        List<ProductDto> products = categoryService.findSearchProduct(search);
        Long count = products.stream().count();
        
        products.forEach(user -> System.out.println(user));
        model.addAttribute("count",count);
        model.addAttribute("search",search);
        model.addAttribute("dto",products);
        
        return "/product/search";
    }
    // ===========  인형 - 전체보기
    @GetMapping("/doll")
    public String dollCategory(Model model) {
        List<ProductDto> products = categoryService.productViewAll();
        List<ProductDto> dollProducts = new ArrayList<>();
        for (ProductDto product : products) {
            if (product.getMainCategory().equals("인형")) {
                dollProducts.add(product);
            }
        }
        model.addAttribute("count",dollProducts.stream().count());
        model.addAttribute("dollAll", dollProducts);
        return "/product/doll_all";
    }

    @GetMapping("/doll/low-price")
    public String dollCategoryLow(Model model) {
        List<ProductDto> products = categoryService.productViewAllSortLowPrice();
        List<ProductDto> dollProducts = new ArrayList<>();
        for (ProductDto product : products) {
            if (product.getMainCategory().equals("인형")) {
                dollProducts.add(product);
            }
        }
        model.addAttribute("count",dollProducts.stream().count());
        model.addAttribute("dollAll", dollProducts);
        return "/product/doll_all";
    }

    @GetMapping("/doll/high-price")
    public String dollCategoryHigh(Model model) {
        List<ProductDto> products = categoryService.productViewAllSortHighPrice();
        List<ProductDto> dollProducts = new ArrayList<>();
        for (ProductDto product : products) {
            if (product.getMainCategory().equals("인형")) {
                dollProducts.add(product);
            }
        }
        model.addAttribute("count",dollProducts.stream().count());
        model.addAttribute("dollAll", dollProducts);
        return "/product/doll_all";
    }


    // ===========  인형 - 사람인형

    @GetMapping("/doll_person")
    public String dollPerson(Model model) {
        List<ProductDto> products = categoryService.productViewAll();
        List<ProductDto> dollProducts = new ArrayList<>();
        for (ProductDto product : products) {
            if (product.getSubCategory().equals("사람인형")) {
                dollProducts.add(product);
            }
        }
//              System.out.println("갯수 확인 : " + dollProducts.stream().count());
        model.addAttribute("count",dollProducts.stream().count());
        model.addAttribute("dollAll", dollProducts);
        return "/product/doll_person";
    }

    @GetMapping("/doll_person/low-price")
    public String dollPersonLow(Model model) {
        List<ProductDto> products = categoryService.productViewAllSortLowPrice();
        List<ProductDto> dollProducts = new ArrayList<>();
        for (ProductDto product : products) {
            if (product.getSubCategory().equals("사람인형")) {
                dollProducts.add(product);
            }
        }
//              System.out.println("갯수 확인 : " + dollProducts.stream().count());
        model.addAttribute("count",dollProducts.stream().count());
        model.addAttribute("dollAll", dollProducts);
        return "/product/doll_person";
    }

    @GetMapping("/doll_person/high-price")
    public String dollPersonHigh(Model model) {
        List<ProductDto> products = categoryService.productViewAllSortHighPrice();
        List<ProductDto> dollProducts = new ArrayList<>();
        for (ProductDto product : products) {
            if (product.getSubCategory().equals("사람인형")) {
                dollProducts.add(product);
            }
        }
//              System.out.println("갯수 확인 : " + dollProducts.stream().count());
        model.addAttribute("count",dollProducts.stream().count());
        model.addAttribute("dollAll", dollProducts);
        return "/product/doll_person";
    }


    // ===========  인형 - 동물인형

    @GetMapping("/doll_animal")
    public String dollAnimal(Model model) {
        List<ProductDto> products = categoryService.productViewAll();
        List<ProductDto> dollProducts = new ArrayList<>();
        for (ProductDto product : products) {
            if (product.getSubCategory().equals("동물인형")) {
                dollProducts.add(product);
            }
        }
        model.addAttribute("count",dollProducts.stream().count());
        model.addAttribute("dollAll", dollProducts);
        return "/product/doll_animal";
    }

    @GetMapping("/doll_animal/low-price")
    public String dollAnimalLow(Model model) {
        List<ProductDto> products = categoryService.productViewAllSortLowPrice();
        List<ProductDto> dollProducts = new ArrayList<>();
        for (ProductDto product : products) {
            if (product.getSubCategory().equals("동물인형")) {
                dollProducts.add(product);
            }
        }
        model.addAttribute("count",dollProducts.stream().count());
        model.addAttribute("dollAll", dollProducts);
        return "/product/doll_animal";
    }

    @GetMapping("/doll_animal/high-price")
    public String dollAnimalHigh(Model model) {
        List<ProductDto> products = categoryService.productViewAllSortHighPrice();
        List<ProductDto> dollProducts = new ArrayList<>();
        for (ProductDto product : products) {
            if (product.getSubCategory().equals("동물인형")) {
                dollProducts.add(product);
            }
        }
        model.addAttribute("count",dollProducts.stream().count());
        model.addAttribute("dollAll", dollProducts);
        return "/product/doll_animal";
    }

    // ===========  인형 - 캐릭터인형

    @GetMapping("/doll_character")
    public String dollCharacter(Model model) {
        List<ProductDto> products = categoryService.productViewAll();
        List<ProductDto> dollProducts = new ArrayList<>();
        for (ProductDto product : products) {
            if (product.getSubCategory().equals("캐릭터인형")) {
                dollProducts.add(product);
            }
        }
        model.addAttribute("count",dollProducts.stream().count());
        model.addAttribute("dollAll", dollProducts);
        return "/product/doll_character";
    }

    @GetMapping("/doll_character/low-price")
    public String dollCharacterLow(Model model) {
        List<ProductDto> products = categoryService.productViewAllSortLowPrice();
        List<ProductDto> dollProducts = new ArrayList<>();
        for (ProductDto product : products) {
            if (product.getSubCategory().equals("캐릭터인형")) {
                dollProducts.add(product);
            }
        }
        model.addAttribute("count",dollProducts.stream().count());
        model.addAttribute("dollAll", dollProducts);
        return "/product/doll_character";
    }

    @GetMapping("/doll_character/high-price")
    public String dollCharacterHigh(Model model) {
        List<ProductDto> products = categoryService.productViewAllSortHighPrice();
        List<ProductDto> dollProducts = new ArrayList<>();
        for (ProductDto product : products) {
            if (product.getSubCategory().equals("캐릭터인형")) {
                dollProducts.add(product);
            }
        }
        model.addAttribute("count",dollProducts.stream().count());
        model.addAttribute("dollAll", dollProducts);
        return "/product/doll_character";
    }


    // ===========  완구 - 전체보기

    @GetMapping("/toy")
    public String toyAll(Model model) {
        List<ProductDto> products = categoryService.productViewAll();
        List<ProductDto> toyProduct = new ArrayList<>();
        for (ProductDto product : products) {
            if (product.getMainCategory().equals("완구")) {
                toyProduct.add(product);
            }
        }

        model.addAttribute("count",toyProduct.stream().count());
        model.addAttribute("toyAll", toyProduct);
        return "/product/toy_all";
    }

    @GetMapping("/toy/low-price")
    public String toyAllLow(Model model) {
        List<ProductDto> products = categoryService.productViewAllSortLowPrice();
        List<ProductDto> toyProduct = new ArrayList<>();
        for (ProductDto product : products) {
            if (product.getMainCategory().equals("완구")) {
                toyProduct.add(product);
            }
        }

        model.addAttribute("count",toyProduct.stream().count());
        model.addAttribute("toyAll", toyProduct);
        return "/product/toy_all";
    }

    @GetMapping("/toy/high-price")
    public String toyAllHigh(Model model) {
        List<ProductDto> products = categoryService.productViewAllSortHighPrice();
        List<ProductDto> toyProduct = new ArrayList<>();
        for (ProductDto product : products) {
            if (product.getMainCategory().equals("완구")) {
                toyProduct.add(product);
            }
        }

        model.addAttribute("count",toyProduct.stream().count());
        model.addAttribute("toyAll", toyProduct);
        return "/product/toy_all";
    }


    // ===========  완구 - 레고

    @GetMapping("/toy_lego")
    public String toyLego(Model model) {
        List<ProductDto> products = categoryService.productViewAll();
        List<ProductDto> toyProduct = new ArrayList<>();
        for (ProductDto product : products) {
            if (product.getSubCategory().equals("레고")) {
                toyProduct.add(product);
            }
        }

        model.addAttribute("count",toyProduct.stream().count());
        model.addAttribute("toyAll", toyProduct);
        return "/product/toy_lego";
    }

    @GetMapping("/toy_lego/low-price")
    public String toyLegoLow(Model model) {
        List<ProductDto> products = categoryService.productViewAllSortLowPrice();
        List<ProductDto> toyProduct = new ArrayList<>();
        for (ProductDto product : products) {
            if (product.getSubCategory().equals("레고")) {
                toyProduct.add(product);
            }
        }

        model.addAttribute("count",toyProduct.stream().count());
        model.addAttribute("toyAll", toyProduct);
        return "/product/toy_lego";
    }

    @GetMapping("/toy_lego/high-price")
    public String toyLegoHigh(Model model) {
        List<ProductDto> products = categoryService.productViewAllSortHighPrice();
        List<ProductDto> toyProduct = new ArrayList<>();
        for (ProductDto product : products) {
            if (product.getSubCategory().equals("레고")) {
                toyProduct.add(product);
            }
        }

        model.addAttribute("count",toyProduct.stream().count());
        model.addAttribute("toyAll", toyProduct);
        return "/product/toy_lego";
    }


    // ===========  완구 - 실외

    @GetMapping("/toy_outdoor")
    public String toyOutdoor(Model model) {
        List<ProductDto> products = categoryService.productViewAll();
        List<ProductDto> toyProduct = new ArrayList<>();
        for (ProductDto product : products) {
            if (product.getSubCategory().equals("실외장난감")) {
                toyProduct.add(product);
            }
        }

        model.addAttribute("count",toyProduct.stream().count());
        model.addAttribute("toyAll", toyProduct);
        return "/product/toy_outdoor";
    }

    @GetMapping("/toy_outdoor/low-price")
    public String toyOutdoorLow(Model model) {
        List<ProductDto> products = categoryService.productViewAllSortLowPrice();
        List<ProductDto> toyProduct = new ArrayList<>();
        for (ProductDto product : products) {
            if (product.getSubCategory().equals("실외장난감")) {
                toyProduct.add(product);
            }
        }

        model.addAttribute("count",toyProduct.stream().count());
        model.addAttribute("toyAll", toyProduct);
        return "/product/toy_outdoor";
    }

    @GetMapping("/toy_outdoor/high-price")
    public String toyOutdoorHigh(Model model) {
        List<ProductDto> products = categoryService.productViewAllSortHighPrice();
        List<ProductDto> toyProduct = new ArrayList<>();
        for (ProductDto product : products) {
            if (product.getSubCategory().equals("실외장난감")) {
                toyProduct.add(product);
            }
        }

        model.addAttribute("count",toyProduct.stream().count());
        model.addAttribute("toyAll", toyProduct);
        return "/product/toy_outdoor";
    }

    // ===========  완구 - 촉감놀이

    @GetMapping("/toy_touch")
    public String toyTouch(Model model) {
        List<ProductDto> products = categoryService.productViewAll();
        List<ProductDto> toyProduct = new ArrayList<>();
        for (ProductDto product : products) {
            if (product.getSubCategory().equals("촉각놀이")) {
                toyProduct.add(product);
            }
        }

        model.addAttribute("count",toyProduct.stream().count());
        model.addAttribute("toyAll", toyProduct);
        return "/product/toy_touch";
    }

    @GetMapping("/toy_touch/low-price")
    public String toyTouchLow(Model model) {
        List<ProductDto> products = categoryService.productViewAllSortLowPrice();
        List<ProductDto> toyProduct = new ArrayList<>();
        for (ProductDto product : products) {
            if (product.getSubCategory().equals("촉각놀이")) {
                toyProduct.add(product);
            }
        }

        model.addAttribute("count",toyProduct.stream().count());
        model.addAttribute("toyAll", toyProduct);
        return "/product/toy_touch";
    }

    @GetMapping("/toy_touch/high-price")
    public String toyTouchHigh(Model model) {
        List<ProductDto> products = categoryService.productViewAllSortHighPrice();
        List<ProductDto> toyProduct = new ArrayList<>();
        for (ProductDto product : products) {
            if (product.getSubCategory().equals("촉각놀이")) {
                toyProduct.add(product);
            }
        }

        model.addAttribute("count",toyProduct.stream().count());
        model.addAttribute("toyAll", toyProduct);
        return "/product/toy_touch";
    }


    // ===========  도서 - 전체보기


    @GetMapping("/books")
    public String bookAll(Model model) {
        List<ProductDto> products = categoryService.productViewAll();
        List<ProductDto> bookProduct = new ArrayList<>();
        for (ProductDto product : products) {
            if (product.getMainCategory().equals("도서")) {
                bookProduct.add(product);
            }
        }
        model.addAttribute("main","book");
        model.addAttribute("count",bookProduct.stream().count());
        model.addAttribute("bookAll", bookProduct);
        return "/product/books_all";
    }

    @GetMapping("/books/low-price")
    public String bookAllSortLowPrice(Model model) {
        List<ProductDto> products = categoryService.productViewAllSortLowPrice();
        List<ProductDto> bookProduct = new ArrayList<>();
        for (ProductDto product : products) {
            if (product.getMainCategory().equals("도서")) {
                bookProduct.add(product);
            }
        }
        model.addAttribute("count",bookProduct.stream().count());
        model.addAttribute("bookAll", bookProduct);
        return "/product/books_all";
//        return "/product/low/books_all";
    }

    @GetMapping("/books/high-price")
    public String bookAllSortHighPrice(Model model) {
        List<ProductDto> products = categoryService.productViewAllSortHighPrice();
        List<ProductDto> bookProduct = new ArrayList<>();
        for (ProductDto product : products) {
            if (product.getMainCategory().equals("도서")) {
                bookProduct.add(product);
            }
        }
        model.addAttribute("count",bookProduct.stream().count());
        model.addAttribute("bookAll", bookProduct);
        return "/product/books_all";
//        return "/product/low/books_all";
    }



    // ===========  도서 - 동화

    @GetMapping("/books_story")
    public String bookStory(Model model) {
        List<ProductDto> products = categoryService.productViewAll();
        List<ProductDto> bookProduct = new ArrayList<>();
        for (ProductDto product : products) {
            if (product.getSubCategory().equals("동화")) {
                bookProduct.add(product);
            }
        }

        model.addAttribute("count",bookProduct.stream().count());
        model.addAttribute("bookAll", bookProduct);
        return "/product/books_story";
    }

    @GetMapping("/books_story/low-price")
    public String bookStoryLowPrice(Model model) {
        List<ProductDto> products = categoryService.productViewAllSortLowPrice();
        List<ProductDto> bookProduct = new ArrayList<>();
        for (ProductDto product : products) {
            if (product.getSubCategory().equals("동화")) {
                bookProduct.add(product);
            }
        }

        model.addAttribute("count",bookProduct.stream().count());
        model.addAttribute("bookAll", bookProduct);
        return "/product/books_story";
    }

    @GetMapping("/books_story/high-price")
    public String bookStoryHighPrice(Model model) {
        List<ProductDto> products = categoryService.productViewAllSortHighPrice();
        List<ProductDto> bookProduct = new ArrayList<>();
        for (ProductDto product : products) {
            if (product.getSubCategory().equals("동화")) {
                bookProduct.add(product);
            }
        }

        model.addAttribute("count",bookProduct.stream().count());
        model.addAttribute("bookAll", bookProduct);
        return "/product/books_story";
    }


    // ===========  도서 - 위인전

    @GetMapping("/books_biography")
    public String booksBiography(Model model) {
        List<ProductDto> products = categoryService.productViewAll();
        List<ProductDto> bookProduct = new ArrayList<>();
        for (ProductDto product : products) {
            if (product.getSubCategory().equals("위인전")) {
                bookProduct.add(product);
            }
        }

        model.addAttribute("count",bookProduct.stream().count());
        model.addAttribute("bookAll", bookProduct);
        return "/product/books_biography";
    }

    @GetMapping("/books_biography/low-price")
    public String booksBiographyLowPrice(Model model) {
        List<ProductDto> products = categoryService.productViewAllSortLowPrice();
        List<ProductDto> bookProduct = new ArrayList<>();
        for (ProductDto product : products) {
            if (product.getSubCategory().equals("위인전")) {
                bookProduct.add(product);
            }
        }

        model.addAttribute("count",bookProduct.stream().count());
        model.addAttribute("bookAll", bookProduct);
        return "/product/books_biography";
    }

    @GetMapping("/books_biography/high-price")
    public String booksBiographyHighPrice(Model model) {
        List<ProductDto> products = categoryService.productViewAllSortHighPrice();
        List<ProductDto> bookProduct = new ArrayList<>();
        for (ProductDto product : products) {
            if (product.getSubCategory().equals("위인전")) {
                bookProduct.add(product);
            }
        }

        model.addAttribute("count",bookProduct.stream().count());
        model.addAttribute("bookAll", bookProduct);
        return "/product/books_biography";
    }



    // ===========  도서 - 영어
    @GetMapping("/books_eng")
    public String booksEng(Model model) {
        List<ProductDto> products = categoryService.productViewAll();
        List<ProductDto> bookProduct = new ArrayList<>();
        for (ProductDto product : products) {
            if (product.getSubCategory().equals("영어도서")) {
                bookProduct.add(product);
            }
        }

        model.addAttribute("count",bookProduct.stream().count());
        model.addAttribute("bookAll", bookProduct);
        return "/product/books_eng";
    }

    @GetMapping("/books_eng/low-price")
    public String booksEngLowPrice(Model model) {
        List<ProductDto> products = categoryService.productViewAllSortLowPrice();
        List<ProductDto> bookProduct = new ArrayList<>();
        for (ProductDto product : products) {
            if (product.getSubCategory().equals("영어도서")) {
                bookProduct.add(product);
            }
        }

        model.addAttribute("count",bookProduct.stream().count());
        model.addAttribute("bookAll", bookProduct);
        return "/product/books_eng";
    }

    @GetMapping("/books_eng/high-price")
    public String booksEngHighPrice(Model model) {
        List<ProductDto> products = categoryService.productViewAllSortHighPrice();
        List<ProductDto> bookProduct = new ArrayList<>();
        for (ProductDto product : products) {
            if (product.getSubCategory().equals("영어도서")) {
                bookProduct.add(product);
            }
        }

        model.addAttribute("count",bookProduct.stream().count());
        model.addAttribute("bookAll", bookProduct);
        return "/product/books_eng";
    }
}

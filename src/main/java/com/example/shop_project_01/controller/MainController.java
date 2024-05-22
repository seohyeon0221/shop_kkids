package com.example.shop_project_01.controller;

import com.example.shop_project_01.dto.NoticeDto;
import com.example.shop_project_01.dto.ProductDto;
import com.example.shop_project_01.dto.UserAccountDto;
import com.example.shop_project_01.entity.Notice;
import com.example.shop_project_01.entity.Product;
import com.example.shop_project_01.repository.NoticeRepository;
import com.example.shop_project_01.service.CategoryService;
import com.example.shop_project_01.service.NoticeService;
import com.example.shop_project_01.service.PaginationService;
import com.example.shop_project_01.service.UserAccountService;
import jakarta.websocket.server.PathParam;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

       @Autowired
       CategoryService categoryService;

       @Autowired
       NoticeService noticeService;
       @Autowired
       PaginationService paginationService;

       @GetMapping("/")
       public String main(Model model) {
              // 최근에 등록된 상품 중에서 첫 페이지에 있는 2개의 상품 가져오기
              List<Product> dto = categoryService.showRecentProducts(PageRequest.of(0, 2));
              model.addAttribute("dto", dto);
              return "/product/main";
       }
       
       @GetMapping("/admin_page")
       public String adminPage() {
              return "/admin/admin_page";
       }



       //공지사항 페이지 - 페이징처리
       @GetMapping("notice")
       public String testView(Model model,
                              @PageableDefault(page = 0,size = 5,sort = "createdAt",direction = Sort.Direction.DESC)
                              Pageable pageable) {
              //넘겨온 페이지 번호로 리스트 받아오기
              Page<Notice> paging = noticeService.pagingList(pageable);

              //페이지 출력 처리 (1,2,3,4,5)
              int totalPage = paging.getTotalPages();
              List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), totalPage);
              model.addAttribute("paginationBarNumbers", barNumbers);
              Long count = paging.stream().count();
              model.addAttribute("count", count);
              model.addAttribute("paging", paging);
              return "/notice/notice_all";
       }

       //공지사항 제목 링크를 클릭하면 내용확인
       @GetMapping("/notice_showOne/{noticeId}")
       public String noticeChange(@PathVariable("noticeId")Long noticeId, Model model){
              NoticeDto noticeDto = noticeService.noticeViewFindById(noticeId);
              model.addAttribute("noticeDto",noticeDto);
              return "/notice/notice_showOne";
       }





       


}


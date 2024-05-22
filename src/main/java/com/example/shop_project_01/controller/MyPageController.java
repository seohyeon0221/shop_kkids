package com.example.shop_project_01.controller;

import com.example.shop_project_01.dto.UserAccountDto;
import com.example.shop_project_01.service.CartAndBuyService;
import com.example.shop_project_01.service.UserAccountService;
import com.example.shop_project_01.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MyPageController {
    //마이페이지 네브바에 있는 내용들 컨트롤러(카트 없음)
    @Autowired
    UserAccountService userAccountService;

    @Autowired
    CartAndBuyService cartAndBuyService;

    @Autowired
    UserService userService;
    //마이 페이지
    @GetMapping("/mypage")
    public String myPage(Model model) {
        // 사용자의 아이디를 기반으로 사용자 정보를 가져옵니다.
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserAccountDto accountDto = userAccountService.getMyPage(username);

        // 사용자 정보가 존재하지 않으면 예외 처리
        if (accountDto == null) {
            // 예외 처리 코드 작성
            return "redirect:/error"; // 예시로 에러 페이지로 리다이렉트하도록 설정
        }

        // 모델에 사용자 정보를 추가하여 마이페이지로 전달합니다.
        model.addAttribute("accountDto", accountDto);

        return "/myPage/mypage";
    }

    //회원정보 수정하기
    @GetMapping("/update")
    public String updateAccount(@RequestParam("username") String username,
                                Model model){
        UserAccountDto accountDto = userAccountService.getMyPage(username);

//        System.out.println(accountDto.toString());

        model.addAttribute("accountDto", accountDto);
        return "/myPage/updateForm";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("accountDto") UserAccountDto dto,
                         BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "/myPage/updateForm";
        }

        if (!dto.getPassword().equals(dto.getPasswordCheck())) {
            bindingResult.rejectValue
                    ("passwordCheck", "passwordIncorrect", "비밀번호가 일치하지 않습니다.");
            return "/myPage/updateForm";
        }
//        System.out.println("수정받은 dto : " + dto.toString());
        userAccountService.update(dto);// 사용자 정보 업데이트 메서드를 호출해야 함

        return "redirect:/mypage"; // 업데이트 후 마이페이지로 리다이렉트
    }


    //탈퇴하기

    @PostMapping("delete/{deleteId}")
    public String deleteAccount(@RequestParam("deleteId") String username,
                                HttpServletRequest request, HttpServletResponse response,
                                RedirectAttributes redirectAttributes){
        userAccountService.deleteAccount(username);
        redirectAttributes.addFlashAttribute("msg", "회원탈퇴가 정상적으로 처리되었습니다!!");

        //회원탈퇴 후 로그아웃
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";

    }

    @GetMapping("/cart/insert_point")
    public String insertPoint(Model model){
        String loginUsername = userService.loginUsername();
        int userPoint = cartAndBuyService.userPointFindByUsername(loginUsername);
        
        model.addAttribute("userPoint",userPoint);
        model.addAttribute("username",loginUsername);
        
        return "/cart/insert_point";
    }

    @PostMapping("/insert_point")
    public String insertPoint(@RequestParam("username")String username,
                              @RequestParam("insertMoney")int insertMoney,
                              Model model){
        userService.insertPoint(username,insertMoney);
        
        int userPoint = cartAndBuyService.userPointFindByUsername(username);
        
        model.addAttribute("userPoint",userPoint);
        model.addAttribute("username",username);
        
        return "redirect:/cart/insert_point";
    }

}

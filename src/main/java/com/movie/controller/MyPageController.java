package com.movie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyPageController {

    @GetMapping("/mypage")
    public String myPage(Model model) {
        // 컨텐츠에 mypage 템플릿 경로 추가
        model.addAttribute("content", "mypage/mypage");
        return "layout/base";
    }
}

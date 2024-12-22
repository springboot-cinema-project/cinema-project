package com.movie.controller;

import com.movie.domain.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class ExController {

    @GetMapping("/oo")
    public String oo(Model model, HttpSession session) {
        // 세션에서 사용자 정보 가져오기
        User user = (User) session.getAttribute("USER");

        if (user != null) {
            model.addAttribute("id", user.getId());
            model.addAttribute("email", user.getEmail());
            model.addAttribute("name", user.getName());
            model.addAttribute("phone", user.getPhone());
            model.addAttribute("birth", user.getBirth());
            model.addAttribute("socialProvider", user.getSocialProvider());
            model.addAttribute("createdAt", user.getCreatedAt());
            model.addAttribute("updatedAt", user.getUpdatedAt());
            model.addAttribute("role", user.getRole());
            model.addAttribute("socialId", user.getSocialId());
        } else {
            model.addAttribute("error", "로그인 상태 X");
        }

        model.addAttribute("title", "Test");
        model.addAttribute("content", "test/oo");

        return "layout/base";
    }
}

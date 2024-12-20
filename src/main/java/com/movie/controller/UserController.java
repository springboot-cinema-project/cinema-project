package com.movie.controller;

import com.movie.service.EmailService;
import com.movie.util.ValidationUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private ValidationUtil validationUtil;

    private final Map<String, Boolean> verifiedEmails = new HashMap<>();

    @PostMapping("/sendcode")
    @ResponseBody
    public ResponseEntity<?> sendVerificationCode(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");
        if (!validationUtil.isValidEmail(email)) {
            return ResponseEntity.badRequest().body("無効なメールアドレスです。");
        }
        emailService.sendVerificationCode(email);
        return ResponseEntity.ok("認証コードが送信されました。");
    }

    @PostMapping("/verifycode")
    @ResponseBody
    public ResponseEntity<?> verifyCode(@RequestBody Map<String, String> payload, HttpSession session) {
        String email = payload.get("email");
        String code = payload.get("code");

        if (!validationUtil.isValidEmail(email) || !validationUtil.isValidCode(code)) {
            return ResponseEntity.badRequest().body("無効なデータです。");
        }

        if (emailService.verifyCode(email, code)) {
            session.setAttribute("verifiedEmail", email);
            session.setAttribute("verifiedEmailExpiry", System.currentTimeMillis() + 10 * 60 * 1000);
            return ResponseEntity.ok("認証が成功しました。");
        } else {
            return ResponseEntity.badRequest().body("認証コードが間違っています。");
        }
    }


    @GetMapping("/signup")
    public String signup(HttpSession session, Model model) {
        String email = (String) session.getAttribute("verifiedEmail");
        Long expiryTime = (Long) session.getAttribute("verifiedEmailExpiry");
//        System.out.println(email);
        if(email == null || expiryTime == null || System.currentTimeMillis() > expiryTime) {
            session.removeAttribute("verifiedEmail");
            session.removeAttribute("verifiedEmailExpiry");

            return "redirect:/";
        }

        model.addAttribute("email", email);
        model.addAttribute("content", "user/signup");
        model.addAttribute("title", "会員登録");
        return "layout/base";
    }

}

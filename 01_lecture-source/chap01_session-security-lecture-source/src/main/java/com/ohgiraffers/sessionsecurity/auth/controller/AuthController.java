package com.ohgiraffers.sessionsecurity.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/auth/*")
public class AuthController {

    @GetMapping("login")
    public void loginPage() {}

    // @PostMapping("login") 은 security 에서 자동으로 해줌

    @GetMapping("fail")
    public ModelAndView loginFail(@RequestParam String message, ModelAndView mv) {

        // 실패 시 핸들러에서 쿼리스트링으로 보내주는 errorMessage
        mv.addObject("message", message);

        mv.setViewName("auth/fail");

        return mv;
    }

}

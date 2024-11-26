package com.ohgiraffers.sessionsecurity.user.controller;

import com.ohgiraffers.sessionsecurity.user.model.dto.SignupDTO;
import com.ohgiraffers.sessionsecurity.user.model.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user/*")
public class UserController {

    // 필드로 주입 (final 붙일 수 없음)
    @Autowired
    private MemberService memberService;

    @GetMapping("signup")
    public void signupPage() {}

    @PostMapping("signup")
    public ModelAndView signup(@ModelAttribute SignupDTO signupDTO, ModelAndView mv){

        // 회원가입은 insert 작업이기 때문에 insert 완료 시 1 이 반환되어 int 사용
        // if문에서 기본자료형인 int 는 null 과 같을 수 없으므로 Integer
        Integer result = memberService.regist(signupDTO);

        String message = null;

        /* comment. controller 의 역할은 어떠한 view 를 보여줄 지 선택하는 것 */
        if(result == null) {
            message = "중복 된 회원이 존재합니다.";
        } else if(result == 0) {
            message = "서버 내부에서 오류가 발생했습니다.";
            mv.setViewName("user/signup");
        } else if(result >= 1) {
            message = "회원가입이 완료되었습니다.";
            mv.setViewName("auth/login");
        }

        mv.addObject("message", message);

        return mv;
    }
}

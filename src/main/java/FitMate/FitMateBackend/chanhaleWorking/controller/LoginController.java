package FitMate.FitMateBackend.chanhaleWorking.controller;

import FitMate.FitMateBackend.chanhaleWorking.SessionConst;
import FitMate.FitMateBackend.domain.User;
import FitMate.FitMateBackend.chanhaleWorking.form.LoginForm;
import FitMate.FitMateBackend.chanhaleWorking.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    @ResponseBody
    public String login(@Valid @ModelAttribute LoginForm loginForm, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()){
            return "fail";//"입력 오류";
        }
        log.info("login attempt [{}]",loginForm.getLoginId() );


        User loginUser = loginService.login(loginForm.getLoginId(),loginForm.getPassword());
        if(loginUser == null){
            return "fail";//"로그인 실패. 아이디와 패스워드를 확인해주세요.";
        }
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_USER, loginUser);
        log.info("login success [{}]",loginForm.getLoginId() );
        return "ok";
    }

    @PostMapping("/logout")
    @ResponseBody
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "ok";
    }
}

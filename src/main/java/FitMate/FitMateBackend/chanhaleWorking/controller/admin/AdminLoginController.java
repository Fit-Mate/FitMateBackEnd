package FitMate.FitMateBackend.chanhaleWorking.controller.admin;

import FitMate.FitMateBackend.chanhaleWorking.SessionConst;
import FitMate.FitMateBackend.chanhaleWorking.form.LoginForm;
import FitMate.FitMateBackend.chanhaleWorking.service.LoginService;
import FitMate.FitMateBackend.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
@ResponseBody
public class AdminLoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public String login(LoginForm loginForm, HttpServletRequest request){

        log.info("ADMIN login attempt [{}]",loginForm.getLoginId() );
        User loginAdmin = loginService.adminLogin(loginForm.getLoginId(), loginForm.getPassword());
        if(loginAdmin == null){
            return "fail";//"로그인 실패. 아이디와 패스워드를 확인해주세요.";
        }

        HttpSession session = request.getSession();

        session.setAttribute(SessionConst.LOGIN_ADMIN, loginAdmin);
        log.info("ADMIN login success [{}]",loginForm.getLoginId() );

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

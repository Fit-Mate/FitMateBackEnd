package FitMate.FitMateBackend.chanhaleWorking.controller;

import FitMate.FitMateBackend.chanhaleWorking.form.RegisterForm;
import FitMate.FitMateBackend.chanhaleWorking.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * User 생성, 수정 삭제에 관한 컨트롤러
 */
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
@ResponseBody
public class UserController {
    private final UserService userService;

    @PostMapping
    public String register(RegisterForm registerForm){
        log.info("REGISTER [{}] [{}]", registerForm.getUserName(), registerForm.getSex());
        String errMsg = registerForm.validateFields();
        if (!errMsg.equals("ok"))
            return errMsg;
        if (userService.checkDuplicatedLoginId(registerForm.getLoginId()))
            return "아이디 중복";
        userService.register(registerForm);
        return "ok";
    }

    @PostMapping("/admin/register")
    public String adminRegister(RegisterForm registerForm){
        log.info("REGISTER [{}] [{}]", registerForm.getUserName(), registerForm.getSex());
        String errMsg = registerForm.validateFields();
        if (errMsg != "ok")
            return errMsg;
        if (userService.checkDuplicatedLoginId(registerForm.getLoginId()))
            return "아이디 중복";
        userService.registerAdmin(registerForm);
        return "ok";
    }
}

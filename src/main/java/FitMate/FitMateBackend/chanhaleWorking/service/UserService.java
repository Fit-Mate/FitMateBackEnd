package FitMate.FitMateBackend.chanhaleWorking.service;

import FitMate.FitMateBackend.chanhaleWorking.form.RegisterForm;
import FitMate.FitMateBackend.chanhaleWorking.repository.UserRepository;
import FitMate.FitMateBackend.domain.BodyData;
import FitMate.FitMateBackend.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public Long register(RegisterForm registerForm){
        User newUser = User.createUser(registerForm, "Customer");
        newUser.addBodyDataHistory(BodyData.createBodyData(registerForm.getBodyDataForm()));
        userRepository.save(newUser);
        return newUser.getId();
    }
    @Transactional
    public Long registerAdmin(RegisterForm registerForm){
        User newUser = User.createUser(registerForm, "Admin");
        newUser.addBodyDataHistory(BodyData.createBodyData(registerForm.getBodyDataForm()));
        userRepository.save(newUser);
        return newUser.getId();
    }

    @Transactional(readOnly = true)
    public Boolean checkDuplicatedLoginId(String loginId){
        return userRepository.CheckDuplicatedLoginId(loginId);
    }
}

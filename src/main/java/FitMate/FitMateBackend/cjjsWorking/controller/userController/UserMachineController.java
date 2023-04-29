package FitMate.FitMateBackend.cjjsWorking.controller.userController;

import FitMate.FitMateBackend.cjjsWorking.service.MachineService;
import FitMate.FitMateBackend.consts.SessionConst;
import FitMate.FitMateBackend.domain.Machine;
import FitMate.FitMateBackend.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserMachineController {

    private final MachineService machineService;

    @PostMapping("machines/list") //부위별 조회 (개발 중)
    public List<Machine> findMachines(@SessionAttribute(name = SessionConst.LOGIN_USER) User user) {
        return null;
    }
}
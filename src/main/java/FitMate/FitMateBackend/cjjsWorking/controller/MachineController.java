package FitMate.FitMateBackend.cjjsWorking.controller;

import FitMate.FitMateBackend.cjjsWorking.repository.BodyPartRepository;
import FitMate.FitMateBackend.cjjsWorking.service.MachineService;
import FitMate.FitMateBackend.consts.SessionConst;
import FitMate.FitMateBackend.domain.BodyPart;
import FitMate.FitMateBackend.domain.Machine;
import FitMate.FitMateBackend.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MachineController {

    private final MachineService machineService;
    private final BodyPartRepository bodyPartRepository;

    @PostMapping("admin/machines") //생성
    public Long saveMachine(@SessionAttribute(name = SessionConst.LOGIN_ADMIN, required = false) User admin,
                            @RequestBody MachineRequest request) {

        if(admin != null) {
            Machine machine = new Machine();
            List<BodyPart> bodyParts = new ArrayList<>();

            for (String koreanName : request.bodyPartKoreanName) {
                BodyPart findBodyPart = bodyPartRepository.findByKoreanName(koreanName);
                //bodyPart logic
                bodyParts.add(findBodyPart);
            }
            machine.update(request.englishName, request.koreanName, bodyParts);

            Long machineId = machineService.saveMachine(machine);
            return machineId;
        } else {
            return null;
        }
    }

    @PutMapping("admin/machines/{machineId}") //수정
    public Long updateMachine(@PathVariable("machineId") Long machineId,
                              @SessionAttribute(name = SessionConst.LOGIN_ADMIN, required = false) User admin,
                              @RequestBody MachineRequest request) {

        if(admin != null) {
            List<BodyPart> bodyParts = new ArrayList<>();
            for (String koreanName : request.bodyPartKoreanName) {
                BodyPart findBodyPart = bodyPartRepository.findByKoreanName(koreanName);
                bodyParts.add(findBodyPart);
            }

            Machine findMachine = machineService.findOne(machineId);
            findMachine.update(request.englishName, request.koreanName, bodyParts);
            return machineId;
        } else {
            return null;
        }
    }

    @GetMapping("admin/machines/{machineId}") //단건조회
    public Machine findMachine(@PathVariable("machineId") Long machineId,
                            @SessionAttribute(name = SessionConst.LOGIN_ADMIN, required = false) User admin) {
        if(admin != null) {
            Machine findMachine = machineService.findOne(machineId);
            return findMachine;
        } else {
            return null;
        }
    }

    @GetMapping("admin/machines/list/{page}") //batch 단위 조회
    public List<Machine> findMachines_page(@PathVariable("page") int page,
                                           @SessionAttribute(name = SessionConst.LOGIN_ADMIN, required = false) User admin) {
        if(admin != null) {
            //page1 -> bodyPartId: 0~9
            int offset = (page - 1);
            int limit = ((page * 10) - 1);
            List<Machine> findMachines = machineService.findAll(offset, limit);
            return findMachines;
        } else {
            return null;
        }
    }

    @DeleteMapping("admin/machines/{machineId}") //삭제
    public Long deleteMachine(@PathVariable("machineId") Long machineId,
                              @SessionAttribute(name = SessionConst.LOGIN_ADMIN, required = false) User admin) {
        if(admin != null) {
            machineService.removeMachine(machineId);
            return machineId;
        } else {
            return null;
        }
    }

    @Data
    @AllArgsConstructor
    static class MachineRequest {
        private String englishName;
        private String koreanName;
        private String[] bodyPartKoreanName;
    }
}
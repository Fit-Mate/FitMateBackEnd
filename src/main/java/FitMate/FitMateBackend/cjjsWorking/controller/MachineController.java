package FitMate.FitMateBackend.cjjsWorking.controller;

import FitMate.FitMateBackend.cjjsWorking.repository.BodyPartRepository;
import FitMate.FitMateBackend.cjjsWorking.service.MachineService;
import FitMate.FitMateBackend.consts.SessionConst;
import FitMate.FitMateBackend.domain.BodyPart;
import FitMate.FitMateBackend.domain.Machine;
import FitMate.FitMateBackend.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MachineController {

    private final MachineService machineService;
    private final BodyPartRepository bodyPartRepository;

    @PostMapping("admin/machines") //생성 (완료)
    public Long saveMachine(@SessionAttribute(name = SessionConst.LOGIN_ADMIN, required = false) User admin,
                            @RequestBody MachineRequest request) {
        Machine machine = new Machine();
        machine.update(request.englishName, request.koreanName);

        for (String koreanName : request.bodyPartKoreanName) {
            BodyPart findBodyPart = bodyPartRepository.findByKoreanName(koreanName);
            findBodyPart.addMachine(machine);
            machine.getBodyParts().add(findBodyPart);
        }

        Long machineId = machineService.saveMachine(machine);
        return machineId;
    }

    @PutMapping("admin/machines/{machineId}") //수정 (완료)
    public Long updateMachine(@PathVariable("machineId") Long machineId,
                              @SessionAttribute(name = SessionConst.LOGIN_ADMIN, required = false) User admin,
                              @RequestBody MachineRequest request) {

        machineService.updateMachine(machineId, request.englishName, request.koreanName, request.bodyPartKoreanName);
        return machineId;
    }

    @GetMapping("admin/machines/{machineId}") //단건조회 (완료)
    public GetMachineResponse findMachine(@PathVariable("machineId") Long machineId,
                               @SessionAttribute(name = SessionConst.LOGIN_ADMIN, required = false) User admin) {
        Machine findMachine = machineService.findOne(machineId);
        return new GetMachineResponse(findMachine.getEnglishName(), findMachine.getKoreanName(),
                findMachine.getBodyParts());
    }

    @GetMapping("admin/machines/list/{page}") //batch 단위 조회 (완료)
    public List<MachineDto> findMachines_page(@PathVariable("page") int page,
                                           @SessionAttribute(name = SessionConst.LOGIN_ADMIN, required = false) User admin) {
        //page1 -> bodyPartId: 0~9
        int offset = (page-1)*10;
        int limit = ((page*10)-1);

        List<Machine> findMachines = machineService.findAll(offset, limit);

        return findMachines.stream()
                .map(m -> new MachineDto(m))
                .collect(Collectors.toList());
    }

    @DeleteMapping("admin/machines/{machineId}") //삭제 (완료)
    public Long deleteMachine(@PathVariable("machineId") Long machineId,
                              @SessionAttribute(name = SessionConst.LOGIN_ADMIN, required = false) User admin) {
        Machine findMachine = machineService.findOne(machineId);
        for (BodyPart bodyPart : findMachine.getBodyParts()) {
            bodyPart.removeMachine(findMachine);
        }

        machineService.removeMachine(machineId);
        return machineId;
    }

    @Data
    @AllArgsConstructor
    static class MachineRequest {
        private String englishName;
        private String koreanName;
        private List<String> bodyPartKoreanName;
    }

    @Data
    static class GetMachineResponse {
        private String englishName;
        private String koreanName;
        private List<String> bodyPartKoreanName = new ArrayList<>();

        public GetMachineResponse(String englishName, String koreanName, List<BodyPart> bodyParts) {
            this.englishName = englishName;
            this.koreanName = koreanName;
            for (BodyPart bodyPart : bodyParts) {
                this.bodyPartKoreanName.add(bodyPart.getKoreanName());
            }
        }
    }

    @Getter
    static class MachineDto {
        private Long id;
        private String englishName;
        private String koreanName;
        private List<String> bodyPartKoreanName = new ArrayList<>();

        public MachineDto(Machine machine) {
            this.id = machine.getId();
            this.englishName = machine.getEnglishName();
            this.koreanName = machine.getKoreanName();
            for (BodyPart bodyPart : machine.getBodyParts()) {
                this.bodyPartKoreanName.add(bodyPart.getKoreanName());
            }
        }
    }
}
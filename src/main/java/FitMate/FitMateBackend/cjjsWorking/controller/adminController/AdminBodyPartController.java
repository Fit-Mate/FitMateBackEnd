package FitMate.FitMateBackend.cjjsWorking.controller.adminController;

import FitMate.FitMateBackend.cjjsWorking.dto.bodyPart.BodyPartResponseDto;
import FitMate.FitMateBackend.cjjsWorking.service.BodyPartService;
import FitMate.FitMateBackend.consts.SessionConst;
import FitMate.FitMateBackend.domain.BodyPart;
import FitMate.FitMateBackend.domain.Machine;
import FitMate.FitMateBackend.domain.User;
import FitMate.FitMateBackend.domain.Workout;
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
public class AdminBodyPartController {

    private final BodyPartService bodyPartService;

    @PostMapping("admin/bodyParts") //운동 부위 정보 등록 (TEST 완료) ok
    public Long saveBodyPart(@SessionAttribute(name = SessionConst.LOGIN_ADMIN, required = false) User admin,
                             @RequestBody BodyPartRequest request) {
            BodyPart bodyPart = new BodyPart();
            bodyPart.update(request.englishName, request.koreanName);

            Long bodyPartId = bodyPartService.saveBodyPart(bodyPart);
            return bodyPartId;
    }

    @PutMapping("admin/bodyParts/{bodyPartId}") //운동 부위 정보 수정 (TEST 완료)
    public Long updateBodyPart(@PathVariable("bodyPartId") Long bodyPartId,
                               @SessionAttribute(name = SessionConst.LOGIN_ADMIN, required = false) User admin,
                               @RequestBody BodyPartRequest request) {
        bodyPartService.updateBodyPart(bodyPartId, request.englishName, request.koreanName);
        return bodyPartId;
    }

    @GetMapping("admin/bodyParts/list") //운동 부위 전체 검색 (TEST 완료) ok
    public GetAllBodyPartResponse findBodyPartAll(@SessionAttribute(name = SessionConst.LOGIN_ADMIN, required = false) User admin) {
        List<BodyPart> findBodyParts = bodyPartService.findAll();
        return new GetAllBodyPartResponse(findBodyParts);
    }

    @GetMapping("admin/bodyParts/list/{page}") //batch 단위 조회 (TEST 완료) ok
    public List<BodyPartDto> findBodyParts_page(@PathVariable(value = "page") int page,
                                            @SessionAttribute(name = SessionConst.LOGIN_ADMIN, required = false) User admin) {

        int offset = (page-1)*10;
        int limit = ((page*10)-1);
        List<BodyPart> findBodyParts = bodyPartService.findAll(offset, limit);

        return findBodyParts.stream()
                .map(b -> new BodyPartDto(b))
                .collect(Collectors.toList());
    }

    @GetMapping("admin/bodyParts/{bodyPartId}") //운동 부위 단일 조회 (TEST 완료) ok
    public BodyPartResponseDto findBodyPart(@PathVariable("bodyPartId") Long bodyPartId,
                                            @SessionAttribute(name = SessionConst.LOGIN_ADMIN, required = false) User admin) {

        BodyPart findBodyPart = bodyPartService.findOne(bodyPartId);
        return new BodyPartResponseDto(findBodyPart.getEnglishName(), findBodyPart.getKoreanName());
    }

    @DeleteMapping("admin/bodyParts/{bodyPartId}") //운동 부위 삭제 (TEST 완료)
    public Long removeBodyPart(@PathVariable("bodyPartId") Long bodyPartId,
                               @SessionAttribute(name = SessionConst.LOGIN_ADMIN, required = false) User admin) {
        BodyPart findBodyPart = bodyPartService.findOne(bodyPartId);

        //관련 machine 삭제
        List<Machine> machines = findBodyPart.getMachines();
        for (Machine machine : machines) {
            machine.getBodyParts().remove(findBodyPart);
        }

        //관련 workout 삭제
        List<Workout> workouts = findBodyPart.getWorkouts();
        for (Workout workout : workouts) {
            workout.getBodyParts().remove(findBodyPart);
        }

        bodyPartService.removeBodyPart(bodyPartId);
        return bodyPartId;
    }

    @Data
    @AllArgsConstructor
    static class BodyPartRequest {
        private String englishName;
        private String koreanName;
    }

    @Data
    static class GetAllBodyPartResponse {
        private List<String> bodyPartKoreanName = new ArrayList<>();

        public GetAllBodyPartResponse(List<BodyPart> bodyParts) {
            for (BodyPart bodyPart : bodyParts) {
                bodyPartKoreanName.add(bodyPart.getKoreanName());
            }
        }
    }

    @Getter
    static class BodyPartDto {
        private Long id;
        private String englishName;
        private String koreanName;

        public BodyPartDto(BodyPart bodyPart) {
            this.id = bodyPart.getId();
            this.englishName = bodyPart.getEnglishName();
            this.koreanName = bodyPart.getKoreanName();
        }
    }

}
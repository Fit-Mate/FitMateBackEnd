package FitMate.FitMateBackend.cjjsWorking.controller;

import FitMate.FitMateBackend.cjjsWorking.service.BodyPartService;
import FitMate.FitMateBackend.consts.SessionConst;
import FitMate.FitMateBackend.domain.BodyPart;
import FitMate.FitMateBackend.domain.User;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class bodyPartController {

    private final BodyPartService bodyPartService;
    private final HttpSession session;

    @PostMapping("admin/bodyParts") //운동 부위 정보 등록
    public Long saveBodyPart(/*@SessionAttribute(name = SessionConst.LOGIN_ADMIN, required = false) User admin,*/
                             @RequestBody BodyPartRequest request) {

//        if(admin != null) { //find session
            BodyPart bodyPart = new BodyPart();
            bodyPart.update(request.englishName, request.koreanName);

            Long bodyPartId = bodyPartService.saveBodyPart(bodyPart);
            return bodyPartId;
//        } else { //session null
//            return null; //예외 처리 필요
//        }
    }

    @PutMapping("admin/bodyParts/{bodyPartId}") //운동 부위 정보 수정
    public Long updateBodyPart(@PathVariable("bodyPartId") Long bodyPartId,
                               @SessionAttribute(name = SessionConst.LOGIN_ADMIN, required = false) User admin,
                               @RequestBody BodyPartRequest request) {

        if(admin != null) { //find session
            BodyPart findBodyPart = bodyPartService.findOne(bodyPartId);
            findBodyPart.update(request.englishName, request.koreanName);
            return bodyPartId;
        } else { //session null
            return -1L; //예외 처리 필요
        }
    }

    @GetMapping("admin/bodyParts/list") //운동 부위 전체 검색
    public List<BodyPart> findBodyPartAll(@SessionAttribute(name = SessionConst.LOGIN_ADMIN, required = false) User admin) {

        if(admin != null) {
            List<BodyPart> findBodyParts = bodyPartService.findAll();
            return findBodyParts; //Dto 처리 필요
        } else {
            return null; //예외 처리 필요
        }
    }

    @GetMapping("admin/bodyParts/list/{page}") //batch 단위 조회
    public List<BodyPart> findBodyParts_page(@PathVariable(value = "page") int page,
                                            @SessionAttribute(name = SessionConst.LOGIN_ADMIN, required = false) User admin) {

        if(admin != null) {
            //page1 -> bodyPartId: 0~9
            int offset = (page - 1);
            int limit = ((page * 10) - 1);
            List<BodyPart> findBodyParts = bodyPartService.findAll(offset, limit);
            return findBodyParts; //Dto 처리 필요
        } else {
            return null; //예외 처리 필요
        }
    }

    @GetMapping("admin/bodyParts/{bodyPartId}") //운동 부위 단일 조회
    public BodyPart findBodyPart(@PathVariable("bodyPartId") Long bodyPartId,
                                 @SessionAttribute(name = SessionConst.LOGIN_ADMIN, required = false) User admin) {

        if(admin != null) {
            BodyPart findBodyPart = bodyPartService.findOne(bodyPartId);
            return findBodyPart; //Dto 처리 필요
        } else {
            return null; //예외 처리 필요
        }
    }

    @DeleteMapping("admin/bodyParts/{bodyPartId}") //운동 부위 삭제
    public Long removeBodyPart(@PathVariable("bodyPartId") Long bodyPartId,
                               @SessionAttribute(name = SessionConst.LOGIN_ADMIN, required = false) User admin) {

        if(admin != null) {
            bodyPartService.removeBodyPart(bodyPartId);
            return bodyPartId;
        } else {
            return -1L; //예외 처리 필요
        }
    }

    @Data
    @AllArgsConstructor
    static class BodyPartRequest {
        private String englishName;
        private String koreanName;
    }
}
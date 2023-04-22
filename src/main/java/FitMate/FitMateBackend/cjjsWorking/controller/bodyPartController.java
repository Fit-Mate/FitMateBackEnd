package FitMate.FitMateBackend.cjjsWorking.controller;

import FitMate.FitMateBackend.cjjsWorking.service.BodyPartService;
import FitMate.FitMateBackend.domain.BodyPart;
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

    @PostMapping("admin/bodyParts")
    public Long saveBodyPart(@RequestParam(value = "cookie") String cookie,
                             @RequestBody BodyPartRequest request) {
        String adminId = session.getAttribute(cookie).toString();

        if(adminId != null) { //find session
            BodyPart bodyPart = new BodyPart();
            bodyPart.update(request.englishName, request.koreanName);

            Long bodyPartId = bodyPartService.saveBodyPart(bodyPart);
            return bodyPartId;
        } else { //session null
            return -1L; //예외 처리 필요
        }
    }

    @PutMapping("admin/bodyParts/{bodyPartId}")
    public Long updateBodyPart(@PathVariable("bodyPartId") Long bodyPartId,
                               @RequestParam(value = "cookie") String cookie,
                               @RequestBody BodyPartRequest request) {
        String adminId = session.getAttribute(cookie).toString();

        if(adminId != null) { //find session
            BodyPart findBodyPart = bodyPartService.findOne(bodyPartId);
            findBodyPart.update(request.englishName, request.koreanName);
            return bodyPartId;
        } else { //session null
            return -1L; //예외 처리 필요
        }
    }

    @GetMapping("admin/bodyParts/list")
    public List<BodyPart> findBodyPartAll(@RequestParam(value = "cookie") String cookie) {
        String adminId = session.getAttribute(cookie).toString();

        if(adminId != null) {
            List<BodyPart> findBodyParts = bodyPartService.findAll();
            return findBodyParts; //Dto 처리 필요
        } else {
            return null; //예외 처리 필요
        }
    }

    @GetMapping("admin/bodyParts/list/{page}")
    public List<BodyPart> findBodyPart_page(@PathVariable(value = "page") int page,
                                            @RequestParam(value = "cookie") String cookie) {
        String adminId = session.getAttribute(cookie).toString();

        if(adminId != null) {
            //page1 -> bodyPartId: 0~9
            int offset = (page - 1);
            int limit = ((page * 10) - 1);
            List<BodyPart> findBodyParts = bodyPartService.findAll(offset, limit);
            return findBodyParts; //Dto 처리 필요
        } else {
            return null; //예외 처리 필요
        }
    }

    @GetMapping("admin/bodyParts/{bodyPartId}")
    public BodyPart findBodyPart(@PathVariable("bodyPartId") Long bodyPartId,
                                 @RequestParam(value = "cookie") String cookie) {
        String adminId = session.getAttribute(cookie).toString();

        if(adminId != null) {
            BodyPart findBodyPart = bodyPartService.findOne(bodyPartId);
            return findBodyPart; //Dto 처리 필요
        } else {
            return null; //예외 처리 필요
        }
    }

    @DeleteMapping("admin/bodyParts/{bodyPartId}")
    public Long removeBodyPart(@PathVariable("bodyPartId") Long bodyPartId,
                               @RequestParam(value = "cookie") String cookie) {
        String adminId = session.getAttribute(cookie).toString();

        if(adminId != null) {
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
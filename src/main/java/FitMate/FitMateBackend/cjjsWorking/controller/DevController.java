package FitMate.FitMateBackend.cjjsWorking.controller;

import FitMate.FitMateBackend.cjjsWorking.service.BodyPartService;
import FitMate.FitMateBackend.domain.BodyPart;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dev")
public class DevController {

    private final BodyPartService bodyPartService;

    @PostMapping("bodyParts/create")
    public void createBodyParts(@RequestBody DevBodyPartsRequest request) {

        for (DevBodyPartName bodyPart : request.bodyParts) {
            BodyPart bp = new BodyPart();
            bp.update(bodyPart.englishName, bodyPart.koreanName);
            bodyPartService.saveBodyPart(bp);
        }

    }

    @Data
    static class DevBodyPartsRequest {
        private List<DevBodyPartName> bodyParts;
    }

    @Data
    static class DevBodyPartName {
        private String englishName;
        private String koreanName;
    }
}

package FitMate.FitMateBackend.cjjsWorking.controller;

import FitMate.FitMateBackend.cjjsWorking.service.BodyPartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class bodyPartController {

    private final BodyPartService bodyPartService;

}

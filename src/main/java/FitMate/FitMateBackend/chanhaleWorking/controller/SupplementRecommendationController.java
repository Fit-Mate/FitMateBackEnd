package FitMate.FitMateBackend.chanhaleWorking.controller;


import FitMate.FitMateBackend.chanhaleWorking.config.argumentresolver.Login;
import FitMate.FitMateBackend.chanhaleWorking.dto.SupplementRecommendationDto;
import FitMate.FitMateBackend.chanhaleWorking.form.recommendation.SupplementRecommendationForm;
import FitMate.FitMateBackend.chanhaleWorking.service.ChatGptService;
import FitMate.FitMateBackend.chanhaleWorking.service.SupplementRecommendationService;
import FitMate.FitMateBackend.chanhaleWorking.service.SupplementService;
import FitMate.FitMateBackend.domain.User;
import FitMate.FitMateBackend.domain.recommendation.SupplementRecommendation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@ResponseBody
@RequestMapping("/recommendation")
@RequiredArgsConstructor
public class SupplementRecommendationController {
    private final SupplementRecommendationService supplementRecommendationService;
    private final SupplementService supplementService;
    private final ChatGptService chatGptService;
    @PostMapping("/add")
    public Long getRecommendedText(@Login User user, @RequestBody SupplementRecommendationForm form) throws Exception {
        Long recommendationId = supplementRecommendationService.createSupplementRecommendation(user.getId(), form);
        log.info("purpose:[{}][{}]", form.getMonthlyBudget(), form.getPurpose().get(0));
        String question = "I have these supplements.".concat(supplementService.getSupplementString());
        SupplementRecommendation supplementRecommendation = supplementRecommendationService.findById(recommendationId);
        question = question.concat(supplementRecommendation.getQueryText());
        log.info(question);
        chatGptService.sendRequest(supplementRecommendation.getId(), question);
        return recommendationId;
    }
    @GetMapping("/{supplementRecommendationId}")
    public SupplementRecommendationDto getRecommendedText(@Login User user, @PathVariable("supplementRecommendationId") Long supplementRecommendationId) throws Exception {
        // User 소유의 supplementRecommendation 인지 확인하는 기능 필요
        SupplementRecommendation sr = supplementRecommendationService.getSupplementRecommendation(supplementRecommendationId);
        if (sr == null) {
            return new SupplementRecommendationDto();
        }
        return SupplementRecommendationDto.createSupplementRecommendationDto(sr);
    }
}

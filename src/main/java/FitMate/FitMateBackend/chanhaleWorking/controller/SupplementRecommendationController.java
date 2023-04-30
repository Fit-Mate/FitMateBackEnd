package FitMate.FitMateBackend.chanhaleWorking.controller;


import FitMate.FitMateBackend.chanhaleWorking.form.recommendation.SupplementRecommendationForm;
import FitMate.FitMateBackend.chanhaleWorking.service.SupplementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/recommendation")
@ResponseBody
@RequiredArgsConstructor
public class SupplementRecommendationController {
//    private final SupplementRecommendationService supplementService;
//    @ResponseBody
//    @RequestMapping("/add")
//    public Long getRecommendedText(@RequestBody SupplementRecommendationForm form) throws Exception {
//        Long recommendationId = supplementRecommendationService.saveRecommendation(form);
//        log.info("input = {}", articleForm.generateQuestion());
//        /**
//         * 질의문 생성
//         */
//        // String question = ~~~~
//        chatGptService.sendRequest(recommendationId, question);
//        async 하게 돌아가기 때문에 즉시 반환
//        return recommendationId;
//    }
}

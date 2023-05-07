package FitMate.FitMateBackend.cjjsWorking.controller.userController;

import FitMate.FitMateBackend.chanhaleWorking.service.ChatGptService;
import FitMate.FitMateBackend.cjjsWorking.service.WorkoutRecommendationService;
import FitMate.FitMateBackend.consts.SessionConst;
import FitMate.FitMateBackend.domain.User;
import FitMate.FitMateBackend.domain.recommendation.WorkoutRecommendation;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WorkoutRecommendationController {

    private final WorkoutRecommendationService workoutRecommendationService;
    private final ChatGptService chatGptService;

    @PostMapping("recommendation/workout")
    public Long getWorkoutRecommendation(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User user,
                                         @RequestBody WorkoutRecommendationRequest request) {
        Long recommendationId = workoutRecommendationService.
                createWorkoutRecommendation(user.getId(), request.bodyPartKoreanName, request.machineKoreanName);

        WorkoutRecommendation workoutRecommendation = workoutRecommendationService.findById(recommendationId);
        String question = workoutRecommendation.getQueryText();

        chatGptService.sendWorkoutRequest(user.getId(), workoutRecommendation.getId(), question);
        return recommendationId;
    }

    @Data
    static class WorkoutRecommendationRequest {
        private List<String> bodyPartKoreanName;
        private List<String> machineKoreanName;
    }
}

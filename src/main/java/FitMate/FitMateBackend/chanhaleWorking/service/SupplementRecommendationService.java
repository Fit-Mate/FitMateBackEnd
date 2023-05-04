package FitMate.FitMateBackend.chanhaleWorking.service;

import FitMate.FitMateBackend.chanhaleWorking.form.recommendation.SupplementRecommendationForm;
import FitMate.FitMateBackend.chanhaleWorking.repository.SupplementRecommendationRepository;
import FitMate.FitMateBackend.chanhaleWorking.repository.UserRepository;
import FitMate.FitMateBackend.domain.User;
import FitMate.FitMateBackend.domain.recommendation.SupplementRecommendation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class SupplementRecommendationService {
    private final SupplementRecommendationRepository supplementRecommendationRepository;
    private final UserRepository userRepository;
    private final ChatGptService chatGptService;
    private final SupplementService supplementService;

    @Transactional
    public Long createSupplementRecommendation(Long userId, SupplementRecommendationForm supplementRecommendationForm) {
        User user = userRepository.findOne(userId);
        if (user == null) {
            return null;
        }
        SupplementRecommendation supplementRecommendation = SupplementRecommendation
                .createSupplementRecommendation(user.getBodyDataHistory().get(0), user, supplementRecommendationForm.getPurpose(), supplementRecommendationForm.getMonthlyBudget());
        supplementRecommendationRepository.save(supplementRecommendation);
        String question = "I have these supplements.".concat(supplementService.getSupplementString());
        question.concat(supplementRecommendation.getQueryText());
        chatGptService.sendRequest(supplementRecommendation.getId(), question);
        return supplementRecommendation.getId();
    }

    @Transactional
    public void updateGptResponse(Long recommendationId, String response) {
        SupplementRecommendation supplementRecommendation = supplementRecommendationRepository.findById(recommendationId);
        if (supplementRecommendation == null)
            return;
        supplementRecommendation.updateRecommend(response);
    }
}

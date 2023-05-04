package FitMate.FitMateBackend.chanhaleWorking.service;

import FitMate.FitMateBackend.chanhaleWorking.config.ChatGptConfig;
import FitMate.FitMateBackend.chanhaleWorking.form.recommendation.SupplementRecommendationForm;
import FitMate.FitMateBackend.chanhaleWorking.repository.RecommendedSupplementRepository;
import FitMate.FitMateBackend.chanhaleWorking.repository.SupplementRecommendationRepository;
import FitMate.FitMateBackend.chanhaleWorking.repository.UserRepository;
import FitMate.FitMateBackend.consts.ServiceConst;
import FitMate.FitMateBackend.domain.User;
import FitMate.FitMateBackend.domain.recommendation.RecommendedSupplement;
import FitMate.FitMateBackend.domain.recommendation.SupplementRecommendation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class SupplementRecommendationService {
    private final SupplementRecommendationRepository supplementRecommendationRepository;
    private final UserRepository userRepository;
    private final RecommendedSupplementRepository recommendedSupplementRepository;

    @Transactional
    public Long createSupplementRecommendation(Long userId, SupplementRecommendationForm supplementRecommendationForm) {
        User user = userRepository.findOne(userId);
        if (user == null) {
            return null;
        }

        SupplementRecommendation supplementRecommendation = SupplementRecommendation
                .createSupplementRecommendation(user.getBodyDataHistory().get(0), user, supplementRecommendationForm.getPurpose(), supplementRecommendationForm.getMonthlyBudget());
        supplementRecommendationRepository.save(supplementRecommendation);
        return supplementRecommendation.getId();
    }

    public SupplementRecommendation findById(Long supplementRecommendationId) {
        return supplementRecommendationRepository.findById(supplementRecommendationId);
    }

    @Transactional
    public void updateGptResponse(Long recommendationId, String response) {
        SupplementRecommendation supplementRecommendation = supplementRecommendationRepository.findById(recommendationId);
        if (supplementRecommendation == null)
            return;

        int numStart = -1;
        int numEnd = -1;
        while (true) {
            numStart = response.indexOf(ServiceConst.RECOMMEND_PREFIX, numStart+1);
            if (numStart == -1) {
                break;
            }
            numEnd = response.indexOf(ServiceConst.RECOMMEND_SUFFIX, numEnd+1);
            Long number = Long.parseLong(response.substring(numStart + ServiceConst.RECOMMEND_PREFIX.length(), numEnd));
            int strEnd = response.indexOf(ServiceConst.RECOMMEND_PREFIX, numStart + 1);
            if (strEnd == -1) {
                strEnd = response.length()-1;
            }
            String str = response.substring(numEnd + ServiceConst.RECOMMEND_SUFFIX.length(), strEnd);
            RecommendedSupplement recommendedSupplement = RecommendedSupplement.createRecommendedSupplement(number, str);
            recommendedSupplementRepository.save(recommendedSupplement);
            supplementRecommendation.addRecommendSupplements(recommendedSupplement);
        }
    }

    public SupplementRecommendation getSupplementRecommendation(Long id) {
        return supplementRecommendationRepository.findById(id);
    }
}

package FitMate.FitMateBackend.chanhaleWorking.dto;

import FitMate.FitMateBackend.domain.recommendation.RecommendedSupplement;
import FitMate.FitMateBackend.domain.recommendation.SupplementRecommendation;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Getter
@NoArgsConstructor
public class SupplementRecommendationDto {
    private LocalDate date;
    private String question;
    private Long monthlyBudget;
    private List<RecommendedSupplement> recommendedSupplementList;

    public static SupplementRecommendationDto createSupplementRecommendationDto(SupplementRecommendation supplementRecommendation) {
        SupplementRecommendationDto supplementRecommendationDto = new SupplementRecommendationDto();
        supplementRecommendationDto.date = supplementRecommendation.getDate();
        supplementRecommendationDto.question = supplementRecommendation.getQueryText();
        supplementRecommendationDto.monthlyBudget = supplementRecommendation.getMonthlyBudget();
        supplementRecommendationDto.recommendedSupplementList = supplementRecommendation.getRecommendedSupplements();
        return supplementRecommendationDto;
    }
}

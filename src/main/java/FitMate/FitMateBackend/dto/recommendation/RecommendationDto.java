package FitMate.FitMateBackend.dto.recommendation;

import FitMate.FitMateBackend.dto.BodyDataDto;
/**
 * 보조제 추천, 운동 추천 dto 들의 부모클래스
 */
public abstract class RecommendationDto {
    private Long recommendationId;
    private BodyDataDto bodyDataDto;
}

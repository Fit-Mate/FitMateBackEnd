package FitMate.FitMateBackend.dto.recommendation;

import java.util.List;
/**
 * 운동 추천 결과를 전송하기 위해 사용할 dto
 */
public class WorkoutRecommendationDto extends RecommendationDto {
    private List<String> bodyPartList;
    private List<String> MachineList;
    private String preRecommendation;// 추천 앞단부
    private List<RecommendedWorkoutDto> workouts; // 추천 루틴 리스트
    private String postRecommendation;// 추천 뒷단부
}

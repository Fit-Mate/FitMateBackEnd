package FitMate.FitMateBackend.chanhaleWorking.deprecated.dto.recommendation;
/**
 * WorkoutRecommendationDto에서  운동 루틴 1개를 담을 dto
 */
public class RecommendedWorkoutDto {
    private String workoutEnglishName;
    private String workoutKoreanName;
    private Integer numberMoreThan; // 운동 횟수 numberMoreThan ~ numberUpTo
    private Integer numberUpTo; // 운동 횟수 numberMoreThan ~ numberUpTo
    private Integer set; // 세트 수
    private String description; // 운동 설명
    private String videoUrl; // 운동 영상 URL
}

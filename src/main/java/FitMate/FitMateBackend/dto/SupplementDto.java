package FitMate.FitMateBackend.dto;
/**
 * 보조제 정보 조회 요청에 사용할 dto
 */
public class SupplementDto {

    private String englishName;
    private String koreanName;
    private Integer price;
    private Float servings;
    private String description;
    private String marketURL;

    private String protein;
    // protein, gainer
    private Float proteinPerServing;
    private Float fatPerServing;
    private Float carbohydratePerServing;
    private String source;
    // protein, gainer, bcaa
    private String flavor;

}

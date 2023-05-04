package FitMate.FitMateBackend.domain.recommendation;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "recommended_supplements")
@NoArgsConstructor
public class RecommendedSupplement {
    @Id
    @GeneratedValue
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "recommendation_id")
    SupplementRecommendation supplementRecommendation;


    private Long supplementId;
    @Column(length = 2000)
    private String englishRecommendationString;
    @Column(length = 2000)
    private String koreanRecommendationString;

    public static RecommendedSupplement createRecommendedSupplement(Long supplementId, String englishRecommendationString) {
        RecommendedSupplement recommendedSupplement = new RecommendedSupplement();
        recommendedSupplement.supplementId = supplementId;
        recommendedSupplement.englishRecommendationString = englishRecommendationString;
        return recommendedSupplement;
    }
}

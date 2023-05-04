package FitMate.FitMateBackend.chanhaleWorking.repository;

import FitMate.FitMateBackend.domain.recommendation.Recommendation;
import FitMate.FitMateBackend.domain.recommendation.SupplementRecommendation;
import FitMate.FitMateBackend.domain.supplement.Supplement;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class SupplementRecommendationRepository {
    private final EntityManager em;

    public void save(Recommendation recommendation){
        if (recommendation.getId() == null) {
            em.persist(recommendation);
        }else{
            em.merge(recommendation);
        }
    }
    public SupplementRecommendation findById(Long id) {
        Recommendation recommendation = em.find(Recommendation.class, id);
        if(recommendation instanceof SupplementRecommendation)
            return (SupplementRecommendation) recommendation;
        return null;
    }

}

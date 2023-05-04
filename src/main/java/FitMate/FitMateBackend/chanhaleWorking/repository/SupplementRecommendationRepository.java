package FitMate.FitMateBackend.chanhaleWorking.repository;

import FitMate.FitMateBackend.domain.recommendation.Recommendation;
import FitMate.FitMateBackend.domain.recommendation.SupplementRecommendation;
import FitMate.FitMateBackend.domain.supplement.Supplement;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class SupplementRecommendationRepository {
    @PersistenceContext
    private final EntityManager em;

    public void save(Recommendation recommendation){
        if (recommendation.getId() == null) {
            em.persist(recommendation);
        }else{
            em.merge(recommendation);
        }
    }
    public SupplementRecommendation findById(Long id) {
        SupplementRecommendation recommendation = em.find(SupplementRecommendation.class, id);
        log.info(recommendation.getId().toString());
        return recommendation;
    }

}

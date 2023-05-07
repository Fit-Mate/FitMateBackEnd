package FitMate.FitMateBackend.cjjsWorking.repository;

import FitMate.FitMateBackend.domain.recommendation.WorkoutRecommendation;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class WorkoutRecommendationRepository {

    private final EntityManager em;

    public void save(WorkoutRecommendation workoutRecommendation) {
        em.persist(workoutRecommendation);
    }

    public WorkoutRecommendation findById(Long recommendationId) {
        return em.find(WorkoutRecommendation.class, recommendationId);
    }
}

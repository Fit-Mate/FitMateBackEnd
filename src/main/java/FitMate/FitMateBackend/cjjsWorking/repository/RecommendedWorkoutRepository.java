package FitMate.FitMateBackend.cjjsWorking.repository;

import FitMate.FitMateBackend.domain.recommendation.RecommendedWorkout;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RecommendedWorkoutRepository {

    private final EntityManager em;

    public void save(RecommendedWorkout recommendedWorkout) {
        em.persist(recommendedWorkout);
    }
}

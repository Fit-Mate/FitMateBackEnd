package FitMate.FitMateBackend.cjjsWorking.repository;

import FitMate.FitMateBackend.domain.QWorkout;
import FitMate.FitMateBackend.domain.Workout;
import FitMate.FitMateBackend.domain.recommendation.QWorkoutRecommendation;
import FitMate.FitMateBackend.domain.recommendation.RecommendedWorkout;
import FitMate.FitMateBackend.domain.recommendation.WorkoutRecommendation;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

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

    public List<WorkoutRecommendation> findAllWithWorkoutRecommendation(int offset, int limit, Long userId) {
        QWorkoutRecommendation workoutRecommendation = QWorkoutRecommendation.workoutRecommendation;
        JPAQueryFactory query = new JPAQueryFactory(em);
        return query
                .select(workoutRecommendation)
                .from(workoutRecommendation)
                .where(workoutRecommendation.user.id.eq(userId))
                .offset(offset)
                .limit(limit)
                .fetch();
    }
}

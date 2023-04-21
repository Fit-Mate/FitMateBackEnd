package FitMate.FitMateBackend.cjjsWorking.repository;

import FitMate.FitMateBackend.domain.Workout;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class WorkoutRepository {

    private final EntityManager em;

    public void save(Workout workout) {
        em.persist(workout);
    }
}
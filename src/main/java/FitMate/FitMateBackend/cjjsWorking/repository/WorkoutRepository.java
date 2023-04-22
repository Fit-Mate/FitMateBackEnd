package FitMate.FitMateBackend.cjjsWorking.repository;

import FitMate.FitMateBackend.domain.Workout;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class WorkoutRepository {

    private final EntityManager em;

    public void save(Workout workout) {
        em.persist(workout);
    }

    public Workout findById(Long id) {
        return em.find(Workout.class, id);
    }

    public List<Workout> findAll(int offset, int limit) {
        return em.createQuery("select w from Workout w", Workout.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    public void remove(Workout workout) {
        em.remove(workout);
    }
}
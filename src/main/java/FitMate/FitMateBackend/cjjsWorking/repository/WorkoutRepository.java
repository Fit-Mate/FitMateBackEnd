package FitMate.FitMateBackend.cjjsWorking.repository;

import FitMate.FitMateBackend.domain.QWorkout;
import FitMate.FitMateBackend.domain.Workout;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

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

    public List<Workout> findSearchAll(int offset, int limit, WorkoutSearch search) {
        QWorkout workout = QWorkout.workout;
        JPAQueryFactory query = new JPAQueryFactory(em);
        return query
                .select(workout)
                .from(workout)
                .where(nameLike(search.getSearchKeyword()))
                .limit(1000)
                .fetch();
    }

    private BooleanExpression nameLike(String name) {
        if (!StringUtils.hasText(name)) {
            return null;
        }
        System.out.println(name);
        System.out.println(QWorkout.workout.koreanName);
        return QWorkout.workout.koreanName.like("%" + name + "%");
    }
}
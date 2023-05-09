package FitMate.FitMateBackend.domain.recommendation;

import FitMate.FitMateBackend.domain.Workout;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class RecommendedWorkout {

    @Id @GeneratedValue
    @Column(name = "recommended_workout_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recommend_id")
    private WorkoutRecommendation workoutRecommendation;

    @ManyToMany
    @JoinTable(name = "recommended_workout_workout",
            joinColumns = @JoinColumn(name = "recommendation_id"),
            inverseJoinColumns = @JoinColumn(name ="workout_id"))
    private List<Workout> recommendedWorkouts = new ArrayList<>();

    public void update(WorkoutRecommendation workoutRecommendation) {
        this.workoutRecommendation = workoutRecommendation;
    }
}

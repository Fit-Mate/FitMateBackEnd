package FitMate.FitMateBackend.domain.recommendation;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@DiscriminatorValue("Workout")
public class WorkoutRecommendation extends Recommendation {


//    @OneToMany(mappedBy = "recommend")
//    private List<BodyPart> bodyPart;
//    @OneToMany(mappedBy = "recommend")
//    private List<Machine> machines;

}

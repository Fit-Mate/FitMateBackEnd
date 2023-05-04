package FitMate.FitMateBackend.domain.recommendation;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@DiscriminatorValue("Workout")
public class WorkoutRecommendation extends Recommendation {
    @Override
    public void updateRecommend(String gptResponse) {
        //TODO
    }

//    @OneToMany(mappedBy = "recommend")
//    private List<BodyPart> bodyPart;
//    @OneToMany(mappedBy = "recommend")
//    private List<Machine> machines;

}

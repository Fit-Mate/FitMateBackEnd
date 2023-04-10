package FitMate.FitMateBackend.domain.chatGPT;

import FitMate.FitMateBackend.domain.BodyPart;
import FitMate.FitMateBackend.domain.Machine;
import FitMate.FitMateBackend.form.BodyDataForm;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@DiscriminatorValue("Workout")
public class WorkoutRecommend extends Recommend {

    @OneToMany(mappedBy = "recommend")
    private List<BodyPart> bodyPart;
    @OneToMany(mappedBy = "recommend")
    private List<Machine> machines;

}

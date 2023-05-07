package FitMate.FitMateBackend.domain.recommendation;

import FitMate.FitMateBackend.domain.*;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@DiscriminatorValue("Workout")
public class WorkoutRecommendation extends Recommendation {
    private String bodyPartQuery = "";
    private String machineQuery = "";

    public static WorkoutRecommendation createWorkoutRecommendation
            (BodyData bodyData, User user, List<BodyPart> bodyParts, List<Machine> machines) {
        WorkoutRecommendation workoutRecommendation = new WorkoutRecommendation();
        workoutRecommendation.setBodyData(bodyData);
        workoutRecommendation.setUser(user);

        workoutRecommendation.setRecommendationType("Workout");

        //query문 수정 중
        String qString = "suggest up to 3 workouts in this list. For a ";
        qString = qString.concat(user.getSex() == "남성" ? "man" : "woman").concat(" who is ");
        qString = qString.concat(bodyData.describe());

        qString = qString.concat(user.getSex() == "남성" ? "His" : "Her").concat(" 가 원하는 운동 부위는 ");
        if(!machines.isEmpty()) qString = qString.concat(user.getSex() == "남성" ? "His" : "Her").concat(" 가 원하는 운동 기구는 ");
        //query문 수정 중

        workoutRecommendation.setQueryText(qString);
        return workoutRecommendation;
    }
}

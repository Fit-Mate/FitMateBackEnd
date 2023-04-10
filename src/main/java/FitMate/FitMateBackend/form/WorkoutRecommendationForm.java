package FitMate.FitMateBackend.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutRecommendationForm {
    private BodyDataForm bodyDataForm;
    private List<String> bodyPart;
    private List<String> machine;
}

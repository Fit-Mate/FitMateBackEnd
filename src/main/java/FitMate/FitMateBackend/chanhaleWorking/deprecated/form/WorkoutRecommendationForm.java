package FitMate.FitMateBackend.chanhaleWorking.deprecated.form;

import FitMate.FitMateBackend.chanhaleWorking.form.user.BodyDataForm;
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

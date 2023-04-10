package FitMate.FitMateBackend.form;

import FitMate.FitMateBackend.domain.Purpose;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SupplementRecommendationForm {
    private BodyDataForm bodyDataForm;
    private Long monthlyBudget;
    private List<Purpose> purpose;
}
